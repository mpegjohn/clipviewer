package com.vidlib.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table (name = "scene")
@NamedQueries({
	@NamedQuery(name="Scene.Get_Last_Scene_number", query="select max(s.sceneNumber) from Scene s where s.media.id_media = :id")
})
public class Scene implements Serializable
{
	private long id_scene;
	private Media media;
	private int sceneNumber;
	private int version;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_scene")
	public long getIdScene() {
		return id_scene;
	}
	public void setIdScene(long id_scene) {
		this.id_scene = id_scene;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_media")
	public Media getMedia() {
		return this.media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	
	private List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
	
	@OneToMany(mappedBy = "scene", cascade=CascadeType.ALL,
			orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Thumbnail> getThumbnails() {
		return this.thumbnails;
	}
	public void setThumbnails(List<Thumbnail> thumbnails) {
		this.thumbnails = thumbnails;
	}
	
	public void addThumbnail(Thumbnail thumbnail) {
		thumbnail.setScene(this);
		this.thumbnails.add(thumbnail);
	}
	
	private Property property;
	
	@OneToOne(mappedBy="scene", cascade=CascadeType.ALL,
			orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	
	public void addProperty(Property property) {
		property.setScene(this);
		this.property = property;
	}
	
	@Column(name = "sceneNumber")
	public int getSceneNumber() {
		return sceneNumber;
	}

	public void setSceneNumber(int sceneNumber) {
		this.sceneNumber = sceneNumber;
	}
	
	@Column(name = "version")
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
