package com.reto.ocr.service;

import java.util.List;

public interface ICRUD<T> {
	
	T registrar(T objeto);
	
	List<T> listarTodos();
	
	T buscar(String id);

}
