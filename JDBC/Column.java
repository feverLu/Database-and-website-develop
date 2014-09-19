package edu.neu.cs5200.jga.jdbc;

public class Column {
	private String name;
	private int type;
	private int size;
	public Column(String name, int type, int size) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String toString() {
		String typeStr = "";
		switch (this.type) {
			case java.sql.Types.INTEGER :
				typeStr = "INTEGER";
				break;
			case java.sql.Types.FLOAT :
				typeStr = "FLOAT";
				break;
			case java.sql.Types.DOUBLE :
				typeStr = "DOUBLE";
				break;
			case java.sql.Types.VARCHAR :
				typeStr = "VARCHAR";
				break;
			case java.sql.Types.DATE :
				typeStr = "DATE";
				break;
			case java.sql.Types.TIME :
				typeStr = "TIME";
				break;
			case java.sql.Types.TIMESTAMP :
				typeStr = "TIMESTAMP";
				break;
		}
		return this.name + " " + typeStr + "(" + this.size + ")";
	}
	public String toSql() {
		String typeStr = "";
		switch (this.type) {
			case java.sql.Types.INTEGER :
				typeStr = "INTEGER";
				break;
			case java.sql.Types.FLOAT :
				typeStr = "FLOAT";
				break;
			case java.sql.Types.DOUBLE :
				typeStr = "DOUBLE";
				break;
			case java.sql.Types.VARCHAR :
				typeStr = "VARCHAR";
				break;
			case java.sql.Types.DATE :
				typeStr = "DATE";
				break;
			case java.sql.Types.TIME :
				typeStr = "TIME";
				break;
			case java.sql.Types.TIMESTAMP :
				typeStr = "TIMESTAMP";
				break;
		}
		return this.name + " " + typeStr + "(" + this.size + ")";
	}
}
