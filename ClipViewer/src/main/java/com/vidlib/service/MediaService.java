package com.vidlib.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vidlib.domain.Media;

public interface MediaService {

	@Transactional(readOnly = true)
	public abstract List<Media> findAll();

	@Transactional(readOnly = true)
	public abstract int getLastSceneCount(long mediaId);

	@Transactional(readOnly = true)
	public abstract Media findById(long id);

}