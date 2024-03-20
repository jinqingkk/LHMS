package edu.xhu.lhms.module.volunActivity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.module.volunActivity.dao.VolunActivityDao;
import edu.xhu.lhms.module.volunActivity.entity.VolunActivity;
import edu.xhu.lhms.module.volunActivity.service.VolunActivityService;
import edu.xhu.lhms.module.volunActivity.vo.VolunActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class VolunActivityServiceImpl implements VolunActivityService {
    @Autowired
    private VolunActivityDao volunActivityDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result<VolunActivity> insertModel(VolunActivity model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        model.setState("待报名");
        if(volunActivityDao.insert(model)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<VolunActivity> updateModel(VolunActivity model) {
        VolunActivity volunActivity=volunActivityDao.selectById(model.getId());
        if(model.getTitle()!=volunActivity.getTitle()){
            volunActivity.setTitle(model.getTitle());
        }
        if(model.getContent()!=volunActivity.getContent()){
            volunActivity.setContent(model.getContent());
        }
        if(model.getState()!=volunActivity.getState()){
            volunActivity.setState(model.getState());
        }
        volunActivity.setUpdateDate(LocalDateTime.now());
        if(volunActivityDao.updateById(volunActivity)>0){
            return Result.ok(volunActivity);
        }
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        if(volunActivityDao.deleteById(id)>0){
            return Result.ok();
        }
        return Result.faild();
    }

    @Override
    public Result<VolunActivity> getModelById(int id) {
        VolunActivity volunActivity=volunActivityDao.selectById(id);
        if (volunActivity!=null)
            volunActivity.setUsername(userDao.selectById(volunActivity.getUserId()).getUserName());
        return Result.ok(volunActivity);
    }

    @Override
    public PageInfo<VolunActivity> getModelsBySearch(Search search) {
        return null;
    }

    @Override
    public Result<VolunActivity> getModelByTitle(String title) {
        VolunActivity volunActivity=volunActivityDao.selectByTitle(title);
        if(volunActivity!=null){
            volunActivity.setUsername(userDao.selectById(volunActivity.getUserId()).getUserName());
            return Result.ok(volunActivity);
        }
        return Result.faild();
    }

    @Override
    public PageInfo<VolunActivity> findModelsBySearch(VolunActivityVo search) {
        if(search.getUsername()!=null||search.getUsername()==""){
            search.setUserId(userDao.getUserByUserName(search.getUsername()).getId());
        }
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(volunActivityDao.getActsBySearch(search))
                .orElse(Collections.emptyList()));
    }
}
