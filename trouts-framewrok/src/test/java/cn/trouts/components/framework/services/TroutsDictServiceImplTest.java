package cn.trouts.components.framework.services;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.trouts.components.services.framework.TroutsDictService;
import cn.trouts.entitys.framework.TroutsDict;
import cn.trouts.framework.enums.TroutsDictType;
import cn.trouts.framework.test.TroutsTest;

public class TroutsDictServiceImplTest extends TroutsTest {
	@Autowired
	private TroutsDictService troutsDictService;

	@Test
	public void testGetByDictType() {
		List<TroutsDict> tds = troutsDictService
				.getByDictType(TroutsDictType.SYSTEM_PARAMETER);
		for (TroutsDict troutsDict : tds) {
			System.out.println(troutsDict.getName());
		}
	}

	@Test
	public void testGetDictValue() {
		TroutsDict td = troutsDictService.getDictValue(
				TroutsDictType.SYSTEM_PARAMETER, "paream_1");

		System.out.println(td.getValue());

	}
	@Test
	public void test(){
		
//		TroutsDict td = (TroutsDict)troutsDictService.findById("40289fac5a7a6215015a7a6229a30000");
////		td.setId("40289fac5a7a6215015a7a6229a30000");
////		td.setType(TroutsDictType.SYSTEM_PARAMETER.name());
//		td.setName("参数名11155555");
//		td.setValue("参数值1");
//		troutsDictService.addOrUpdate(td);
	}

}
