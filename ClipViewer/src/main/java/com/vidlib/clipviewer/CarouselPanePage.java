package com.vidlib.clipviewer;

import java.util.ArrayList;
import java.util.List;

public class CarouselPanePage {

	List<Long> sceneIds;
	
	int numPages;
	
	public CarouselPanePage() {
		this.sceneIds = new ArrayList<Long>();
	}

	public List<Long> getSceneIds() {
		return sceneIds;
	}

	public void setSceneIds(List<Long> sceneIds) {
		this.sceneIds = sceneIds;
	}
	
	public void addSceneId(Long id)
	{
		this.sceneIds.add(id);
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

}
