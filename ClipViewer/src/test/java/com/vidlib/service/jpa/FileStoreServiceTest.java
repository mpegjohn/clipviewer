package com.vidlib.service.jpa;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import com.vidlib.domain.Scene;
import com.vidlib.domain.Thumbnail;

import com.vidlib.service.FileStoreService;
import com.vidlib.service.SceneService;

public class FileStoreServiceTest extends AbstractServiceTest{

	@Autowired
	FileStoreService fileStoreService;
	
	@Autowired
	SceneService sceneService;
	
	@Test
	@DatabaseSetup("with_real_thumbs.xml")
	public void TestAddThumbnailPath()
	{

		Scene scene = sceneService.find(1L);
		
		Scene newScene = fileStoreService.AddThumbnailsUrls(scene);
		
		assertNotNull("Scene found and not null",newScene);
		
		List<Thumbnail> list = newScene.getThumbnails();
		
		for(int i = 0 ; i< list.size(); i++)
		{
			Thumbnail t = list.get(i);
			
			assertEquals("thumbnail has correct image", "image[" + (i+1) +"].jpg", t.getImage());
			
		}
		
	}
	
	

}
