package com.vidlib.clipviewer;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	@RequestMapping(value = "/thumbnails", 
            method = ( RequestMethod.GET ), 
            produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody ImageItemList listJSON(@RequestParam int first , @RequestParam int last )
	{
		
		logger.info("Getting thumbnails");
		
		ImageItemList theList = new ImageItemList();
		
		ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();
		
		for (int i = 1; i < 4; i++) {
			ImageItem item = new ImageItem();
			
			item.setUrl("/image[" + (i) + "].jpg");
			item.setTimeStamp("00:00:" + (i));
			imageItems.add(item);
		}
		
		theList.setImageList(imageItems);
		theList.setTotal(imageItems.size());
		
		return theList;
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		// from
		// http://stackoverflow.com/questions/2792731/how-to-do-an-array-of-hashmaps
		List<List<Map<String, String>>> listOfListOfMaps = new ArrayList<List<Map<String, String>>>();

		ArrayList<Map<String, String>> listOfMaps = null;

		for (int a = 0; a < 2; a++) {

			Map<String, String> imageMap = null;
			listOfMaps = new ArrayList<Map<String, String>>();
			
			for (int i = 1; i < 7; i++) {

				imageMap = new HashMap<String, String>();
				
				
				imageMap.put("imagePath", "/resources/image[" + ((a*5)+i) + "].jpg");
				imageMap.put("imageTime", "00:00:" + ((a*5)+i));

				listOfMaps.add(imageMap);

			}
			listOfListOfMaps.add(listOfMaps);
		}
		model.addAttribute("thumbs", listOfListOfMaps);
		return "home";
	}

}
