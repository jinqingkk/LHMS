package edu.xhu.lhms.module.volunActivity.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.volunActivity.entity.VolunActivity;
import edu.xhu.lhms.module.volunActivity.vo.VolunActivityVo;

import java.util.List;

public interface VolunActivityService extends ModelService<VolunActivity,VolunActivityVo> {
    Result<VolunActivity> getModelByTitle(String donaThings);

    Result<PageInfo<VolunActivity>> findModelsBySearch(VolunActivityVo search);

    Result<List<VolunActivity>> getTitleList();
}
