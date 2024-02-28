package com.Application.entity.pojo;


public class News {

  private Integer newsId;
  private String image;
  private String title;
  private String content;
  private java.util.Date createTime;
  private java.util.Date updateTime;
  private Integer likeNumber;
  private String state;
  private Integer userId;


  public Integer getNewsId() {
    return newsId;
  }

  public void setNewsId(Integer newsId) {
    this.newsId = newsId;
  }


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


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public java.util.Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.util.Date updateTime) {
    this.updateTime = updateTime;
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
