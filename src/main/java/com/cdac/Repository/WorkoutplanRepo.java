package com.cdac.Repository;

import java.util.List;

import com.cdac.Entity.Workoutplan;


public interface WorkoutplanRepo {

	Workoutplan findById(int workoutid);

	List<Workoutplan> findAll();

	Workoutplan save(Workoutplan wp);

	void deleteById(int workoutid);
}
