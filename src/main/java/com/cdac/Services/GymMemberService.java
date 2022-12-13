package com.cdac.Services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.Entity.GymMember;
import com.cdac.Repository.GymMemberRepo;
import com.cdac.dao.GymMemberDao;

@Transactional
@Service
public class GymMemberService implements GymMemberRepo{
	
	@Autowired 
	private GymMemberDao gymMemberDao;

	@Override
	public GymMember findById(int memberId) {
		Optional<GymMember> b = gymMemberDao.findById(memberId);
		return b.orElse(null);
	}

	@Override
	public List<GymMember> findAll() {
		return gymMemberDao.findAll();
	}

	@Override
	public GymMember save(GymMember gm) {
		return gymMemberDao.save(gm);
	}

//	@Override
//	public GymMember update(GymMember gm) {
//		return gymMemberDao.save(gm);
//	}

	@Override
	public void deleteById(int memberId) {
		gymMemberDao.deleteById(memberId);
		
	}

}
