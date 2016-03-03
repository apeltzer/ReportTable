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
package utilities;

/**
 * @author Alexander Seitz
 *
 */
public enum OutputFields {
	SampleNumber {
		@Override 
		public String toString(){
			return "Sample number";
		}
	},
	SampleName {
		@Override 
		public String toString(){
			return "Sample Name";
		}
	},
	NumRawReads {
		@Override 
		public String toString(){
			return "# of Raw Reads prior Clip & Merge (C&M)";
		}
	},
	NumUsableReadsAfterMerging {
		@Override 
		public String toString(){
			return "# reads after C&M prior mapping";
		}
	},
	NumMergedReads {
		@Override 
		public String toString(){
			return "# of Merged Reads";
		}
	},
	PerCentMergedReads {
		@Override 
		public String toString(){
			return "% Merged Reads";
		}
	},
	TotalNumMappedReadsBeforeDupRemoval {
		@Override 
		public String toString(){
			return "# mapped reads prior RMDup";
		}
	},
	TotalNumMappedReadsBeforeDupRemovalQF {
		@Override 
		public String toString(){
			return "# mapped reads prior RMDup QF";
		}
	},
	NumDupRemoved {
		@Override 
		public String toString(){
			return "# of Duplicates removed";
		}
	},
	MappedReadsAfterDupRemoval {
		@Override 
		public String toString(){
			return "Mapped Reads after RMDup";
		}
	},
	PerCentMappedReads {
		@Override 
		public String toString(){
			return "% mapped Reads after RMDup";
		}
	},
	GCContent {
		@Override 
		public String toString(){
			return "GC content";
		}
	},
	EndogenousDNA {
		@Override 
		public String toString(){
			return "Endogenous DNA (%)";
		}
	},
	ClusterFactor {
		@Override 
		public String toString(){
			return "Cluster Factor";
		}
	},
	EndogenousDNAQF {
		@Override 
		public String toString(){
			return "Endogenous DNA QF (%)";
		}
	},
	ClusterFactorQF {
		@Override 
		public String toString(){
			return "Cluster Factor QF";
		}
	},
	MeanCoverage {
		@Override 
		public String toString(){
			return "Mean Coverage";
		}
	},
	StdDevCoverage {
		@Override 
		public String toString(){
			return "std. dev. Coverage";
		}
	},
	Coverage1X {
		@Override 
		public String toString(){
			return "Coverage >= 1X";
		}
	},
	Coverage2X {
		@Override 
		public String toString(){
			return "Coverage >= 2X";
		}
	},
	Coverage3X {
		@Override 
		public String toString(){
			return "Coverage >= 3X";
		}
	},
	Coverage4X {
		@Override 
		public String toString(){
			return "Coverage >= 4X";
		}
	},
	Coverage5X {
		@Override 
		public String toString(){
			return "Coverage >= 5X";
		}
	},
	NumSNPs {
		@Override 
		public String toString(){
			return "# SNPs";
		}
	},
	NumReadsMT {
		@Override 
		public String toString(){
			return "# of reads on mitochondrium";
		}
	},
	AVGCovMT {
		@Override 
		public String toString(){
			return "AVG Coverage on mitochondrium";
		}
	},
	MTNUCRatio {
		@Override 
		public String toString(){
			return "MT/NUC Ratio";
		}
	},
//	SNPCCNumPosFound {
//		@Override 
//		public String toString(){
//			return "SNPCC: # of Positions found by genotyper";
//		}
//	},
//	SNPCCNumCheckedPos {
//		@Override 
//		public String toString(){
//			return "SNPCC: # of checked positions against list";
//		}
//	},
//	SNPCCPerCentSNPCovered {
//		@Override 
//		public String toString(){
//			return "SNPCC: Percentage of SNP Positions covered";
//		}
//	},
//	SNPCCTotalNumHQPos {
//		@Override 
//		public String toString(){
//			return "SNPCC: Total # of HQ Positions";
//		}
//	},
//	SNPCCTotalNumLQPos {
//		@Override 
//		public String toString(){
//			return "SNPCC: Total # of LQ Positions";
//		}
//	},
//	SNPCCCovHQ {
//		@Override 
//		public String toString(){
//			return "SNPCC: Coverage on HQ Sites";
//		}
//	},
//	SNPCCCovLQ {
//		@Override 
//		public String toString(){
//			return "SNPCC: Coverage on LQ Sites";
//		}
//	},
//	SNPCCCovVerified {
//		@Override 
//		public String toString(){
//			return "SNPCC: Coverage on verified Site";
//		}
//	},
	Damage3Prime1st{
		@Override
		public String toString(){
			return "DMG 1st Base 3'";
		}
	},
	Damage3Prime2nd{
		@Override
		public String toString(){
			return "DMG 2nd Base 3'";
		}
	},
	Damage5Prime1st{
		@Override
		public String toString(){
			return "DMG 1st Base 5'";
		}
	},
	Damage5Prime2nd{
		@Override
		public String toString(){
			return "DMG 2nd Base 5'";
		}
	}, 
	DamageLgDistribution{
		@Override
		public String toString(){
			return "average fragment length";
		}
	},
	InitialContamination{
		@Override
		public String toString(){
			return "Initial cont est";
		}
	},
	InitialContaminationLowerBound{
		@Override
		public String toString(){
			return "Initial cont est low";
		}
	},
	InitialContaminationUpperBound{
		@Override
		public String toString(){
			return "Initial cont est high";
		}
	},
	FinalContamination{
		@Override
		public String toString(){
			return "Final cont est";
		}
	},
	FinalContaminationLowerBound{
		@Override
		public String toString(){
			return "Final cont est low";
		}
	},
	FinalContaminationUpperBound{
		@Override
		public String toString(){
			return "Final cont est high";
		}
	};
}
