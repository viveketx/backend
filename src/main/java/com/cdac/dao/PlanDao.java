package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.Entity.Plan;

public interface PlanDao extends JpaRepository<Plan, Integer> {

}
