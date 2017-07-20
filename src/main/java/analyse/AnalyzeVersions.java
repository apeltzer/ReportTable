/**
 * 
 */
package analyse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Alexander Seitz
 *
 */
public class AnalyzeVersions extends AbstractAnalyze {
	
	private Map<String, Set<String>> versions;

	/**
	 * @param sampleFolder
	 */
	public AnalyzeVersions(File sampleFolder) {
		super(sampleFolder);
		analyzeVersions();
	}

	/**
	 * 
	 */
	private void analyzeVersions() {
		this.versions = new TreeMap<String, Set<String>>();
		for(String name: this.sampleFolder.list()) {
			if(name.endsWith("versions")) {
				File versionFile = new File(this.sampleFolder.getAbsolutePath()+"/"+name);
				if(versionFile.exists()) {
					analyzeVersionsFile(versionFile);
				}
			}else if(name.endsWith("EAGER.log")) {
				File versionFile = new File(this.sampleFolder.getAbsolutePath()+"/"+name);
				if(versionFile.exists()) {
					analyzeEAGERLogFile(versionFile);
				}
			}
		}		
	}
	
	/**
	 * @param versionFile
	 */
	private void analyzeEAGERLogFile(File versionFile) {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(versionFile));
			String currLine = "";
			while((currLine = br.readLine()) != null){
				if(currLine.contains("EAGER Version used for this run")) {
					String[] splitted = currLine.split(":");
					if(splitted.length==2) {
						Set<String> currVersion = new TreeSet<String>();
						currVersion.add(splitted[1]);
						this.versions.put("EAGER-CLI", currVersion);
					}
				}
			}
		} catch (IOException e) {
		}
	}

	/**
	 * @param versionFile
	 */
	private void analyzeVersionsFile(File versionFile) {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(versionFile));
			String currLine = "";
			Integer lineNum = 0;
			String program = "";
			String version = "";
			while((currLine = br.readLine()) != null){
				if(lineNum%2==0) {
					program = currLine.trim();
				}else {
					version = currLine.trim();
					Set<String> currVersion = new TreeSet<String>();
					currVersion.add(version);
					this.versions.put(program, currVersion);
				}
				lineNum++;
			}
		} catch (IOException e) {
		}
	}

	public Map<String, Set<String>> getVersions(){
		return this.versions;
	}

}
