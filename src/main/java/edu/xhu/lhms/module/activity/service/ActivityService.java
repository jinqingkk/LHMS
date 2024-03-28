package edu.xhu.lhms.module.activity.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.activity.entity.Activity;
import edu.xhu.lhms.module.activity.vo.ActivityVo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;

public interface ActivityService  extends ModelService<Activity,ActivityVo> {
    Result<Activity> getModelByTitle(String title);

    PageInfo<Activity> findModelsBySearch(ActivityVo search);

    Result<Activity> updateModelState(int id, String state);
}
