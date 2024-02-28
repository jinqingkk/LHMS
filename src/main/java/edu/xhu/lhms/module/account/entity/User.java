package edu.xhu.lhms.module.account.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;
import edu.xhu.lhms.module.common.entity.Image;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;


@Entity
@Table(name = "user_info")
@TableName("user_info")
public class User extends AbstractEntity {
	private String userName;
	private String password;
	@Transient
	@TableField(exist = false)
	private List<Image> userImg;

	private String gender;
	private String email;
	private  String phoneNumber;
	private String rellname;
	private String idNumber;

	private String identity;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Transient
	@TableField(exist = false)
	private String verifCode;
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRellname() {
		return rellname;
	}

	public void setRellname(String rellname) {
		this.rellname = rellname;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Image> getImages() {
		return userImg;
	}

	public void setImages(List<Image> images) {
		this.userImg = images;
	}
}
