package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.Entity.Workoutplan;

public interface WorkoutplanDao extends JpaRepository<Workoutplan , Integer> {

}
