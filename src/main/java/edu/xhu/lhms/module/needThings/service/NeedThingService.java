package edu.xhu.lhms.module.needThings.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.needThings.entity.NeedThing;
import edu.xhu.lhms.module.needThings.vo.NeedThingVo;

public interface NeedThingService extends ModelService<NeedThing> {

    PageInfo<NeedThing> findModelsBySearch(NeedThingVo search);

    Result<NeedThing> getModelByItem(String item);
}
