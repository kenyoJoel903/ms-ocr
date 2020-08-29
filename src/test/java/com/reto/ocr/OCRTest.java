package com.reto.ocr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.reto.ocr.util.Lenguaje;
import com.reto.ocr.util.OCRUtil;

public class OCRTest {
	
	@Test
	public void imagenTotext() {
		String pathImagen = OCRTest.class.getClassLoader().getResource("test/messi.png").getPath();
		String resultado = OCRUtil.imagenToText(pathImagen, Lenguaje.ESP);
		assertTrue(resultado.toLowerCase().contains("Lionel Messi".toLowerCase()));
	}

}
