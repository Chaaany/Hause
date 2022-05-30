package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.CommentDto;
import com.ssafy.vue.dto.QnaBoard;
import com.ssafy.vue.mapper.QnaBoardMapper;



@Service
public class QnaBoardServiceImpl implements QnaBoardService {
	
    @Autowired
	private QnaBoardMapper qnaBoardMapper;

    @Override
	public List<QnaBoard> retrieveBoard(int offset) {
		return qnaBoardMapper.selectBoard(offset);
	}
    
  	@Override
	public boolean writeBoard(QnaBoard board) {
		return qnaBoardMapper.insertBoard(board) == 1;
	}

	@Override
	public QnaBoard detailBoard(int articleno) {
		return qnaBoardMapper.selectBoardByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(QnaBoard board) {
		return qnaBoardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return qnaBoardMapper.deleteBoard(articleno) == 1;
	}

	@Override
	public List<QnaBoard> searchByUserid(String value) {
		return qnaBoardMapper.searchByUserid(value);
	}

	@Override
	public List<QnaBoard> searchByArticleno(String value) {
		return qnaBoardMapper.searchByArticleno(value);
	}

	@Override
	public List<QnaBoard> searchBySubject(String value) {
		return qnaBoardMapper.searchBySubject(value);
	}
	
	@Override
	public int countBoard() {
		return qnaBoardMapper.countBoard();
	}

	@Override
	public List<CommentDto> commentList(int articleno) {
		return qnaBoardMapper.commentList(articleno);
	}

	@Override
	public boolean writeComment(CommentDto comment) {
		return qnaBoardMapper.writeComment(comment) == 1;
	}

	@Override
	public boolean updateComment(CommentDto comment) {
		return qnaBoardMapper.updateComment(comment) == 1;
	}

	@Override
	public boolean deleteComment(int commentno) {
		return qnaBoardMapper.deleteComment(commentno) == 1;
	}

	@Override
	public CommentDto detailComment(int commentno) {
		return qnaBoardMapper.selectCommentByNo(commentno);
	}

}