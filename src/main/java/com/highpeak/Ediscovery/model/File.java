package com.highpeak.Ediscovery.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="File")
public class File {

	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String path;
	private Calendar createdDate;
	private Calendar modifiedDate;
	private boolean isActive=true;
	private boolean isProcessed=false;
	
	/*public static String separator;
	
	
	@ManyToOne
	@JoinColumn(name="CaseId")
	private Cases cases;*/

	
	public File() {
		super();
	}
	
	
	
	public File(int id, String name, String path, 	Calendar createdDate, Calendar modifiedDate, boolean isActive,
			boolean isProcessed) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.isActive = isActive;
		this.isProcessed = isProcessed;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		path = path;
	}
	public Calendar getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		createdDate = createdDate;
	}
	public Calendar getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		modifiedDate = modifiedDate;
	}
	public boolean isIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		isActive = isActive;
	}
	public boolean isIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(boolean isProcessed) {
		isProcessed = isProcessed;
	}

	
}
