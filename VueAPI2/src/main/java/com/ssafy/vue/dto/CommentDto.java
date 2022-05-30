package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Comment (댓글)", description = "글번호, 작성자아이디, 내용, 작성일을 가진   Class")
public class CommentDto {
	@ApiModelProperty(value = "댓글번호")
	private int commentno;
	@ApiModelProperty(value = "글번호")
	private int articleno;
	@ApiModelProperty(value = "작성자아이디")
	private String userid;
	@ApiModelProperty(value = "작성자이름")
	private String username;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	
	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

}