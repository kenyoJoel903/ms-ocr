package com.reto.ocr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.reto.ocr.domain.Imagen;

public interface IOCRService {
	
	Imagen procesarImagen(MultipartFile file);
	
	List<Imagen> obtenerImagenesProcesadas();
	
	byte[] obtenerImagen(String idImagen);

}
