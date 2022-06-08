package com.kh.mybatis.board.model.service;

import java.util.ArrayList;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;
//인터페이스 : 상수필드 (public static final) + 추상메소드(public abstract)
public interface BoardService {

	// 게시글 리스트 조회 
	/*public abstract*/int selectListCount(); //(게시글 갯수 조회용 페이징처리용)
	
	ArrayList<Board> selectList(PageInfo pi); //게시글 전체 조회용
	
	//게시글 상세 조회
	int increaseCount(int boardNo); //(게시글 조회수 증가용)
	
	Board selectBoard(int boardNo); //(게시글 상세조회용)
	
	// 댓글 조회
	ArrayList<Reply> selectReplyList(int boardNo);
	
	
	
}
