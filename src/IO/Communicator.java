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

package IO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 01.10.13
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class Communicator implements Serializable {
    /**
     * I/O Required Information
     */
    private String GUI_filepathinput;
    private String GUI_filepathresults;
    private ArrayList<String> GUI_inputfiles;
    private String GUI_reference;
    private String GUI_resultspath;
    private String GUI_GATKSNPreference;

    /**
     * Parameters required by separate tools
     */

    /**
     * General Configuration
     */
    private String cpucores;
    private String maxmemory;


    /**
     * 0-FastQC
     */
    private String fastqc_advanced;

    /**
     * 1-ClipAndMerge
     */
    private String merge_fwadaptor;
    private String merge_bwadaptor;
    private String merge_advanced;
    private boolean merge_only_clipping;
    private String merge_type = "";
    private String merge_barcode3p = "0";
    private String merge_barcode5p = "0";

    /**
     * 2-QualityTrimmer
     */
    private int quality_minreadquality;
    private int quality_readlength;
    private String quality_advanced;


    /**
     * 3-Mapper
     */
    private String mapper_readgroup;
    private String mapper_seedlength;
    private String mapper_mismatches;
    private String mapper_advanced;
    private boolean mapper_filter_unmapped;
    private String mapper_to_use;
    private String unmapped_reads_to_merge;
    private String mapper_mapquality_filter;
    private String filter_for_mt;

    //BWA Mem Mode
    private String mapper_bwamem_seedlength;
    private String mapper_bwamem_bandwidth;
    private String mapper_bwamem_advanced;

    /**
     * 4-CircularMapper
     */
    private int CM_elongationfac;
    private String CM_advancedparams;
    private String CM_referencemt_elong;
    private String CM_realigned;
    private String CM_remapped;
    private String CM_tobemapped_against;


    /**
     * 5-RMDup
     */

    private boolean rmdup_run;
    private boolean markdup_run;

    /**
     * QualiMap
     */

    private String bedfile;

    /**
     * Schmutzi Run
     */

    private String schmutzi_library_type;
    private String schmutzi_mt_ref;
    private String schmutzi_mt_refDB;
    private boolean schmutzi_run;


    /**
     * 6-MapDamage Configuration
     */
    private String mapdamage_length;
    private String mapdamage_advanced;
    private String mapdamage_onlymerged;
    private String DNA_damage_calculator_to_use;
    private boolean damageProfilerOnlyMerged;

    /**
     * 7-Preseq
     */
    private int preseq_ccurve_stepsize;
    private int preseq_lcextrap_stepsize;
    private String preseq_lcextrap_extrapolationsize;
    private int preseq_lcextrap_bootstraps;
    private boolean preseq_lcextrap_quickmode_nobootstrapping;


    /**
     * 8-GATK-SNP Calling
     */
    private String gatk_standard_call_confidence;
    private String gatk_standard_emit_confidence;
    private String gatk_downsampling;
    private String gatk_caller;
    private String gatk_snp_advanced;
    private boolean gatk_emit_all_sites;
    private boolean gatk_emit_all_confident_sites;
    private String snpcapture_type;
    private String gatk_ploidy;

    /**
     * ANGSD Genotype Likelihoods
     */

    private String angsd_glf_model;
    private String angsd_glm_outformat;
    private String angsd_fasta_callmethod;
    private boolean angsd_create_fasta;

    /**
     * 9-GATK SNP Filtering
     */

    private String gatk_snpfilter_advanced;
    private String gatk_variantfilter_qualityfilter;
    private String gatk_variantfilter_coveragefilter;

    /**
     * 10-VCF2Genome
     */

    private int vcf2draft_minquality;
    private String vcf2draft_advanced;
    private int vcf2dmincov;
    private double vcf2dminsnpall;
    private String vcf2dname;


    /**
     * Boolean Variables to determine how to run
     * distinct parts of the pipeline
     */

    private boolean referenceselected;
    private int conflict;
    private boolean organism;
    private boolean udgtreatment;
    private boolean pairmenttype;
    private boolean dbsnpreference;
    private boolean organismage;
    private boolean inputfastqselected;
    private boolean snpcapturedata;
    private boolean input_already_merged;
    private boolean merge_bam_files;


    /**
     * Variables which parts of the pipeline have been selected to be run
     */

    private boolean run_fastqc;
    private boolean run_qualityfilter;
    private boolean run_clipandmerge;
    private boolean run_mapping;
    private boolean run_coveragecalc;
    private boolean run_mapdamage;
    private boolean run_complexityestimation;
    private boolean run_gatksnpcalling;
    private boolean run_gatksnpfiltering;
    private boolean run_vcf2draft;
    private boolean run_cleanup;
    private boolean run_reportgenerator;
    private boolean run_mapping_extractmappedandunmapped;
    private boolean run_mt_capture_mode;




    public Communicator() {
        //Setting up default values for all the values, except input GUI_inputfiles.
        cpucores = "4";
        CM_elongationfac = 500;
        CM_tobemapped_against = "chrMT";
        maxmemory = "64";
        quality_minreadquality = 20;
        quality_readlength = 30;
        merge_fwadaptor = "AGATCGGAAGAGCACACGTCTGAACTCCAGTCAC";
        merge_bwadaptor = "AGATCGGAAGAGCGTCGTGTAGGGAAAGAGTGTA";
        mapper_readgroup = "@RG\tID:ILLUMINA-52179E_0039_FC62HDBAAXX_1_1\tSM:48_2\tPL:illumina";
        mapper_seedlength = "32";
        mapper_mapquality_filter = "0";
        mapper_mismatches = "0.01";
        mapper_bwamem_bandwidth = "100";
        mapper_bwamem_seedlength = "19";
        mapper_bwamem_advanced = "";
        filter_for_mt = "chrMT";
        mapdamage_length = "100";
        schmutzi_library_type = "DOUBLE";
        bedfile = "/opt/snpcc/390KPos.bed";
        gatk_standard_call_confidence = "50";
        gatk_standard_emit_confidence = "10";
        gatk_variantfilter_qualityfilter = "30";
        gatk_variantfilter_coveragefilter = "0";
        preseq_ccurve_stepsize = 1000;
        preseq_lcextrap_stepsize = 1000;
        preseq_lcextrap_bootstraps = 100;
        preseq_lcextrap_extrapolationsize = "10000000000";
        gatk_downsampling = "250";
        angsd_glf_model = "1";
        angsd_glm_outformat = "1";
        angsd_fasta_callmethod = "1";
        vcf2draft_minquality = 30;
        vcf2dminsnpall = 0.9;
        vcf2dmincov = 5;
        referenceselected = false;
        gatk_caller = "HaplotypeCaller";
        vcf2dname = "VCF2D_Output";
        conflict = 0;
        CM_advancedparams = "";
        quality_advanced = "";
        fastqc_advanced = "";
        gatk_snp_advanced = "";
        mapdamage_advanced = "";
        mapper_advanced = "";
        merge_advanced = "";
        vcf2draft_advanced = "";
        gatk_snpfilter_advanced = "";
        gatk_ploidy = "2";
        snpcapture_type = "390K";
        damageProfilerOnlyMerged = false;



    }

    public int getPreseq_ccurve_stepsize() {
        return preseq_ccurve_stepsize;
    }

    public void setPreseq_ccurve_stepsize(int preseq_ccurve_stepsize) {
        this.preseq_ccurve_stepsize = preseq_ccurve_stepsize;
    }

    public String getGatk_caller() {
        return gatk_caller;
    }

    public void setGatk_caller(String gatk_caller) {
        this.gatk_caller = gatk_caller;
    }

    public boolean isInputfastqselected() {
        return inputfastqselected;
    }

    public void setInputfastqselected(boolean inputfastqselected) {
        this.inputfastqselected = inputfastqselected;
    }

    public boolean isReferenceselected() {
        return referenceselected;
    }

    public void setReferenceselected(boolean referenceselected) {
        this.referenceselected = referenceselected;
    }

    public String getGatk_variantfilter_qualityfilter() {
        return gatk_variantfilter_qualityfilter;
    }

    public void setGatk_variantfilter_qualityfilter(String gatk_variantfilter_qualityfilter) {
        this.gatk_variantfilter_qualityfilter = gatk_variantfilter_qualityfilter;
    }

    public boolean isInput_already_merged() {
        return input_already_merged;
    }

    public void setInput_already_merged(boolean input_already_merged) {
        this.input_already_merged = input_already_merged;
    }

    public String getGatk_variantfilter_coveragefilter() {
        return gatk_variantfilter_coveragefilter;
    }

    public void setGatk_variantfilter_coveragefilter(String gatk_variantfilter_coveragefilter) {
        this.gatk_variantfilter_coveragefilter = gatk_variantfilter_coveragefilter;
    }

    public int getVcf2draft_minquality() {
        return vcf2draft_minquality;
    }

    public void setVcf2draft_minquality(int vcf2draft_minquality) {
        this.vcf2draft_minquality = vcf2draft_minquality;
    }

    public String getVcf2dname() {
        return vcf2dname;
    }

    public void setVcf2dname(String vcf2dname) {
        this.vcf2dname = vcf2dname;
    }

    public ArrayList<String> getGUI_inputfiles() {
        return GUI_inputfiles;
    }

    public void setGUI_inputfiles(ArrayList<String> GUI_inputfiles) {
        this.GUI_inputfiles = GUI_inputfiles;
    }

    public String getGUI_reference() {
        return GUI_reference;
    }

    public void setGUI_reference(String GUI_reference) {
        this.GUI_reference = GUI_reference;
    }

    public int getQuality_minreadquality() {
        return quality_minreadquality;
    }

    public void setQuality_minreadquality(int quality_minreadquality) {
        this.quality_minreadquality = quality_minreadquality;
    }

    public int getQuality_readlength() {
        return quality_readlength;
    }

    public void setQuality_readlength(int quality_readlength) {
        this.quality_readlength = quality_readlength;
    }

    public String getMerge_fwadaptor() {
        return merge_fwadaptor;
    }

    public void setMerge_fwadaptor(String merge_fwadaptor) {
        this.merge_fwadaptor = merge_fwadaptor;
    }

    public String getMerge_bwadaptor() {
        return merge_bwadaptor;
    }

    public void setMerge_bwadaptor(String merge_bwadaptor) {
        this.merge_bwadaptor = merge_bwadaptor;
    }

    public String getCpucores() {
        return cpucores;
    }

    public void setCpucores(String cpucores) {
        this.cpucores = cpucores;
    }

    public String getMapper_readgroup() {
        return mapper_readgroup;
    }

    public void setMapper_readgroup(String mapper_readgroup) {
        this.mapper_readgroup = mapper_readgroup;
    }

    public String getMapdamage_length() {
        return mapdamage_length;
    }

    public void setMapdamage_length(String mapdamage_length) {
        this.mapdamage_length = mapdamage_length;
    }

    public String getGUI_GATKSNPreference() {
        return GUI_GATKSNPreference;
    }

    public void setGUI_GATKSNPreference(String GUI_GATKSNPreference) {
        this.GUI_GATKSNPreference = GUI_GATKSNPreference;
    }

    public String getGatk_standard_call_confidence() {
        return gatk_standard_call_confidence;
    }

    public void setGatk_standard_call_confidence(String gatk_standard_call_confidence) {
        this.gatk_standard_call_confidence = gatk_standard_call_confidence;
    }

    public String getGatk_standard_emit_confidence() {
        return gatk_standard_emit_confidence;
    }

    public void setGatk_standard_emit_confidence(String gatk_standard_emit_confidence) {
        this.gatk_standard_emit_confidence = gatk_standard_emit_confidence;
    }

    public String getGatk_downsampling() {
        return gatk_downsampling;
    }

    public void setGatk_downsampling(String gatk_downsampling) {
        this.gatk_downsampling = gatk_downsampling;
    }

    public int getCM_elongationfac() {
        return CM_elongationfac;
    }

    public void setCM_elongationfac(int CM_elongationfac) {
        this.CM_elongationfac = CM_elongationfac;
    }


    public int getVcf2dmincov() {
        return vcf2dmincov;
    }

    public void setVcf2dmincov(int vcf2dmincov) {
        this.vcf2dmincov = vcf2dmincov;
    }

    public String getMaxmemory() {
        return maxmemory;
    }

    public void setMaxmemory(String maxmemory) {
        this.maxmemory = maxmemory;
    }

    public double getVcf2dminsnpall() {
        return vcf2dminsnpall;
    }

    public void setVcf2dminsnpall(double vcf2dminsnpall) {
        this.vcf2dminsnpall = vcf2dminsnpall;
    }

    public String getGUI_resultspath() {
        return GUI_resultspath;
    }

    public void setGUI_resultspath(String GUI_resultspath) {
        this.GUI_resultspath = GUI_resultspath;
    }

    public int getConflict() {
        return conflict;
    }

    public void setConflict(int conflict) {
        this.conflict = conflict;
    }

    public String getFastqc_advanced() {
        return fastqc_advanced;
    }

    public void setFastqc_advanced(String fastqc_advanced) {
        this.fastqc_advanced = fastqc_advanced;
    }

    public String getMerge_advanced() {
        return merge_advanced;
    }

    public void setMerge_advanced(String merge_advanced) {
        this.merge_advanced = merge_advanced;
    }

    public String getMapper_advanced() {
        return mapper_advanced;
    }

    public void setMapper_advanced(String mapper_advanced) {
        this.mapper_advanced = mapper_advanced;
    }

    public String getMapdamage_advanced() {
        return mapdamage_advanced;
    }

    public void setMapdamage_advanced(String mapdamage_advanced) {
        this.mapdamage_advanced = mapdamage_advanced;
    }

    public String getGatk_snp_advanced() {
        return gatk_snp_advanced;
    }

    public void setGatk_snp_advanced(String gatk_snp_advanced) {
        this.gatk_snp_advanced = gatk_snp_advanced;
    }

    public String getCM_advancedparams() {
        return CM_advancedparams;
    }

    public void setCM_advancedparams(String CM_advancedparams) {
        this.CM_advancedparams = CM_advancedparams;
    }

    public String getMapper_mapquality_filter() {
        return mapper_mapquality_filter;
    }

    public void setMapper_mapquality_filter(String mapper_mapquality_filter) {
        this.mapper_mapquality_filter = mapper_mapquality_filter;
    }

    public String getVcf2draft_advanced() {
        return vcf2draft_advanced;
    }

    public void setVcf2draft_advanced(String vcf2draft_advanced) {
        this.vcf2draft_advanced = vcf2draft_advanced;
    }

    public String getQuality_advanced() {
        return quality_advanced;
    }

    public void setQuality_advanced(String quality_advanced) {
        this.quality_advanced = quality_advanced;
    }

    public String getGatk_snpfilter_advanced() {
        return gatk_snpfilter_advanced;
    }

    public void setGatk_snpfilter_advanced(String gatk_snpfilter_advanced) {
        this.gatk_snpfilter_advanced = gatk_snpfilter_advanced;
    }

    public boolean isOrganism() {
        return organism;
    }

    public void setOrganism(boolean organism) {
        this.organism = organism;
    }

    public boolean isUdgtreatment() {
        return udgtreatment;
    }

    public void setUdgtreatment(boolean udgtreatment) {
        this.udgtreatment = udgtreatment;
    }

    public boolean isPairmenttype() {
        return pairmenttype;
    }

    public void setPairmenttype(boolean pairmenttype) {
        this.pairmenttype = pairmenttype;
    }

    public boolean isDbsnpreference() {
        return dbsnpreference;
    }

    public void setDbsnpreference(boolean dbsnpreference) {
        this.dbsnpreference = dbsnpreference;
    }

    public boolean isOrganismage() {
        return organismage;
    }

    public void setOrganismage(boolean organismage) {
        this.organismage = organismage;
    }

    public boolean isRun_fastqc() {
        return run_fastqc;
    }

    public void setRun_fastqc(boolean run_fastqc) {
        this.run_fastqc = run_fastqc;
    }

    public boolean isRun_qualityfilter() {
        return run_qualityfilter;
    }

    public void setRun_qualityfilter(boolean run_qualityfilter) {
        this.run_qualityfilter = run_qualityfilter;
    }

    public boolean isRun_clipandmerge() {
        return run_clipandmerge;
    }

    public void setRun_clipandmerge(boolean run_clipandmerge) {
        this.run_clipandmerge = run_clipandmerge;
    }

    public boolean isRun_mapping() {
        return run_mapping;
    }

    public void setRun_mapping(boolean run_mapping) {
        this.run_mapping = run_mapping;
    }

    public boolean isRun_coveragecalc() {
        return run_coveragecalc;
    }

    public void setRun_coveragecalc(boolean run_coveragecalc) {
        this.run_coveragecalc = run_coveragecalc;
    }

    public boolean isRun_mapdamage() {
        return run_mapdamage;
    }

    public void setRun_mapdamage(boolean run_mapdamage) {
        this.run_mapdamage = run_mapdamage;
    }

    public boolean isRun_complexityestimation() {
        return run_complexityestimation;
    }

    public void setRun_complexityestimation(boolean run_complexityestimation) {
        this.run_complexityestimation = run_complexityestimation;
    }

    public boolean isRun_gatksnpcalling() {
        return run_gatksnpcalling;
    }

    public void setRun_gatksnpcalling(boolean run_gatksnpcalling) {
        this.run_gatksnpcalling = run_gatksnpcalling;
    }

    public boolean isRun_gatksnpfiltering() {
        return run_gatksnpfiltering;
    }

    public void setRun_gatksnpfiltering(boolean run_gatksnpfiltering) {
        this.run_gatksnpfiltering = run_gatksnpfiltering;
    }

    public boolean isRun_vcf2draft() {
        return run_vcf2draft;
    }

    public void setRun_vcf2draft(boolean run_vcf2draft) {
        this.run_vcf2draft = run_vcf2draft;
    }

    public boolean isRun_cleanup() {
        return run_cleanup;
    }

    public void setRun_cleanup(boolean run_cleanup) {
        this.run_cleanup = run_cleanup;
    }

    public String getCM_referencemt_elong() {
        return CM_referencemt_elong;
    }

    public void setCM_referencemt_elong(String CM_referencemt_elong) {
        this.CM_referencemt_elong = CM_referencemt_elong;
    }

    public String getGUI_filepathinput() {
        return GUI_filepathinput;
    }

    public void setGUI_filepathinput(String GUI_filepathinput) {
        this.GUI_filepathinput = GUI_filepathinput;
    }

    public String getGUI_filepathresults() {
        return GUI_filepathresults;
    }

    public void setGUI_filepathresults(String GUI_filepathresults) {
        this.GUI_filepathresults = GUI_filepathresults;
    }

    public String getCM_realigned() {
        return CM_realigned;
    }

    public void setCM_realigned(String CM_realigned) {
        this.CM_realigned = CM_realigned;
    }

    public String getCM_remapped() {
        return CM_remapped;
    }

    public void setCM_remapped(String CM_remapped) {
        this.CM_remapped = CM_remapped;
    }

    public String getMapdamage_onlymerged() {
        return mapdamage_onlymerged;
    }

    public void setMapdamage_onlymerged(String mapdamage_onlymerged) {
        this.mapdamage_onlymerged = mapdamage_onlymerged;
    }

    public boolean isDamageProfilerOnlyMerged() {
        return damageProfilerOnlyMerged;
    }

    public void setDamageProfilerOnlyMerged(boolean damageProfilerOnlyMerged) {
        this.damageProfilerOnlyMerged = damageProfilerOnlyMerged;
    }

    public boolean isMapper_filter_unmapped() {
        return mapper_filter_unmapped;
    }

    public void setMapper_filter_unmapped(boolean mapper_filter_unmapped) {
        this.mapper_filter_unmapped = mapper_filter_unmapped;
    }

    public boolean isGatk_emit_all_sites() {
        return gatk_emit_all_sites;
    }

    public void setGatk_emit_all_sites(boolean gatk_emit_all_sites) {
        this.gatk_emit_all_sites = gatk_emit_all_sites;
    }

    public boolean isGatk_emit_all_confident_sites() {
        return gatk_emit_all_confident_sites;
    }

    public void setGatk_emit_all_confident_sites(boolean gatk_emit_all_confident_sites) {
        this.gatk_emit_all_confident_sites = gatk_emit_all_confident_sites;
    }

    public String getMapper_to_use() {
        return mapper_to_use;
    }

    public void setMapper_to_use(String mapper_to_use) {
        this.mapper_to_use = mapper_to_use;
    }

    public String getUnmapped_reads_to_merge() {
        return unmapped_reads_to_merge;
    }

    public void setUnmapped_reads_to_merge(String unmapped_reads_to_merge) {
        this.unmapped_reads_to_merge = unmapped_reads_to_merge;
    }

    public boolean isRmdup_run() {
        return rmdup_run;
    }

    public void setRmdup_run(boolean rmdup_run) {
        this.rmdup_run = rmdup_run;
    }

    public boolean isSnpcapturedata() {
        return snpcapturedata;
    }

    public void setSnpcapturedata(boolean snpcapturedata) {
        this.snpcapturedata = snpcapturedata;
    }


    public boolean isRun_reportgenerator() {
        return run_reportgenerator;
    }

    public void setRun_reportgenerator(boolean run_reportgenerator) {
        this.run_reportgenerator = run_reportgenerator;
    }

    public String getMapper_seedlength() {
        return mapper_seedlength;
    }

    public void setMapper_seedlength(String mapper_seedlength) {
        this.mapper_seedlength = mapper_seedlength;
    }

    public String getMapper_mismatches() {
        return mapper_mismatches;
    }

    public void setMapper_mismatches(String mapper_mismatches) {
        this.mapper_mismatches = mapper_mismatches;
    }

    public String getGatk_ploidy() {
        return gatk_ploidy;
    }

    public void setGatk_ploidy(String gatk_ploidy) {
        this.gatk_ploidy = gatk_ploidy;
    }

    public boolean isRun_mapping_extractmappedandunmapped() {
        return run_mapping_extractmappedandunmapped;
    }

    public void setRun_mapping_extractmappedandunmapped(boolean run_mapping_extractmappedandunmapped) {
        this.run_mapping_extractmappedandunmapped = run_mapping_extractmappedandunmapped;
    }

    public int getPreseq_lcextrap_stepsize() {
        return preseq_lcextrap_stepsize;
    }

    public void setPreseq_lcextrap_stepsize(int preseq_lcextrap_stepsize) {
        this.preseq_lcextrap_stepsize = preseq_lcextrap_stepsize;
    }

    public String getPreseq_lcextrap_extrapolationsize() {
        return preseq_lcextrap_extrapolationsize;
    }

    public void setPreseq_lcextrap_extrapolationsize(String preseq_lcextrap_extrapolationsize) {
        this.preseq_lcextrap_extrapolationsize = preseq_lcextrap_extrapolationsize;
    }

    public int getPreseq_lcextrap_bootstraps() {
        return preseq_lcextrap_bootstraps;
    }

    public void setPreseq_lcextrap_bootstraps(int preseq_lcextrap_bootstraps) {
        this.preseq_lcextrap_bootstraps = preseq_lcextrap_bootstraps;
    }

    public boolean isPreseq_lcextrap_quickmode_nobootstrapping() {
        return preseq_lcextrap_quickmode_nobootstrapping;
    }

    public void setPreseq_lcextrap_quickmode_nobootstrapping(boolean preseq_lcextrap_quickmode_nobootstrapping) {
        this.preseq_lcextrap_quickmode_nobootstrapping = preseq_lcextrap_quickmode_nobootstrapping;
    }
    public boolean isMerge_bam_files() {
        return merge_bam_files;
    }

    public void setMerge_bam_files(boolean merge_bam_files) {
        this.merge_bam_files = merge_bam_files;
    }

    public String getCM_tobemapped_against() {
        return CM_tobemapped_against;
    }

    public void setCM_tobemapped_against(String CM_tobemapped_against) {
        this.CM_tobemapped_against = CM_tobemapped_against;
    }

    public boolean isMerge_only_clipping() {
        return merge_only_clipping;
    }

    public void setMerge_only_clipping(boolean merge_only_clipping) {
        this.merge_only_clipping = merge_only_clipping;
    }

    public boolean isMarkdup_run() {
        return markdup_run;
    }

    public void setMarkdup_run(boolean markdup_run) {
        this.markdup_run = markdup_run;
    }

    public String getFilter_for_mt() {
        return filter_for_mt;
    }

    public void setFilter_for_mt(String filter_for_mt) {
        this.filter_for_mt = filter_for_mt;
    }

    public boolean isRun_mt_capture_mode() {
        return run_mt_capture_mode;
    }

    public void setRun_mt_capture_mode(boolean run_mt_capture_mode) {
        this.run_mt_capture_mode = run_mt_capture_mode;
    }

    public String getSnpcapture_type() {
        return snpcapture_type;
    }

    public void setSnpcapture_type(String snpcapture_type) {
        this.snpcapture_type = snpcapture_type;
    }

    public String getMapper_bwamem_seedlength() {
        return mapper_bwamem_seedlength;
    }

    public void setMapper_bwamem_seedlength(String mapper_bwamem_seedlength) {
        this.mapper_bwamem_seedlength = mapper_bwamem_seedlength;
    }

    public String getMapper_bwamem_bandwidth() {
        return mapper_bwamem_bandwidth;
    }

    public void setMapper_bwamem_bandwidth(String mapper_bwamem_bandwidth) {
        this.mapper_bwamem_bandwidth = mapper_bwamem_bandwidth;
    }

    public String getMapper_bwamem_advanced() {
        return mapper_bwamem_advanced;
    }

    public void setMapper_bwamem_advanced(String mapper_bwamem_advanced) {
        this.mapper_bwamem_advanced = mapper_bwamem_advanced;
    }

    public String getDNA_damage_calculator_to_use() {
        return DNA_damage_calculator_to_use;
    }

    public void setDNA_damage_calculator_to_use(String DNA_damage_calculator_to_use) {
        this.DNA_damage_calculator_to_use = DNA_damage_calculator_to_use;
    }

    public String getMerge_type() {
        return merge_type;
    }

    public void setMerge_type(String merge_type) {
        this.merge_type = merge_type;
    }

    public String getAngsd_glf_model() {
        return angsd_glf_model;
    }

    public void setAngsd_glf_model(String angsd_glf_model) {
        this.angsd_glf_model = angsd_glf_model;
    }

    public String getAngsd_glm_outformat() {
        return angsd_glm_outformat;
    }

    public void setAngsd_glm_outformat(String angsd_glm_outformat) {
        this.angsd_glm_outformat = angsd_glm_outformat;
    }

    public String getAngsd_fasta_callmethod() {
        return angsd_fasta_callmethod;
    }

    public void setAngsd_fasta_callmethod(String angsd_fasta_callmethod) {
        this.angsd_fasta_callmethod = angsd_fasta_callmethod;
    }

    public boolean isAngsd_create_fasta() {
        return angsd_create_fasta;
    }

    public void setAngsd_create_fasta(boolean angsd_create_fasta) {
        this.angsd_create_fasta = angsd_create_fasta;
    }


    public String getSchmutzi_library_type() {
        return schmutzi_library_type;
    }

    public void setSchmutzi_library_type(String schmutzi_library_type) {
        this.schmutzi_library_type = schmutzi_library_type;
    }

    public String getSchmutzi_mt_ref() {
        return schmutzi_mt_ref;
    }

    public void setSchmutzi_mt_ref(String schmutzi_mt_ref) {
        this.schmutzi_mt_ref = schmutzi_mt_ref;
    }

    public String getSchmutzi_mt_refDB() {
        return schmutzi_mt_refDB;
    }

    public void setSchmutzi_mt_refDB(String schmutzi_mt_refDB) {
        this.schmutzi_mt_refDB = schmutzi_mt_refDB;
    }

    public boolean isSchmutzi_run() {
        return schmutzi_run;
    }

    public void setSchmutzi_run(boolean schmutzi_run) {
        this.schmutzi_run = schmutzi_run;
    }


    public String getBedfile() {
        return bedfile;
    }

    public void setBedfile(String bedfile) {
        this.bedfile = bedfile;
    }

    public String getMerge_barcode3p() {
        return merge_barcode3p;
    }

    public void setMerge_barcode3p(String merge_barcode3p) {
        this.merge_barcode3p = merge_barcode3p;
    }

    public String getMerge_barcode5p() {
        return merge_barcode5p;
    }

    public void setMerge_barcode5p(String merge_barcode5p) {
        this.merge_barcode5p = merge_barcode5p;
    }
}