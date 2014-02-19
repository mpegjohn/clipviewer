package com.vidlib.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class SceneTest {

	private Scene scene;
	
	@Before
	public void setup() {
		this.scene = new Scene();
	}

	@Test
	public void testThumbnail() {
		List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
		
		
		for(int i=0; i<10; i++)
		{
			Thumbnail thumbnail = new Thumbnail();
			thumbnail.setId_thumbnail(i);
			thumbnails.add(thumbnail);
		}
		scene.setThumbnails(thumbnails);
		
		assertTrue("Both Thumbnail lists are the same",thumbnails.equals(scene.getThumbnails()));
	}
	
	@Test
	public void testIdScene() {
		scene.setIdScene(1l);
		assertEquals(1l, scene.getIdScene());
	}
	
	@Test
	public void testMedia() {
		Media media = new Media();
		scene.setMedia(media);
		assertTrue("Both Media objects are the same", media.equals(scene.getMedia()));
	}
	
	@Test
	public void testProperty() {
		Property property = new Property();
		scene.setProperty(property);
		assertTrue("Both Property objects are the same", property.equals(scene.getProperty()));
	}
	
	@Test
	public void testSceneNumber() {
		scene.setSceneNumber(22);
		assertEquals(22, scene.getSceneNumber());
	}
	
	@Test
	public void testVersion() {
		scene.setVersion(67);
		assertEquals(67, scene.getVersion());
	}
}
