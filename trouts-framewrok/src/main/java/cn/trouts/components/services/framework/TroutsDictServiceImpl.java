package cn.trouts.components.services.framework;


import cn.trouts.components.services.TroutsServiceImpl;
import cn.trouts.entitys.framework.TroutsDict;
import cn.trouts.framework.context.DictType;
import cn.trouts.framework.utils.TroutsLogUtils;
import cn.trouts.repositories.TroutsRepository;
import cn.trouts.repositories.framework.TroutsDictRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TroutsDictServiceImpl extends TroutsServiceImpl implements TroutsDictService {
    private static final Logger LOGGER = TroutsLogUtils
            .getLogger(TroutsDictServiceImpl.class);

    @Autowired
    private TroutsDictRepository troutsDictRepository;

    @Override
    public List<TroutsDict> getByDictType(DictType dictType) {
        // TODO Auto-generated method stub
        TroutsDict td = new TroutsDict();
        td.
                setType(dictType.
                        getType());


        td.setDirection(Direction.ASC);
        td.setSortFields(new String[]{"seq"});
        return troutsDictRepository.findByCriteria(td);
    }

    @Override
    public TroutsDict getDictValue(DictType dictType, String name) {
        // TODO Auto-generated method stub
        TroutsDict td = new TroutsDict();
        td.setType(dictType.getType());
        td.setName(name);
        List list = troutsDictRepository.findByCriteria(td);
        if (list != null && list.size() > 0) {
            return (TroutsDict) list.get(0);
        } else {
            return null;
        }

    }

    @Override
    public TroutsRepository getRepository() {
        // TODO Auto-generated method stub
        return troutsDictRepository;
    }

}
