package com.tminnova.model;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
public class DefinitionTable {
	
	private String key;
	private String name;
	
	public DefinitionTable(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}
	
	

}
