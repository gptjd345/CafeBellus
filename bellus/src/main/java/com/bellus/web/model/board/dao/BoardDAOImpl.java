package com.bellus.web.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bellus.web.model.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession sqlSession;
	
	//게시글 모든 리스트 가져오는 DAO
	@Override
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		return sqlSession.selectList("board.listAll", map);
	}
	
	//게시글 개수 가져오는 DAO
	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("board.countArticle",map);
	}
	
	@Override
	public BoardDTO read(int bno) throws Exception {
		//맵퍼이름 view -> detail로 변경했음
		return sqlSession.selectOne("board.detail",bno);
	}
	
	//글 전체 업데이트
	@Override
	public void update(BoardDTO dto) throws Exception {
		sqlSession.update("board.update",dto);
		
	}
	
	@Override
	public void updateAttach(String fullName, int bno) {
		Map<String,Object> map=new HashMap<String,Object>(); //값을 여러개 담을때는 haspmap를 사용한다. 
		map.put("fullName", fullName); //첨부파일 이름
		map.put("bno", bno); //게시물 번호
		sqlSession.insert("board.updateAttach", map); //updateAttach mapper을 호출
		
	}
	
	@Override
	public List<String> getAttach(int bno) {
		return sqlSession.selectList("board.getAttach", bno);
	}
	
	@Override
	public void deleteFile(String fullName) {
		sqlSession.delete("board.deleteAttach", fullName);
	}
	//글쓰기
	@Override
	public int create(BoardDTO dto) throws Exception {
	
		return sqlSession.insert("board.insert", dto);
		
		
	}

	@Override
	public void addAttach(String fullName, int bno) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fullName", fullName);
		map.put("bno", bno);
		
		sqlSession.insert("board.addAttach",map);
	}
	
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.deleteArticle",bno); //mapper로 게시글 번호 넘긴다.

	}


	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("board.increaseViewcnt",bno);

	}

	

}
