package cn.trouts.repositories;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import cn.trouts.entitys.TroutsEntity;
import cn.trouts.framework.context.TroutsCriteria;

public class TroutsRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements TroutsRepository<T, ID> {

	private final EntityManager entityManager;

	public TroutsRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public List findByCriteria(TroutsCriteria criteria) {
		Example<T> example = null;
		if(criteria.getCriteriaMatcher()!=null){
			example = (Example<T>) Example.of(criteria,criteria.getCriteriaMatcher());
		} else {
			example = (Example<T>) Example.of(criteria);
		}

		Pageable pageable = null;
		if (criteria.getDirection() == null || criteria.getSortFields() == null) {
			pageable = new PageRequest(criteria.getPage(),
					criteria.getPageSize());
		} else {
			pageable = new PageRequest(criteria.getPage(), criteria.getPageSize(), criteria.getDirection(),
					criteria.getSortFields());
		}

		Page page = findAll(example, pageable);
		return page.getContent();
	}

}
