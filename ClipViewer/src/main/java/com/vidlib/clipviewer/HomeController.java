package com.vidlib.clipviewer;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.vidlib.domain.Media;
import com.vidlib.domain.Scene;
import com.vidlib.domain.Thumbnail;
import com.vidlib.service.FileStoreService;
import com.vidlib.service.MediaService;
import com.vidlib.service.SceneService;


/**
 * Handles requests for the application home page.
 */
/**
 * @author john
 *
 */

@Controller
public class HomeController {
	
	@Autowired
	SceneService sceneService;

	@Autowired
	MediaService mediaService;
	
	@Autowired
	private FileStoreService fileStore;

	/**
	 * currentCarouselPage is a Page collection that holds a list of scenes for the current 
	 * selected page.
	 */
	private Page<Scene> currentCarouselPage;
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	/**
	 * The UI requests a specific page of caraousels to show. It foes this by setting the page
	 * number and the number of scenes /carousels to display.
	 * This method thatn retrieves the correct list of scenes for tis page.
	 * <p>
	 * For each scene it must construct the correct URL to retrieve the thumbnails.
	 * It does this by using the filestore  AddThumbnailsUrls method, that adds the list of 
	 * Thumbnail URL's to each scene. This is stored in the currentCarouselPage.
	 * <p>
	 * The CarouselPanePage returned contains a list of scene ID's and the number of pages.
	 * This allows the UI to create the correct number of carousels and give each the correct scene ID
	 * As well as setting up the pagination control.
	 * 
	 * @param mediaId The selected media ID to show scenes from
	 * @param page The current page number
	 * @param size The number of scenes in this page
	 * @return A JSON object derived from CarouselPanePage class
	 * @see CarouselPanePage
	 */
	@RequestMapping(value = "/media/{id}", method = (RequestMethod.GET), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CarouselPanePage getCrouselPage(@PathVariable("id") long mediaId, @RequestParam int page, @RequestParam int size)
	{
		page = page -1;
		PageRequest pageRequest = new PageRequest(page, size);
		
		// Get all the scenes for this media and page 
		this.currentCarouselPage = sceneService.FindByMediaIdPageable(mediaId, pageRequest);
		
		CarouselPanePage carouselPane = new CarouselPanePage();
		
		// iterate over the scenes and add the thumbnail url to each,
		// also for each scene get its ID and add to the list of id's for this page.
		for(Scene scene : this.currentCarouselPage)
		{
			//Scene scene = this.currentCarouselPage.getContent().get(i);
			scene = fileStore.AddThumbnailsUrls(scene);
			carouselPane.addSceneId(scene.getIdScene());
		}
		carouselPane.numPages = this.currentCarouselPage.getTotalPages();
		
		return carouselPane;
	}
	
	/**
	 * When a carousel comes to load data, it calls this method, to get a list of thumbnails
	 * and a total.
	 * @param first The number of the first thumbnail to get
	 * @param last the number of the last thumbnail to get
	 * @param id the scene ID to which the thumbnails belong
	 * @return a JSON object that is a list of thumbnails and a total.
	 */
	@RequestMapping(value = "/thumbnails", method = (RequestMethod.GET), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ThumbnailItemList listJSON(@RequestParam int first, @RequestParam int last, @RequestParam long id) {

		logger.info("Getting thumbnails");
		logger.info("First:" + first + " last:" + last + " id:" + id);

		// Get all the thumbnails for this scene
		List<Thumbnail> thumbs = getThumbnailsFromPage(id);

		// Ensure the last item does not go past the end of the list
		if(last > thumbs.size()-1)
		{
			last = thumbs.size()-1;
		}
		
		// We only want a subset that we need to display.
		List<Thumbnail> slice = thumbs.subList(first-1, last);

		ThumbnailItemList theList = new ThumbnailItemList();
		
		// Create the object to send back to the UI
		theList.setThumbnailList(slice);
		theList.setTotal(thumbs.size());

		return theList;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		List<Media> mediaList = mediaService.findAll();
		
		//sceneService.find(1L);
		
		//String name = dao.findFirstMediaName();
		
		//int [] scene_id = new int[3];
		
		//scene_id[0] = 1;
		//scene_id[1] = 2;
		//scene_id[2] = 3;
		
		model.addAttribute("mediaList",mediaList );
		
		//PageRequest pageRequest = new PageRequest(0, 20);
		
		//this.currentCarouselPage = sceneService.FindByMediaIdPageable(1, pageRequest);
		
		return "home";
	}

	private List<Thumbnail> getThumbnailsFromPage(long sceneId)
	{
		for(Scene scene : currentCarouselPage)
		{
			if(scene.getIdScene() == sceneId)
			{
				return scene.getThumbnails();
			}
		}
		return null;
	}
}
