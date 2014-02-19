package com.vidlib.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class PropertyTest {
	
	private Property property;

	@Before
	public void setup() {
		this.property = new Property();
	}
	
	@Test
	public void testId_property() {
		property.setId_property(1l);
		assertEquals(1l, property.getId_property());
	}
	
	@Test
	public void testSetFileName() {
		property.setFileName("a_filename");
		assertEquals("a_filename", property.getFileName());
	}
	
	@Test
	public void testFileExtension() {
		property.setFileExtension("mpg");
		assertEquals("mpg", property.getFileExtension());
	}
	
	@Test
	public void testFullFilename() {
		property.setFullFilename("a_full_filename");
		assertEquals("a_full_filename", property.getFullFilename());
	}

	@Test
	public void testFilesize() {
		property.setFilesize(1258l);
		assertEquals(1258l, property.getFilesize());
	}
	
	@Test
	public void testVersion() {
		property.setVersion(136);
		assertEquals(136, property.getVersion());
	}
	
	@Test
	public void testCreateDate() {
		Date fileCreateDate = new Date();
		property.setFileCreateDate(fileCreateDate);
		assertTrue("fileCreation date OK", fileCreateDate.equals(property.getFileCreateDate()));
	}
	
	@Test
	public void testFileImportDate() {
		Date fileImportDate = new Date();
		property.setFileImportDate(fileImportDate);
		assertTrue("fileImportDate date OK", fileImportDate.equals(property.getFileImportDate()));
	}
	
	@Test
	public void testRecordedDate() {
		Date recordedDate = new Date();
		property.setRecordedDate(recordedDate);
		assertTrue("recorededDate date OK", recordedDate.equals(property.getRecordedDate()));
	}
} 
