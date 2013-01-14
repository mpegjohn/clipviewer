package com.vidlib.clipviewer;


/**
 * @author john
 * An object containing the url and the timestamp of an image. 
 */
public class ImageItem {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	private String timeStamp;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
