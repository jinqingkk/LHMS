package com.Application.entity.pojo;


public class LoginInfo {

  private Integer loginId;
  private java.util.Date loginInTime;
  private java.util.Date loginOutTime;
  private Integer userId;


  public Integer getLoginId() {
    return loginId;
  }

  public void setLoginId(Integer loginId) {
    this.loginId = loginId;
  }


  public java.util.Date getLoginInTime() {
    return loginInTime;
  }

  public void setLoginInTime(java.util.Date loginInTime) {
    this.loginInTime = loginInTime;
  }


  public java.util.Date getLoginOutTime() {
    return loginOutTime;
  }

  public void setLoginOutTime(java.util.Date loginOutTime) {
    this.loginOutTime = loginOutTime;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

}
