package pdf2txt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFManager {

	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	private String filePath;
	private File file;
	public int aux;
	public int errors;

	public PDFManager() {

	}

	public void ToText(PrintWriter writePDF) throws IOException {
		this.pdfStripper = null;
		this.pdDoc = null;
		this.cosDoc = null;
		file = new File(this.filePath);
		parser = new PDFParser(new RandomAccessFile(file, "r"));
		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		pdDoc.getNumberOfPages();
		aux = 1;
		errors = 0;
		while (aux <= pdDoc.getNumberOfPages()) {
			pdfStripper.setStartPage(aux);
			pdfStripper.setEndPage(aux + 1);
			try {
				writePDF.printf(pdfStripper.getText(pdDoc));
			} catch (Exception e) {
				errors += 1;
			} catch (Throwable e) {
				errors += 1;
			}
			aux += 2;
		}
		System.out.println(String.valueOf(errors));
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}