package com.reto.ocr.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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

}
