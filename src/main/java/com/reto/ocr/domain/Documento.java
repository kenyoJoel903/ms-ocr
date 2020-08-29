package com.reto.ocr.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "documentos")
public class Documento {
	
	@Id
	private String id;
	
	private String nroDNI;
	
	private String apellidoPaterno;
	
	private String apellidoMaterno;
	
	private String ubicacion;
	
	private String texto;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaProceso;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNroDNI() {
		return nroDNI;
	}

	public void setNroDNI(String nroDNI) {
		this.nroDNI = nroDNI;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(LocalDateTime fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	

}
