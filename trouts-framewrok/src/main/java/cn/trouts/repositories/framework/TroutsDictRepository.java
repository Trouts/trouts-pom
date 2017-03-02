package cn.trouts.repositories.framework;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.trouts.entitys.framework.TroutsDict;
import cn.trouts.repositories.TroutsRepository;

public interface TroutsDictRepository extends TroutsRepository<TroutsDict, String> ,JpaSpecificationExecutor{
	

}
