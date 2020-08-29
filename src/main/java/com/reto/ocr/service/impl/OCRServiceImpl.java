package com.reto.ocr.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reto.ocr.domain.Documento;
import com.reto.ocr.domain.Imagen;
import com.reto.ocr.exception.AppException;
import com.reto.ocr.service.IDocumentoService;
import com.reto.ocr.service.IImagenService;
import com.reto.ocr.service.IOCRService;
import com.reto.ocr.util.Lenguaje;
import com.reto.ocr.util.OCRUtil;

@Service
public class OCRServiceImpl implements IOCRService {
	
	@Autowired
	private IImagenService imagenService;
	
	@Autowired
	private IDocumentoService documentoService;
	
	@Value("${directorio.imagenes}")
	private String pathDirectorio;

	@Override
	public Imagen procesarImagen(MultipartFile file) {
		long tiempoInicio = Instant.now().toEpochMilli(); 
		String[] nombreArchivo = file.getOriginalFilename().split("\\.");
		String extension = nombreArchivo[nombreArchivo.length - 1];
		String pathImagen = pathDirectorio.concat("/").concat(tiempoInicio + "").concat(".").concat(extension);
		Imagen imagen;
		try {
			if(!OCRUtil.guardarImegenEnDirectorio(file.getBytes(), pathImagen))
				throw new AppException("NO SE PUDO CARGAR LA IMAGEN");
			String texto = OCRUtil.imagenToText(pathImagen, Lenguaje.ESP);
			imagen = new Imagen();
			imagen.setNombre(file.getOriginalFilename());
			imagen.setUbicacion(pathImagen);
			imagen.setTexto(texto);
			imagen.setFechaProceso(LocalDateTime.now());
			return imagenService.registrar(imagen);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	@Override
	public List<Imagen> obtenerImagenesProcesadas() {
		return imagenService.listarTodos();
	}

	@Override
	public byte[] obtenerImagen(String idImagen) {
		try {
			File imagenFile = new File(imagenService.buscar(idImagen).getUbicacion());
			InputStream imagenIs = new FileInputStream(imagenFile);
			return IOUtils.toByteArray(imagenIs);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

	@Override
	public Documento procesarDocumento(MultipartFile file) {
		long tiempoInicio = Instant.now().toEpochMilli(); 
		String[] nombreArchivo = file.getOriginalFilename().split("\\.");
		String extension = nombreArchivo[nombreArchivo.length - 1];
		String pathImagen = pathDirectorio.concat("/").concat(tiempoInicio + "").concat(".").concat(extension);
		Documento documento;
		try {
			if(!OCRUtil.guardarImegenEnDirectorio(file.getBytes(), pathImagen))
				throw new AppException("NO SE PUDO CARGAR LA IMAGEN");
			String texto = OCRUtil.imagenToText(pathImagen, Lenguaje.ESP);
			documento = OCRUtil.procesarDNI(texto);
			documento.setUbicacion(pathImagen);
			documento.setFechaProceso(LocalDateTime.now());
			documentoService.registrar(documento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		return documento;
	}

	@Override
	public List<Documento> obtenerDocumentos() {
		return documentoService.listarTodos();
	}

}
