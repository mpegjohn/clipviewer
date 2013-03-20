package com.vidlib.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vidlib.domain.Media;
import com.vidlib.domain.Contact;


@Service("jpaMediaService")
@Repository
@Transactional
public class MediaService {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<Media> findAll() {
		List<Media> media = em.createNamedQuery("Media.findAll", Media.class).getResultList();
		return media;
	}
	
	@Transactional(readOnly=true)
	public int getLastSceneCount(long mediaId)
	{
		TypedQuery<Integer> query = em.createNamedQuery("Scene.Get_Last_Scene_number",Integer.class);
		
		query.setParameter("id", mediaId);
		
		int last_value;
		try
		{
			last_value = query.getSingleResult();
		}
		catch(NullPointerException ex)
		{
			last_value = 0;
		}
		
		return last_value;
		
	}
	
	@Transactional(readOnly=true)
	public Media findById(long id)
	{
		Media media = em.find(Media.class, id);
		return media;		
	}

	public Media save(Media media)
	{
		if(media.getId_media() == null)
		{
			em.persist(media);
		}
		else
		{
			em.merge(media);
		}
		return media;
	}
}
