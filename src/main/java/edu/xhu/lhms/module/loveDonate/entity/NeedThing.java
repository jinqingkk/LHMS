package edu.xhu.lhms.module.loveDonate.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "need_thing")
@TableName("need_thing")
public class NeedThing  extends AbstractEntity {

  private String content;

  private String state;
  private Integer userId;



  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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
