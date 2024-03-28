package edu.xhu.lhms.module.regisActivity.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.regisActivity.entity.RegisActivity;
import edu.xhu.lhms.module.regisActivity.vo.RegisActivityVo;

import java.util.List;

public interface RegisActivityService extends ModelService<RegisActivity,RegisActivityVo> {
    Result<RegisActivity> getModelByUserId(int userId);

    Result<List<RegisActivity>> getModelByActId(int actId);

    Result<PageInfo<RegisActivity>> findModelsBySearch(RegisActivityVo search);
}
