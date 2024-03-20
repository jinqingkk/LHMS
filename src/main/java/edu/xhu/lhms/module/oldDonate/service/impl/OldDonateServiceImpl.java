package edu.xhu.lhms.module.oldDonate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.module.oldDonate.dao.OldDonateDao;
import edu.xhu.lhms.module.oldDonate.entity.OlddonateInfo;
import edu.xhu.lhms.module.oldDonate.service.OldDonateService;
import edu.xhu.lhms.module.oldDonate.vo.OldDonateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class OldDonateServiceImpl implements OldDonateService {
    @Autowired
    private OldDonateDao oldDonateDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result<OlddonateInfo> insertModel(OlddonateInfo model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        model.setState("已预约");
        if(oldDonateDao.insert(model)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<OlddonateInfo> updateModel(OlddonateInfo model) {
        OlddonateInfo olddonateInfo=oldDonateDao.selectById(model.getId());
        if(model.getOldthings()!=olddonateInfo.getOldthings()){
            olddonateInfo.setOldthings(model.getOldthings());
        }
        if(model.getState()!=olddonateInfo.getState()){
            olddonateInfo.setState(model.getState());
        }
        olddonateInfo.setUpdateDate(LocalDateTime.now());
        if(oldDonateDao.updateById(olddonateInfo)>0){
            return Result.ok(olddonateInfo);
        }
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        if(oldDonateDao.deleteById(id)>0){
            return Result.ok();
        }
        return Result.faild();
    }

    @Override
    public Result<OlddonateInfo> getModelById(int id) {
        OlddonateInfo olddonateInfo=oldDonateDao.selectById(id);
        if (olddonateInfo!=null)
            olddonateInfo.setUsername(userDao.selectById(olddonateInfo.getUserId()).getUserName());
        return Result.ok(olddonateInfo);
    }

    @Override
    public PageInfo<OlddonateInfo> getModelsBySearch(Search search) {
        return null;
    }

    @Override
    public Result<OlddonateInfo> getModelByDonaThings(String oldthings) {
        OlddonateInfo olddonateInfo=oldDonateDao.selectByDonaThing(oldthings);
        if(olddonateInfo!=null){
            olddonateInfo.setUsername(userDao.selectById(olddonateInfo.getUserId()).getUserName());
            return Result.ok(olddonateInfo);
        }
        return Result.faild();
    }

    @Override
    public PageInfo<OlddonateInfo> findModelsBySearch(OldDonateVo search) {
        if(search.getUsername()!=null||search.getUsername()==""){
            search.setUserId(userDao.getUserByUserName(search.getUsername()).getId());
        }
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(oldDonateDao.getActsBySearch(search))
                .orElse(Collections.emptyList()));
    }
}
