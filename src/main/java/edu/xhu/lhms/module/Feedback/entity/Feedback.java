package edu.xhu.lhms.module.Feedback.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "feedback")
@TableName("feedback")
public class Feedback extends AbstractEntity {

  private String title;
  private String content;
  private int userId;
  @Transient
  @TableField(exist = false)
  private String username;
  private String state;
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getUserId() {
    return userId;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
