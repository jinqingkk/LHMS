package edu.xhu.lhms.module.loveDonate.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lovedonate_info")
@TableName("lovedonate_info")
public class LovedonateInfo  extends AbstractEntity {

  private String donaThings;

  private String state;
  private int userId;
  @Transient
  @TableField(exist = false)
  private  String username;



  public String getDonaThings() {
    return donaThings;
  }

  public void setDonaThings(String donaThings) {
    this.donaThings = donaThings;
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
