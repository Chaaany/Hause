package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.CommentDto;
import com.ssafy.vue.dto.QnaBoard;
@Mapper
public interface QnaBoardMapper {
	public List<QnaBoard> selectBoard(int offset);
	public QnaBoard selectBoardByNo(int articleno);
	public int insertBoard(QnaBoard board);
	public int updateBoard(QnaBoard board);
	public int deleteBoard(int articleno);
	public List<QnaBoard> searchByUserid(String value);
	public List<QnaBoard> searchByArticleno(String value);
	public List<QnaBoard> searchBySubject(String value);
	public int countBoard();
	public List<CommentDto> commentList(int articleno);
	public int writeComment(CommentDto comment);
	public int updateComment(CommentDto comment);
	public int deleteComment(int commentno);
	public CommentDto selectCommentByNo(int commentno);
}