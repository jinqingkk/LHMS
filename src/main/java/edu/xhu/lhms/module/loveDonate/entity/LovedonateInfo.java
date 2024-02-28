package edu.xhu.lhms.module.loveDonate.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lovedonate_info")
@TableName("lovedonate_info")
public class LovedonateInfo  extends AbstractEntity {

  private String donaThings;

  private String state;
  private Integer userId;




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


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

}
