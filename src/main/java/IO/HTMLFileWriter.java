package IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import analyse.AnalyzeSample;
import utilities.OutputFields;
import utilities.OutputStrings;

public class HTMLFileWriter extends AReportWriter {

	public HTMLFileWriter(String filename, HashMap<OutputFields,Boolean> outputMap) {
		super(filename, ".html", outputMap);
		writeImage();
	}

	private void writeImage() {
		File f = new File(this.filename);
		String image = new File(f.getAbsolutePath()).getParent()+"/EAGER_Logo.png";
		File imageFile = new File(image);
		if(!imageFile.exists()){
			ExportResource("/EAGER_Logo.png", image);
		}
	}

	@Override
	protected void writeHeaderLine(HashMap<OutputFields, Boolean> outputMap) {
		writeHTMLHeader(getFieldType(outputMap));
		List<String> headerList = getHeaderFields(outputMap);
		this.writer.println("\t\t<table id=\"example\" class=\"display\" cellspacing=\"0\" width=\"100%\">");
		for(int i=0; i<2; i++){
			if(i==0){
				this.writer.println("\t\t\t<thead>");
			} else{
				this.writer.println("\t\t\t<tfoot>");
			}
			this.writer.println("\t\t\t\t<tr>");
			for(String column: headerList){
				String line = "\t\t\t\t\t<th>";
				if(i==0){
					line+=column;
				}
				line+="</th>";
//				this.writer.println("\t\t\t\t\t<th>"+column+"</th>");
				this.writer.println(line);
			}
			this.writer.println("\t\t\t\t</tr>");
			if(i==0){
				this.writer.println("\t\t\t</thead>");
			} else{
				this.writer.println("\t\t\t</tfoot>");
			}
		}
		this.writer.println("\t\t\t<tbody>");
	}

	private List<String> getFieldType(HashMap<OutputFields, Boolean> outputMap) {
		List<String> result = new LinkedList<String>();
		for(OutputFields currField: OutputFields.values()){
			if(outputMap.containsKey(currField)
				&& outputMap.get(currField)){
				result.add(currField.getFieldType());
			}
		}
		return result;
	}

	@Override
	public void finalizeWriting() {
		this.writer.println("\t\t\t</tbody>");
		this.writer.println("\t\t</table>");
		this.writer.println("\t</body>");
		this.writer.println("</html>");
		this.writer.flush();
		this.writer.close();
	}

	private void writeHTMLHeader(List<String> types){
		this.writer.println("<!DOCTYPE html>");
		this.writer.println("<html>");
		this.writer.println("\t<head>");
		this.writer.println("\t\t<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\">");
		this.writer.println("\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		this.writer.println("\t\t<title>EAGER Report</title>");
		this.writer.println("\t\t<link rel=\"shortcut icon\" type=\"image/png\" href=\"EAGER_Logo.png\">");
		// add the css for the buttons.dataTables library: https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css
		this.writer.println("\t\t<style>");
		this.writer.print(readResourceFile("buttons.dataTables.min.css", "\t\t\t"));
		this.writer.println("\t\t</style>");
		// add the css for the jquery.dataTables library: https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css
		this.writer.println("\t\t<style>");
		this.writer.print(readResourceFile("jquery.dataTables.min.css", "\t\t\t"));
		this.writer.println("\t\t</style>");
		// add the css for the jquery-ui library: http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/base/jquery-ui.css
		this.writer.println("\t\t<style>");
		this.writer.print(readResourceFile("jquery-ui.css", "\t\t\t"));
		this.writer.println("\t\t</style>");
		// add the css for the yadcf library
		this.writer.println("\t\t<style>");
		this.writer.print(readResourceFile("jquery.dataTables.yadcf.css", "\t\t\t"));
		this.writer.println("\t\t</style>");
		// redefine some css values
		this.writer.println("\t\t<style>");
		this.writer.println("\t\t\ttable.dataTable thead th{");
		this.writer.println("\t\t\t\tfont-size: 11px;");
		this.writer.println("\t\t\t}");
		this.writer.println("\t\t\ttable.dataTable tbody td{");
		this.writer.println("\t\t\t\tfont-size: 9px;");
		this.writer.println("\t\t\t\ttext-align: center;");
		this.writer.println("\t\t\t}");
		this.writer.println("\t\t</style>");
		this.writer.println();
//		this.writer.println("\t\t<script src=\"https://code.jquery.com/jquery-1.12.3.min.js\"></script>");
		// add the js for the jquery library https://code.jquery.com/jquery-1.12.3.min.js
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("jquery-1.12.3.min.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
//		this.writer.println("\t\t<script src=\"https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js\"></script>");
		// add the js for the dataTables library https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("jquery.dataTables.min.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
//		this.writer.println("\t\t<script src=\"https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js\"></script>");
		// add the js for the dataTables.buttons library https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("dataTables.buttons.min.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
//		this.writer.println("\t\t<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js\"></script>");
		// add the js for the jszip library https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("jszip.min.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
//		this.writer.println("\t\t<script src=\"https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js\"></script>");
		// add the js for the pdfmake library https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("pdfmake.min.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
//		this.writer.println("\t\t<script src=\"https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js\"></script>");
		// add the js for the vfs_fonts library https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("vfs_fonts.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
//		this.writer.println("\t\t<script src=\"https://cdn.datatables.net/buttons/1.1.2/js/buttons.html5.min.js\"></script>");
		// add the js for the buttons.html5 library https://cdn.datatables.net/buttons/1.1.2/js/buttons.html5.min.js
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("buttons.html5.min.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
//		this.writer.println("\t\t<script src=\"https://cdn.datatables.net/buttons/1.1.2/js/buttons.colVis.min.js\"></script>");
		// add the js for the buttons.colVis library https://cdn.datatables.net/buttons/1.1.2/js/buttons.colVis.min.js
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("buttons.colVis.min.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
//		this.writer.println("\t\t<script src=\"jquery.dataTables.yadcf.js\"></script>");
		// add the js for the yadcf library
		this.writer.println("\t\t<script>");
		this.writer.print(readResourceFile("jquery.dataTables.yadcf.js", "\t\t\t"));
		this.writer.println("\t\t</script>");
		this.writer.println("\t\t<script>");
		this.writer.println("\t\t\t$(document).ready(function(){");
		this.writer.println("\t\t\tvar table = $('#example').dataTable({");
		this.writer.println("\t\t\t\tlengthMenu: [[10, 25, 50, -1], [10, 25, 50, \"All\"]],");
		this.writer.println("\t\t\t\tdom: 'lBfrtip',");
		this.writer.println("\t\t\t\tscrollX: true,");
		this.writer.println("\t\t\t\tbuttons: [");
//		this.writer.println("\t\t\t\t\t'copyHtml5',");
		this.writer.println("\t\t\t\t\t{");
		this.writer.println("\t\t\t\t\t\textend: 'copyHtml5',");
		this.writer.println("\t\t\t\t\t\texportOptions : {");
		this.writer.println("\t\t\t\t\t\t\tcolumns : ':visible',");
		this.writer.println("\t\t\t\t\t\t\tformat : {");
		this.writer.println("\t\t\t\t\t\t\t\theader : function (mDataProp, columnIdx) {");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar htmlText = '<span>' + mDataProp + '</span>';");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar jHtmlObject = jQuery(htmlText);");
		this.writer.println("\t\t\t\t\t\t\t\t\tjHtmlObject.find('div').remove();");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar newHtml = jHtmlObject.text();");
		this.writer.println("\t\t\t\t\t\t\t\t\tconsole.log('My header > ' + newHtml);");
		this.writer.println("\t\t\t\t\t\t\t\t\treturn newHtml;");
		this.writer.println("\t\t\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t},");
//		this.writer.println("\t\t\t\t\t'excelHtml5',"); // we have to remove the "x" from the yadcf filtering fields
		this.writer.println("\t\t\t\t\t{");
		this.writer.println("\t\t\t\t\t\textend: 'excelHtml5',");
		this.writer.println("\t\t\t\t\t\texportOptions : {");
		this.writer.println("\t\t\t\t\t\t\tcolumns : ':visible',");
		this.writer.println("\t\t\t\t\t\t\tformat : {");
		this.writer.println("\t\t\t\t\t\t\t\theader : function (mDataProp, columnIdx) {");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar htmlText = '<span>' + mDataProp + '</span>';");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar jHtmlObject = jQuery(htmlText);");
		this.writer.println("\t\t\t\t\t\t\t\t\tjHtmlObject.find('div').remove();");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar newHtml = jHtmlObject.text();");
		this.writer.println("\t\t\t\t\t\t\t\t\tconsole.log('My header > ' + newHtml);");
		this.writer.println("\t\t\t\t\t\t\t\t\treturn newHtml;");
		this.writer.println("\t\t\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t},");
//		this.writer.println("\t\t\t\t\t'csvHtml5',");
		this.writer.println("\t\t\t\t\t{");
		this.writer.println("\t\t\t\t\t\textend: 'csvHtml5',");
		this.writer.println("\t\t\t\t\t\texportOptions : {");
		this.writer.println("\t\t\t\t\t\t\tcolumns : ':visible',");
		this.writer.println("\t\t\t\t\t\t\tformat : {");
		this.writer.println("\t\t\t\t\t\t\t\theader : function (mDataProp, columnIdx) {");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar htmlText = '<span>' + mDataProp + '</span>';");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar jHtmlObject = jQuery(htmlText);");
		this.writer.println("\t\t\t\t\t\t\t\t\tjHtmlObject.find('div').remove();");
		this.writer.println("\t\t\t\t\t\t\t\t\tvar newHtml = jHtmlObject.text();");
		this.writer.println("\t\t\t\t\t\t\t\t\tconsole.log('My header > ' + newHtml);");
		this.writer.println("\t\t\t\t\t\t\t\t\treturn newHtml;");
		this.writer.println("\t\t\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t},");
//		this.writer.println("\t\t\t\t\t'pdfHtml5',");
		this.writer.println("\t\t\t\t\t'colvis',");
		this.writer.println("\t\t\t\t\t// Buttons to increase / decrease the font size of the table");
		this.writer.println("\t\t\t\t\t{");
		this.writer.println("\t\t\t\t\t\ttext: 'T+',");
		this.writer.println("\t\t\t\t\t\taction: function ( e, dt, node, config ) {");
		this.writer.println("\t\t\t\t\t\t\t$('table.dataTable thead th').css('font-size', parseInt($('table.dataTable thead th').css('font-size'))+1);");
		this.writer.println("\t\t\t\t\t\t\t$('table.dataTable tbody td').css('font-size', parseInt($('table.dataTable tbody td').css('font-size'))+1);");
		this.writer.println("\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t},");
		this.writer.println("\t\t\t\t\t{");
		this.writer.println("\t\t\t\t\t\ttext: 'T-',");
		this.writer.println("\t\t\t\t\t\taction: function ( e, dt, node, config ) {");
		this.writer.println("\t\t\t\t\t\t\t$('table.dataTable thead th').css('font-size', parseInt($('table.dataTable thead th').css('font-size'))-1);");
		this.writer.println("\t\t\t\t\t\t\t$('table.dataTable tbody td').css('font-size', parseInt($('table.dataTable tbody td').css('font-size'))-1);");
		this.writer.println("\t\t\t\t\t\t}");
		this.writer.println("\t\t\t\t\t}");
		this.writer.println("\t\t\t\t],");
		this.writer.println("\t\t\t});");//.yadcf(");
		this.writer.println("\t\t\tyadcf.init(table.api(),");
		this.writer.println("\t\t\t\t[");
		int colnum=0;
		for(String type: types){
			this.writer.println("\t\t\t\t\t{column_number: "+colnum+type+"},");
			colnum++;
		}
		this.writer.println("\t\t\t\t]);");//, 'footer');");
		this.writer.println("\t\t\t\ttable.fnAdjustColumnSizing();");
		this.writer.println("\t\t\t});");
		this.writer.println("\t\t</script>");
		this.writer.println("\t</head>");
		this.writer.println("\t<body>");
		this.writer.println("\t\t<div align=\"left\">");
		this.writer.println("\t\t<table id=\"head\">");
		this.writer.println("\t\t\t<tr>");
		this.writer.println("\t\t\t\t<td><img src=\"EAGER_Logo.png\" alt=\"EAGER\" width=150px></td>");
		this.writer.println("\t\t\t\t<td><h1 align=\"center\">EAGER Report</h1></td>");
		this.writer.println("\t\t\t</tr>");
		this.writer.println("\t\t</table>");
		this.writer.println("\t\t</div>");
//		this.writer.println("\t<table>");
//		this.writer
	}

	@Override
	public void writeDataLine(HashMap<OutputFields, Boolean> outputMap, AnalyzeSample sample) {
		List<String> entries = this.generateDataFields(outputMap, sample);
		this.writer.println("\t\t\t\t<tr>");
		for(String entry: entries){
			if(entry.contains("/")){
				String[] splitted = entry.split("/");
				int len=splitted[1].length();
				String res=splitted[0].replace("Sample ", "");
				while(res.length() < len){
					res = "0"+res;
				}
				this.writer.println("\t\t\t\t\t<td data-filter=\""+res+"\" data-sort=\""+res+"\">"+entry+"</td>");
			} else if(entry.contains("%")){
				this.writer.println("\t\t\t\t\t<td data-filter=\""+entry.replace("%", "").trim()+"\">"+entry+"</td>");
			} else if(OutputStrings.notFound.equals(entry.trim()) || OutputStrings.notRun.equals(entry.trim())){
				this.writer.println("\t\t\t\t\t<td data-filter=\"-1\">"+entry+"</td>");
			}else{
				this.writer.println("\t\t\t\t\t<td>"+entry+"</td>");
			}
		}
		this.writer.println("\t\t\t\t</tr>");
		this.writer.flush();
	}



}
