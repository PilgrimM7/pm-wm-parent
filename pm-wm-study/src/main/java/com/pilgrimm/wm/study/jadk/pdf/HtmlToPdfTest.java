package com.pilgrimm.wm.study.jadk.pdf;

import java.io.FileInputStream;
import java.io.InputStream;

public class HtmlToPdfTest {
	
	public static void main(String[] args) throws Exception {
		parseHtmlToPdf();
	}
 
	/**
	 * 使用 iText XML Worker实现HTML转PDF
	 * itextpdf-5.5.6.jar
	 * @param fileName
	 * @throws Exception
	 */
	public static void parseHtmlToPdf() throws Exception {
		String htmlFile = "F:/iText/demo.html";
		String pdfFile = "F:/iText/htmlToPdf.pdf";
		InputStream htmlFileStream = new FileInputStream(htmlFile);
 
		// 创建一个document对象实例
		Document document = new Document();
		// 为该Document创建一个Writer实例
		PdfWriter pdfwriter = PdfWriter.getInstance(document,
				new FileOutputStream(pdfFile));
		pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
		// 打开当前的document
		document.open();
 
		InputStreamReader isr = new InputStreamReader(htmlFileStream, "UTF-8");
		XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, isr);
		document.close();
	}

}
