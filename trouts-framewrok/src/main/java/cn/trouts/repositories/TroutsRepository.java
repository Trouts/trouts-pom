package cn.trouts.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.trouts.framework.context.TroutsCriteria;
@NoRepositoryBean
public interface TroutsRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>  {
 
	 public List findByCriteria(TroutsCriteria criteria);
	
}
