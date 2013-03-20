package com.vidlib.service.jpa;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vidlib.domain.Scene;
import com.vidlib.service.SceneService;
import com.vidlib.service.repository.SceneRepository;

@Service("jpaSceneService")
@Repository
@Transactional
public class SceneServiceJpa implements SceneService {

	@Autowired
	SceneRepository sceneRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	
	/* (non-Javadoc)
	 * @see com.vidlib.service.jpa.Scene#find(long)
	 */
	@Override
	@Transactional(readOnly=true)
	public Scene find(long id)
	{
		Scene scene = em.find(Scene.class, id);
		return scene;
	}

	@Transactional(readOnly=true)
	public List<Scene> FindByMediaId(long id)
	{
		List<Scene> list = sceneRepo.findByMedia(id);
		
		return list;		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Scene> FindByIdScene(List<Long> id) {
		
		List<Scene> list = sceneRepo.findByIdScene(id);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Scene> FindByMediaIdPageable(long id, Pageable pageable) {
		Page<Scene> page = sceneRepo.findByMediaPageable(id, pageable);
		return page;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Scene> FindByIdScenePageable(List<Long> id, Pageable pageable) {
		Page<Scene> page = sceneRepo.findByIdScenePageable(id, pageable);
		return page;
	}
	
}
