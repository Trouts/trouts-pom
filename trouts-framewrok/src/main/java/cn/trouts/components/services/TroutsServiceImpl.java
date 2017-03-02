package cn.trouts.components.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.trouts.entitys.TroutsEntity;
import cn.trouts.framework.context.TroutsCriteria;
import cn.trouts.repositories.TroutsRepository;

@Transactional
public abstract class TroutsServiceImpl implements TroutsService {

	@Override
	public List findByCriteria(TroutsCriteria criteria) {

		return getRepository().findByCriteria(criteria);
	}

	@Override
	public TroutsEntity findById(String ID) {
		// TODO Auto-generated method stub
		return (TroutsEntity) getRepository().findOne(ID);
	}

	@Override
	public TroutsEntity addOrUpdate(TroutsEntity entity) {
		// TODO Auto-generated method stub
		return (TroutsEntity) getRepository().save(entity);

	}

	@Override
	public void delete(TroutsEntity entity) {
		// TODO Auto-generated method stub
		getRepository().delete(entity);
	}

	@Override
	public void deleteById(String ID) {
		// TODO Auto-generated method stub
		getRepository().delete(ID);
	}

}
