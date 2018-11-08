package com.tminnova.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tminnova.model.DBTable;
import com.tminnova.model.DBTableRequest;
import com.tminnova.model.TableColumn;

@RestController
public class DataController {

	static <K, V> List<K> getAllKeysForValue(Map<K, TableColumn> mapOfWords, TableColumn value) {
		List<K> listOfKeys = null;

		// Check if Map contains the given value
		if (mapOfWords.containsValue(value)) {
			// Create an Empty List
			listOfKeys = new ArrayList<>();

			// Iterate over each entry of map using entrySet
			for (Entry<K, TableColumn> entry : mapOfWords.entrySet()) {
				// Check if value matches with given value
				if (entry.getValue().equals(value)) {
					// Store the key from entry to the list
					listOfKeys.add(entry.getKey());
				}
			}
		}
		// Return the list of keys whose value matches with given value.
		return listOfKeys;
	}

	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	@PostMapping("/greeting")
	public String greeting(@RequestBody DBTableRequest req) {

		Map<String, TableColumn> columnsTableDB = new TreeMap<String, TableColumn>();
		Map<String, String> columnsTableRequest = new TreeMap<String, String>();

		columnsTableDB.put("Clients.id", new TableColumn("Clients", "id"));
		columnsTableDB.put("Clients.name", new TableColumn("Clients", "name"));
		columnsTableDB.put("Clients.lastname", new TableColumn("Clients", "lastname"));
		columnsTableDB.put("Clients.dob", new TableColumn("Clients", "dob"));

		// columnsTableDB.put("id", "Companies");
		// columnsTableDB.put("name", "Companies");
		// columnsTableDB.put("address", "Companies");

		// columnsTableDB.put("Clients", "id");
		// columnsTableDB.put("Clients", "name");
		// columnsTableDB.put("Clients", "lastname");
		// columnsTableDB.put("Clients", "dob");
		//
		// columnsTableDB.put("Companies", "id");
		// columnsTableDB.put("Companies", "name");
		// columnsTableDB.put("Companies", "address");

		// Validate if exist table from Request in Map
		// for (DBTable reqTable : req.getTables()) {
		//
		// if (columnsTable.get(reqTable.getTable()) != null) {
		// System.out.println(reqTable.getTable() + " existe");
		//
		// } else {
		// System.out.println(reqTable.getTable() + " no existe");
		// }
		//
		// }

		// System.out.println("Key: " + this.getKey(columnsTableDB, "dob"));

		// columnsTableDB.values().toString()

		List<String> keyListDB = new ArrayList<String>(columnsTableDB.keySet());

		for (DBTable reqTable : req.getTables()) {

			if (keyListDB.toString().contains(reqTable.getTable())) {
				// System.out.println("Tabla " + reqTable.getTable() + " existe");

				for (String ReqColumn : reqTable.getColumns()) {
					
					if(!columnsTableDB.containsValue(new TableColumn(reqTable.getTable(), ReqColumn))) {
						System.out.println("Columna " + ReqColumn + " no existe para la Tabla " + reqTable.getTable());
					}

				}

			} else {
				System.out.println("Tabla " + reqTable.getTable() + " no existe");
			}

		}

		return "Hello";
	}

}
