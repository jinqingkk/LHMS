package edu.xhu.lhms.module.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.service.UserService;
import edu.xhu.lhms.module.account.vo.UserVo;
//import edu.xhu.lhms.module.balance.dao.BalanceDao;
//import edu.xhu.lhms.module.balance.entity.Balance;
import edu.xhu.lhms.module.common.dao.ImageDao;
import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.vo.ImageType;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ImageDao imageDao;
//	@Autowired
//	private BalanceDao balanceDao;

	@Override
	@Transactional
	public Result<User> login(User model,HttpSession session) {
		User userId=userDao.getIdByUsernameAndPassword(model.getUserName(),MD5Util.getMD5(model.getPassword()));
		if(userId !=null){
			session.setAttribute("userId",userId.getId());
			return Result.ok(userId);
		}
		return Result.faild("用户名或密码错误！");
	}


	@Override
	//@Transactional
	public Result<User> insertModel(User model) {
//		User temp = userDao.getUserByUserName(model.getUserName());
//		if (temp != null) {
//			return Result.faild("用户名重复。");
//		}
//
//		model.setCreateDate(LocalDateTime.now());
//		model.setUpdateDate(LocalDateTime.now());
//		model.setPassword(MD5Util.getMD5(model.getPassword()));
//
////		// 处理images
////		model.getImages().stream().forEach(item -> {
////			item.setSubject(
////					String.format("%s%s", ImageType.PROFILE.name, model.getId()));
////			item.setCreateDate(LocalDateTime.now());
////			item.setUpdateDate(LocalDateTime.now());
////			imageDao.insert(item);
////		});
//		userDao.insert(model);
//		Balance balance=new Balance();
//		User u1=userDao.getUserByUserName(model.getUserName());
//		balance.setUserId(6);
//		balance.setBalance(0);
//		balance.setCashback(0);
//		balance.setCreateDate(model.getCreateDate());
//		balance.setUpdateDate(LocalDateTime.now());
//		balanceDao.insert(balance);

		return null;
//		Result.ok(model);
	}

	@Override
//	@Transactional
	public Result<User> updateModel(User model) {
		User temp = userDao.getUserByUserName(model.getUserName());
		if (temp != null&& temp.getId()!=model.getId()) {
			return Result.faild("用户名重复。");
		}
		model.setPassword(MD5Util.getMD5(model.getPassword()));
		User tempUser=userDao.selectById(model.getId());
		if(model.getPassword()!=null&&model.getPassword()!=tempUser.getPassword()){
			tempUser.setPassword(model.getPassword());
		}
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
//		imageDao.deleteImagesBySubject(
//				String.format("%s%s", ImageType.PROFILE.name, model.getId()));
//		model.getImages().stream().forEach(item -> {
//			item.setSubject(
//					String.format("%s%s", ImageType.PROFILE.name, model.getId()));
//			item.setCreateDate(LocalDateTime.now());
//			item.setUpdateDate(LocalDateTime.now());
//			imageDao.insert(item);
//		});
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
		userDao.deleteById(id);
		imageDao.deleteImagesBySubject(
				String.format("%s%s", ImageType.PROFILE.name, id));
		//balanceDao.deleteByUserId(id);
		return Result.ok(id);
	}

	@Override
	@Transactional
	public User getModelById(int id) {
		User user=userDao.selectById(id);
		if (user != null) {
			List<Image> images = Optional
					.ofNullable(imageDao.getImagesBySubject(
							String.format("%s%s", ImageType.PROFILE.name, user.getId())))
					.orElse(Collections.emptyList());
			user.setImages(images);
		}
		return user;
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
}
