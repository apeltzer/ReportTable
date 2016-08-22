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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.OutputStrings;
import utilities.Pipelines;

/**
 * @author Alexander Seitz
 *
 */
public class AnalyzeMapper extends AbstractAnalyze{

	private String version = OutputStrings.notFound;
	private String mapped = OutputStrings.notFound;
	private String endogenousDNA = OutputStrings.notFound;
	private String numReads = OutputStrings.notFound;
	private String unmapped = OutputStrings.notFound;

  public AnalyzeMapper(File sampleFolder) {
    this(sampleFolder, Collections.emptySet());
  }

	public AnalyzeMapper(File sampleFolder, Set<String> excludeMatchingEndsWithStatsFiles) {
		super(sampleFolder);
		String[] names = this.sampleFolder.list();
		for(String name: names){
			File currFile = new File(this.sampleFolder+"/"+name);
			if(currFile.isFile() && currFile.getName().equals("log.log")){
				parseFile(currFile);
			}
		}
		File dataDir = this.getCurrFolder(Pipelines.Mapping);
//		if(dataDir.exists()){
		if(dataDir != null && dataDir.list() != null){
			//names = dataDir.list();
      //System.out.println(Arrays.asList(dataDir.list()).stream().collect(Collectors.toList()));
      //System.out.println(Arrays.asList(dataDir.list()).stream().filter(n -> excludeMatchingEndsWithStatsFiles.stream().filter( e -> n.endsWith(e) ).collect(Collectors.counting()) == 0 ).collect(Collectors.toList()));
      List<String> namesList = Arrays.asList(dataDir.list()).stream().filter(n -> excludeMatchingEndsWithStatsFiles.stream().filter( e -> n.endsWith(e) ).collect(Collectors.counting()) == 0 ).collect(Collectors.toList());
      names = namesList.toArray(new String [namesList.size()]);
			for(String name:names){
				File currFile = new File(dataDir+"/"+name);
				if(currFile.isFile()){
					if(currFile.getName().endsWith(".stats")){
						parseFlagstatsFile(currFile);
					}
				}
			}
		}
		if(!OutputStrings.notFound.equals(this.mapped) && !OutputStrings.notFound.equals(this.unmapped)){
			Double mappedReads = Double.parseDouble(this.mapped);
			Double unmappedReads = Double.parseDouble(this.unmapped);
			Double numberReads = mappedReads + unmappedReads;
			this.numReads = String.format("%s", numberReads);
			this.endogenousDNA = String.format("%.3f", (mappedReads / numberReads) * 100);
		}
	}

	private void parseFlagstatsFile(File currFile) {
		// TODO Auto-generated method stub
		Integer all = 0;
		Integer mappedR = 0;
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(currFile));
			String currLine = "";
			while((currLine = br.readLine()) != null){
				if(currLine.contains("in total")){
					String[] splitted = currLine.split(" ");
					this.numReads = splitted[0].trim();
					all = Integer.parseInt(this.numReads);
				}else if(currLine.contains("mapped (")){
					String[] splitted = currLine.split(" ");
					this.mapped = splitted[0].trim();
					mappedR = Integer.parseInt(this.mapped);
				}
			}
		} catch (IOException e) {
		}
		if((all != 0.0) && (mappedR != 0.0)){
			this.unmapped = ""+(all-mappedR);
		}
	}

	// parse the given file for the needed information
		private void parseFile(File currFile) {
			try {
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(currFile));
				String currLine = "";
				// the results are located after the file contains the Message "Finished merging!"
				while((currLine = br.readLine()) != null){
					if(currLine.contains("[main] Version:")){
						String[] splittedLine = currLine.split(" ");
						this.version = "bwa  " + splittedLine[splittedLine.length-1].trim();
						break;
					}
				}
			} catch (IOException e) {

			}

		}

		/**
		 * @return the version
		 */
		public String getVersion() {
			return version;
		}

		public String getMapped() {
			return mapped;
		}

		public String getEndogenousDNA() {
			return endogenousDNA;
		}

		public String getNumReads() {
			return numReads;
		}

		public String getUnmapped() {
			return unmapped;
		}

}
