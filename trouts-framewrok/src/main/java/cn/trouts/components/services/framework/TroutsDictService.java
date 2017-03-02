package cn.trouts.components.services.framework;



import java.util.List;

import cn.trouts.components.services.TroutsService;
import cn.trouts.entitys.framework.TroutsDict;
import cn.trouts.framework.context.DictType;

public interface TroutsDictService extends TroutsService{
	/**
	 * 根据字典类型查询所有字典值.
	 * @param dictType
	 * @return
	 */
	public List<TroutsDict> getByDictType(DictType dictType);
	
	/**
	 * 查询字典value
	 * @param dictType 字典类型
	 * @param name 字典值
	 * @return
	 */
	public TroutsDict getDictValue(DictType dictType, String name);

}
