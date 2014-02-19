package com.vidlib.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NotesTest {
	
	Notes notes;
	
	@Before
	public void setup() {
		this.notes = new Notes();
	}

	@Test
	public void testId_notes() {
		notes.setId_notes(22l);
		assertEquals(22l, notes.getId_notes());
	}

	@Test
	public void testId_scene() {
		notes.setId_scene(33);
		assertEquals(33, notes.getId_scene());
	}

	@Test
	public void testOperator() {
		notes.setOperator("myself");
		assertEquals("myself", notes.getOperator());
	}

	@Test
	public void testDescription() {
		notes.setDescription("A short description");
		assertEquals("A short description", notes.getDescription());
	}

	@Test
	public void testNoteName() {
		notes.setNoteName("A fine note to make");
		assertEquals("A fine note to make", notes.getNoteName());
	}

	@Test
	public void testLocation() {
		notes.setLocation("London");
		assertEquals("London", notes.getLocation());
	}

	@Test
	public void testDateChanged() {
		notes.setDateChanged(1);
		assertEquals(1, notes.getDateChanged());
	}

	@Test
	public void testId_importer() {
		notes.setId_importer(66);
		assertEquals(66, notes.getId_importer());
	}

	@Test
	public void testId_cameraman() {
		notes.setId_cameraman(44);
		assertEquals(44, notes.getId_cameraman());
	}

	@Test
	public void testId_location() {
		notes.setId_location(123);
		assertEquals(123, notes.getId_location());
	}

	@Test
	public void testTitle() {
		notes.setTitle("A title");
		assertEquals("A title", notes.getTitle());
	}

	@Test
	public void testHeadline() {
		notes.setHeadline("A headline");
		assertEquals("A headline", notes.getHeadline());
	}

	@Test
	public void testCaption() {
		notes.setCaption("A caption");
		assertEquals("A caption", notes.getCaption());
	}

	@Test
	public void testKeywords() {
		notes.setKeywords("Summer");
		assertEquals("Summer", notes.getKeywords());
	}

	@Test
	public void testStarrating() {
		notes.setStarrating(679);
		assertEquals(679, notes.getStarrating());
	}
}
