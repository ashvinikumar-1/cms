package com.highpeak.Ediscovery.bean;

import java.sql.Date;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FileModel {

	private int id;
	private String name;
	private String path;
	private boolean isActive;
	private boolean isProcessed;
	
	
	@JsonIgnore
	private Calendar createdAt;
	
	@JsonIgnore
	private Calendar modifiedAt;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isProcessed() {
		return isProcessed;
	}
	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	public Calendar getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Calendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	
	
}
