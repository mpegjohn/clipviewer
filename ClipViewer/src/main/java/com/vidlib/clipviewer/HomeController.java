package com.vidlib.clipviewer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UrlPathHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private FileStore fileStore;

	@Autowired
	private WebApplicationContext context;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/thumbnails", method = (RequestMethod.GET), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ImageItemList listJSON(@RequestParam int first, @RequestParam int last) {

		logger.info("Getting thumbnails");
		logger.info("First:" + first + "last:" + last);


		List<String> fileNames = fileStore.GetThumbnailsUrls(1, 1, new Date());

		ImageItemList theList = new ImageItemList();

		ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();

		List<String> slice = fileNames.subList(first-1, last);
		
		
		for (int i = 0; i < slice.size(); i++) {
			ImageItem item = new ImageItem();

			logger.info("Adding-" + slice.get(i));
			
			item.setUrl("/" + slice.get(i));
			item.setTimeStamp("00:00:" + i);
			imageItems.add(item);
		}

		theList.setImageList(imageItems);
		theList.setTotal(fileNames.size());

		return theList;
	}

	@RequestMapping(value = "/images/*", method = RequestMethod.GET)
	public void getFileFromStore(HttpServletRequest request, HttpServletResponse response) {
		UrlPathHelper helper = new UrlPathHelper();
		String pathInfo = helper.getServletPath(request);

		String filename = pathInfo.replaceFirst("/.*/", "");

		File file = new File("/home/john/Dropbox/temp_jpgs/", filename);

		response.setContentType(context.getServletContext().getMimeType(
				file.getName()));
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition",
				"inline; filename=\"" + file.getName() + "\"");

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			input = new BufferedInputStream(new FileInputStream(file));
			output = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[8192];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} catch (Exception ex) {

		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException ignore) {
				}
			if (input != null)
				try {
					input.close();
				} catch (IOException ignore) {
				}
		}

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

				imageMap.put("imagePath", "/resources/image[" + ((a * 5) + i)
						+ "].jpg");
				imageMap.put("imageTime", "00:00:" + ((a * 5) + i));

				listOfMaps.add(imageMap);

			}
			listOfListOfMaps.add(listOfMaps);
		}
		model.addAttribute("thumbs", listOfListOfMaps);
		return "home";
	}

}
