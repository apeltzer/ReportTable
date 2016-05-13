/*
 * Copyright (c) 2016 ReportTable Alexander Seitz
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 */
package analyse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import utilities.OutputStrings;
import utilities.Pipelines;

/**
 * Class for the analsyis of the results generated by the
 * FastQC step of the pipeline.
 *
 * @author Alexander Seitz
 *
 */
public class AnalyzeFastQC extends AbstractAnalyze {

	// member variables
	private String numRawReads = OutputStrings.notFound;
	private String version = OutputStrings.notFound;
	private long numRawReadsLong = 0;
	private int numFilesToAnalyze = 0;
	private int numFilesAnalyzed = 0;
	private int numErrors;

	/**
	 * Constructor, the given Folder should point to the root directory of
	 * the sample, containing the folders generated by the pipeline.
	 *
	 * @param sampleFolder
	 */
	public AnalyzeFastQC(File sampleFolder){
		super(sampleFolder);
		analyzeFastQC();
	}

	/**
	 * Find the relevant folders for each input sample file
	 */
	private void analyzeFastQC() {
//		File currFolder = new File(this.sampleFolder + "/" + Pipelines.FastQC.toString());
		File currFolder = this.getCurrFolder(Pipelines.FastQC);
//		if(currFolder.exists()){
		if(currFolder != null){
			String[] names = currFolder.list();
			for(String name:filterDataDir(names)){
				File currFile = new File(currFolder+"/"+name);
				if(currFile.isDirectory()){
					this.numFilesToAnalyze++;
					analyzeFastQC(currFile);
				}
			}
			if(this.numFilesToAnalyze == 0){
				analyzeHTMLFiles();
			}
			createOutputString();
		}
	}

	private void analyzeHTMLFiles() {
		File currFolder = new File(this.sampleFolder + "/" + Pipelines.FastQC.toString());
		if(currFolder.exists()){
			String[] names = currFolder.list();
			for(String name:filterDataDir(names)){
				File currFile = new File(currFolder+"/"+name);
				if(currFile.isFile() && currFile.getName().endsWith("html")){
					this.numFilesToAnalyze++;
					parseFastQChtml(currFile);
				}
			}
		}
	}

	private void parseFastQChtml(File currFile) {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(currFile));
			String currLine = "";
			boolean parseNextLine = false;
			while((currLine = br.readLine()) != null){
				if(parseNextLine){
					String numReads = currLine.replace("<td>", "").replace("</td>","");
					this.numRawReadsLong += Long.parseLong(numReads.trim());
					this.numFilesAnalyzed++;
					break;
				}
				if(currLine.contains("<td>Total Sequences</td>")){
					parseNextLine = true;
				}
			}
		} catch (IOException e) {
			this.numErrors++;
		}
	}

	// depending, how many files could be read, generate the output string
	private void createOutputString() {
		if(this.numRawReadsLong == 0){
			this.numRawReads = OutputStrings.notFound;
		}else if(numFilesToAnalyze != numFilesAnalyzed){
			this.numRawReads = "" + this.numRawReadsLong + " (" + numFilesAnalyzed + "/" + numFilesToAnalyze + ")";
		}else if(this.numErrors > 0){
			this.numRawReads = "" + this.numRawReadsLong + " " + OutputStrings.notFound;
		}else{
			this.numRawReads = "" + this.numRawReadsLong;
		}

	}

	// find the correct file in the folder and parse it
	private void analyzeFastQC(File currFolder) {
		String[] names = currFolder.list();
		for(String name:names){
			File currFile = new File(currFolder+"/"+name);
			if(currFile.isFile() && currFile.getName().equals("fastqc_data.txt")){
				parseFastQCFile(currFile);
				break;
			}
		}
	}

	// parse the FastQC file that contains the relevant information
	private void parseFastQCFile(File currFile) {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(currFile));
			String currLine = "";
			while((currLine = br.readLine()) != null){
				if(currLine.contains("##FastQC")){
					String[] splittedLine = currLine.split("\t");
					this.version = splittedLine[1].trim();
				}
				if(currLine.contains("Total Sequences")){
					this.numFilesAnalyzed++;
					String[] splittedLine = currLine.split("\t");
					this.numRawReadsLong += Long.parseLong(splittedLine[splittedLine.length-1].trim());
				}
			}
		} catch (IOException e) {
			this.numErrors++;
		}
	}


	/**
	 * Getter Method for the Field that contains the number of raw reads that were
	 * analyzed by the pipeline
	 * @return the number of raw reads
	 */
	public String getNumRawReads() {
		return numRawReads;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

}
