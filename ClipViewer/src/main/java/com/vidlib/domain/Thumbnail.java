package com.vidlib.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table (name = "thumbnail")
public class Thumbnail implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id_thumbnail;
	private Scene scene;
	private String image;
	private String imageTime;
	private int imageSize;
	private int imageOrderNumber;
	private String path;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_thumbnail")
	@JsonIgnore
	public long getId_thumbnail() {
		return id_thumbnail;
	}
	public void setId_thumbnail(long id_thumbnail) {
		this.id_thumbnail = id_thumbnail;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_scene")
	@JsonIgnore
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Column(name = "image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Transient
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "imageTime")
	public String getImageTime() {
		return imageTime;
	}
	public void setImageTime(String imageTime) {
		this.imageTime = imageTime;
	}
	
	@Column(name = "imageSize")
	@JsonIgnore
	public int getImageSize() {
		return imageSize;
	}
	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
	}
	
	@Column(name = "imageOrderNumber")
	@JsonIgnore
	public int getImageOrderNumber() {
		return imageOrderNumber;
	}
	public void setImageOrderNumber(int imageOrderNumber) {
		this.imageOrderNumber = imageOrderNumber;
	}
}

