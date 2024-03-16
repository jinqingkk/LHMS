package edu.xhu.lhms.module.volunActivity.vo;

import edu.xhu.lhms.module.common.vo.Search;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public class VolunActivityVo extends Search {
    private String image;
    private String title;
    private String content;

    private String state;
    private int userId;
    private String username;
    private LocalDateTime createDate;
    int page;
    int limit;
    public void initSearch() {
        this.currentPage = this.page == 0 ? 1 : this.page;
        this.pageSize = this.limit == 0 ? 5 : this.limit;
        this.sort = StringUtils.isBlank(this.sort) ? "id" : this.sort;
        this.direction = StringUtils.isBlank(this.direction) ? "asc" : this.direction;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
