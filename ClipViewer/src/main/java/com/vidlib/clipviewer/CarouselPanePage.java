package com.vidlib.clipviewer;

import java.util.ArrayList;
import java.util.List;

public class CarouselPanePage {

	List<Integer> sceneIds;
	
	int numPages;
	
	public CarouselPanePage() {
		this.sceneIds = new ArrayList<Integer>();
	}

	public List<Integer> getSceneIds() {
		return sceneIds;
	}

	public void setSceneIds(List<Integer> sceneIds) {
		this.sceneIds = sceneIds;
	}
	
	public void addSceneId(int id)
	{
		this.sceneIds.add((Integer)id);
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

}
