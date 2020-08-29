package com.reto.ocr.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.reto.ocr.domain.Documento;
import com.reto.ocr.exception.AppException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class OCRUtil {
	
	public static boolean  guardarImegenEnDirectorio(byte[] imagenData, String pathImagen) {
		File imagenFile = new File(pathImagen);
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imagenFile));
			stream.write(imagenData);
			stream.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String imagenToText(String pathImagen, Lenguaje lenguaje)  {
		ITesseract instance = new Tesseract();
		try {
			instance.setDatapath(OCRUtil.class.getClassLoader().getResource("tessdata").getPath());
			instance.setLanguage(lenguaje.getValor());
			return instance.doOCR(new File(pathImagen));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}
	
	public static Documento procesarDNI(String texto) {
		Documento documento = new Documento();
		String[] dataTexto = texto.split(" ");
		for (String data : dataTexto) {
			if(data.length() == 8) {
				try {
					documento.setNroDNI(data);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		documento.setTexto(texto);
		return documento;
	}

}
