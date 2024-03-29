package edu.xhu.lhms.module.Feedback.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.Feedback.dao.FeedbackDao;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import edu.xhu.lhms.module.Feedback.service.FeedbackService;
import edu.xhu.lhms.module.Feedback.vo.FeedbackVo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.volunActivity.entity.VolunActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result<Feedback> insertModel(Feedback model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        model.setState("未处理");
        if(feedbackDao.insert(model)>0){
            return Result.ok();
        }
        return Result.faild();
    }

    @Override
    public Result<Feedback> updateModel(Feedback model) {
        Feedback feedback=feedbackDao.selectById(model.getId());
        if(model.getState()!=null&&model.getState()!=feedback.getState()){
            feedback.setState(model.getState());
        }
        if(model.getTitle()!=null&&model.getTitle()!=feedback.getTitle()){
            feedback.setTitle(model.getTitle());
        }
        if(model.getContent()!=null&&model.getContent()!=feedback.getContent()){
            feedback.setContent(model.getContent());
        }
        feedback.setUpdateDate(LocalDateTime.now());
        if(feedbackDao.updateById(feedback)>0){
            return Result.ok(feedback);
        }
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        return Result.ok(feedbackDao.deleteById(id));
    }

    @Override
    public Result<Feedback> getModelById(int id) {
        return Result.ok(feedbackDao.selectById(id));
    }

    @Override
    public Result<PageInfo<Feedback>> getModelsBySearch(FeedbackVo search) {
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        PageInfo<Feedback> pageInfo = new PageInfo<Feedback>(Optional
                .ofNullable(feedbackDao.getFeedbacksBySearch(search))
                .orElse(Collections.emptyList()));
        pageInfo.getList().stream().forEach(item -> {
            item.setUsername(userDao.selectById(item.getUserId()).getUserName());
        });
        return  Result.ok(pageInfo);
    }
}
