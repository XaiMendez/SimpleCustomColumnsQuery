package com.tminnova.model;

import lombok.Data;

@Data
public class TableColumn {

	private String table;
	private String column;
	public TableColumn(String table, String column) {
		super();
		this.table = table;
		this.column = column;
	}
	
	
	
	

}
