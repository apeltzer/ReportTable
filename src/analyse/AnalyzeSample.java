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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import utilities.OutputFields;
import utilities.OutputStrings;
import utilities.Pipelines;

/**
 * @author Alexander Seitz
 *
 */
public class AnalyzeSample {

	private File sampleFolder;
	private boolean foundConfig = false;
	private HashMap<Pipelines, Boolean> runPipelines;

	// the values of each field in the output file
	private String sampleNumber = "";
	private String sampleName = "";
	private String numberRawReads = "";
	private String numberUsableReadsAfterMerging = "";
	private String numberMergedReads = "";
	private String perCentMergedReads = "";
	private String totalNumMappedReadsBeforeDupRemoval = "";
	private String numberDuplicatesRemoved = "";
	private String numMitochondrealReads = "";
	private String avgCoverageOnMt = "";
	private String mttonucratio = "";
	private String mappedReadsAfterDupRemoval = "";
//	private String perCentMappedReads = "";
	private String gcContent = "";
	private String endogenousDNA = "";
	private String clusterFactor = "";
//	private String snpccNumOfPositionsFoundByGenotyper = "";
//	private String snpccNumOfCheckedPositionAgainsList = "";
//	private String snpccPercentageOfSNPPositionsCovered = "";
//	private String snpccTotalNumberOfHQPositions = "";
//	private String snpccTotalNumberOfLQPositions = "";
//	private String snpccCoverageOnHQSites = "";
//	private String snpccCoverageOnLQSites = "";
//	private String snpccCoverageOnVerifiedSites = "";
	private String meanCoverage = "";
	private String stdCoverage = "";
	private String coverage1x = "";
	private String coverage2x = "";
	private String coverage3x = "";
	private String coverage4x = "";
	private String coverage5x = "";
	private String numSNPs = "";
	private String damage3Prime1 = "";
	private String damage3Prime2 = "";
	private String damage5Prime1 = "";
	private String damage5Prime2 = "";
	private String meanFragmentLength = "";
	private String mappedQF = "";
	private String endogenousDNAQF = "";
	private String numReadsQF = "";
	private String unmappedQF = "";
	private String clusterFactorQF = "";
	private String initialEstimation = "";
	private String initialLowerBound = "";
	private String initialUpperBound = "";
	private String finalEstimation = "";
	private String finalLowerBound = "";
	private String finalUpperBound = "";


	// versions
	private String versionFastQC = "";
	private String versionClipAndMerge = "";
	private String versionMapper = "";
	//	private String versionSamtools = "";
	//	private String versionBetterRMDup = "";
	//	private String versionQualiMap = "";
	//	private String versionMapDamage = "";
	//	private String versionPreseq = "";
	//	private String versionGATK = "";
	//	private String versionVcf2draft = "";

	// the result string
	private String resultString;

	public String getResultString() {
		return resultString;
	}

	public AnalyzeSample(File f, Integer sampleNumber, Integer numSamples) {
		this.sampleNumber = "Sample " + sampleNumber + "/" + numSamples;
		this.sampleName = f.getName();
		this.sampleFolder = f;
		this.foundConfig = false;
		parseConfig();
		analyseFiles();
		//		generateOutputStringOld();
	}

	private void analyseFiles() {
		for(Pipelines p: Pipelines.values()){
			switch(p){
			case FastQC:
				if(this.runPipelines.containsKey(Pipelines.FastQC)
						&& this.runPipelines.get(Pipelines.FastQC)){
					AnalyzeFastQC analyzeFastQC = new AnalyzeFastQC(this.sampleFolder);
					this.numberRawReads = analyzeFastQC.getNumRawReads();
					this.versionFastQC = analyzeFastQC.getVersion();
				}else{
					this.numberRawReads = OutputStrings.notRun;
					this.versionFastQC = OutputStrings.notRun;
				}
				break;
			case ClipAndMerge:
				if(this.runPipelines.containsKey(Pipelines.ClipAndMerge)
						&& this.runPipelines.get(Pipelines.ClipAndMerge)){
					AnalyzeClipAndMerge analyzeClipAndMerge = new AnalyzeClipAndMerge(this.sampleFolder);
					this.numberUsableReadsAfterMerging = analyzeClipAndMerge.getNumberUsableReadsBeforeMerging();
					this.numberMergedReads = analyzeClipAndMerge.getNumberMergedReads();
					this.perCentMergedReads = analyzeClipAndMerge.getPerCentMergedReads();
					this.versionClipAndMerge = analyzeClipAndMerge.getVersion();
				}else{
					this.numberUsableReadsAfterMerging = OutputStrings.notRun;
					this.numberMergedReads = OutputStrings.notRun;
					this.perCentMergedReads = OutputStrings.notRun;
					this.versionClipAndMerge = OutputStrings.notRun;
				}
				break;
			case QualityTrimming:
				break;
			case Mapping:
				if(this.runPipelines.containsKey(Pipelines.Mapping)
						&& this.runPipelines.get(Pipelines.Mapping)){
					AnalyzeMapper analyzeMapper = new AnalyzeMapper(this.sampleFolder);
					this.versionMapper = analyzeMapper.getVersion();
					if("".equals(this.totalNumMappedReadsBeforeDupRemoval)
							|| OutputStrings.notFound.equals(this.totalNumMappedReadsBeforeDupRemoval)
							|| OutputStrings.notRun.equals(this.totalNumMappedReadsBeforeDupRemoval)){
						this.totalNumMappedReadsBeforeDupRemoval = analyzeMapper.getMapped();
					}
					if("".equals(this.endogenousDNA)
							|| OutputStrings.notFound.equals(this.endogenousDNA)
							|| OutputStrings.notRun.equals(this.endogenousDNA)){
						this.endogenousDNA = analyzeMapper.getEndogenousDNA();
					}
					if("".equals(this.numberUsableReadsAfterMerging)
							|| OutputStrings.notFound.equals(this.numberUsableReadsAfterMerging)
							|| OutputStrings.notRun.equals(this.numberUsableReadsAfterMerging)){
						this.numberUsableReadsAfterMerging = analyzeMapper.getNumReads();
					}
				}else{
					this.versionMapper = OutputStrings.notRun;
				}
				break;
			case Samtools:
				if(this.runPipelines.containsKey(Pipelines.Samtools)
						&& this.runPipelines.get(Pipelines.Samtools)){
					AnalyzeSamtools analyzeSamtools = new AnalyzeSamtools(this.sampleFolder);
					if("".equals(this.totalNumMappedReadsBeforeDupRemoval)
							|| OutputStrings.notFound.equals(this.totalNumMappedReadsBeforeDupRemoval)
							|| OutputStrings.notRun.equals(this.totalNumMappedReadsBeforeDupRemoval)){
						this.totalNumMappedReadsBeforeDupRemoval = analyzeSamtools.getMapped();
					}
					if("".equals(this.endogenousDNA)
							|| OutputStrings.notFound.equals(this.endogenousDNA)
							|| OutputStrings.notRun.equals(this.endogenousDNA)){
						this.endogenousDNA = analyzeSamtools.getEndogenousDNA();
					}
					if("".equals(this.numberUsableReadsAfterMerging)
							|| OutputStrings.notFound.equals(this.numberUsableReadsAfterMerging)
							|| OutputStrings.notRun.equals(this.numberUsableReadsAfterMerging)){
						this.numberUsableReadsAfterMerging = analyzeSamtools.getNumReads();
					}
					if("".equals(this.mttonucratio)
							|| OutputStrings.notFound.equals(this.mttonucratio)
							|| OutputStrings.notRun.equals(this.mttonucratio)){
						this.mttonucratio = analyzeSamtools.getMtnucratio();
						this.numMitochondrealReads = analyzeSamtools.getNumMitochondrealReads();
						this.avgCoverageOnMt = analyzeSamtools.getAvgCoverageOnMt();
					}
					if("".equals(this.numberUsableReadsAfterMerging)
							|| OutputStrings.notFound.equals(this.numberUsableReadsAfterMerging)
							|| OutputStrings.notRun.equals(this.numberUsableReadsAfterMerging)){
						this.numberUsableReadsAfterMerging = analyzeSamtools.getNumReads();
					}
					if("".equals(this.mappedQF)
							|| OutputStrings.notFound.equals(this.mappedQF)
							|| OutputStrings.notRun.equals(this.mappedQF)){
						this.mappedQF = analyzeSamtools.getMappedQF();
					}
					if("".equals(this.endogenousDNAQF)
							|| OutputStrings.notFound.equals(this.endogenousDNAQF)
							|| OutputStrings.notRun.equals(this.endogenousDNAQF)){
						this.endogenousDNAQF = analyzeSamtools.getEndogenousDNAQF();
					}
					if("".equals(this.unmappedQF)
							|| OutputStrings.notFound.equals(this.unmappedQF)
							|| OutputStrings.notRun.equals(this.unmappedQF)){
						this.unmappedQF = analyzeSamtools.getUnmappedQF();
					}
					if("".equals(this.numReadsQF)
							|| OutputStrings.notFound.equals(this.numReadsQF)
							|| OutputStrings.notRun.equals(this.numReadsQF)){
						this.numReadsQF = analyzeSamtools.getNumReadsQF();
					}
				}else{
					this.totalNumMappedReadsBeforeDupRemoval = OutputStrings.notRun;
					this.endogenousDNA = OutputStrings.notRun;
					this.mappedQF = OutputStrings.notRun;
					this.endogenousDNAQF = OutputStrings.notRun;
					this.unmappedQF = OutputStrings.notRun;
					this.numReadsQF = OutputStrings.notRun;
					if("".equals(this.mttonucratio)){
						this.mttonucratio = OutputStrings.notRun;
						this.numMitochondrealReads = OutputStrings.notRun;
						this.avgCoverageOnMt = OutputStrings.notRun;
					}
					if("".equals(this.numberUsableReadsAfterMerging)){
						this.numberUsableReadsAfterMerging = OutputStrings.notRun;
					}
				}
				break;
			case DeDup:
				if(this.runPipelines.containsKey(Pipelines.DeDup)
						&& this.runPipelines.get(Pipelines.DeDup)){
					AnalyzeDeDup analyzeBetterRMDup = new AnalyzeDeDup(this.sampleFolder);
					this.numberDuplicatesRemoved = analyzeBetterRMDup.getDuplicatesRemoved();
					if("".equals(this.mttonucratio)
							|| OutputStrings.notFound.equals(this.mttonucratio)
							|| OutputStrings.notRun.equals(this.mttonucratio)){
						this.mttonucratio = analyzeBetterRMDup.getMtnucratio();
						this.numMitochondrealReads = analyzeBetterRMDup.getNumMitochondrealReads();
						this.avgCoverageOnMt = analyzeBetterRMDup.getAvgCoverageOnMt();
					}
				}else{
					this.numberDuplicatesRemoved = OutputStrings.notRun;
					if("".equals(this.mttonucratio)){
						this.mttonucratio = OutputStrings.notRun;
						this.numMitochondrealReads = OutputStrings.notRun;
						this.avgCoverageOnMt = OutputStrings.notRun;
					}
				}
				break;
			case Schmutzi:
				if(this.runPipelines.containsKey(Pipelines.Schmutzi)
						&& this.runPipelines.get(Pipelines.Schmutzi)){
					AnalyzeSchmutzi analyzeSchmutzi = new AnalyzeSchmutzi(this.sampleFolder);
					this.initialEstimation = analyzeSchmutzi.getInitialEstimation();
					this.initialLowerBound = analyzeSchmutzi.getInitialLowerBound();
					this.initialUpperBound = analyzeSchmutzi.getInitialUpperBound();
					this.finalEstimation = analyzeSchmutzi.getFinalEstimation();
					this.finalLowerBound = analyzeSchmutzi.getFinalLowerBound();
					this.finalUpperBound = analyzeSchmutzi.getFinalUpperBound();
				}else{
					this.initialEstimation = OutputStrings.notRun;
					this.initialLowerBound = OutputStrings.notRun;
					this.initialUpperBound = OutputStrings.notRun;
					this.finalEstimation = OutputStrings.notRun;
					this.finalLowerBound = OutputStrings.notRun;
					this.finalUpperBound = OutputStrings.notRun;
				}
			case QualiMap:
				if(this.runPipelines.containsKey(Pipelines.QualiMap)
						&& this.runPipelines.get(Pipelines.QualiMap)){
					AnalyzeQualiMap analyzeQualiMap = new AnalyzeQualiMap(this.sampleFolder);
					this.mappedReadsAfterDupRemoval = analyzeQualiMap.getMappedReads();
					this.gcContent = analyzeQualiMap.getGc();
					this.meanCoverage = analyzeQualiMap.getMeanCoverage();
					this.stdCoverage = analyzeQualiMap.getStdCoverage();
					this.coverage1x = analyzeQualiMap.getCoverage1x();
					this.coverage2x = analyzeQualiMap.getCoverage2x();
					this.coverage3x = analyzeQualiMap.getCoverage3x();
					this.coverage4x = analyzeQualiMap.getCoverage4x();
					this.coverage5x = analyzeQualiMap.getCoverage5x();
				}else{
					this.mappedReadsAfterDupRemoval = OutputStrings.notRun;
					this.gcContent = OutputStrings.notRun;
					this.meanCoverage = OutputStrings.notRun;
					this.stdCoverage = OutputStrings.notRun;
					this.coverage1x = OutputStrings.notRun;
					this.coverage2x = OutputStrings.notRun;
					this.coverage3x = OutputStrings.notRun;
					this.coverage4x = OutputStrings.notRun;
					this.coverage5x = OutputStrings.notRun;
				}
				break;
			case MapDamage:
				if(this.runPipelines.containsKey(Pipelines.MapDamage)
						&& this.runPipelines.get(Pipelines.MapDamage)){
					AnalyzeMapDamage analyzeMapDamage = new AnalyzeMapDamage(this.sampleFolder);
					this.damage3Prime1 = analyzeMapDamage.getFirstBase3();
					this.damage3Prime2 = analyzeMapDamage.getSecondBase3();
					this.damage5Prime1 = analyzeMapDamage.getFirstBase5();
					this.damage5Prime2 = analyzeMapDamage.getSecondBase5();
					this.meanFragmentLength = analyzeMapDamage.getMeanFragmentLength();
				}else{
					this.damage3Prime1 = OutputStrings.notRun;
					this.damage3Prime2 = OutputStrings.notRun;
					this.damage5Prime1 = OutputStrings.notRun;
					this.damage5Prime2 = OutputStrings.notRun;
					this.meanFragmentLength = OutputStrings.notRun;
				}
				break;
			case Preseq:
				break;
			case GATKBasics:
				break;
//			case GATKGenotyper:
//				if(this.runPipelines.containsKey(Pipelines.GATKGenotyper)
//						&& this.runPipelines.get(Pipelines.GATKGenotyper)){
//					AnalyzeGATKGenotyper analyzeGATKGenotyper = new AnalyzeGATKGenotyper(this.sampleFolder);
//					this.snpccNumOfPositionsFoundByGenotyper = analyzeGATKGenotyper.getPositionsFoundByGenotyper();
//					this.snpccNumOfCheckedPositionAgainsList = analyzeGATKGenotyper.getCheckedPositionsAgainstList();
//					this.snpccPercentageOfSNPPositionsCovered = analyzeGATKGenotyper.getSnpPercentage();
//					this.snpccTotalNumberOfHQPositions = analyzeGATKGenotyper.getNumHQPositions();
//					this.snpccTotalNumberOfLQPositions = analyzeGATKGenotyper.getNumLQPositions();
//					this.snpccCoverageOnHQSites = analyzeGATKGenotyper.getCoverageHQ();
//					this.snpccCoverageOnLQSites = analyzeGATKGenotyper.getCoverageLQ();
//					this.snpccCoverageOnVerifiedSites = analyzeGATKGenotyper.getCoverageVerified();
//				}else{					
//					this.snpccNumOfPositionsFoundByGenotyper = OutputStrings.notRun;
//					this.snpccNumOfCheckedPositionAgainsList = OutputStrings.notRun;
//					this.snpccPercentageOfSNPPositionsCovered = OutputStrings.notRun;
//					this.snpccTotalNumberOfHQPositions = OutputStrings.notRun;
//					this.snpccTotalNumberOfLQPositions = OutputStrings.notRun;
//					this.snpccCoverageOnHQSites = OutputStrings.notRun;
//					this.snpccCoverageOnLQSites = OutputStrings.notRun;
//					this.snpccCoverageOnVerifiedSites = OutputStrings.notRun;
//				}
//				break;
			case GATKVariantFilter:
				break;
			case VCF2Genome:
				if(this.runPipelines.containsKey(Pipelines.VCF2Genome)
						&& this.runPipelines.get(Pipelines.VCF2Genome)){
					AnalyzeVCF2Draft analyzeVCF2Draft = new AnalyzeVCF2Draft(sampleFolder);
					this.numSNPs = analyzeVCF2Draft.getNumSNPs();
				}else{
					this.numSNPs = OutputStrings.notRun;
				}
				break;
			default:
				break;
			}
		}
		//		if("".equals(this.perCentMappedReads)
		//				|| OutputStrings.notFound.equals(this.perCentMappedReads)
		//				|| OutputStrings.notRun.equals(this.perCentMappedReads)){
//		if(!"".equals(this.mappedReadsAfterDupRemoval)
//				&& !OutputStrings.notFound.equals(this.mappedReadsAfterDupRemoval)
//				&& !OutputStrings.notRun.equals(this.mappedReadsAfterDupRemoval)
//				&& !"".equals(this.numberUsableReadsAfterMerging)
//				&& !OutputStrings.notFound.equals(this.numberUsableReadsAfterMerging)
//				&& !OutputStrings.notRun.equals(this.numberUsableReadsAfterMerging)){
//			Double usable = Double.parseDouble(this.numberUsableReadsAfterMerging);
//			Double mapped = Double.parseDouble(this.mappedReadsAfterDupRemoval);
//			Double perCentMapped = (mapped / usable)*100;
//			this.perCentMappedReads = String.format(Locale.ENGLISH, "%.3f", perCentMapped);
//		}
		//		}
		// calculate the number of reads removed by RMDup
		if(!"".equals(this.mappedReadsAfterDupRemoval)
				&& !OutputStrings.notFound.equals(this.mappedReadsAfterDupRemoval)
				&& !OutputStrings.notRun.equals(this.mappedReadsAfterDupRemoval)
				&& !"".equals(this.totalNumMappedReadsBeforeDupRemoval)
				&& !OutputStrings.notFound.equals(this.totalNumMappedReadsBeforeDupRemoval)
				&& !OutputStrings.notRun.equals(this.totalNumMappedReadsBeforeDupRemoval)){
			Double mappedbefore = Double.parseDouble(this.totalNumMappedReadsBeforeDupRemoval);
			Double mappedafter = Double.parseDouble(this.mappedReadsAfterDupRemoval);
			Double removed = mappedbefore - mappedafter;
			this.numberDuplicatesRemoved = String.format(Locale.ENGLISH, "%d", removed.longValue());
		}
		if ("".equals(this.endogenousDNA)
				|| OutputStrings.notFound.equals(this.endogenousDNA)
				|| OutputStrings.notRun.equals(this.endogenousDNA)){
			if(!OutputStrings.notFound.equals(this.totalNumMappedReadsBeforeDupRemoval)
					&& !OutputStrings.notFound.equals(this.totalNumMappedReadsBeforeDupRemoval)
					&& !OutputStrings.notRun.equals(this.numberUsableReadsAfterMerging)
					&& !OutputStrings.notFound.equals(this.numberUsableReadsAfterMerging)){
				Double mappedReads = Double.parseDouble(this.totalNumMappedReadsBeforeDupRemoval);
				Double allReads = Double.parseDouble(this.numberUsableReadsAfterMerging);
				Double endogenuous = (mappedReads
						/ allReads)
						* 100.0;
				this.endogenousDNA = String.format("%.3f", endogenuous);
			}else{
				this.endogenousDNA = OutputStrings.notFound;
			}
		}
		if ("".equals(this.endogenousDNA)
				|| OutputStrings.notFound.equals(this.endogenousDNAQF)
				|| OutputStrings.notRun.equals(this.endogenousDNAQF)){
			if(!OutputStrings.notFound.equals(this.mappedQF)
					&& !OutputStrings.notFound.equals(this.mappedQF)
					&& !OutputStrings.notRun.equals(this.numReadsQF)
					&& !OutputStrings.notFound.equals(this.numReadsQF)){
				Double mappedReads = Double.parseDouble(this.mappedQF);
				Double allReads = Double.parseDouble(this.numberUsableReadsAfterMerging);
				Double endogenuous = (mappedReads
						/ allReads)
						* 100.0;
				this.endogenousDNA = String.format("%.3f", endogenuous);
			}else{
				this.endogenousDNAQF = OutputStrings.notFound;
			}
		}
		if(!OutputStrings.notFound.equals(this.totalNumMappedReadsBeforeDupRemoval)
				&& !OutputStrings.notFound.equals(this.mappedReadsAfterDupRemoval)
				&& !OutputStrings.notRun.equals(this.totalNumMappedReadsBeforeDupRemoval)
				&& !OutputStrings.notRun.equals(this.mappedReadsAfterDupRemoval)){
			Double clusterFactor = Double.parseDouble(this.totalNumMappedReadsBeforeDupRemoval)
					/ Double.parseDouble(this.mappedReadsAfterDupRemoval);
			this.clusterFactor = String.format(Locale.ENGLISH, "%.3f", clusterFactor);
		}else{
			this.clusterFactor = OutputStrings.notFound;
		}
		if(!OutputStrings.notFound.equals(this.mappedQF)
				&& !OutputStrings.notFound.equals(this.mappedReadsAfterDupRemoval)
				&& !OutputStrings.notRun.equals(this.mappedQF)
				&& !OutputStrings.notRun.equals(this.mappedReadsAfterDupRemoval)
				&& Double.parseDouble(this.mappedReadsAfterDupRemoval) > 0){
			Double clusterFactor = Double.parseDouble(this.mappedQF)
					/ Double.parseDouble(this.mappedReadsAfterDupRemoval);
			this.clusterFactor = String.format(Locale.ENGLISH, "%.3f", clusterFactor);
		}else{
			this.clusterFactorQF = OutputStrings.notFound;
		}
	}

	private boolean wasRunSuccessful(String s){
		return !("".equals(s)
				|| "NaN".equals(s)
				|| OutputStrings.notFound.equals(s)
				|| OutputStrings.notRun.equals(s));
	}

	/**
	 * update the HashMap containing the successful analyzed Parts of the pipeline
	 * @param HashMap containing the successful analyzed Parts of the pipeline
	 */
	public void analyzeSuccessful(HashMap<OutputFields, Boolean> outputMap){
		for(OutputFields key: outputMap.keySet()){
			if(!outputMap.get(key)){
				switch(key){
				case SampleNumber:
					outputMap.put(OutputFields.SampleNumber, wasRunSuccessful(this.sampleNumber));
					break;
				case SampleName:
					outputMap.put(OutputFields.SampleName, wasRunSuccessful(this.sampleName));
					break;
				case NumRawReads:
					outputMap.put(OutputFields.NumRawReads, wasRunSuccessful(this.numberRawReads));
					break;
				case NumUsableReadsAfterMerging:
					outputMap.put(OutputFields.NumUsableReadsAfterMerging, wasRunSuccessful(this.numberUsableReadsAfterMerging));
					break;
				case NumMergedReads:
					outputMap.put(OutputFields.NumMergedReads, wasRunSuccessful(this.numberMergedReads));
					break;
				case PerCentMergedReads:
					outputMap.put(OutputFields.PerCentMergedReads, wasRunSuccessful(this.perCentMergedReads));
					break;
				case TotalNumMappedReadsBeforeDupRemoval:
					outputMap.put(OutputFields.TotalNumMappedReadsBeforeDupRemoval, wasRunSuccessful(this.totalNumMappedReadsBeforeDupRemoval));
					break;
				case NumDupRemoved:
					outputMap.put(OutputFields.NumDupRemoved, wasRunSuccessful(this.numberDuplicatesRemoved));
					break;
				case MappedReadsAfterDupRemoval:
					outputMap.put(OutputFields.MappedReadsAfterDupRemoval, wasRunSuccessful(this.mappedReadsAfterDupRemoval));
					break;
//				case PerCentMappedReads:
//					outputMap.put(OutputFields.PerCentMappedReads, wasRunSuccessful(this.perCentMappedReads));
				case GCContent:
					outputMap.put(OutputFields.GCContent, wasRunSuccessful(this.gcContent));
					break;
				case EndogenousDNA:
					outputMap.put(OutputFields.EndogenousDNA, wasRunSuccessful(this.endogenousDNA));
					break;
				case ClusterFactor:
					outputMap.put(OutputFields.ClusterFactor, wasRunSuccessful(this.clusterFactor));
					break;
				case TotalNumMappedReadsBeforeDupRemovalQF:
					outputMap.put(OutputFields.TotalNumMappedReadsBeforeDupRemovalQF, wasRunSuccessful(this.mappedQF));
					break;
//				case EndogenousDNAQF:
//					outputMap.put(OutputFields.EndogenousDNAQF, wasRunSuccessful(this.endogenousDNAQF));
//					break;
//				case ClusterFactorQF:
//					outputMap.put(OutputFields.ClusterFactorQF, wasRunSuccessful(this.clusterFactorQF));
//					break;
				case MeanCoverage:
					outputMap.put(OutputFields.MeanCoverage, wasRunSuccessful(this.meanCoverage));
					break;
				case StdDevCoverage:
					outputMap.put(OutputFields.StdDevCoverage, wasRunSuccessful(this.stdCoverage));
					break;
				case Coverage1X:
					outputMap.put(OutputFields.Coverage1X, wasRunSuccessful(this.coverage1x));
					break;
				case Coverage2X:
					outputMap.put(OutputFields.Coverage2X, wasRunSuccessful(this.coverage2x));
					break;
				case Coverage3X:
					outputMap.put(OutputFields.Coverage3X, wasRunSuccessful(this.coverage3x));
					break;
				case Coverage4X:
					outputMap.put(OutputFields.Coverage4X, wasRunSuccessful(this.coverage4x));
					break;
				case Coverage5X:
					outputMap.put(OutputFields.Coverage5X, wasRunSuccessful(this.coverage5x));
					break;
				case NumSNPs:
					outputMap.put(OutputFields.NumSNPs, wasRunSuccessful(this.numSNPs));
					break;
				case NumReadsMT:
					outputMap.put(OutputFields.NumReadsMT, wasRunSuccessful(this.numMitochondrealReads));
					break;
				case AVGCovMT:
					outputMap.put(OutputFields.AVGCovMT, wasRunSuccessful(this.avgCoverageOnMt));
					break;
				case MTNUCRatio:
					outputMap.put(OutputFields.MTNUCRatio, wasRunSuccessful(this.mttonucratio));
					break;
//				case SNPCCNumPosFound:
//					outputMap.put(OutputFields.SNPCCNumPosFound, wasRunSuccessful(this.snpccNumOfPositionsFoundByGenotyper));
//					break;
//				case SNPCCNumCheckedPos:
//					outputMap.put(OutputFields.SNPCCNumCheckedPos, wasRunSuccessful(this.snpccNumOfCheckedPositionAgainsList));
//					break;
//				case SNPCCPerCentSNPCovered:
//					outputMap.put(OutputFields.SNPCCPerCentSNPCovered, wasRunSuccessful(this.snpccPercentageOfSNPPositionsCovered));
//					break;
//				case SNPCCTotalNumHQPos:
//					outputMap.put(OutputFields.SNPCCTotalNumHQPos, wasRunSuccessful(this.snpccTotalNumberOfHQPositions));
//					break;
//				case SNPCCTotalNumLQPos:
//					outputMap.put(OutputFields.SNPCCTotalNumLQPos, wasRunSuccessful(this.snpccTotalNumberOfLQPositions));
//					break;
//				case SNPCCCovHQ:
//					outputMap.put(OutputFields.SNPCCCovHQ, wasRunSuccessful(this.snpccCoverageOnHQSites));
//					break;
//				case SNPCCCovLQ:
//					outputMap.put(OutputFields.SNPCCCovLQ, wasRunSuccessful(this.snpccCoverageOnLQSites));
//					break;
//				case SNPCCCovVerified:
//					outputMap.put(OutputFields.SNPCCCovVerified, wasRunSuccessful(this.snpccCoverageOnVerifiedSites));
//					break;
				case Damage3Prime1st:
					outputMap.put(OutputFields.Damage3Prime1st, wasRunSuccessful(this.damage3Prime1));
					break;
				case Damage3Prime2nd:
					outputMap.put(OutputFields.Damage3Prime2nd, wasRunSuccessful(this.damage3Prime2));
					break;
				case Damage5Prime1st:
					outputMap.put(OutputFields.Damage5Prime1st, wasRunSuccessful(this.damage5Prime1));
					break;
				case Damage5Prime2nd:
					outputMap.put(OutputFields.Damage5Prime2nd, wasRunSuccessful(this.damage5Prime2));
					break;
				case DamageLgDistribution:
					outputMap.put(OutputFields.DamageLgDistribution, wasRunSuccessful(this.meanFragmentLength));
					break;
				case InitialContamination:
					outputMap.put(OutputFields.InitialContamination, wasRunSuccessful(this.initialEstimation));
					break;
				case InitialContaminationLowerBound:
					outputMap.put(OutputFields.InitialContaminationLowerBound, wasRunSuccessful(this.initialLowerBound));
					break;
				case InitialContaminationUpperBound:
					outputMap.put(OutputFields.InitialContaminationUpperBound, wasRunSuccessful(this.initialUpperBound));
					break;
				case FinalContamination:
					outputMap.put(OutputFields.FinalContamination, wasRunSuccessful(this.finalEstimation));
					break;
				case FinalContaminationLowerBound:
					outputMap.put(OutputFields.FinalContaminationLowerBound, wasRunSuccessful(this.finalLowerBound));
					break;
				case FinalContaminationUpperBound:
					outputMap.put(OutputFields.FinalContaminationUpperBound, wasRunSuccessful(this.finalUpperBound));
					break;
				}
			}
		}
	}

	public String generateOutputString(HashMap<OutputFields, Boolean> outputMap){
		StringBuffer result = new StringBuffer("");
		if(outputMap.containsKey(OutputFields.SampleNumber)
				&& outputMap.get(OutputFields.SampleNumber)){
			result.append(this.sampleNumber);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.SampleName)
				&& outputMap.get(OutputFields.SampleName)){
			result.append(this.sampleName);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumRawReads)
				&& outputMap.get(OutputFields.NumRawReads)){
			result.append(this.numberRawReads);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumMergedReads)
				&& outputMap.get(OutputFields.NumMergedReads)){
			result.append(this.numberMergedReads);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.PerCentMergedReads)
				&& outputMap.get(OutputFields.PerCentMergedReads)){
			result.append(this.perCentMergedReads);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumUsableReadsAfterMerging)
				&& outputMap.get(OutputFields.NumUsableReadsAfterMerging)){
			result.append(this.numberUsableReadsAfterMerging);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.TotalNumMappedReadsBeforeDupRemoval)
				&& outputMap.get(OutputFields.TotalNumMappedReadsBeforeDupRemoval)){
			result.append(this.totalNumMappedReadsBeforeDupRemoval);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.TotalNumMappedReadsBeforeDupRemovalQF)
				&& outputMap.get(OutputFields.TotalNumMappedReadsBeforeDupRemovalQF)){
			result.append(this.mappedQF);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumDupRemoved)
				&& outputMap.get(OutputFields.NumDupRemoved)){
			result.append(this.numberDuplicatesRemoved);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.MappedReadsAfterDupRemoval)
				&& outputMap.get(OutputFields.MappedReadsAfterDupRemoval)){
			result.append(this.mappedReadsAfterDupRemoval);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.EndogenousDNA)
				&& outputMap.get(OutputFields.EndogenousDNA)){
			result.append(this.endogenousDNA);
			result.append(",");
		}
//		if(outputMap.containsKey(OutputFields.PerCentMappedReads)
//				&& outputMap.get(OutputFields.PerCentMappedReads)){
//			result.append(this.perCentMappedReads);
//			result.append(",");
//		}
		if(outputMap.containsKey(OutputFields.ClusterFactor)
				&& outputMap.get(OutputFields.ClusterFactor)){
			result.append(this.clusterFactor);
			result.append(",");
		}
//		if(outputMap.containsKey(OutputFields.ClusterFactorQF)
//				&& outputMap.get(OutputFields.ClusterFactorQF)){
//			result.append(this.clusterFactorQF);
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.EndogenousDNAQF)
//				&& outputMap.get(OutputFields.EndogenousDNAQF)){
//			result.append(this.endogenousDNAQF);
//			result.append(",");
//		}
		if(outputMap.containsKey(OutputFields.MeanCoverage)
				&& outputMap.get(OutputFields.MeanCoverage)){
			result.append(this.meanCoverage);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.StdDevCoverage)
				&& outputMap.get(OutputFields.StdDevCoverage)){
			result.append(this.stdCoverage);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage1X)
				&& outputMap.get(OutputFields.Coverage1X)){
			result.append(this.coverage1x);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage2X)
				&& outputMap.get(OutputFields.Coverage2X)){
			result.append(this.coverage2x);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage3X)
				&& outputMap.get(OutputFields.Coverage3X)){
			result.append(this.coverage3x);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage4X)
				&& outputMap.get(OutputFields.Coverage4X)){
			result.append(this.coverage4x);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Coverage5X)
				&& outputMap.get(OutputFields.Coverage5X)){
			result.append(this.coverage5x);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumSNPs)
				&& outputMap.get(OutputFields.NumSNPs)){
			result.append(this.numSNPs);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.NumReadsMT)
				&& outputMap.get(OutputFields.NumReadsMT)){
			result.append(this.numMitochondrealReads);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.AVGCovMT)
				&& outputMap.get(OutputFields.AVGCovMT)){
			result.append(this.avgCoverageOnMt);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.MTNUCRatio)
				&& outputMap.get(OutputFields.MTNUCRatio)){
			result.append(this.mttonucratio);
			result.append(",");
		}
//		if(outputMap.containsKey(OutputFields.SNPCCNumPosFound)
//				&& outputMap.get(OutputFields.SNPCCNumPosFound)){
//			result.append(this.snpccNumOfPositionsFoundByGenotyper);
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCNumCheckedPos)
//				&& outputMap.get(OutputFields.SNPCCNumCheckedPos)){
//			result.append(this.snpccNumOfCheckedPositionAgainsList);
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCPerCentSNPCovered)
//				&& outputMap.get(OutputFields.SNPCCPerCentSNPCovered)){
//			result.append(this.snpccPercentageOfSNPPositionsCovered);
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCTotalNumHQPos)
//				&& outputMap.get(OutputFields.SNPCCTotalNumHQPos)){
//			result.append(this.snpccTotalNumberOfHQPositions);
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCTotalNumLQPos)
//				&& outputMap.get(OutputFields.SNPCCTotalNumLQPos)){
//			result.append(this.snpccTotalNumberOfLQPositions);
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCCovHQ)
//				&& outputMap.get(OutputFields.SNPCCCovHQ)){
//			result.append(this.snpccCoverageOnHQSites);
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCCovLQ)
//				&& outputMap.get(OutputFields.SNPCCCovLQ)){
//			result.append(this.snpccCoverageOnLQSites);
//			result.append(",");
//		}
//		if(outputMap.containsKey(OutputFields.SNPCCCovVerified)
//				&& outputMap.get(OutputFields.SNPCCCovVerified)){
//			result.append(this.snpccCoverageOnVerifiedSites);
//			result.append(",");
//		}
		if(outputMap.containsKey(OutputFields.Damage3Prime1st)
				&& outputMap.get(OutputFields.Damage3Prime1st)){
			result.append(this.damage3Prime1);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Damage3Prime2nd)
				&& outputMap.get(OutputFields.Damage3Prime2nd)){
			result.append(this.damage3Prime2);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Damage5Prime1st)
				&& outputMap.get(OutputFields.Damage5Prime1st)){
			result.append(this.damage5Prime1);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.Damage5Prime2nd)
				&& outputMap.get(OutputFields.Damage5Prime2nd)){
			result.append(this.damage5Prime2);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.DamageLgDistribution)
				&& outputMap.get(OutputFields.DamageLgDistribution)){
			result.append(this.meanFragmentLength);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.InitialContamination)
				&& outputMap.get(OutputFields.InitialContamination)){
			result.append(this.initialEstimation);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.InitialContaminationLowerBound)
				&& outputMap.get(OutputFields.InitialContaminationLowerBound)){
			result.append(this.initialLowerBound);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.InitialContaminationUpperBound)
				&& outputMap.get(OutputFields.InitialContaminationUpperBound)){
			result.append(this.initialUpperBound);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.FinalContamination)
				&& outputMap.get(OutputFields.FinalContamination)){
			result.append(this.finalEstimation);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.FinalContaminationLowerBound)
				&& outputMap.get(OutputFields.FinalContaminationLowerBound)){
			result.append(this.finalLowerBound);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.FinalContaminationUpperBound)
				&& outputMap.get(OutputFields.FinalContaminationUpperBound)){
			result.append(this.finalUpperBound);
			result.append(",");
		}
		if(outputMap.containsKey(OutputFields.GCContent)
				&& outputMap.get(OutputFields.GCContent)){
			result.append(this.gcContent);
			result.append(",");
		}
		return result.toString();
	}

	private void parseConfig() {
		File configFile = findConfig();
		if(this.foundConfig){
			AnalyzeConfigFile analyzeConfigFile = new AnalyzeConfigFile(configFile);
			if(analyzeConfigFile.isAnalysisSuccessful()){
				this.runPipelines = analyzeConfigFile.getRunPipelines();
			}else{
				setAllPipelinesToRun();
			}
		}else{
			setAllPipelinesToRun();
		}
	}

	private void setAllPipelinesToRun() {
		this.runPipelines = new HashMap<Pipelines, Boolean>();
		for(Pipelines p: Pipelines.values()){
			this.runPipelines.put(p, true);
		}
	}

	private File findConfig() {
		List<File> configFile = new LinkedList<File>();
		String[] names = sampleFolder.list();
		for(String name:names){
			File currFile = new File(sampleFolder+"/"+name);
			if(currFile.getName().endsWith(".xml")){
				configFile.add(currFile);
			}
		}
		// if there is no config file or if there are multiple config files, return null
		if(configFile.size() == 1){
			this.foundConfig = true;
			return configFile.get(0);
		}
		this.foundConfig=false;
		return null;
	}

	/**
	 * @return the version of fastQC
	 */
	public String getFastQCVersion(){
		return this.versionFastQC;
	}

	/**
	 * @return the version of ClipAndMerge
	 */
	public String getClipAndMergeVersion(){
		return this.versionClipAndMerge;
	}

	/**
	 * @return the version of the Mapper
	 */
	public String getMapperVersion(){
		return this.versionMapper;
	}

	/**
	 * @return the sampleNumber
	 */
	public String getSampleNumber() {
		return sampleNumber;
	}

	/**
	 * @return the sampleName
	 */
	public String getSampleName() {
		return sampleName;
	}

	/**
	 * @return the avgCoverageOnMt
	 */
	public String getAvgCoverageOnMt() {
		return avgCoverageOnMt;
	}

	/**
	 * @return the clusterFactor
	 */
	public String getClusterFactor() {
		return clusterFactor;
	}

	/**
	 * @return the clusterFactorQF
	 */
	public String getClusterFactorQF() {
		return clusterFactorQF;
	}

	/**
	 * @return the numberRawReads
	 */
	public String getNumberRawReads() {
		return numberRawReads;
	}

	/**
	 * @return the numberUsableReadsAfterMerging
	 */
	public String getNumberUsableReadsAfterMerging() {
		return numberUsableReadsAfterMerging;
	}

	/**
	 * @return the numberMergedReads
	 */
	public String getNumberMergedReads() {
		return numberMergedReads;
	}

	/**
	 * @return the perCentMergedReads
	 */
	public String getPerCentMergedReads() {
		return perCentMergedReads;
	}

	/**
	 * @return the totalNumMappedReadsBeforeDupRemoval
	 */
	public String getTotalNumMappedReadsBeforeDupRemoval() {
		return totalNumMappedReadsBeforeDupRemoval;
	}

	/**
	 * @return the numberDuplicatesRemoved
	 */
	public String getNumberDuplicatesRemoved() {
		return numberDuplicatesRemoved;
	}

	/**
	 * @return the mappedQF
	 */
	public String getMappedQF() {
		return mappedQF;
	}

	/**
	 * @return the mappedReadsAfterDupRemoval
	 */
	public String getMappedReadsAfterDupRemoval() {
		return mappedReadsAfterDupRemoval;
	}

	/**
	 * @return the endogenousDNA
	 */
	public String getEndogenousDNA() {
		return endogenousDNA;
	}

	/**
	 * @return the meanCoverage
	 */
	public String getMeanCoverage() {
		return meanCoverage;
	}

	/**
	 * @return the stdCoverage
	 */
	public String getStdCoverage() {
		return stdCoverage;
	}

	/**
	 * @return the coverage1x
	 */
	public String getCoverage1x() {
		return coverage1x;
	}

	/**
	 * @return the coverage2x
	 */
	public String getCoverage2x() {
		return coverage2x;
	}

	/**
	 * @return the coverage3x
	 */
	public String getCoverage3x() {
		return coverage3x;
	}

	/**
	 * @return the coverage4x
	 */
	public String getCoverage4x() {
		return coverage4x;
	}

	/**
	 * @return the coverage5x
	 */
	public String getCoverage5x() {
		return coverage5x;
	}

	/**
	 * @return the numMitochondrealReads
	 */
	public String getNumMitochondrealReads() {
		return numMitochondrealReads;
	}

	/**
	 * @return the mttonucratio
	 */
	public String getMttonucratio() {
		return mttonucratio;
	}

	/**
	 * @return the numSNPs
	 */
	public String getNumSNPs() {
		return numSNPs;
	}

	/**
	 * @return the damage3Prime1
	 */
	public String getDamage3Prime1() {
		return damage3Prime1;
	}

	/**
	 * @return the damage3Prime2
	 */
	public String getDamage3Prime2() {
		return damage3Prime2;
	}

	/**
	 * @return the damage5Prime1
	 */
	public String getDamage5Prime1() {
		return damage5Prime1;
	}

	/**
	 * @return the damage5Prime2
	 */
	public String getDamage5Prime2() {
		return damage5Prime2;
	}

	/**
	 * @return the meanFragmentLength
	 */
	public String getMeanFragmentLength() {
		return meanFragmentLength;
	}

	/**
	 * @return the gcContent
	 */
	public String getGcContent() {
		return gcContent;
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

	public String getValue(OutputFields currField) {
		String result = OutputStrings.notFound;
		try {
			java.lang.reflect.Method method = this.getClass().getMethod(currField.getGetterName());
			result = (String) method.invoke(this);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			result = OutputStrings.notFound;
		}
		return result;
	}

}
