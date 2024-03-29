package edu.xhu.lhms.module.loveDonate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.loveDonate.dao.LoveDonateDao;
import edu.xhu.lhms.module.loveDonate.entity.LovedonateInfo;
import edu.xhu.lhms.module.loveDonate.service.LoveDonateService;
import edu.xhu.lhms.module.loveDonate.vo.LoveDonateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class LoveDonateServiceImpl implements LoveDonateService {
    @Autowired
    private LoveDonateDao loveDonateDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result<LovedonateInfo> insertModel(LovedonateInfo model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        model.setState("已预约");
        if(loveDonateDao.insert(model)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<LovedonateInfo> updateModel(LovedonateInfo model) {
        LovedonateInfo lovedonateInfo=loveDonateDao.selectById(model.getId());
        if(model.getDonaThings()!=lovedonateInfo.getDonaThings()){
            lovedonateInfo.setDonaThings(model.getDonaThings());
        }
        if(model.getState()!=lovedonateInfo.getState()){
            lovedonateInfo.setState(model.getState());
        }
        lovedonateInfo.setUpdateDate(LocalDateTime.now());
        if(loveDonateDao.updateById(lovedonateInfo)>0){
            return Result.ok(lovedonateInfo);
        }
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        if(loveDonateDao.deleteById(id)>0){
            return Result.ok();
        }
        return Result.faild();
    }

    @Override
    public Result<LovedonateInfo> getModelById(int id) {
        LovedonateInfo lovedonateInfo=loveDonateDao.selectById(id);
        if (lovedonateInfo!=null)
            lovedonateInfo.setUsername(userDao.selectById(lovedonateInfo.getUserId()).getUserName());
        return Result.ok(lovedonateInfo);
    }

    @Override
    public Result<PageInfo<Feedback>> getModelsBySearch(LoveDonateVo search) {
        return null;
    }

    @Override
    public Result<LovedonateInfo> getModelByDonaThings(String donaThings) {
        LovedonateInfo lovedonateInfo=loveDonateDao.selectByDonaThing(donaThings);
        if(lovedonateInfo!=null){
            lovedonateInfo.setUsername(userDao.selectById(lovedonateInfo.getUserId()).getUserName());
            return Result.ok(lovedonateInfo);
        }
        return Result.faild();
    }

    @Override
    public PageInfo<LovedonateInfo> findModelsBySearch(LoveDonateVo search) {
        if(search.getUsername()!=null||search.getUsername()==""){
            search.setUserId(userDao.getUserByUserName(search.getUsername()).getId());
        }
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(loveDonateDao.getActsBySearch(search))
                .orElse(Collections.emptyList()));
    }
}
