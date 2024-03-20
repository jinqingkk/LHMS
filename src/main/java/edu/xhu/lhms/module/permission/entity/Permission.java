package edu.xhu.lhms.module.permission.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permission")
@TableName("permission")
public class Permission {

  @Id
  private int id;
  private String authName;
  private String path;
  private int parentId;
  private int identity;
  @Transient
  @TableField(exist = false)
  List<Permission> children;

  @Transient
  @TableField(exist = false)
  private String parentName;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getAuthName() {
    return authName;
  }

  public void setAuthName(String authName) {
    this.authName = authName;
  }


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }


  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public int getIdentity() {
    return identity;
  }

  public void setIdentity(int identity) {
    this.identity = identity;
  }

  public List<Permission> getChildren() {
    return children;
  }

  public void setChildren(List<Permission> children) {
    this.children = children;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }
}
