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
				if (child.isDirectory()) {
					for (File grandchild : child.listFiles()) {
						String childname = grandchild.getName();
						Matcher matcher = pattern.matcher(childname);
						if (matcher.find()) {
							FileWriter arq = new FileWriter(path.path+"/"+child.getName()+"/"+matcher.group(1) + "txt");
							PrintWriter writePDF = new PrintWriter(arq);
							pdfManager.setFilePath(path.path+"/"+child.getName()+"/"+childname);
							pdfManager.ToText(writePDF);
							arq.close();
//							System.out.println("Errors found with " + childname);
						}
					}
				}
				String childname = child.getName();
				Matcher matcher = pattern.matcher(childname);
				if (matcher.find()) {
					FileWriter arq = new FileWriter(path.path+"/"+matcher.group(1) + "txt");
					PrintWriter writePDF = new PrintWriter(arq);
					pdfManager.setFilePath(path.path+"/"+childname);
					pdfManager.ToText(writePDF);
					arq.close();
//					System.out.println("Errors found with " + childname);
				}
			}
		} else {
			return;
		}
	}
}
