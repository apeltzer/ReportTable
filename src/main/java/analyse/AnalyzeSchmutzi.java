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
 * @author Alexander Seitz
 *
 */
public class AnalyzeSchmutzi extends AbstractAnalyze{
	
	private String initialEstimation = OutputStrings.notFound;
	private String initialLowerBound = OutputStrings.notFound;
	private String initialUpperBound = OutputStrings.notFound;
	private String finalEstimation = OutputStrings.notFound;
	private String finalLowerBound = OutputStrings.notFound;
	private String finalUpperBound = OutputStrings.notFound;
	private String finalEstimationMtnotpredc = OutputStrings.notFound;
	private String finalLowerBoundMtnotpredc = OutputStrings.notFound;
	private String finalUpperBoundMtnotpredc = OutputStrings.notFound;

	public AnalyzeSchmutzi(File sampleFolder) {
		super(sampleFolder);
		if(this.getCurrFolder(Pipelines.DeDup) == null){
			return;
		}
		boolean oldStyle = analyzeOldStyle();
		if(!oldStyle){
			analyzeNewStyle();
		}
	}

	/**
	 * 
	 */
	private void analyzeNewStyle() {
		File contDeamFolder = new File(this.getCurrFolder(Pipelines.DeDup).getAbsolutePath()+"/contDeam");
		File schmutziDefaultFolder = new File(this.getCurrFolder(Pipelines.DeDup).getAbsolutePath()+"/schmutzi_default");
		File schmutziMtnotpredcFolder = new File(this.getCurrFolder(Pipelines.DeDup).getAbsolutePath()+"/schmutzi_mtnotpredc");
		if(contDeamFolder.exists()){
			for(String name: contDeamFolder.list()){
				File currFile = new File(contDeamFolder+"/"+name);
				if(currFile.getName().endsWith(".cont.est")){
					parseFile(currFile, 1);
					break;
				}
			}
		}
		if(schmutziDefaultFolder.exists()){
			for(String name: schmutziDefaultFolder.list()){
				File currFile = new File(schmutziDefaultFolder+"/"+name);
				if(currFile.getName().endsWith("_final.cont.est")){
					parseFile(currFile, 2);
					break;
				}
			}
		}
		if(schmutziMtnotpredcFolder.exists()){
			for(String name: schmutziMtnotpredcFolder.list()){
				File currFile = new File(schmutziMtnotpredcFolder+"/"+name);
				if(currFile.getName().endsWith("_final.cont.est")){
					parseFile(currFile, 3);
					break;
				}
			}
		}
	}

	/**
	 * 
	 */
	private boolean analyzeOldStyle() {
		File dataDir = this.getCurrFolder(Pipelines.DeDup);
		boolean foundInitial = false;
		boolean foundFinal = false;
		if(dataDir != null){
			String[] names = dataDir.list();
			for(String name: names){
				File currFile = new File(dataDir+"/"+name);
				if(currFile.isFile()){
					if(currFile.getName().endsWith("_final.cont.est")){
						parseFile(currFile, 2);
						foundFinal = true;
					}else if(currFile.getName().endsWith(".cont.est")){
						parseFile(currFile, 1);
						foundInitial = true;
					}
				}
			}
		}
		return foundInitial && foundFinal;
	}
	
	private void parseFile(File currFile, int EstimationType) {
		String estimation = "";
		String lowerBound = "";
		String upperBound = "";
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(currFile));
			String currLine = "";
			while((currLine = br.readLine()) != null){
				String[] splitted = currLine.split("\t");
				if(splitted.length==3){
					estimation = splitted[0];
					lowerBound = splitted[1];
					upperBound = splitted[2];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch(EstimationType){
		case 1: this.initialEstimation = estimation;
				this.initialLowerBound = lowerBound;
				this.initialUpperBound = upperBound;
				break;
		case 2: this.finalEstimation = estimation;
				this.finalLowerBound = lowerBound;
				this.finalUpperBound = upperBound;
				break;
		case 3: this.finalEstimationMtnotpredc = estimation;
				this.finalLowerBoundMtnotpredc = lowerBound;
				this.finalUpperBoundMtnotpredc = upperBound;
				break;
		}
//		if(finalEstimation){
//			this.finalEstimation = estimation;
//			this.finalLowerBound = lowerBound;
//			this.finalUpperBound = upperBound;
//		}else{
//			this.initialEstimation = estimation;
//			this.initialLowerBound = lowerBound;
//			this.initialUpperBound = upperBound;
//		}
		
	}

	/**
	 * @return the initialEstimation
	 */
	public String getInitialEstimation() {
		return initialEstimation;
	}

	/**
	 * @return the initialLowerBound
	 */
	public String getInitialLowerBound() {
		return initialLowerBound;
	}

	/**
	 * @return the initialUpperBound
	 */
	public String getInitialUpperBound() {
		return initialUpperBound;
	}

	/**
	 * @return the finalEstimation
	 */
	public String getFinalEstimation() {
		return finalEstimation;
	}

	/**
	 * @return the finalLowerBound
	 */
	public String getFinalLowerBound() {
		return finalLowerBound;
	}

	/**
	 * @return the finalUpperBound
	 */
	public String getFinalUpperBound() {
		return finalUpperBound;
	}

	/**
	 * @return the finalEstimationMtnotpredc
	 */
	public String getFinalEstimationMtnotpredc() {
		return finalEstimationMtnotpredc;
	}

	/**
	 * @return the finalLowerBoundMtnotpredc
	 */
	public String getFinalLowerBoundMtnotpredc() {
		return finalLowerBoundMtnotpredc;
	}

	/**
	 * @return the finalUpperBoundMtnotpredc
	 */
	public String getFinalUpperBoundMtnotpredc() {
		return finalUpperBoundMtnotpredc;
	}

}
