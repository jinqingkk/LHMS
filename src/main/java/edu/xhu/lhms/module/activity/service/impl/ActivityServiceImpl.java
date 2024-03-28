package edu.xhu.lhms.module.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.activity.dao.ActivityDao;
import edu.xhu.lhms.module.activity.entity.Activity;
import edu.xhu.lhms.module.activity.service.ActivityService;
import edu.xhu.lhms.module.activity.vo.ActivityVo;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result<Activity> insertModel(Activity model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        model.setState("待报名");
        if(activityDao.insert(model)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<Activity> updateModel(Activity model) {
        Activity activity=activityDao.selectById(model.getId());
        if(model.getTitle()!=activity.getTitle()){
            activity.setTitle(model.getTitle());
        }
        if(model.getImage()!=activity.getImage()){
            activity.setImage(model.getImage());
        }
        if(model.getContent()!=activity.getContent()){
            activity.setContent(model.getContent());
        }
        if(model.getState()!=activity.getState()){
            activity.setState(model.getState());
        }
        activity.setUpdateDate(LocalDateTime.now());
        if(activityDao.updateById(activity)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        if(activityDao.deleteById(id)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<Activity> getModelById(int id) {
        Activity activity=activityDao.selectById(id);
        if(activity!=null)
           activity.setUsername(userDao.selectById(activity.getUserId()).getUserName());
        return Result.ok(activity);
    }

    @Override
    public PageInfo<Activity> getModelsBySearch(ActivityVo search) {
        return null;
    }

    @Override
    public Result<Activity> getModelByTitle(String title) {
        Activity activity=activityDao.selectByTitle(title);
        if(activity==null){
            return  Result.faild();
        }
        return Result.ok(activity);
    }

    @Override
    public PageInfo<Activity> findModelsBySearch(ActivityVo search) {
        if(search.getUsername()!=null||search.getUsername()==""){
            search.setUserId(userDao.getUserByUserName(search.getUsername()).getId());
        }
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(activityDao.getActsBySearch(search))
                .orElse(Collections.emptyList()));
    }

    @Override
    public Result<Activity> updateModelState(int id, String state) {
        Activity activity=activityDao.selectById(id);
        activity.setUpdateDate(LocalDateTime.now());
        activity.setState(state);
        if(activityDao.updateById(activity)>0)
            return Result.ok();
        return Result.faild();
    }
}
