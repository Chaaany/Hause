package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.CommentDto;
import com.ssafy.vue.dto.QnaBoard;

public interface QnaBoardService {
	public List<QnaBoard> retrieveBoard(int offset);
	public QnaBoard detailBoard(int articleno);
	public boolean writeBoard(QnaBoard board);
	public boolean updateBoard(QnaBoard board);
	public boolean deleteBoard(int articleno);
	public List<QnaBoard> searchByUserid(String value);
	public List<QnaBoard> searchByArticleno(String value);
	public List<QnaBoard> searchBySubject(String value);
	public int countBoard();
	public List<CommentDto> commentList(int articleno);
	public boolean writeComment(CommentDto comment);
	public boolean updateComment(CommentDto comment);
	public boolean deleteComment(int commentno);
	public CommentDto detailComment(int commentno);
}