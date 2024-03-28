package edu.xhu.lhms.module.Feedback.service;


import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import edu.xhu.lhms.module.Feedback.vo.FeedbackVo;
import edu.xhu.lhms.module.account.entity.LoginInfo;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.vo.LoginInfoVo;
import edu.xhu.lhms.module.account.vo.UserVo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface FeedbackService extends ModelService<Feedback, FeedbackVo> {


}
