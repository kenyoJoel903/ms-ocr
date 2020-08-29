package com.reto.ocr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.reto.ocr.domain.Imagen;

public interface IImagenRepository extends MongoRepository<Imagen, String> {

}
