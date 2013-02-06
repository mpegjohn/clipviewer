package com.vidlib.clipviewer;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MediaDAO implements InitializingBean {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	@Resource(name="dataSource")
	public void setDatasource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}
	
	public String findFirstMediaName()
	{
		String name = jdbcTemplate.queryForObject("SELECT name FROM media WHERE id_media = ?", new Object[]{1}, String.class);
		return name;
	}
	
	
	public void afterPropertiesSet() throws Exception{
		
		if (dataSource == null)
		{
			throw new BeanCreationException("Must set datasource");
		}
		
	}
}
