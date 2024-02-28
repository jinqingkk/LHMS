package edu.xhu.lhms.module.news.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "news")
@TableName("news")
public class News  extends AbstractEntity {

  private String image;
  private String title;
  private String content;

  private Integer likeNumber;
  private String state;
  private Integer userId;




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




  public Integer getLikeNumber() {
    return likeNumber;
  }

  public void setLikeNumber(Integer likeNumber) {
    this.likeNumber = likeNumber;
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
