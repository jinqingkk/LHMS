package edu.xhu.lhms.module.oldDonate.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "olddonate_info")
@TableName("olddonate_info")
public class OlddonateInfo  extends AbstractEntity {

  private String oldthings;

  private String state;
  private int userId;
  @Transient
  @TableField(exist = false)
  private  String username;



  public String getOldthings() {
    return oldthings;
  }

  public void setOldthings(String oldthings) {
    this.oldthings = oldthings;
  }


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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }



}
