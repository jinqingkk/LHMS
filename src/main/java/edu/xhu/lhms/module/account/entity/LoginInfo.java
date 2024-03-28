package edu.xhu.lhms.module.account.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "login_info")
@TableName("login_info")
public class LoginInfo  extends AbstractEntity {
  private int userId;

  private String username;
  private  String discription;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDiscription() {
    return discription;
  }

  public void setDiscription(String discription) {
    this.discription = discription;
  }
}
