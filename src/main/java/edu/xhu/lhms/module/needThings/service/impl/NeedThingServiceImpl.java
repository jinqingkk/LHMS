package edu.xhu.lhms.module.needThings.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.module.needThings.dao.NeedThingDao;
import edu.xhu.lhms.module.needThings.entity.NeedThing;
import edu.xhu.lhms.module.needThings.service.NeedThingService;
import edu.xhu.lhms.module.needThings.vo.NeedThingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class NeedThingServiceImpl implements NeedThingService {
    @Autowired
    private NeedThingDao needThingDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result<NeedThing> insertModel(NeedThing model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        model.setState("已预约");
        if(needThingDao.insert(model)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<NeedThing> updateModel(NeedThing model) {
        NeedThing needThing=needThingDao.selectById(model.getId());
        if(model.getItem()!=needThing.getItem()){
            needThing.setItem(model.getItem());
        }
        if(model.getCount()!=needThing.getCount()){
            needThing.setCount(model.getCount());
        }
        if(model.getState()!=needThing.getState()){
            needThing.setState(model.getState());
        }
        needThing.setUpdateDate(LocalDateTime.now());
        if(needThingDao.updateById(needThing)>0){
            return Result.ok(needThing);
        }
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        if(needThingDao.deleteById(id)>0){
            return Result.ok();
        }
        return Result.faild();
    }

    @Override
    public Result<NeedThing> getModelById(int id) {
        NeedThing needThing=needThingDao.selectById(id);
        if (needThing!=null)
            needThing.setUsername(userDao.selectById(needThing.getUserId()).getUserName());
        return Result.ok(needThing);
    }

    @Override
    public PageInfo<NeedThing> getModelsBySearch(NeedThingVo search) {
        return null;
    }


    @Override
    public Result<NeedThing> getModelByItem(String item) {
        NeedThing needThing=needThingDao.selectByItem(item);
        if(needThing!=null){
            needThing.setUsername(userDao.selectById(needThing.getUserId()).getUserName());
            return Result.ok(needThing);
        }
        return Result.faild();
    }

    @Override
    public PageInfo<NeedThing> findModelsBySearch(NeedThingVo search) {
        if(search.getUsername()!=null||search.getUsername()==""){
            search.setUserId(userDao.getUserByUserName(search.getUsername()).getId());
        }
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(needThingDao.getActsBySearch(search))
                .orElse(Collections.emptyList()));
    }
}
