package com.reto.ocr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reto.ocr.domain.Imagen;
import com.reto.ocr.service.IOCRService;

@RestController
@RequestMapping("/ocr")
public class OCRController {
	
	@Autowired
	private IOCRService service;
	
	@PostMapping
	public ResponseEntity<Imagen> procesarImagen(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity(service.procesarImagen(file), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Imagen>> obtenerImagenes() {
		return new ResponseEntity(service.obtenerImagenesProcesadas(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] mostrarImagen(@PathVariable String id) {
		return service.obtenerImagen(id);
	}

}
