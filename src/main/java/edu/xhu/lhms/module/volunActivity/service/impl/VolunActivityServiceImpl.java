package edu.xhu.lhms.module.volunActivity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.common.dao.ImageDao;
import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.vo.ImageType;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.module.regisActivity.dao.RegisActivityDao;
import edu.xhu.lhms.module.volunActivity.dao.VolunActivityDao;
import edu.xhu.lhms.module.volunActivity.entity.VolunActivity;
import edu.xhu.lhms.module.volunActivity.service.VolunActivityService;
import edu.xhu.lhms.module.volunActivity.vo.VolunActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VolunActivityServiceImpl implements VolunActivityService {
    @Autowired
    private VolunActivityDao volunActivityDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private RegisActivityDao regisActivityDao;
    @Override
    public Result<VolunActivity> insertModel(VolunActivity model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        if(volunActivityDao.insert(model)>0){
            int newId=volunActivityDao.getMaxId();
//             处理images
            model.getImages().stream().forEach(item -> {
                item.setSubject(
                        String.format("%s%s", ImageType.VOLUNACT.name, newId));
                item.setCreateDate(LocalDateTime.now());
                item.setUpdateDate(LocalDateTime.now());
                imageDao.insert(item);
            });
            return Result.ok();
        }
        return Result.faild();
    }

    @Override
    public Result<VolunActivity> updateModel(VolunActivity model) {
        VolunActivity volunActivity=volunActivityDao.selectById(model.getId());

        if(model.getTitle()!=null&&model.getTitle()!=volunActivity.getTitle()){
            volunActivity.setTitle(model.getTitle());
        }
        if(model.getContent()!=null&& model.getContent()!=volunActivity.getContent()){
            volunActivity.setContent(model.getContent());
        }
        if(model.getState()!=null&&model.getState()!=volunActivity.getState()){
            volunActivity.setState(model.getState());
        }
        if(model.getRegion()!=null&&model.getRegion()!=volunActivity.getRegion()){
            volunActivity.setRegion(model.getRegion());
        }
        if(model.getStartDate()!=null&& model.getStartDate()!=volunActivity.getStartDate()){
            volunActivity.setStartDate(model.getStartDate());
        }
        if(model.getEndDate()!=null&&model.getEndDate()!=volunActivity.getEndDate()){
            volunActivity.setEndDate(model.getEndDate());
        }
        if(model.getDisplay()!=null&& model.getDisplay()!=volunActivity.getDisplay()){
            volunActivity.setDisplay(model.getDisplay());
        }
        if(model.getCount()!=0 && model.getCount()!=volunActivity.getCount()){
            volunActivity.setCount(model.getCount());
        }
        volunActivity.setUpdateDate(LocalDateTime.now());
        // 处理images
        if(model.getImages()!=null){
            imageDao.deleteImagesBySubject(
                    String.format("%s%s", ImageType.VOLUNACT.name, model.getId()));
            model.getImages().stream().forEach(item -> {
                item.setSubject(
                        String.format("%s%s", ImageType.VOLUNACT.name, model.getId()));
                item.setCreateDate(LocalDateTime.now());
                item.setUpdateDate(LocalDateTime.now());
                imageDao.insert(item);
            });
        }
        if(volunActivityDao.updateById(volunActivity)>0){
            return Result.ok(volunActivity);
        }
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        imageDao.deleteImagesBySubject(String.format("%s%s", ImageType.VOLUNACT.name, id));
        regisActivityDao.deleteByVolunActId(id);
        return Result.ok(volunActivityDao.deleteById(id));
    }

    @Override
    public Result<VolunActivity> getModelById(int id) {
        VolunActivity volunActivity=volunActivityDao.selectById(id);
        if (volunActivity!=null){
            volunActivity.setUsername(userDao.selectById(volunActivity.getUserId()).getUserName());
            volunActivity.setImages(imageDao.getImagesBySubject(ImageType.VOLUNACT.name+volunActivity.getId()));
        }
        return Result.ok(volunActivity);
    }

    @Override
    public PageInfo<VolunActivity> getModelsBySearch(VolunActivityVo search) {
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
    public Result<PageInfo<VolunActivity>> findModelsBySearch(VolunActivityVo search) {
        if(search.getUsername()!=null||search.getUsername()==""){
            User user=userDao.getUserByUserName(search.getUsername());
            if(user!=null)
            {
                search.setUserId(user.getId());
            }
        }
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        PageInfo<VolunActivity> pageInfo = new PageInfo<VolunActivity>(Optional
                .ofNullable(volunActivityDao.getActsBySearch(search))
                .orElse(Collections.emptyList()));
        pageInfo.getList().stream().forEach(item -> {
            List<Image> images = Optional
                    .ofNullable(imageDao.getImagesBySubject(
                            String.format("%s%s", ImageType.VOLUNACT.name, item.getId())))
                    .orElse(Collections.emptyList());
            item.setImages(images);
            item.setUsername(userDao.selectById(item.getUserId()).getUserName());
            item.setRegisCount(regisActivityDao.getRegisCount(item.getId()));//item.getUserId(),
        });
        return Result.ok(pageInfo);
    }

    @Override
    public Result<List<VolunActivity>> getTitleList() {
        return Result.ok(volunActivityDao.getTitleList());
    }
}
