package edu.xhu.lhms.module.news.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import edu.xhu.lhms.module.common.entity.AbstractEntity;
import edu.xhu.lhms.module.common.entity.Image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
@TableName("news")
public class News  extends AbstractEntity {
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
  private int likeNumber;
  private String state;
  private  String region;
  private int userId;
  @Transient
  @TableField(exist = false)
  private String username;


  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
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
}
