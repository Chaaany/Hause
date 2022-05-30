package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.CommentDto;
import com.ssafy.vue.dto.QnaBoard;
import com.ssafy.vue.service.QnaBoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qnaboard")
public class QnaBoardController {

	private static final Logger logger = LoggerFactory.getLogger(QnaBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaBoardService qnaboardService;

	@ApiOperation(value = "모든 Qna게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnaBoard>> retrieveBoard(@RequestParam int pg) throws Exception {
		logger.debug("retrieveBoard - 호출");
		int offset = (pg - 1) * 10;
		return new ResponseEntity<List<QnaBoard>>(qnaboardService.retrieveBoard(offset), HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 Qna게시글의 정보를 반환한다.", response = QnaBoard.class)
	@GetMapping("{articleno}")
	public ResponseEntity<QnaBoard> detailBoard(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<QnaBoard>(qnaboardService.detailBoard(articleno), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 Qna게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody QnaBoard board) {
		logger.debug("writeBoard - 호출");
		if (qnaboardService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 Qna게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody QnaBoard board) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);

		if (qnaboardService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 Qna게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		if (qnaboardService.deleteBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("search")
	@ApiOperation(value = "?key=검색기준&word=검색패턴 에 해당하는 모든 사용자 Qna게시글을 반환한다.", response = QnaBoard.class)
	public ResponseEntity<?> search(@RequestParam String key, @RequestParam String value) throws Exception {
		// 키 == userid, articleno, subject일 때 각각 searchByUserid, searchByArticleno,
		// searchBySubject
		List<QnaBoard> list = null;
		if (key.equals("userid")) {
			list = qnaboardService.searchByUserid(value);
		} else if (key.equals("articleno")) {
			list = qnaboardService.searchByArticleno(value);
		} else if (key.equals("subject")) {
			list = qnaboardService.searchBySubject(value);
		} else {
			int offset = 0;
			list = qnaboardService.retrieveBoard(offset);
		}

		if (list != null && list.size() > 0) {
			return new ResponseEntity<List<QnaBoard>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "Qna게시판 글개수", notes = "모든 Qna게시글의 개수를 반환한다.", response = Integer.class)
	@GetMapping("/count")
	public ResponseEntity<Integer> countBoard() throws Exception {
		logger.debug("countBoard - 호출");
		return new ResponseEntity<Integer>(qnaboardService.countBoard(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 리스트", notes = "해당 번호 글의 댓글 리스트를 반환한다.", response = List.class)
	@GetMapping("/comment/{articleno}")
	public ResponseEntity<List<CommentDto>> commentList(@PathVariable int articleno) throws Exception {
		logger.debug("commentList - 호출");
		return new ResponseEntity<List<CommentDto>>(qnaboardService.commentList(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/comment")
	public ResponseEntity<String> writeBoard(@RequestBody CommentDto comment) {
		logger.debug("writeComment - 호출");
		if (qnaboardService.writeComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 번호에 해당하는 댓글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/comment/{commentno}")
	public ResponseEntity<String> updateComment(@RequestBody CommentDto comment) {
		logger.debug("updateComment - 호출");
		if (qnaboardService.updateComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/comment/{commentno}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentno) {
		logger.debug("deleteComment - 호출");
		if (qnaboardService.deleteComment(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 번호에 해당하는 댓글의 정보를 반환한다.", response = CommentDto.class)
	@GetMapping("/comment/detail/{commentno}")
	public ResponseEntity<CommentDto> detailComment(@PathVariable int commentno) {
		logger.debug("detailComment - 호출");
		return new ResponseEntity<CommentDto>(qnaboardService.detailComment(commentno), HttpStatus.OK);
	}
}