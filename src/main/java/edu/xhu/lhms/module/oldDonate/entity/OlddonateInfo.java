package edu.xhu.lhms.module.oldDonate.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "olddonate_info")
@TableName("olddonate_info")
public class OlddonateInfo  extends AbstractEntity {

  private String oldthings;

  private String state;
  private Integer userId;




  public String getOldthings() {
    return oldthings;
  }

  public void setOldthings(String oldthings) {
    this.oldthings = oldthings;
  }





  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

}
