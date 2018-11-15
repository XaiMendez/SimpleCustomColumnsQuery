package com.tminnova.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tminnova.model.DefinitionTable;

@RestController
public class FileController {
	
	@GetMapping("/file")
	public Map getData() {
		
		Map<String, Map> response = new HashMap();
		Map map = new HashMap();
		List<DefinitionTable> dtList = new ArrayList<DefinitionTable>();
		
		int result = 0;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode nodes = mapper.readTree(new ClassPathResource("json-files/test.json").getFile());
			
			for (JsonNode node : nodes) {
			    //System.out.println("Element: " + element.get("fields"));
			    
			    ArrayNode tables = (ArrayNode)node.get("tables");
			    
			    for (JsonNode table : tables) {
			    	
			    	 ArrayNode columns = (ArrayNode)table.get("columns");
			    	 
			    	 for (JsonNode column : columns) {
			    		 dtList.add(
						    		new DefinitionTable(
						    				column.get("key").textValue(),
						    				column.get("name").textValue()
						    				));
					    	
					    	 map.put(table.get("table").textValue(), dtList);
			    		 
			    	 }
			    	
			    	
			    }
			    
			    
			    response.put(node.get("node").textValue(), map);
			    
			   
			}
		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}

	

}
