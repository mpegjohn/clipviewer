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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.vidlib.domain.Media;
import com.vidlib.domain.Property;
import com.vidlib.domain.Scene;
import com.vidlib.domain.Thumbnail;

import com.vidlib.service.SceneService;

public class SceneServiceTest extends AbstractServiceTest{

	@Autowired
	SceneService sceneService;
	
	@Test
	@DatabaseSetup("with_thumbs.xml")
	public void TestGetScene()
	{

		Scene scene = sceneService.find(1L);
		
		assertNotNull("Scene found and not null",scene);
	}
	
	@Test
	@DatabaseSetup("with_thumbs.xml")
	public void TestSceneThumbnails()
	{

		Scene scene = sceneService.find(1L);
		
		assertEquals("Scene has correct number of thumbnails",9, scene.getThumbnails().size());
	}
	
	@Test
	@DatabaseSetup("with_thumbs.xml")
	public void TestSceneMedia()
	{

		Scene scene = sceneService.find(1L);
		
		Media media = scene.getMedia();
		
		assertNotNull(media);
		
		long mId = media.getId_media();
		
		assertEquals("Scene has a media object",1l, mId);
	}
	
	@Test
	@DatabaseSetup("with_thumbs.xml")
	public void TestGetFromMediaId()
	{		
		List<Scene> list = sceneService.FindByMediaId(1l);
		
		assertEquals("Got all the scenes for this media",10, list.size());		
	}
	
	@Test
	@DatabaseSetup("with_thumbs.xml")
	public void TestGetFromSceneId()
	{
		List<Long> list = new ArrayList<Long>();
		list.add(3L);
		list.add(4L);
				
		List<Scene> sceneList = sceneService.FindByIdScene(list);
		
		assertEquals(2, sceneList.size());
		assertEquals(3, sceneList.get(0).getIdScene());
		assertEquals(4, sceneList.get(1).getIdScene());
	}

	@Test
	@DatabaseSetup("with_thumbs.xml")
	public void TestGetFirstMediaIdPageable()
	{	
		PageRequest pageRequest = new PageRequest(0, 4);
		Page<Scene> page = sceneService.FindByMediaIdPageable(1L, pageRequest);
		
		assertEquals(true, page.isFirstPage());
		assertEquals("Got the first page of 4 scenes",4, page.getSize());
		assertEquals("There are 3 pages",3, page.getTotalPages());
		assertEquals("There are 2 scenes to this page",4, page.getContent().size());
	}

	@Test
	@DatabaseSetup("with_thumbs.xml")
	public void TestGetLastMediaIdPageable()
	{	
		PageRequest pageRequest = new PageRequest(2, 4);
		Page<Scene> page = sceneService.FindByMediaIdPageable(1L, pageRequest);
		
		assertEquals("Got the last page of 2 scenes",true, page.isLastPage());
		assertEquals("There are 2 scenes to this page",2, page.getContent().size());
	}


}
