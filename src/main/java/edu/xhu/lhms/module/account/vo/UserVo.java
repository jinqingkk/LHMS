package edu.xhu.lhms.module.account.vo;

import edu.xhu.lhms.module.common.vo.Search;
import org.apache.commons.lang3.StringUtils;

public class UserVo extends Search {
    int id;
    String userName;
    String gender;
    String rellname;
    private  String phoneNumber;
    private String email;
    private String idNumber;

    private String identity;
//    int page;
//    int limit;
//    public void initSearch() {
//        this.currentPage = this.page == 0 ? 1 : this.page;
//        this.pageSize = this.limit == 0 ? 5 : this.limit;
//        this.sort = StringUtils.isBlank(this.sort) ? "id" : this.sort;
//        this.direction = StringUtils.isBlank(this.direction) ? "asc" : this.direction;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRellname() {
        return rellname;
    }

    public void setRellname(String rellname) {
        this.rellname = rellname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
