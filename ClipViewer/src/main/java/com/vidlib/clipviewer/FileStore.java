package com.vidlib.clipviewer;

import java.util.Date;
import java.util.List;

import com.vidlib.domain.Scene;

public interface FileStore {
	
	public Scene GetThumbnailsUrls(Scene scene);
}
