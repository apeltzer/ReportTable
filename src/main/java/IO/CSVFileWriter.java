package IO;

import java.util.HashMap;
import java.util.List;

import analyse.AnalyzeSample;
import utilities.OutputFields;

public class CSVFileWriter extends AReportWriter {

	public CSVFileWriter(String filename, HashMap<OutputFields, Boolean> outputMap) {
		super(filename, ".csv", outputMap);
	}

	@Override
	protected void writeHeaderLine(HashMap<OutputFields, Boolean> outputMap) {
		List<String> headerList = getHeaderFields(outputMap);
		this.writer.println(generateCSVLine(headerList));
		
	}
	
	private String generateCSVLine(List<String> data){
		StringBuffer result = new StringBuffer();
		int i=0;
		for(String dataPoint: data){
			result.append(dataPoint);
			if(i<data.size()-1){
				result.append(",");
			}
			i++;
		}
		return result.toString();
	}

	@Override
	public void finalizeWriting() {
		this.writer.flush();
		this.writer.close();
	}

	@Override
	public void writeDataLine(HashMap<OutputFields, Boolean> outputMap, AnalyzeSample sample) {
		List<String> entries = this.generateDataFields(outputMap, sample);
		this.writer.println(generateCSVLine(entries));
	}

}
