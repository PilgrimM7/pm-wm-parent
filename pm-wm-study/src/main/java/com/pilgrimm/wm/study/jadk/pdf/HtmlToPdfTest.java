package com.pilgrimm.wm.study.jadk.pdf;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HtmlToPdfTest {
	
	public static void main(String[] args) throws Exception {
		parseHtmlToPdf();
	}
 
	/**
	 * 使用 iText XML Worker实现HTML转PDF
	 */
	public static void parseHtmlToPdf() throws Exception {
		String pdfFile = "F:/iText/htmlToPdf.pdf";

//		String htmlFile = "D:/GitRepository/pm-wm-parent/pm-wm-study/src/main/webapp/doc/iText/demo.html";
//		InputStream htmlFileStream = new FileInputStream(htmlFile);
		
		StringBuilder html = new StringBuilder("");
		html.append("<!DOCTYPE html>");
		html.append("<html>");
		html.append("<head>");
		html.append("</head>");
		html.append("<body>");
		html.append("<h1>你好！</h1>");
		html.append("</body>");
		ByteArrayInputStream htmlStringStream = new ByteArrayInputStream(html.toString().getBytes("UTF-8"));
 
		// 创建一个document对象实例
		Document document = new Document();
		// 为该Document创建一个Writer实例
		PdfWriter pdfwriter = PdfWriter.getInstance(document,
				new FileOutputStream(pdfFile));
		pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
		// 打开当前的document
		document.open();
 
//		InputStreamReader isr = new InputStreamReader(htmlFileStream, "UTF-8");
		
//		InputStreamReader isr = new InputStreamReader(htmlStringStream, "UTF-8");
		XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, htmlStringStream, Charset.forName("UTF-8"),
				new AsianFontProvider());
		document.close();
	}
	
	public static class AsianFontProvider extends XMLWorkerFontProvider {
		public Font getFont(final String fontname, final String encoding, final boolean embedded, final float size,
				final int style, final BaseColor color) {
			BaseFont bf = null;
			try {
				bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Font font = new Font(bf, size, style, color);
			font.setColor(color);
			return font;
		}
	}

}
