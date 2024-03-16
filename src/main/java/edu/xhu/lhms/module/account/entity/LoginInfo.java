package edu.xhu.lhms.module.account.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "login_info")
@TableName("login_info")
public class LoginInfo  extends AbstractEntity {
  private int userId;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

}
