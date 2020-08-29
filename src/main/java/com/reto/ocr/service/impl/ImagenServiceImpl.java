package com.reto.ocr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.ocr.domain.Imagen;
import com.reto.ocr.exception.ModeloNotFoundException;
import com.reto.ocr.repository.IImagenRepository;
import com.reto.ocr.service.IImagenService;

@Service
public class ImagenServiceImpl implements IImagenService{
	
	@Autowired
	private IImagenRepository repository;

	@Override
	public Imagen registrar(Imagen imagen) {
		return repository.save(imagen);
	}

	@Override
	public List<Imagen> listarTodos() {
		return repository.findAll();
	}

	@Override
	public Imagen buscarImagen(String id) {
		Optional<Imagen> optImagen = repository.findById(id);
		if(!optImagen.isPresent())
			throw new ModeloNotFoundException("Imagen no encontrada " + id);
		return optImagen.get();
	}

}
