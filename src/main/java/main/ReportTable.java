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
package main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import IO.AReportWriter;
import IO.AnalyzeFolders;
import IO.CSVFileWriter;
import IO.HTMLFileWriter;
import analyse.AnalyzeSample;
import utilities.OutputFields;


/**
 * @author Alexander Seitz
 *
 */
public class ReportTable {

	private static String VERSION = "x.x";
	private static String TITLE = "ReportTable";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadMetadata();
		System.out.println(ReportTable.TITLE+" version "+ReportTable.VERSION);
		if(args.length != 2){
			System.err.println("Wrong number of parameters!");
			System.out.println("Usage:");
			System.out.println(ReportTable.TITLE+" <output_file> <input_folder>");
			System.exit(0);
		}
		Locale.setDefault(new Locale("en", "US"));
		
		// parse 
		String outputFile = args[0];
		String inputFolder = args[1];
		
		// analyze the input folder
		AnalyzeFolders analyzeFolders = new AnalyzeFolders(inputFolder);
		
		// initialize outputMap
		HashMap<OutputFields,Boolean> outputMap = initOutputMap(false);
		
		//get List of Samples and add to list
		int sampleNum = 1;
		List<File> listOfSamples = new LinkedList<File>();
		for(File f: analyzeFolders.getSampleLocations()){
			listOfSamples.add(f);
		}
		// sort the Samples by name
		Collections.sort(listOfSamples, new Comparator<File>(){
			public int compare(File f1, File f2){
				return f1.getName().compareTo(f2.getName());
			}
		});
		// analyze the samples
		List<AnalyzeSample> analyzedSamples = new LinkedList<AnalyzeSample>();
		for(File f: listOfSamples){
			System.out.println("Analyzing Sample: " + f.getName());
			AnalyzeSample analyzeSample = new AnalyzeSample(f, sampleNum, analyzeFolders.getNumSamples());
			analyzeSample.analyzeSuccessful(outputMap);
			analyzedSamples.add(analyzeSample);
			sampleNum++;
		}
		
		// print the results
		List<AReportWriter> outputWriter = new LinkedList<AReportWriter>();
		outputWriter.add(new HTMLFileWriter(outputFile, outputMap));
		outputWriter.add(new CSVFileWriter(outputFile, outputMap));
		for(AReportWriter afw: outputWriter){
			afw.writeNotSuccessfulAnalyzed(outputMap);
		}
		Map<String, Set<String>> versions = new TreeMap<String, Set<String>>();
		for(AnalyzeSample analyzeSample: analyzedSamples){
			for(AReportWriter afw: outputWriter){
				afw.writeDataLine(outputMap, analyzeSample);
			}
			versions = addVersions(versions, analyzeSample.getVersions());
			Set<String> reportVersion = new TreeSet<String>();
			reportVersion.add(ReportTable.VERSION);
			versions.put("ReportTable Version:", reportVersion);
			
		}
		for(AReportWriter afw: outputWriter){
			afw.finalizeWriting();
		}
		for(AReportWriter afw: outputWriter){
			afw.writeVersions(versions);
		}
		System.out.println("finished");
	}

	/**
	 * @param versions
	 * @param versions2
	 * @return
	 */
	private static Map<String, Set<String>> addVersions(Map<String, Set<String>> versions, Map<String, Set<String>> newVersions) {
		for(String program: newVersions.keySet()) {
			if(!versions.containsKey(program)) {
				versions.put(program, newVersions.get(program));
			}else {
				Set<String> currVersions = versions.get(program);
				for(String version: newVersions.get(program)) {
					if(!currVersions.contains(version)) {
						currVersions.add(version);
					}
				}
				versions.put(program, currVersions);
			}
		}
		return versions;
	}

	private static HashMap<OutputFields, Boolean> initOutputMap(boolean b) {
		HashMap<OutputFields, Boolean> outputMap = new HashMap<OutputFields, Boolean>();
		for(OutputFields o: OutputFields.values()){
			outputMap.put(o, b);
		}
		return outputMap;
	}
	
	public static String toCorrectNumberFormat(String s){
		if(s.contains(".") && s.contains(",")){
			int i = s.indexOf('.');
			int j = s.indexOf(',');
			if(i<j){
				String s1 = s.replace(".", "");
				s1 = s1.replace(",",".");
				return s1;
			}else{
				return s.replace(",", "");
			}
		}
		if(s.contains(".")){
			return s;
		}
		if(s.contains(",")){
			return s.replace(",", ".");
		}
		return s;
	}
	
	private static void loadMetadata(){
		Properties properties = new Properties();
		try {
			//load version
			InputStream in = ReportTable.class.getResourceAsStream("/version.properties");
			properties.load(in);
			ReportTable.VERSION = properties.getProperty("version");
			in.close();
			// load title
			in = ReportTable.class.getResourceAsStream("/title.properties");
			properties.load(in);
			ReportTable.TITLE  = properties.getProperty("title");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
