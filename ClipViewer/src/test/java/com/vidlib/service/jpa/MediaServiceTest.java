package com.vidlib.service.jpa;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.vidlib.domain.Media;
import com.vidlib.domain.Property;
import com.vidlib.domain.Scene;
import com.vidlib.domain.Thumbnail;
import com.vidlib.service.MediaService;

public class MediaServiceTest extends AbstractServiceTest{

	@Autowired
	MediaService mediaService;
	
	@Test
	@DatabaseSetup("empty_media.xml")
	@DatabaseTearDown("empty_media.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "first_media.xml")
	public void TestCreateMedia()
	{
		//deleteFromTables("media", "property", "scene", "notes");
		simpleJdbcTemplate.getJdbcOperations().execute("ALTER TABLE media AUTO_INCREMENT=1");
		Media media = new Media();
		media.setName("First media");
		media.setId_media(1l);
		//media.setImportDate(new Date(113,1,26,0,0,0));
		Calendar cal = new GregorianCalendar(2013, 0, 2,1,2,3);
		
		Date dt = cal.getTime();
		
		media.setImportDate(dt);
		
		mediaService.save(media);
		em.flush();
	}

	@Test
	@DatabaseSetup("first_media.xml")
	@DatabaseTearDown("empty_media.xml")
	public void TestAddScene()
	{
		simpleJdbcTemplate.getJdbcOperations().execute("ALTER TABLE scene AUTO_INCREMENT=1");
		Media media = mediaService.findById(1);

		int count = mediaService.getLastSceneCount(media.getId_media());

		// Check that a non existing scene returns zero for a last scene number
		assertEquals(0, count);
		
		Scene scene = new Scene();
		scene.setSceneNumber(1);

		media.addScene(scene);

		scene = new Scene();
		
		count = mediaService.getLastSceneCount(media.getId_media());
		
		assertEquals(1, count);
		
		scene.setSceneNumber(count+1);
		
		media.addScene(scene);
		
		mediaService.save(media);
		
		assertEquals(2, countRowsInTable("scene"));
	}
	
	@Test
	@DatabaseSetup("list_of_media.xml")
	@DatabaseTearDown("empty_media.xml")
	public void TestFindAll()
	{
		List<Media> mediaList = mediaService.findAll();
		
		assertEquals(10, mediaList.size());
	}
	
	@Test
	@DatabaseSetup("first_media.xml")
	@DatabaseTearDown("empty_media.xml")
	public void TestReadMedia()
	{
		Media media = mediaService.findById(1);
		assertEquals("First media", media.getName());
		
		Calendar cal = new GregorianCalendar(2013, 0, 2,1,2,3);
		
		Date act = media.getImportDate();
		
		Calendar actDt = new GregorianCalendar();
		actDt.setTime(act);
		
		assertEquals(cal,actDt);
	}
	
	@Test
	@DatabaseSetup("empty_media.xml")
	public void TestNoMedia()
	{
		//deleteFromTables("media", "property", "scene", "notes");
		Media media = mediaService.findById(1);
		assertNull(media);
	}

	@Test
	@DatabaseSetup("with_scenes.xml")
	@DatabaseTearDown("empty_media.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "with_thumbs.xml")
	public void TestAddThumbnail()
	{
		simpleJdbcTemplate.getJdbcOperations().execute("ALTER TABLE thumbnail AUTO_INCREMENT=1");
			
		Media media = mediaService.findById(1l);
		
		List<Scene> scenes = media.getScenes();
		
		Scene scene = scenes.get(0);
		
		for(int i = 1; i < 10; i++)
		{
			Thumbnail thumbnail = new Thumbnail();
			thumbnail.setImage("image " + i);
			thumbnail.setImageOrderNumber(i);
			thumbnail.setImageTime("fred");
			thumbnail.setImageSize(100);
			
			scene.addThumbnail(thumbnail);
		}
		
		mediaService.save(media);
		em.flush();
	}
	
	@Test
	@DatabaseSetup("with_thumbs.xml")
	@DatabaseTearDown("empty_media.xml")
	public void TestReadThumbnails()
	{			
		Media media = mediaService.findById(1l);
		
		List<Scene> scenes = media.getScenes();
		
		assertEquals(10, scenes.size());
		
		Scene scene = scenes.get(0);
	
		List<Thumbnail> thumbs = scene.getThumbnails();
		
		assertEquals(9, thumbs.size());
	}
}
