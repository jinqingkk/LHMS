package edu.xhu.lhms.module.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.LoginInfoDao;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.account.entity.LoginInfo;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.service.UserService;
import edu.xhu.lhms.module.account.vo.UserVo;
import edu.xhu.lhms.module.common.dao.ImageDao;
import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.vo.ImageType;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ImageDao imageDao;
	@Autowired
	private LoginInfoDao loginInfoDao;
	@Value("${spring.mail.username}")
	private String from;
	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	@Transactional
	public Result<User> login(User model,HttpSession session) {
		User user=userDao.getIdByUsernameAndPassword(model.getUserName(),MD5Util.getMD5(model.getPassword()));
		if(user !=null){
//			session.setAttribute("userId",user.getId());
			LoginInfo loginInfo=new LoginInfo();
			loginInfo.setUserId(user.getId());
			loginInfo.setCreateDate(LocalDateTime.now());
			loginInfo.setUpdateDate(LocalDateTime.now());
			loginInfoDao.insert(loginInfo);
			//session.setAttribute("userLoginTime",loginInfo.getCreateDate());
			return Result.ok(user);
		}
		return Result.faild("用户名或密码错误！");
	}

	@Override
	public Result<Object> loginout(int id) {
		LoginInfo loginInfo=loginInfoDao.selectByUserId(id);
		loginInfo.setUpdateDate(LocalDateTime.now());
		loginInfoDao.updateById(loginInfo);
		return Result.ok();
	}

	@Override
	//@Transactional
	public Result<User> insertModel(User model) {
		User temp = userDao.getUserByUserName(model.getUserName());
		if (temp != null) {
			return Result.faild("用户名重复。");
		}

		model.setCreateDate(LocalDateTime.now());
		model.setUpdateDate(LocalDateTime.now());
		model.setPassword(MD5Util.getMD5(model.getPassword()));

		// 处理images
//		model.getImages().stream().forEach(item -> {
//			item.setSubject(
//					String.format("%s%s", ImageType.PROFILE.name, model.getId()));
//			item.setCreateDate(LocalDateTime.now());
//			item.setUpdateDate(LocalDateTime.now());
//			imageDao.insert(item);
//		});
		if(userDao.insert(model)>0){
			return Result.ok(model);
		}
		return Result.faild("注册失败");
	}

	@Override
//	@Transactional
	public Result<User> updateModel(User model) {
		User temp = userDao.getUserByUserName(model.getUserName());
		if (temp != null&& temp.getId()!=model.getId()) {
			return Result.faild("用户名重复。");
		}

		User tempUser=userDao.selectById(model.getId());
//		if(model.getPassword()!=null&&model.getPassword()!=tempUser.getPassword()){
//			model.setPassword(MD5Util.getMD5(model.getPassword()));
//			tempUser.setPassword(model.getPassword());
//		}
		if(model.getUserName()!=null&&model.getUserName().compareTo(tempUser.getUserName())!=0){
			tempUser.setUserName(model.getUserName());
		}
		if(model.getEmail()!=null&&model.getEmail().compareTo(tempUser.getEmail())!=0){
			tempUser.setEmail(model.getEmail());
		}
		if(model.getGender()!=null&&model.getGender().compareTo(tempUser.getGender())!=0){
			tempUser.setGender(model.getGender());
		}
		if(model.getPhoneNumber()!=null&&model.getPhoneNumber().compareTo(tempUser.getPhoneNumber())!=0){
			tempUser.setPhoneNumber(model.getPhoneNumber());
		}
		tempUser.setUpdateDate(LocalDateTime.now());

		userDao.updateById(tempUser);
        // 处理images
		if(model.getImages()!=null){
			imageDao.deleteImagesBySubject(
					String.format("%s%s", ImageType.PROFILE.name, model.getId()));
			model.getImages().stream().forEach(item -> {
				item.setSubject(
						String.format("%s%s", ImageType.PROFILE.name, model.getId()));
				item.setCreateDate(LocalDateTime.now());
				item.setUpdateDate(LocalDateTime.now());
				imageDao.insert(item);
			});
		}


		return Result.ok(tempUser);
	}

	@Override
	public Result<User> getModelByUserName(String userName) {
		User temp = userDao.getUserByUserName(userName);
		return Result.ok(temp);
	}

	@Override
	@Transactional
	public Result<Object> deleteModelById(int id) {

//		imageDao.deleteImagesBySubject(
//				String.format("%s%s", ImageType.PROFILE.name, id));
		return Result.ok(userDao.deleteById(id));
	}

	@Override
	@Transactional
	public Result<User> getModelById(int id) {
		User user=userDao.selectById(id);
		if (user != null) {
//			List<Image> images = Optional
//					.ofNullable(imageDao.getImagesBySubject(
//							String.format("%s%s", ImageType.PROFILE.name, user.getId())))
//					.orElse(Collections.emptyList());
//			user.setImages(images);
			return Result.ok(user);
		}
		return Result.faild();
	}

	@Override
	@Transactional
	public PageInfo<User> getModelsBySearch(Search search) {
		search.initSearch();
		PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
		return new PageInfo<>(Optional
				.ofNullable(userDao.getUsersBySearch(search))
				.orElse(Collections.emptyList()));
	}

	//@Override
	@Transactional
	public PageInfo<User> findModelsBySearch(UserVo search) {
		int userId=search.getId();
		String userName=search.getUserName();
		String gender=search.getGender();
		String rellname=search.getRellname();
		String phoneNumber=search.getPhoneNumber();
		search.initSearch();
		PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
		PageInfo<User> pageInfo = new PageInfo<User>(Optional
				.ofNullable(userDao.getUsersBySearchAndUserId(search.getSort(),search.getDirection(), userId,userName,gender,rellname,phoneNumber))
				.orElse(Collections.emptyList()));
		pageInfo.getList().stream().forEach(item -> {
			List<Image> images = Optional
					.ofNullable(imageDao.getImagesBySubject(
							String.format("%s%s", ImageType.PROFILE.name, item.getId())))
					.orElse(Collections.emptyList());
			item.setImages(images);
		});
		return pageInfo;
	}
	@Override
//	@Transactional
	public Result<User> findUserByOpenId(User model) {
		User user=userDao.selectByOpenId(model.getOpenId());
		if(user==null){
			return Result.faild("该用户不存在");
		}
		if(!user.getCredential().equals(model.getCredential())){
			return Result.faild("登录凭证不正确");
		}
		return Result.ok(user);
	}

	@Override
	public Result<User> sendCheckCode(User model) {
		String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
		String message = "您的注册验证码为："+checkCode;
		try{
			SimpleMailMessage mailMessage = new SimpleMailMessage();
//			JavaMailSender javaMailSender = new JavaMailSender() {
//			};
			mailMessage.setFrom(from);
			mailMessage.setTo(model.getEmail());//usersBo.getEmail(), "注册验证码", message
			mailMessage.setSubject("验证码");
			mailMessage.setText(message);
			javaMailSender.send(mailMessage);
//		log.info("邮件发送成功");
		}catch (Exception e){
			return Result.faild();
		}

		return Result.ok(checkCode);
	}

	@Override
	public Result<PageInfo<User>> findUsersByIdentity(UserVo search) {
		search.initSearch();
		PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
		return Result.ok(new PageInfo<>(Optional
				.ofNullable(userDao.findUsersByIdentity(search))
				.orElse(Collections.emptyList())));
	}

	@Override
	public Result<Object> adminToCommon(int id) {
		if(userDao.adminToCommon(id)){
			return Result.ok();
		}
		return  Result.faild();
	}

	@Override
	public Result<Object> commonToAdmin(int id) {
		if(userDao.commonToAdmin(id)){
			return Result.ok();
		}
		return  Result.faild();
	}
}
