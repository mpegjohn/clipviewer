package com.vidlib.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vidlib.domain.Scene;

//PagingAndSortingRepository
public interface SceneRepository extends CrudRepository<Scene, Long> {

	public final static String FIND_BY_MEDIA_ID = "SELECT s"
			+ " FROM Scene s WHERE s.media.id_media = :id";
	
	public final static String FIND_BY_ID_LIST = "SELECT s"
			+ " FROM Scene s WHERE s.idScene IN (:id)";
	
	@Query(FIND_BY_MEDIA_ID)
	public List<Scene> findByMedia(@Param("id") Long id);

	@Query(FIND_BY_MEDIA_ID)
	public Page<Scene> findByMediaPageable(@Param("id") Long id, Pageable page);
	
	@Query(FIND_BY_ID_LIST)
	public List<Scene> findByIdScene(@Param("id") List<Long> id);

	@Query(FIND_BY_ID_LIST)
	public Page<Scene> findByIdScenePageable(@Param("id") List<Long> id, Pageable page);
}
