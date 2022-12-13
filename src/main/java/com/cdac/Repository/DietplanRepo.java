package com.cdac.Repository;

import java.util.List;

import com.cdac.Entity.Dietplan;

public interface DietplanRepo {
	
	Dietplan findById(int planid);

	List<Dietplan> findAll();

	Dietplan save(Dietplan dp);

	Dietplan update(Dietplan dp);

	void deleteById(int planid);
}
