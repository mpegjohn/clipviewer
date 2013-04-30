package com.vidlib.domain;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "media")
@NamedQueries({
		@NamedQuery(name="Media.findAll", query="select m from Media m")
})

public class Media implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id_media;
	private Long id_project;
	private int type;
	private String name;
	private Date importDate;
	private String comment;
	private int numScenes;
	private Long size;
	private String serial_number;
	private String location;
	private int id_serialnumber;
	private int version;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_media")
	public Long getId_media() {
		return id_media;
	}
	public void setId_media(Long id_media) {
		this.id_media = id_media;
	}
	
	@Column(name = "id_project")
	@JsonIgnore
	public Long getId_project() {
		return id_project;
	}
	public void setId_project(Long id_project) {
		this.id_project = id_project;
	}
	
	@Column(name = "type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "importDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getImportDate() {
		return importDate;
	}
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	
	@Column(name = "comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column(name = "numScenes")
	public int getNumScenes() {
		return numScenes;
	}
	public void setNumScenes(int numScenes) {
		this.numScenes = numScenes;
	}
	
	@Column(name = "size")
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	@Column(name = "serial_number")
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	
	@Column(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "id_serialnumber")
	public int getId_serialnumber() {
		return id_serialnumber;
	}
	public void setId_serialnumber(int id_serialnumber) {
		this.id_serialnumber = id_serialnumber;
	}
	
	@Column(name = "version")
	@JsonIgnore
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	private List<Scene> scenes = new ArrayList<Scene>();
	
	@OneToMany(mappedBy = "media", cascade=CascadeType.ALL,
			orphanRemoval=true, fetch=FetchType.LAZY)
	//@LazyCollection(LazyCollectionOption.TRUE)
	@JsonIgnore
	public List<Scene> getScenes() {
		return this.scenes;
	}
	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}
	
	public void addScene(Scene scene) {
		scene.setMedia(this);
		this.getScenes().add(scene);
	}
	
	public void removeScene(Scene scene) {
		this.getScenes().remove(scene);
	}
	
}
