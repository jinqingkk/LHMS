package com.Application.entity.pojo;


public class NeedThing {

  private Integer thingId;
  private String content;
  private java.util.Date createTime;
  private java.util.Date updateTime;
  private String state;
  private Integer userId;


  public Integer getThingId() {
    return thingId;
  }

  public void setThingId(Integer thingId) {
    this.thingId = thingId;
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
