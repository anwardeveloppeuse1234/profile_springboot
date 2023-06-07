package com.exemple.profils.entities;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name ="FILES")
public class FileDb {

	@Id
	private String numTel;
	
	private String name;
	
	private String type;
	
	@Lob
	private byte[] data;
	
	public FileDb() {
		
	}

	public FileDb(String numTel, String name, String type, byte[] data) {
		super();
		this.numTel = numTel;
		this.name = name;
		this.type = type;
		this.data = data;
	}


	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	

}

