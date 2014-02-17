package com.vidlib.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class SceneTest {

	@Test
	public void testScene() {
		Scene scene = new Scene();
		
		scene.setIdScene(1l);
		scene.setSceneNumber(22);
		scene.setVersion(67);
		
		Media media = new Media();
		
		List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
		
		
		for(int i=0; i<10; i++)
		{
			Thumbnail thumbnail = new Thumbnail();
			thumbnail.setId_thumbnail(i);
			thumbnails.add(thumbnail);
		}
		
		Property property = new Property();
		
		scene.setProperty(property);
		scene.setMedia(media);
		scene.setThumbnails(thumbnails);
		
		assertEquals(1l, scene.getIdScene());
		assertEquals(22, scene.getSceneNumber());
		assertEquals(67, scene.getVersion());
		
		List<Thumbnail> actualList = scene.getThumbnails();
		
		assertTrue("Both Thumbnail lists are the same", actualList.equals(thumbnails));
	}

}
