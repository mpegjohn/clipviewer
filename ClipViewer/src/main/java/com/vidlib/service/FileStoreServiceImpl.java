package com.vidlib.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		month = month +1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		int number = scene.getSceneNumber();
		
		String index = String.format("%05d", number);
		
		
		String path = String.format("thumbnails/%d/%02d/%02d/media-%d/media-%d-%s", year, month, day,scene.getMedia().getId_media(),scene.getMedia().getId_media(),index);

		List<Thumbnail> thumbs = scene.getThumbnails();
		Pattern pattern = Pattern.compile("(image\\[\\d+\\].jpg)");
		
		for(Thumbnail thumb : thumbs)
		{
			Matcher matcher = pattern.matcher(thumb.getImage());
			if(matcher.find())
			{
				thumb.setImage(matcher.group(1));
			}
			thumb.setPath(path);
		}

		return scene;
	}

}
