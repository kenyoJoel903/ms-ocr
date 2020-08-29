package com.reto.ocr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.ocr.domain.Documento;
import com.reto.ocr.exception.ModeloNotFoundException;
import com.reto.ocr.repository.IDocumentoRepository;
import com.reto.ocr.service.IDocumentoService;

@Service
public class DocumentoServiceImpl implements IDocumentoService{

	@Autowired
	private IDocumentoRepository repository;
	
	@Override
	public Documento registrar(Documento objeto) {
		return repository.save(objeto);
	}

	@Override
	public List<Documento> listarTodos() {
		return repository.findAll();
	}

	@Override
	public Documento buscar(String id) {
		Optional<Documento> optDoc = repository.findById(id);
		if(!optDoc.isPresent())
			throw new ModeloNotFoundException("DOCUMENTO no encontrado " + id);
		return optDoc.get();
	}

}
