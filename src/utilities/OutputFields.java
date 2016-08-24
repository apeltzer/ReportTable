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

		@Override
		public String getGetterName() {
			return "getSampleNumber";
		}

		@Override
		public String getFieldType() {
			return ", html5_data: 'data-filter', filter_type: 'range_number'";
		}
	},
	SampleName {
		@Override
		public String toString(){
			return "Sample Name";
		}

		@Override
		public String getGetterName() {
			return "getSampleName";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'text'";
		}
	},
	NumRawReads {
		@Override
		public String toString(){
			return "# of Raw Reads prior Clip & Merge (C&M)";
		}

		@Override
		public String getGetterName() {
			return "getNumberRawReads";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	NumUsableReadsAfterMerging {
		@Override
		public String toString(){
			return "# reads after C&M prior mapping";
		}

		@Override
		public String getGetterName() {
			return "getNumberUsableReadsAfterMerging";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	NumMergedReads {
		@Override
		public String toString(){
			return "# of Merged Reads";
		}

		@Override
		public String getGetterName() {
			return "getNumberMergedReads";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	PerCentMergedReads {
		@Override
		public String toString(){
			return "% Merged Reads";
		}

		@Override
		public String getGetterName() {
			return "getPerCentMergedReads";
		}

		@Override
		public String getFieldType() {
			return ", html5_data: 'data-filter', filter_type: 'range_number'";
		}
	},
  TotalNumMappedReadsBeforeDupRemovalGenome {
    @Override
    public String toString(){
      return "# mapped reads prior RMDup (genome)";
    }

    @Override
    public String getGetterName() {
      return "getTotalNumMappedReadsBeforeDupRemovalGenome";
    }

    @Override
    public String getFieldType() {
      return ", filter_type: 'range_number'";
    }
  },
	TotalNumMappedReadsBeforeDupRemoval {
		@Override
		public String toString(){
			return "# mapped reads prior RMDup";
		}

		@Override
		public String getGetterName() {
			return "getTotalNumMappedReadsBeforeDupRemoval";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	TotalNumMappedReadsBeforeDupRemovalQF {
		@Override
		public String toString(){
			return "# mapped reads prior RMDup QF";
		}

		@Override
		public String getGetterName() {
			return "getMappedQF";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	NumDupRemoved {
		@Override
		public String toString(){
			return "# of Duplicates removed";
		}

		@Override
		public String getGetterName() {
			return "getNumberDuplicatesRemoved";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	MappedReadsAfterDupRemoval {
		@Override
		public String toString(){
			return "Mapped Reads after RMDup";
		}

		@Override
		public String getGetterName() {
			return "getMappedReadsAfterDupRemoval";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	EndogenousDNA {
		@Override
		public String toString(){
			return "Endogenous DNA (%)";
		}

		@Override
		public String getGetterName() {
			return "getEndogenousDNA";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
  EndogenousDNAQF {
		@Override
		public String toString(){
			return "Endogenous DNA QF (%)";
		}

		@Override
		public String getGetterName() {
			return "getEndogenousDNAQF";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	ClusterFactor {
		@Override
		public String toString(){
			return "Cluster Factor";
		}

		@Override
		public String getGetterName() {
			return "getClusterFactor";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	MeanCoverage {
		@Override
		public String toString(){
			return "Mean Coverage";
		}

		@Override
		public String getGetterName() {
			return "getMeanCoverage";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	StdDevCoverage {
		@Override
		public String toString(){
			return "std. dev. Coverage";
		}

		@Override
		public String getGetterName() {
			return "getStdCoverage";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	Coverage1X {
		@Override
		public String toString(){
			return "Coverage >= 1X";
		}

		@Override
		public String getGetterName() {
			return "getCoverage1x";
		}

		@Override
		public String getFieldType() {
			return ", html5_data: 'data-filter', filter_type: 'range_number'";
		}
	},
	Coverage2X {
		@Override
		public String toString(){
			return "Coverage >= 2X";
		}

		@Override
		public String getGetterName() {
			return "getCoverage2x";
		}

		@Override
		public String getFieldType() {
			return ", html5_data: 'data-filter', filter_type: 'range_number'";
		}
	},
	Coverage3X {
		@Override
		public String toString(){
			return "Coverage >= 3X";
		}

		@Override
		public String getGetterName() {
			return "getCoverage3x";
		}

		@Override
		public String getFieldType() {
			return ", html5_data: 'data-filter', filter_type: 'range_number'";
		}
	},
	Coverage4X {
		@Override
		public String toString(){
			return "Coverage >= 4X";
		}

		@Override
		public String getGetterName() {
			return "getCoverage4x";
		}

		@Override
		public String getFieldType() {
			return ", html5_data: 'data-filter', filter_type: 'range_number'";
		}
	},
	Coverage5X {
		@Override
		public String toString(){
			return "Coverage >= 5X";
		}

		@Override
		public String getGetterName() {
			return "getCoverage5x";
		}

		@Override
		public String getFieldType() {
			return ", html5_data: 'data-filter', filter_type: 'range_number'";
		}
	},
	NumSNPs {
		@Override
		public String toString(){
			return "# SNPs";
		}

		@Override
		public String getGetterName() {
			return "getNumSNPs";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	NumReadsMT {
		@Override
		public String toString(){
			return "# of reads on mitochondrium";
		}

		@Override
		public String getGetterName() {
			return "getNumMitochondrealReads";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	AVGCovMT {
		@Override
		public String toString(){
			return "AVG Coverage on mitochondrium";
		}

		@Override
		public String getGetterName() {
			return "getAvgCoverageOnMt";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	MTNUCRatio {
		@Override
		public String toString(){
			return "MT/NUC Ratio";
		}

		@Override
		public String getGetterName() {
			return "getMttonucratio";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	Damage3Prime1st{
		@Override
		public String toString(){
			return "DMG 1st Base 3'";
		}

		@Override
		public String getGetterName() {
			return "getDamage3Prime1";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	Damage3Prime2nd{
		@Override
		public String toString(){
			return "DMG 2nd Base 3'";
		}

		@Override
		public String getGetterName() {
			return "getDamage3Prime2";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	Damage5Prime1st{
		@Override
		public String toString(){
			return "DMG 1st Base 5'";
		}

		@Override
		public String getGetterName() {
			return "getDamage5Prime1";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	Damage5Prime2nd{
		@Override
		public String toString(){
			return "DMG 2nd Base 5'";
		}

		@Override
		public String getGetterName() {
			return "getDamage5Prime2";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	DamageLgDistribution{
		@Override
		public String toString(){
			return "average fragment length";
		}

		@Override
		public String getGetterName() {
			return "getMeanFragmentLength";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	InitialContamination{
		@Override
		public String toString(){
			return "Initial cont est";
		}

		@Override
		public String getGetterName() {
			return "getInitialEstimation";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	InitialContaminationLowerBound{
		@Override
		public String toString(){
			return "Initial cont est low";
		}

		@Override
		public String getGetterName() {
			return "getInitialLowerBound";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	InitialContaminationUpperBound{
		@Override
		public String toString(){
			return "Initial cont est high";
		}

		@Override
		public String getGetterName() {
			return "getInitialUpperBound";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	FinalContamination{
		@Override
		public String toString(){
			return "Final cont est";
		}

		@Override
		public String getGetterName() {
			return "getFinalEstimation";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	FinalContaminationLowerBound{
		@Override
		public String toString(){
			return "Final cont est low";
		}

		@Override
		public String getGetterName() {
			return "getFinalLowerBound";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	FinalContaminationUpperBound{
		@Override
		public String toString(){
			return "Final cont est high";
		}

		@Override
		public String getGetterName() {
			return "getFinalUpperBound";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	FinalContaminationMtnotpredc{
		@Override
		public String toString(){
			return "Final cont est no PredC";
		}

		@Override
		public String getGetterName() {
			return "getFinalEstimationMtnotpredc";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	FinalContaminationLowerBoundMtnotpredc{
		@Override
		public String toString(){
			return "Final cont est low no PredC";
		}

		@Override
		public String getGetterName() {
			return "getFinalLowerBoundMtnotpredc";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	FinalContaminationUpperBoundMtnotpredc{
		@Override
		public String toString(){
			return "Final cont est high no PredC";
		}

		@Override
		public String getGetterName() {
			return "getFinalUpperBoundMtnotpredc";
		}

		@Override
		public String getFieldType() {
			return ", filter_type: 'range_number'";
		}
	},
	GCContent {
		@Override
		public String toString(){
			return "GC content";
		}

		@Override
		public String getGetterName() {
			return "getGcContent";
		}

		@Override
		public String getFieldType() {
			return ", html5_data: 'data-filter', filter_type: 'range_number'";
		}
	};

	public abstract String getGetterName();
	public abstract String getFieldType();
}
