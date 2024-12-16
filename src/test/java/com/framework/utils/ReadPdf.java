package com.framework.utils;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadPdf {
	
	
	public static String getText(String filePath) {
		String text ="";
		
		try {
			PDDocument document = PDDocument.load(new File(filePath));
			PDFTextStripper pdf = new PDFTextStripper();
			text = pdf.getText(document);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;		
	}

}
