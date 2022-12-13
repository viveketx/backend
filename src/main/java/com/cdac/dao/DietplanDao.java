package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.Entity.Dietplan;

public interface DietplanDao extends JpaRepository<Dietplan, Integer> {

}
