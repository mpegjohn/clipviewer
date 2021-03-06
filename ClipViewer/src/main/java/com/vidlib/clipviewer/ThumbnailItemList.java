package com.vidlib.clipviewer;

import java.util.List;

import com.vidlib.domain.Thumbnail;

/***
 * 
 * @author John
 * An object containing a list of thumbnails, and a total.
 *
 */
public class ThumbnailItemList {
	
	private int total;
	
	private List<Thumbnail> thumbnailList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Thumbnail> getThumbnailList() {
		return thumbnailList;
	}

	public void setThumbnailList(List<Thumbnail> thumbnailList) {
		this.thumbnailList = thumbnailList;
	}
}
