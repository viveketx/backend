package com.cdac.Repository;

import java.util.List;

import com.cdac.Entity.Plan;

public interface PlanRepo {
	
	Plan findById(int subplanId);

	List<Plan> findAll();

	Plan save(Plan pl);

	Plan update(Plan pl);

	void deleteById(int subplanId);
}
