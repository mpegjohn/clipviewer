package com.vidlib.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ThumbnailTest {

	@Test

	public void testThumbnail() {
		Thumbnail thumbnail = new Thumbnail();
		Scene scene = new Scene();
		
		thumbnail.setId_thumbnail(1l);
		thumbnail.setImageOrderNumber(789);
		thumbnail.setImageSize(2048);
		thumbnail.setImageTime("10.0.45");
		thumbnail.setImage("The image");
		thumbnail.setPath("/the/path");
		thumbnail.setScene(scene);
		
		assertEquals(1l, thumbnail.getId_thumbnail());
		assertEquals(789,thumbnail.getImageOrderNumber());
		assertEquals(2048, thumbnail.getImageSize());
		assertEquals("10.0.45", thumbnail.getImageTime());
		assertEquals("The image", thumbnail.getImage());
		assertEquals("/the/path", thumbnail.getPath());
		assertEquals(scene, thumbnail.getScene());
		
	}

}
