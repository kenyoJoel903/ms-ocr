package com.reto.ocr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.reto.ocr.domain.Documento;

public interface IDocumentoRepository extends MongoRepository<Documento, String> {

}
