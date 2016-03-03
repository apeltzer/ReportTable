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
package IO;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import utilities.Pipelines;

/**
 * Class for the analysis of the folder containing each sample
 * 
 * @author Alexander Seitz
 *
 */
public class AnalyzeFolders {

	// member variables
	private String inputFolder = "";
	private List<File> samples;

	/**
	 * Analyze the given inputFolder and find out how many samples are in that folder
	 * additionally store the location of each sample for further analysis
	 * @param inputFolder the folder where the samples are located
	 */
	public AnalyzeFolders(String inputFolder){
		this.inputFolder = inputFolder;
		this.samples = new LinkedList<File>();
		findSamples();
	}

	private boolean isEAGERResultFolder(File folder){
		if(!folder.exists()){
			return false;
		}
		String[] names = folder.list();
		for(String name: names){
			File currFile = new File(name);
			for(Pipelines pipeline: Pipelines.values()){
				if(currFile.getName().startsWith(pipeline.toString())){
					return true;
				}
			}
		}
		return false;
	}

	// get all the folders of the samples
	private void findSamples() {
		try {
			File folder = new File(new File(inputFolder).getCanonicalPath());
			if(folder.exists()){
				String[] names = folder.list();
				// first check if the current folder already contains the eager results
				if(isEAGERResultFolder(new File(this.inputFolder))){
					this.samples.add(folder);
					return;
				}
				for(String name:names){
					File currFile = new File(inputFolder+"/"+name);
					if(currFile.isDirectory() && isEAGERResultFolder(currFile)){
						this.samples.add(new File(currFile.getAbsolutePath()));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Getter method for field containing the number of samples contained in the
	 * input folder
	 * return the number of samples in the input folder
	 */
	public Integer getNumSamples(){
		return this.samples.size();
	}

	/**
	 * Getter method for the field containing the list of folders. Each folder contains
	 * one sample run
	 * return the List of the sample locations
	 */
	public List<File> getSampleLocations(){
		return this.samples;
	}

}
