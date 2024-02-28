package edu.xhu.lhms.module.activity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "regis_activity")
@TableName("regis_activity")
public class RegisActivity extends AbstractEntity {


  private String state;
  private Integer actId;
  private Integer userId;




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

}
