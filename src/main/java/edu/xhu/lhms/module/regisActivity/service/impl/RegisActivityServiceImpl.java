package edu.xhu.lhms.module.regisActivity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.module.regisActivity.dao.RegisActivityDao;
import edu.xhu.lhms.module.regisActivity.entity.RegisActivity;
import edu.xhu.lhms.module.regisActivity.service.RegisActivityService;
import edu.xhu.lhms.module.regisActivity.vo.RegisActivityVo;
import edu.xhu.lhms.module.volunActivity.dao.VolunActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RegisActivityServiceImpl implements RegisActivityService {
    @Autowired
    private RegisActivityDao regisActivityDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private VolunActivityDao volunActivityDao;

    @Override
    public Result<RegisActivity> insertModel(RegisActivity model) {
        RegisActivity regisActivity=regisActivityDao.selectByActIdAndUserId(model.getActId(),model.getUserId());
        if(regisActivity==null){
            model.setCreateDate(LocalDateTime.now());
            model.setUpdateDate(LocalDateTime.now());
            if(model.getState()!=null){
                model.setState("未审核");
            }
            if(regisActivityDao.insert(model)>0)
                return Result.ok();
        }
        return Result.faild();
    }

    @Override
    public Result<RegisActivity> updateModel(RegisActivity model) {
        RegisActivity regisActivity=regisActivityDao.selectById(model.getId());
        if(model.getActId()!=0&&model.getActId()!=null&&model.getActId()!=regisActivity.getActId()){
            regisActivity.setActId(model.getActId());
        }
        if(model.getUserId()!=0&&model.getUserId()!=null&&model.getUserId()!=regisActivity.getUserId()){
            regisActivity.setUserId(model.getUserId());
        }
        if(model.getState()!=null&&model.getState()!=regisActivity.getState()){
            regisActivity.setState(model.getState());
        }
        if(model.getReason()!=null&&model.getReason()!=regisActivity.getReason()){
            regisActivity.setReason(model.getReason());
        }
        regisActivity.setUpdateDate(LocalDateTime.now());
        if(regisActivityDao.updateById(regisActivity)>0)
            return Result.ok(regisActivity);
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        return Result.ok(regisActivityDao.deleteById(id));
    }

    @Override
    public Result<RegisActivity> getModelById(int id) {
        RegisActivity regisActivity=regisActivityDao.selectById(id);
        if(regisActivity!=null){
            regisActivity.setUsername(userDao.selectById(regisActivity.getUserId()).getUserName());
            regisActivity.setTitle(volunActivityDao.selectById(regisActivity.getActId()).getTitle());
        }
        return Result.ok(regisActivity);
    }

    @Override
    public PageInfo<RegisActivity> getModelsBySearch(RegisActivityVo search) {

        return null;
    }

    @Override
    public Result<RegisActivity> getModelByUserId(int userId) {
        RegisActivity regisActivity=regisActivityDao.selectByUserId(userId);
        if(regisActivity!=null){
            regisActivity.setUsername(userDao.selectById(regisActivity.getUserId()).getUserName());
            regisActivity.setTitle(volunActivityDao.selectById(regisActivity.getActId()).getTitle());
        }
        return Result.ok(regisActivity);
    }

    @Override
    public Result<List<RegisActivity>> getModelByActId(int actId) {
        RegisActivity regisActivity=regisActivityDao.selectByActId(actId);
        if(regisActivity!=null){
            regisActivity.setUsername(userDao.selectById(regisActivity.getUserId()).getUserName());
            regisActivity.setTitle(volunActivityDao.selectById(regisActivity.getActId()).getTitle());
        }
        return Result.ok(regisActivity);
    }

    @Override
    public Result<PageInfo<RegisActivity>> findModelsBySearch(RegisActivityVo search) {
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        PageInfo<RegisActivity> pageInfo = new PageInfo<RegisActivity>(Optional
                .ofNullable(regisActivityDao.getRegisActsBySearch(search))
                .orElse(Collections.emptyList()));
        pageInfo.getList().stream().forEach(item -> {
            item.setUsername(userDao.selectById(item.getUserId()).getUserName());
            item.setTitle(volunActivityDao.selectById(item.getActId()).getTitle());//item.getUserId(),
        });
        return Result.ok(pageInfo);
    }
}
