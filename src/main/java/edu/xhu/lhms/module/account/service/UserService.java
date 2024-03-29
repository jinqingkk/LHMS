package edu.xhu.lhms.module.account.service;


import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.entity.LoginInfo;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.vo.LoginInfoVo;
import edu.xhu.lhms.module.account.vo.UserVo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface UserService extends ModelService<User,UserVo> {

	Result<User> login(User user, HttpSession session);

	Result<Object> deleteModelById(int id);
	Result<User> updateModel(User model);

	Result<User> getModelByUserName(String userName);

	PageInfo<User> findModelsBySearch(UserVo search);

	Result<Object> loginout(int id);
	Result<User> findUserByOpenId(User model);

	Result<User> sendCheckCode(User model);

	Result<PageInfo<User>> findUsersByIdentity(UserVo search);

	Result<Object> adminToCommon(int id);

	Result<Object> commonToAdmin(int id);

    Result<List<User>> getUsernameList();

	Result<Object> deleteLoginInfoById(int id);

	Result<PageInfo<LoginInfo>> findLoginInfos(LoginInfoVo search);

    Result<User> updatePassword(User model);
}
