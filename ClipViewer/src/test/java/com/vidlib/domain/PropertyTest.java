package com.vidlib.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PropertyTest {

	@Test
	public void TestProperty() {

		Property property = new Property();

		property.setId_property(1l);
		property.setFileName("a_filename");
		property.setFileExtension("mpg");
		property.setFullFilename("a_full_filename");
		property.setFilesize(1258l);
		property.setVersion(136);

		assertEquals(1l, property.getId_property());
		
		
	}
} 
