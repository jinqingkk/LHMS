package edu.xhu.lhms.module.volunActivity.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import edu.xhu.lhms.module.common.entity.AbstractEntity;
import edu.xhu.lhms.module.common.entity.Image;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

public class VolunActivity extends AbstractEntity {
  @Transient
  @TableField(exist = false)
 private List<Image> images;
  private String title;
  private String content;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(updatable = false)
  private LocalDateTime startDate;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(updatable = false)
  private LocalDateTime endDate;
  private String region;
  private int count;
  private String state;
  private String display;
  private int userId;
  @Transient
  @TableField(exist = false)
  private String username;
  @Transient
  @TableField(exist = false)
  private int regisCount;



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




  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }


  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getRegisCount() {
    return regisCount;
  }

  public void setRegisCount(int regisCount) {
    this.regisCount = regisCount;
  }
}
