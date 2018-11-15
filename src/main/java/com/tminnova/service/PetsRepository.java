package com.tminnova.service;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tminnova.model.Pets;

public interface PetsRepository extends MongoRepository<Pets, String> {
	  Pets findBy_id(ObjectId _id);
	}
