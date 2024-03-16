package edu.xhu.lhms.module.news.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "news")
@TableName("news")
public class News  extends AbstractEntity {

  private String image;
  private String title;
  private String content;

  private int likeNumber;
  private String state;
  private int userId;
  @Transient
  @TableField(exist = false)
  private String username;



  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public int getLikeNumber() {
    return likeNumber;
  }

  public void setLikeNumber(int likeNumber) {
    this.likeNumber = likeNumber;
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
