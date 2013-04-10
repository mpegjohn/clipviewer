package com.vidlib.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vidlib.domain.Scene;
import com.vidlib.domain.Thumbnail;

@Component
public class FileStoreServiceImpl implements FileStoreService {

	@Override
	public Scene AddThumbnailsUrls(Scene scene) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(scene.getMedia().getImportDate());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		int number = scene.getSceneNumber();
		
		String index = String.format("%05d", number);
		
		String path = String.format("/%d/%d/%d/media_%d/media_%d_%d", year, month, day,scene.getMedia().getId_media(),scene.getMedia().getId_media(),index);

		List<Thumbnail> thumbs = scene.getThumbnails();
		
		for(Thumbnail thumb : thumbs)
		{
			thumb.setPath(path);
		}

		return scene;
	}

}
