package com.cdac.Repository;

import java.util.List;

import com.cdac.Entity.GymMember;

public interface GymMemberRepo {

	GymMember findById(int memberId );
	
	List<GymMember> findAll();

	GymMember save(GymMember gm);

	void deleteById(int memberId);
}
