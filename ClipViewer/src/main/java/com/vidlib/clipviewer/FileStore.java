package com.vidlib.clipviewer;

import java.util.Date;
import java.util.List;

public interface FileStore {
	
	public List<String> GetThumbnailsUrls(int mediaId, int sceneId, Date CreateDate);
}
