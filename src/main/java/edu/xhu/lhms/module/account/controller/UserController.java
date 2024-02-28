package edu.xhu.lhms.module.account.controller;


import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.service.UserService;
import edu.xhu.lhms.module.account.vo.UserVo;
import edu.xhu.lhms.module.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
//@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/api/account")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 127.0.0.1/api/account/user ---- post
	 */
	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> insertModel(@RequestBody User model) {
		return userService.insertModel(model);
	}
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> login(@RequestBody User model, HttpSession session) {
		return userService.login(model,session);
	}
	@PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> updateModel(@RequestBody User model) {
		return userService.updateModel(model);
	}
	@DeleteMapping(value = "/user/{id}")
	public Result<Object> deleteModelById(@PathVariable int id) {
		return userService.deleteModelById(id);
	}
	@GetMapping(value = "/user/{id}")
	public User getModelById(@PathVariable int id) {
		return userService.getModelById(id);
	}
	@GetMapping(value = "/findUser/{userName}")
	public Result<User> getModelByUserName(@PathVariable String userName) {
		return userService.getModelByUserName(userName);
	}
	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<User> getModelsBySearch(@RequestBody UserVo search) {
		return userService.findModelsBySearch(search);
	}
}
