package edu.xhu.lhms.module.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import edu.xhu.lhms.module.common.vo.Search;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class LoginInfoVo extends Search {
  private int userId;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createDate;
  private  String discription;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public String getDiscription() {
    return discription;
  }

  public void setDiscription(String discription) {
    this.discription = discription;
  }
}
