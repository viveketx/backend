package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.Entity.GymMember;

public interface GymMemberDao extends JpaRepository<GymMember, Integer> {

}
