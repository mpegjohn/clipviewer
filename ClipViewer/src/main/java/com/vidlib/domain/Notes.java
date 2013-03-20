package com.vidlib.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "notes")
public class Notes implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id_notes;
	private int id_scene;
	private String operator;
	private String description;
	private String noteName;
	private String location;
	private int dateChanged;
	private int id_importer;
	private int id_cameraman;
	private int id_location;
	private String title;
	private String headline;
	private String caption;
	private String keywords;
	private int starrating;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_notes")
	public long getId_notes() {
		return id_notes;
	}
	public void setId_notes(long id_notes) {
		this.id_notes = id_notes;
	}
	
	@Column(name = "id_scene")
	public int getId_scene() {
		return id_scene;
	}
	public void setId_scene(int id_scene) {
		this.id_scene = id_scene;
	}
	
	@Column(name = "operator")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "noteName")
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
	@Column(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "dateChanged")
	public int getDateChanged() {
		return dateChanged;
	}
	public void setDateChanged(int dateChanged) {
		this.dateChanged = dateChanged;
	}
	
	@Column(name = "id_importer")
	public int getId_importer() {
		return id_importer;
	}
	public void setId_importer(int id_importer) {
		this.id_importer = id_importer;
	}
	
	@Column(name = "id_cameraman")
	public int getId_cameraman() {
		return id_cameraman;
	}
	public void setId_cameraman(int id_cameraman) {
		this.id_cameraman = id_cameraman;
	}
	
	@Column(name = "id_location")
	public int getId_location() {
		return id_location;
	}
	public void setId_location(int id_location) {
		this.id_location = id_location;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "headline")
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	@Column(name = "caption")
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	@Column(name = "keyworda")
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Column(name = "starrating")
	public int getStarrating() {
		return starrating;
	}
	public void setStarrating(int starrating) {
		this.starrating = starrating;
	}
}

