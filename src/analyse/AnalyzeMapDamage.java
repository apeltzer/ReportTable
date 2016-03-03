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
import java.text.DecimalFormat;
import java.util.ArrayList;

import utilities.OutputStrings;
import utilities.Pipelines;

/**
 * @author seitz
 *
 */
public class AnalyzeMapDamage extends AbstractAnalyze {
	private String FirstBase3 = OutputStrings.notFound;
	private String SecondBase3 = OutputStrings.notFound;
	private String FirstBase5 = OutputStrings.notFound;
	private String SecondBase5 = OutputStrings.notFound;
	private String meanFragmentLength = OutputStrings.notFound;
	

	public AnalyzeMapDamage(File sampleFolder) {
		super(sampleFolder);
		analyzeMapDamageFolder();
		// TODO Auto-generated constructor stub
	}


	private void analyzeMapDamageFolder() {
//		File currFolder = new File(this.sampleFolder + "/" + Pipelines.MapDamage.toString());
		File currFolder = this.getCurrFolder(Pipelines.MapDamage);
//		if(currFolder.exists()){
		if(currFolder != null){
			String[] names = currFolder.list();
			for(String name:names){
				File currFile = new File(currFolder+"/"+name);
				if(currFile.isDirectory()){
					analyzeMapDamageFiles(currFile);
				}
			}
		}
	}


	private void analyzeMapDamageFiles(File currFolder) {
		String[] names = currFolder.list();
		for(String name:names){
			File currFile = new File(currFolder+"/"+name);
			if(currFile.isFile()){
				if(currFile.getName().startsWith("3pGtoA")){
					analyzeFile(currFile, true);
				}else if(currFile.getName().startsWith("5pCtoT")){
					analyzeFile(currFile,false);
				}else if(currFile.getName().startsWith("lgdistribution")){
					computeAverageLength(currFile);
				}
			}
		}
	}



	private void analyzeFile(File currFile, boolean threePrime) {
		String one = OutputStrings.notFound;
		String two = OutputStrings.notFound;
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(currFile));
			String currLine = "";
			while((currLine = br.readLine()) != null){
				if(currLine.startsWith("1\t")){
					String[] splitted = currLine.split("\t");
					if(splitted.length>=2){
						one=splitted[1].trim();
					}
				}
				if(currLine.startsWith("2\t")){
					String[] splitted = currLine.split("\t");
					if(splitted.length>=2){
						two=splitted[1].trim();
					}
				}
			}
		} catch (IOException e) {
		}
		DecimalFormat newFormat = new DecimalFormat("#.####");
		if(!OutputStrings.notFound.equals(one)){
			Double oneD = Double.parseDouble(one);
			Double oneDec = Double.valueOf(newFormat.format(oneD));
			one = Double.toString(oneDec);
		}
		if(!OutputStrings.notFound.equals(two)){
			double twoD = Double.parseDouble(two);
			Double twoDec = Double.valueOf(newFormat.format(twoD));
			two = Double.toString(twoDec);
		}
		if(threePrime){
			this.FirstBase3 = one;
			this.SecondBase3 = two;
		}else{
			this.FirstBase5 = one;
			this.SecondBase5 = two;
		}
	}
	
	
	/**
	 * This method computes the weighted mean fragment length
	 * of all fragments given as input
	 *  
	 * @param currFile
	 */
	private void computeAverageLength(File currFile) {
		ArrayList<ArrayList<Integer>> fragments = new ArrayList<ArrayList<Integer>>();	
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(currFile));
			String currLine = "";
			while((currLine = br.readLine()) != null){
				if (currLine.startsWith("-")||currLine.startsWith("+")){
					ArrayList<Integer> pairs = new ArrayList<Integer>();
					String[] line_split = currLine.split("\t");
					pairs.add(Integer.parseInt(line_split[1]));
					pairs.add(Integer.parseInt(line_split[2]));
					fragments.add(pairs);
				}				
			}
		} catch (IOException e) {
		}
		
		ArrayList<Integer> pair;
		int length_sum = 0;
		int number_of_fragments=0;

		for(int i = 0; i < fragments.size(); i++){
			pair = fragments.get(i);
			number_of_fragments += pair.get(1);
			length_sum += pair.get(0) * pair.get(1); 
		}		

		this.meanFragmentLength = Double.toString(Math.round((double) length_sum / number_of_fragments * 100.0) / 100.0);
		
	}


	/**
	 * @return the damage of first Base at the 3' End
	 */
	public String getFirstBase3() {
		return FirstBase3;
	}


	/**
	 * @return the damage of second Base at the 3' End
	 */
	public String getSecondBase3() {
		return SecondBase3;
	}


	/**
	 * @return the damage of first Base at the 5' End
	 */
	public String getFirstBase5() {
		return FirstBase5;
	}


	/**
	 * @return the damage of second Base at the 5' End
	 */
	public String getSecondBase5() {
		return SecondBase5;
	}
	

	/**
	 * @return the weighted average length of the reads
	 */
	public String getMeanFragmentLength() {
		return meanFragmentLength;
	}
	
}
