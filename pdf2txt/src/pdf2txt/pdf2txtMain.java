package pdf2txt;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class pdf2txtMain {
	public static void main(String[] args) throws IOException {

		PDFManager pdfManager = new PDFManager();
		filepath path = new filepath();
		File dir = new File(path.path);
		File[] directoryListing = dir.listFiles();
		Pattern pattern = Pattern.compile("(.+)pdf$");
		if (directoryListing != null) {
			for (File child : directoryListing) {
				String childname = child.getName();
				Matcher matcher = pattern.matcher(childname);
				if (matcher.find()) {
					FileWriter arq = new FileWriter(path.path+"/"+matcher.group(1) + "txt");
					PrintWriter gravarArq = new PrintWriter(arq);
					pdfManager.setFilePath(path.path+"/"+childname);
					pdfManager.ToText(gravarArq);
					arq.close();
					System.out.println("erros encontrados com ");
					System.out.println(child);
				}
			}
		} else {
			return;
		}
	}
}