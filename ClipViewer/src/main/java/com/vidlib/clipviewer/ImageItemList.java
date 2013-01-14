package com.vidlib.clipviewer;

import java.util.List;

/***
 * 
 * @author john
 * An object containing a list of image items, and a total.
 *
 */
public class ImageItemList {
	
	private int total;
	
	private List<ImageItem> imageList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ImageItem> getImageList() {
		return imageList;
	}

	public void setImageList(List<ImageItem> imageList) {
		this.imageList = imageList;
	}
}
