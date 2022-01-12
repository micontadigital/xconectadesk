package com.idalytec.cfdidesk;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;


public class FooterPiePaginaiText extends  PdfPageEventHelper {
	public PdfPTable table;
	 public BaseFont helv;
	 public PdfTemplate tpl;
	 public PdfGState gstate;
	 public Image headerImage;
	 
	 
	 public void onOpenDocument(PdfWriter writer, Document document) {
	        
	    }

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		Image imagen=null;
		try {
						
			PdfContentByte cb = writer.getDirectContent();
			
			imagen = Image.getInstance("/home/cfdiapp/idalytecrojo.png");
			imagen.setAbsolutePosition(10, 6);
			imagen.scalePercent(20);
			cb.addImage(imagen);
			
			imagen = Image.getInstance("/home/cfdiapp/PacSat.png");
			imagen.setAbsolutePosition(278, 5);
			imagen.scalePercent(10);
			cb.addImage(imagen);
			
			imagen = Image.getInstance("/home/cfdiapp/finkok.png");
			imagen.setAbsolutePosition(548, 2);
			imagen.scalePercent(11);
			cb.addImage(imagen);
			
			
		} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	
	}
}
