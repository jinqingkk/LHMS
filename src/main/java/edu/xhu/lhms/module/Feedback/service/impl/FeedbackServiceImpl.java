package edu.xhu.lhms.module.Feedback.service.impl;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.Feedback.dao.FeedbackDao;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import edu.xhu.lhms.module.Feedback.service.FeedbackService;
import edu.xhu.lhms.module.Feedback.vo.FeedbackVo;
import edu.xhu.lhms.module.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;
    @Override
    public Result<Feedback> insertModel(Feedback model) {
        return null;
    }

    @Override
    public Result<Feedback> updateModel(Feedback model) {
        return null;
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        return null;
    }

    @Override
    public Result<Feedback> getModelById(int id) {
        return null;
    }

    @Override
    public PageInfo<Feedback> getModelsBySearch(FeedbackVo search) {
        return null;
    }
}
