package edu.xhu.lhms.module.needThings.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "need_thing")
@TableName("need_thing")
public class NeedThing  extends AbstractEntity {

  private String item;
  private String count;
  private String state;
  private int userId;
  @Transient
  @TableField(exist = false)
  private String username;


  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
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
}
