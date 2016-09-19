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

/**
 * Class to parse the mitochondreal and nuclear DNA statistics
 * @author Alexander Seitz
 *
 */
public class AnalyzeMtNuc {
	
	private File inputFile;
	private String mtnucratio = OutputStrings.notFound;
	private String numMitochondrealReads = OutputStrings.notFound;
	private String avgCoverageOnMt = OutputStrings.notFound;

	/**Class for the analysis of the results for the mitochondreal and nuclear DNA
	 * @param inputFile
	 */
	public AnalyzeMtNuc(File inputFile) {
		this.inputFile = inputFile;
		parseMTNucRatioFile();
	}
	
	// parse the file and extract the needed information
	private void parseMTNucRatioFile() {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(this.inputFile));
			String currLine = "";
			while((currLine = br.readLine()) != null){
				if(currLine.contains("reads on mitochondrium")){
					String[] splitted = currLine.trim().split(" ");
					this.numMitochondrealReads = splitted[splitted.length-1].trim();
				}else if(currLine.contains("AVG Coverage on MT")){
					String[] splitted = currLine.trim().split(" ");
					this.avgCoverageOnMt = splitted[splitted.length-1].trim();
				}else if(currLine.contains("mt/nuc Ratio")){
					String[] splitted = currLine.trim().split(" ");
					this.mtnucratio = splitted[splitted.length-1].trim();
					this.mtnucratio = this.mtnucratio.replace(",", ".");
				}
			}
		} catch (IOException e) {
		}
	}

	/**
	 * Getter method for the field containing the ratio between the mitochondreal
	 * and the nuclear DNA
	 * @return the ratio between the mitochondreal and Nuclear DNA
	 */
	public String getMtnucratio() {
		return mtnucratio;
	}

	/**
	 * Getter method for the field containing the number fo mitochondreal reads
	 * @return the number of mitochondreal reads
	 */
	public String getNumMitochondrealReads() {
		return numMitochondrealReads;
	}

	/**
	 * Getter method for the field containing the average coverage of the
	 * mitochondreal DNA
	 * @return the the average coverage on the mitochondreal DNA
	 */
	public String getAvgCoverageOnMt() {
		return avgCoverageOnMt;
	}

}
