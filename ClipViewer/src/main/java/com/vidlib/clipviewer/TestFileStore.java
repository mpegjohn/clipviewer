package com.vidlib.clipviewer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TestFileStore implements FileStore {

	@Override
	public List<String> GetThumbnailsUrls(int mediaId, int sceneId, Date CreateDate) {
		
		ArrayList<String> fileNames = new ArrayList<String>();
		
		for(int i = 1 ; i<22; i++)
		{
			fileNames.add("image[" + i + "].jpg");			
		}
		
		return fileNames;
	}

}
