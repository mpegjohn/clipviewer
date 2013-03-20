package com.vidlib.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table (name = "property")
public class Property implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id_property;
	private String fileName;
	private Date fileCreateDate;
	private Date recordedDate;
	private String fileExtension;
	private String fullFilename;
	private long filesize;
	private Date fileImportDate;
	private int version;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_property")
	public long getId_property() {
		return id_property;
	}
	public void setId_property(long id_property) {
		this.id_property = id_property;
	}
	
	Scene scene;
	
	@OneToOne
	@JoinColumn(name = "id_scene")
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	@Column(name = "fileName")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "fileCreateDate")
	public Date getFileCreateDate() {
		return fileCreateDate;
	}
	public void setFileCreateDate(Date fileCreateDate) {
		this.fileCreateDate = fileCreateDate;
	}

	@Temporal(value = TemporalType.DATE)
	@Column(name = "recordedDate")
	public Date getRecordedDate() {
		return recordedDate;
	}
	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}
	
	@Column(name = "fileExtension")
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	@Column(name = "fullFilename")
	public String getFullFilename() {
		return fullFilename;
	}
	public void setFullFilename(String fullFilename) {
		this.fullFilename = fullFilename;
	}
	
	@Column(name = "filesize")
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	@Temporal(value = TemporalType.DATE)
	@Column(name = "fileImportDate")
	public Date getFileImportDate() {
		return fileImportDate;
	}
	public void setFileImportDate(Date fileImportDate) {
		this.fileImportDate = fileImportDate;
	}
	
	@Column(name = "version")
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}

