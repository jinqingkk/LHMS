package edu.xhu.lhms.module.account.service;


import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.vo.UserVo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;

import javax.servlet.http.HttpSession;

/**
 * BalanceService
 */
public interface UserService extends ModelService<User> {

	Result<User> login(User user, HttpSession session);

	Result<Object> deleteModelById(int id);
	Result<User> updateModel(User model);

	Result<User> getModelByUserName(String userName);

	PageInfo<User> findModelsBySearch(UserVo search);
}
