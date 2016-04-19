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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

import utilities.OutputFields;

/**
 * @author Alexander Seitz
 *
 */
public class myFileWriter {
	
	private String filename = "";

	public myFileWriter(String filename, HashMap<OutputFields,Boolean> outputMap) {
		this.filename = filename;
		if(!this.filename.endsWith(".csv")){
			this.filename += ".csv";
		}
		if(new File(this.filename).exists()){
			new File(this.filename).delete();
		}
		writeHeaderLine(outputMap);
	}
	
	private void writeHeaderLine(HashMap<OutputFields,Boolean> outputMap){
		StringBuffer result = new StringBuffer("");
		if(outputMap.containsKey(OutputFields.SampleNumber)
				&& outputMap.get(OutputFields.SampleNumber)){
			result.append(OutputFields.SampleNumber.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.SampleName)
				&& outputMap.get(OutputFields.SampleName)){
			result.append(OutputFields.SampleName.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumRawReads)
				&& outputMap.get(OutputFields.NumRawReads)){
			result.append(OutputFields.NumRawReads.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumMergedReads)
				&& outputMap.get(OutputFields.NumMergedReads)){
			result.append(OutputFields.NumMergedReads.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.PerCentMergedReads)
				&& outputMap.get(OutputFields.PerCentMergedReads)){
			result.append(OutputFields.PerCentMergedReads);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumUsableReadsAfterMerging)
				&& outputMap.get(OutputFields.NumUsableReadsAfterMerging)){
			result.append(OutputFields.NumUsableReadsAfterMerging.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.TotalNumMappedReadsBeforeDupRemoval)
				&& outputMap.get(OutputFields.TotalNumMappedReadsBeforeDupRemoval)){
			result.append(OutputFields.TotalNumMappedReadsBeforeDupRemoval.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.TotalNumMappedReadsBeforeDupRemovalQF)
				&& outputMap.get(OutputFields.TotalNumMappedReadsBeforeDupRemovalQF)){
			result.append(OutputFields.TotalNumMappedReadsBeforeDupRemovalQF.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumDupRemoved)
				&& outputMap.get(OutputFields.NumDupRemoved)){
			result.append(OutputFields.NumDupRemoved.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.MappedReadsAfterDupRemoval)
				&& outputMap.get(OutputFields.MappedReadsAfterDupRemoval)){
			result.append(OutputFields.MappedReadsAfterDupRemoval.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.EndogenousDNA)
				&& outputMap.get(OutputFields.EndogenousDNA)){
			result.append(OutputFields.EndogenousDNA.toString());
			result.append(",");
		}
//		if(outputMap.containsKey(OutputFields.PerCentMappedReads)
//				&& outputMap.get(OutputFields.PerCentMappedReads)){
//			result.append(OutputFields.PerCentMappedReads.toString());
//			result.append(",");
//		}
		if(outputMap.containsKey(OutputFields.ClusterFactor)
				&& outputMap.get(OutputFields.ClusterFactor)){
			result.append(OutputFields.ClusterFactor.toString());
			result.append(",");
		}
//		if(outputMap.containsKey(OutputFields.ClusterFactorQF)
//				&& outputMap.get(OutputFields.ClusterFactorQF)){
//			result.append(OutputFields.ClusterFactorQF.toString());
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.EndogenousDNAQF)
//				&& outputMap.get(OutputFields.EndogenousDNAQF)){
//			result.append(OutputFields.EndogenousDNAQF.toString());
//			result.append(",");
//		}
		if(outputMap.containsKey(OutputFields.MeanCoverage)
				&& outputMap.get(OutputFields.MeanCoverage)){
			result.append(OutputFields.MeanCoverage.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.StdDevCoverage)
				&& outputMap.get(OutputFields.StdDevCoverage)){
			result.append(OutputFields.StdDevCoverage.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage1X)
				&& outputMap.get(OutputFields.Coverage1X)){
			result.append(OutputFields.Coverage1X.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage2X)
				&& outputMap.get(OutputFields.Coverage2X)){
			result.append(OutputFields.Coverage2X.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage3X)
				&& outputMap.get(OutputFields.Coverage3X)){
			result.append(OutputFields.Coverage3X.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage4X)
				&& outputMap.get(OutputFields.Coverage4X)){
			result.append(OutputFields.Coverage4X.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage5X)
				&& outputMap.get(OutputFields.Coverage5X)){
			result.append(OutputFields.Coverage5X.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumSNPs)
				&& outputMap.get(OutputFields.NumSNPs)){
			result.append(OutputFields.NumSNPs.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumReadsMT)
				&& outputMap.get(OutputFields.NumReadsMT)){
			result.append(OutputFields.NumReadsMT.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.AVGCovMT)
				&& outputMap.get(OutputFields.AVGCovMT)){
			result.append(OutputFields.AVGCovMT.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.MTNUCRatio)
				&& outputMap.get(OutputFields.MTNUCRatio)){
			result.append(OutputFields.MTNUCRatio.toString());
			result.append(",");
		}
//		if(outputMap.containsKey(OutputFields.SNPCCNumPosFound)
//				&& outputMap.get(OutputFields.SNPCCNumPosFound)){
//			result.append(OutputFields.SNPCCNumPosFound.toString());
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCNumCheckedPos)
//				&& outputMap.get(OutputFields.SNPCCNumCheckedPos)){
//			result.append(OutputFields.SNPCCNumCheckedPos.toString());
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCPerCentSNPCovered)
//				&& outputMap.get(OutputFields.SNPCCPerCentSNPCovered)){
//			result.append(OutputFields.SNPCCPerCentSNPCovered.toString());
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCTotalNumHQPos)
//				&& outputMap.get(OutputFields.SNPCCTotalNumHQPos)){
//			result.append(OutputFields.SNPCCTotalNumHQPos.toString());
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCTotalNumLQPos)
//				&& outputMap.get(OutputFields.SNPCCTotalNumLQPos)){
//			result.append(OutputFields.SNPCCTotalNumLQPos.toString());
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCCovHQ)
//				&& outputMap.get(OutputFields.SNPCCCovHQ)){
//			result.append(OutputFields.SNPCCCovHQ.toString());
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCCovLQ)
//				&& outputMap.get(OutputFields.SNPCCCovLQ)){
//			result.append(OutputFields.SNPCCCovLQ.toString());
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCCovVerified)
//				&& outputMap.get(OutputFields.SNPCCCovVerified)){
//			result.append(OutputFields.SNPCCCovVerified.toString());
//			result.append(",");
//		}
		if(outputMap.containsKey(OutputFields.Damage3Prime1st)
				&& outputMap.get(OutputFields.Damage3Prime1st)){
			result.append(OutputFields.Damage3Prime1st.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Damage3Prime2nd)
				&& outputMap.get(OutputFields.Damage3Prime2nd)){
			result.append(OutputFields.Damage3Prime2nd.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Damage5Prime1st)
				&& outputMap.get(OutputFields.Damage5Prime1st)){
			result.append(OutputFields.Damage5Prime1st.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Damage5Prime2nd)
				&& outputMap.get(OutputFields.Damage5Prime2nd)){
			result.append(OutputFields.Damage5Prime2nd.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.DamageLgDistribution)
				&& outputMap.get(OutputFields.DamageLgDistribution)){
			result.append(OutputFields.DamageLgDistribution.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.InitialContamination)
				&& outputMap.get(OutputFields.InitialContamination)){
			result.append(OutputFields.InitialContamination.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.InitialContaminationLowerBound)
				&& outputMap.get(OutputFields.InitialContaminationLowerBound)){
			result.append(OutputFields.InitialContaminationLowerBound.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.InitialContaminationUpperBound)
				&& outputMap.get(OutputFields.InitialContaminationUpperBound)){
			result.append(OutputFields.InitialContaminationUpperBound.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.FinalContamination)
				&& outputMap.get(OutputFields.FinalContamination)){
			result.append(OutputFields.FinalContamination.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.FinalContaminationLowerBound)
				&& outputMap.get(OutputFields.FinalContaminationLowerBound)){
			result.append(OutputFields.FinalContaminationLowerBound.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.FinalContaminationUpperBound)
				&& outputMap.get(OutputFields.FinalContaminationUpperBound)){
			result.append(OutputFields.FinalContaminationUpperBound.toString());
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.GCContent)
				&& outputMap.get(OutputFields.GCContent)){
			result.append(OutputFields.GCContent.toString());
			result.append(",");
		}
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, false)))) {
		    out.println(result.toString());
		    out.flush();
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	
	public void writeNotSuccessfulAnalyzed(String fil, HashMap<OutputFields,Boolean> outputMap){
		StringBuffer result = new StringBuffer("");
		boolean writeFile = false;
		for(OutputFields o: OutputFields.values()){
			if(!outputMap.containsKey(o)
				|| !outputMap.get(o)){
				result.append(o.toString());
				result.append("\n");
				writeFile = true;
			}
		}
		if(writeFile){
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(fil), false)))) {
			    out.println(result.toString());
			    out.flush();
			}catch (IOException e) {
			}
		}
	}
	
	public void appendLine(String line, HashMap<OutputFields,Boolean> outputMap){
		if(!new File(this.filename).exists()){
			writeHeaderLine(outputMap);
		}
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)))) {
		    out.println(line);
		    out.flush();
		}catch (IOException e) {
		}
	}

	public void writeVersions(String fil, HashSet<String> fastqc, HashSet<String> clipAndMerge, HashSet<String> mapper) {
		// TODO Auto-generated method stub
		StringBuffer result = new StringBuffer("");
		result.append("FastQC:\t");
		result.append(fastqc.toString());
		result.append("\n");
		result.append("ClipAndMerge:\t");
		result.append(clipAndMerge.toString());
		result.append("\n");
		result.append("Mapper:\t");
		result.append(mapper.toString());
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(fil), false)))) {
		    out.println(result.toString());
		    out.flush();
		}catch (IOException e) {
		}
	}
	
	

}
