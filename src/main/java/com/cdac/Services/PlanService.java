package com.cdac.Services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.Entity.Plan;
import com.cdac.Repository.PlanRepo;
import com.cdac.dao.PlanDao;

@Transactional
@Service
public class PlanService implements PlanRepo {
	
	@Autowired
	private PlanDao planDao;

	@Override
	public Plan findById(int subplanId) {
		Optional<Plan> b= planDao.findById(subplanId);
		return b.orElse(null);
	}

	@Override
	public List<Plan> findAll() {
		return planDao.findAll();
	}

	@Override
	public Plan save(Plan pl) {
		return planDao.save(pl);
	}

	@Override
	public Plan update(Plan pl) {
		return planDao.save(pl);
	}

	@Override
	public void deleteById(int subplanId) {
		planDao.deleteById(subplanId);
		
	}

}
