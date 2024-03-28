package edu.xhu.lhms.module.regisActivity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "regis_activity")
@TableName("regis_activity")
public class RegisActivity extends AbstractEntity {


  private String state;
  private int actId;
  @Transient
  @TableField(exist = false)
  private String title;
  private int userId;

  @Transient
  @TableField(exist = false)
  private String username;

  private String reason;



  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public Integer getActId() {
    return actId;
  }

  public void setActId(Integer actId) {
    this.actId = actId;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
