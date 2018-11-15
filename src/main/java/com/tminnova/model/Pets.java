package com.tminnova.model;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("testCollection")
public class Pets {

	@Id
	public ObjectId _id;

	public String name;
	
	

}
