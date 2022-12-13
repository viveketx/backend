package com.cdac.Services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.Entity.Workoutplan;
import com.cdac.Repository.WorkoutplanRepo;
import com.cdac.dao.WorkoutplanDao;

@Transactional
@Service
public class WorkoutplanService implements WorkoutplanRepo {

	@Autowired
	private WorkoutplanDao workoutplanDao;

	@Override
	public Workoutplan findById(int workoutid) {
		Optional<Workoutplan> b = workoutplanDao.findById(workoutid);
		return b.orElse(null);
	}

	@Override
	public List<Workoutplan> findAll() {
		return workoutplanDao.findAll();
	}

	@Override
	public Workoutplan save(Workoutplan wp) {
		return workoutplanDao.save(wp);
	}

	@Override
	public void deleteById(int workoutid) {
		workoutplanDao.deleteById(workoutid);

	}

}
