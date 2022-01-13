package com.micontadigital.facturacion;

import java.awt.Toolkit;
import java.awt.font.ImageGraphicAttribute;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.itextpdf.text.BadElementException;

public class Logo {

	public com.itextpdf.text.Image getLogo() {
		java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("cfdi.png"));
		com.itextpdf.text.Image img = null;
		try {
			img = com.itextpdf.text.Image.getInstance(awtImage, null);

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return img;

	}
	
	public com.itextpdf.text.Image getPac() {
		java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("PacSat.png"));
		com.itextpdf.text.Image img = null;
		try {
			img = com.itextpdf.text.Image.getInstance(awtImage, null);

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return img;

	}
	
	public com.itextpdf.text.Image getIdaly() {
		java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("idalytecrojo.png"));
		com.itextpdf.text.Image img = null;
		try {
			img = com.itextpdf.text.Image.getInstance(awtImage, null);

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return img;

	}
	
	public com.itextpdf.text.Image getSat() {
		java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("finkok.png"));
		com.itextpdf.text.Image img = null;
		try {
			img = com.itextpdf.text.Image.getInstance(awtImage, null);

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return img;

	}

}
