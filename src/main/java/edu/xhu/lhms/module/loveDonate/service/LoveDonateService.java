package edu.xhu.lhms.module.loveDonate.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.loveDonate.entity.LovedonateInfo;
import edu.xhu.lhms.module.loveDonate.vo.LoveDonateVo;

public interface LoveDonateService extends ModelService<LovedonateInfo,LoveDonateVo> {
    Result<LovedonateInfo> getModelByDonaThings(String donaThings);

    PageInfo<LovedonateInfo> findModelsBySearch(LoveDonateVo search);
}
