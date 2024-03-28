package edu.xhu.lhms.module.oldDonate.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.oldDonate.entity.OlddonateInfo;
import edu.xhu.lhms.module.oldDonate.vo.OldDonateVo;

public interface OldDonateService extends ModelService<OlddonateInfo,OldDonateVo> {
    Result<OlddonateInfo> getModelByDonaThings(String donaThings);

    PageInfo<OlddonateInfo> findModelsBySearch(OldDonateVo search);
}
