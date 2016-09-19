/**
 * 
 */
package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import analyse.AnalyzeSample;
import utilities.OutputFields;

/**
 * @author Alexander Seitz
 *
 */
public abstract class AReportWriter {

	private final List<String> endings = new LinkedList<String>(Arrays.asList(".csv", ".html"));

	protected String filename = "";
	protected PrintWriter writer;
	
	private String ending = ""; 
	private String versionFilename = "";
	private String notAnalyzedFilename = "";

	public AReportWriter(String filename, String ending, HashMap<OutputFields,Boolean> outputMap){
		this.filename = filename;
		this.ending = ending;
		checkFilename();
		try {
			this.writer = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeHeaderLine(outputMap);
	}

	protected abstract void writeHeaderLine(HashMap<OutputFields, Boolean> outputMap);

	public abstract void finalizeWriting();

	public abstract void writeDataLine(HashMap<OutputFields, Boolean> outputMap, AnalyzeSample sample);

	protected List<String> generateDataFields(HashMap<OutputFields, Boolean> outputMap, AnalyzeSample sample){
		List<String> result = new LinkedList<String>();
		for(OutputFields currField: OutputFields.values()){
			if(outputMap.containsKey(currField)
					&& outputMap.get(currField)){
				result.add(sample.getValue(currField));
			}
		}
		return result;
	}

	protected List<String> getHeaderFields(HashMap<OutputFields, Boolean> outputMap) {
		List<String> result = new LinkedList<String>();
		for(OutputFields currField: OutputFields.values()){
			if(outputMap.containsKey(currField)
					&& outputMap.get(currField)){
				result.add(currField.toString());
			}
		}
		return result;
	}

	protected void checkFilename(){
		if(!this.filename.endsWith(this.ending)){
			for(String possibleEnding: this.endings){
				if(this.filename.endsWith(possibleEnding)){
					this.filename = this.filename.substring(0, this.filename.length()-possibleEnding.length());
					break;
				}
			}
			this.filename += this.ending;
		}
		this.versionFilename = this.filename.replace(this.ending, "_versions.txt");
		this.notAnalyzedFilename = this.filename.replace(this.ending, "_notAnalyzed.txt");
		if(new File(this.filename).exists()){
			new File(this.filename).delete();
		}
		if(new File(this.versionFilename).exists()){
			new File(this.versionFilename).delete();
		}
		if(new File(this.notAnalyzedFilename).exists()){
			new File(this.notAnalyzedFilename).delete();
		}
	}



	protected String readResourceFile(String filename, String prefix) {
		StringBuffer result = new StringBuffer();
		try {
			InputStream in = getClass().getResourceAsStream("/"+filename); 
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String currLine = "";
			while((currLine = br.readLine()) != null){
				if(currLine.length()>0){
					result.append(prefix);
					result.append(currLine);
					result.append("\n");
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	


	protected void ExportResource(String resourceName, String outputFile) {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        try {
            stream = getClass().getResourceAsStream(resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree
            if(stream == null) {
            	System.err.println("Cannot get resource \"" + resourceName + "\" from Jar file.");
            	return;
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            resStreamOut = new FileOutputStream(outputFile);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return;
        } finally {
            try {
				stream.close();
				resStreamOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
	

	public void writeNotSuccessfulAnalyzed(HashMap<OutputFields,Boolean> outputMap){
		if(new File(this.notAnalyzedFilename).exists()){
			return;
		}
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
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(this.notAnalyzedFilename), false)))) {
			    out.println(result.toString());
			    out.flush();
			}catch (IOException e) {
			}
		}
	}
	

	public void writeVersions(HashSet<String> fastqc, HashSet<String> clipAndMerge, HashSet<String> mapper) {
		// TODO Auto-generated method stub
		if(new File(this.versionFilename).exists()){
			return;
		}
		StringBuffer result = new StringBuffer("");
		result.append("FastQC:\t");
		result.append(fastqc.toString());
		result.append("\n");
		result.append("ClipAndMerge:\t");
		result.append(clipAndMerge.toString());
		result.append("\n");
		result.append("Mapper:\t");
		result.append(mapper.toString());
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(this.versionFilename), false)))) {
		    out.println(result.toString());
		    out.flush();
		}catch (IOException e) {
		}
	}

}
