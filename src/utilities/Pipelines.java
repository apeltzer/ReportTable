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
public enum Pipelines {
	FastQC {
		@Override 
		public String toString(){
//			return "0-FastQC";
			return "0-";
			}
		},
	ClipAndMerge {
			@Override
			public String toString(){
//				return "1-ClipAndMerge";
				return "1-";
			}
		},
	QualityTrimming {
		@Override
		public String toString(){
//			return "2-QualityTrimming";
			return "2-";
		}
	},
	Mapping {
		@Override 
		public String toString(){
//			return "3-Mapping";
			return "3-";
			}
	},
	Samtools {
		@Override 
		public String toString(){
//			return "4-Samtools";
			return "4-";
			}
		},
	DeDup {
		@Override 
		public String toString(){
//			return "5-BetterRMDup";
			return "5-";
			}
		},
	Schmutzi {
		@Override 
		public String toString(){
//				return "5-BetterRMDup";
		return "5-";
		}
	},
	QualiMap {
		@Override 
		public String toString(){
//			return "6-QualiMap";
			return "6-";
			}
		},
	MapDamage {
		@Override 
		public String toString(){
//			return "7-MapDamage";
			return "7-";
			}
		},
	Preseq {
		@Override 
		public String toString(){
//			return "8-Preseq";
			return "8-";
			}
		},
	GATKBasics {
		@Override 
		public String toString(){
//			return "9-GATKBasics";
			return "9-";
			}
		},
	GATKGenotyper {
		@Override 
		public String toString(){
//			return "10-GATKGenotyper";
			return "10-";
			}
		},
	GATKVariantFilter {
		@Override 
		public String toString(){
//			return "11-GATKVariantFilter";
			return "11-";
			}
		},
	VCF2Genome {
		@Override 
		public String toString(){
//			return "12-VCF2Genome";
			return "12-";
		}
	};
}
