package cn.trouts.components.services;

import java.util.List;

import cn.trouts.entitys.TroutsEntity;
import cn.trouts.framework.context.TroutsCriteria;
import cn.trouts.repositories.TroutsRepository;

public interface TroutsService {
	public TroutsRepository getRepository();
	List findByCriteria(TroutsCriteria criteria);
	TroutsEntity findById(String ID);
	TroutsEntity addOrUpdate(TroutsEntity entity);
	void delete(TroutsEntity entity);
	void deleteById(String ID);

}
