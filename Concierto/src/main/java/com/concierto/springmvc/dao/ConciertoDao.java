package com.concierto.springmvc.dao;

import java.util.List;

import com.concierto.springmvc.model.Concierto;

public interface ConciertoDao {
	Concierto findById(int id);

	void save(Concierto concierto);

	void delete(Concierto concierto);

	List<Concierto> findAll();
	
	void update(Concierto concierto);
	
	List<Concierto> filtra(Concierto concierto);
}
