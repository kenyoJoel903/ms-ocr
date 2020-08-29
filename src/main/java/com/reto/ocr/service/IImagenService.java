package com.reto.ocr.service;

import java.util.List;

import com.reto.ocr.domain.Imagen;

public interface IImagenService {
	
	Imagen registrar(Imagen imagen);
	
	List<Imagen> listarTodos();
	
	Imagen buscarImagen(String id);

}
