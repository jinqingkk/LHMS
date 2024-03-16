package edu.xhu.lhms.module.news.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import edu.xhu.lhms.module.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "news_like")
@TableName("news_like")
public class NewsLike   extends AbstractEntity {

  private int newsId;
  private int userId;


  public int getNewsId() {
    return newsId;
  }

  public void setNewsId(int newsId) {
    this.newsId = newsId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

}
