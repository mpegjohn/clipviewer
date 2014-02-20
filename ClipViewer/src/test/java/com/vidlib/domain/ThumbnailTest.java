package com.vidlib.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ThumbnailTest {

	Thumbnail thumbnail;
	
	@Before
	public void setup() {
		this.thumbnail = new Thumbnail();
	}
	
	@Test
	public void testScene() {
		Scene scene = new Scene();
		thumbnail.setScene(scene);
		assertEquals(scene, thumbnail.getScene());
	}
	
	@Test
	public void testId_thumbnail() {
		thumbnail.setId_thumbnail(1l);
		assertEquals(1l, thumbnail.getId_thumbnail());
	}
	
	@Test
	public void testImageOrderNumber() {
		thumbnail.setImageOrderNumber(789);
		assertEquals(789,thumbnail.getImageOrderNumber());
	}
	
	@Test
	public void testImageSize() {
		thumbnail.setImageSize(2048);
		assertEquals(2048, thumbnail.getImageSize());
	}
	
	@Test
	public void testImageTime() {
		thumbnail.setImageTime("10.0.45");
		assertEquals("10.0.45", thumbnail.getImageTime());
	}
	
	@Test
	public void testImage() {
		thumbnail.setImage("The image");
		assertEquals("The image", thumbnail.getImage());
	}
	
	@Test
	public void testPath() {
		thumbnail.setPath("/the/path");
		assertEquals("/the/path", thumbnail.getPath());
	}
}
