package com.tminnova.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.query.NativeQuery;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tminnova.model.DBTable;
import com.tminnova.model.DBTableRequest;
import com.tminnova.model.TableColumn;

@RestController
public class ValidateTableController {

	@Autowired
	private DataSource dataSource;

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

	@PostMapping("/data")
	public String greeting(@RequestBody HashMap<String, String[]> hm) {
		
		String query = "";

		for (Entry<String, String[]> mapElement: hm.entrySet()) {
			

			for (String column : mapElement.getValue()) {
				//System.out.println("Element: " + element);
//				System.out.println("Exist column " + column + " :"  +
//				existColumn(mapElement.getKey(), column));
				if(existColumn(mapElement.getKey(), column)) {
					query = query.concat(mapElement.getKey() + "." + column + ",");
				}
				
			}
			

		};
		query = method(query);
		return query;
	}
	
	public String method(String str) {
	    if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
	        str = str.substring(0, str.length() - 1);
	    }
	    return str;
	}

	public boolean existColumn(String table, String column) {
		
		boolean result = false;

		Connection con;
		try {
			con = dataSource.getConnection();
			ResultSet rs = con.createStatement()
					.executeQuery("select " + column + " from " + table + " limit 1");
			ResultSetMetaData rsmd = rs.getMetaData();
			
			if(!rs.next()) {
				result =  true;
				
			}

//			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//				System.out.println(
//						"column name: " + rsmd.getColumnName(i) + " column type: " + rsmd.getColumnTypeName(i));
//			}

		} catch (SQLException e) {
			
			//e.printStackTrace();
		}
		return result;
		
	}

}
