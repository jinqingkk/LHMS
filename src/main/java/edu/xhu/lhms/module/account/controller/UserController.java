package edu.xhu.lhms.module.account.controller;


import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.entity.LoginInfo;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.service.UserService;
import edu.xhu.lhms.module.account.vo.LoginInfoVo;
import edu.xhu.lhms.module.account.vo.UserVo;
import edu.xhu.lhms.module.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/account")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 127.0.0.1/account/user ---- post
	 */
	@PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> insertModel(@RequestBody User model) {
		return userService.insertModel(model);
	}
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> login(@RequestBody User model, HttpSession session) {
		return userService.login(model,session);
	}
	@PostMapping(value = "/loginbywechat", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> loginbywechat(@RequestBody User model) {
		return userService.findUserByOpenId(model);
	}
	@PostMapping(value = "/sendCheckCode", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> sendCheckCode(@RequestBody User model) {
		return userService.sendCheckCode(model);
	}
	@GetMapping(value = "/loginout/{id}")
	public Result<Object> loginout(@PathVariable int id){return userService.loginout(id);}

	@GetMapping(value = "/adminToCommon/{id}")
	public Result<Object> adminToCommon(@PathVariable int id){return userService.adminToCommon(id);}
	@GetMapping(value = "/commonToAdmin/{id}")
	public Result<Object> commonToAdmin(@PathVariable int id){return userService.commonToAdmin(id);}

	@PutMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> updateModel(@RequestBody User model) {
		return userService.updateModel(model);
	}
	@DeleteMapping(value = "/deleteUserByid/{id}")
	public Result<Object> deleteModelById(@PathVariable int id) {
		return userService.deleteModelById(id);
	}
	@GetMapping(value = "/getUserById/{id}")
	public Result<User> getModelById(@PathVariable int id) {
		return userService.getModelById(id);
	}
	@GetMapping(value = "/findUser/{userName}")
	public Result<User> getModelByUserName(@PathVariable String userName) {
		return userService.getModelByUserName(userName);
	}
	@GetMapping(value = "/getUsernameList")
	public Result<List<User>> getUsernameList() {
		return userService.getUsernameList();
	}
	@PostMapping(value = "/getUsers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<User> getModelsBySearch(@RequestBody UserVo search) {
		return userService.findModelsBySearch(search);
	}
	@PostMapping(value = "/findUsersByIdentity", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageInfo<User>> findUsersByIdentity(@RequestBody UserVo search) {
		return userService.findUsersByIdentity(search);
	}
	// 针对登录日志
	@DeleteMapping(value = "/delete/{id}")
	public Result<Object> deleteLoginInfoById(@PathVariable int id) {
		return userService.deleteLoginInfoById(id);
	}
	@PostMapping(value = "/findLoginInfos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageInfo<LoginInfo>> findLoginInfos(@RequestBody LoginInfoVo search) {
		return userService.findLoginInfos(search);
	}
}
