package edu.xhu.lhms.module.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description Image
 * @Author JiangHu
 * @Date 2023/6/12 15:11
 */
@Entity
@Table(name = "common_image")
@TableName("common_image")
public class Image extends AbstractEntity {
	private String subject; // 图片类型+目标的id
	private String src;
	private String alt;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
}
