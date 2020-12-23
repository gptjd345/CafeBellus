package com.bellus.web.service.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.bellus.web.model.board.dto.BoardDTO;
import com.bellus.web.model.view.dto.ViewDTO;

public interface ViewService {
	//pikicast 리스트 다가져오기
	public List<ViewDTO> listAll() throws Exception;
	
	//pikicast 글번호를 이용해 해당 이미지와 정보 가져오기
	public ViewDTO detail(int pnum) ;
	
	//pikicast 수정하기
	public void update(ViewDTO dto) throws Exception; 
	
	//pikicast 추가하기
	public void insert(ViewDTO dto) throws Exception;
	
	public void deleteFile(String fullName); // 첨부파일 삭제
	public List<String> getAttach(int bno); // 첨부파일 목록
	
	public void create(BoardDTO dto) throws Exception; // 글쓰기
	public BoardDTO read(int bno) throws Exception; // 글읽기
	
	public void delete(int bno) throws Exception; // 글 삭제
	
    
	//조회수 증가 처리
	public void increaseViewcnt(int bno,HttpSession session) throws Exception; 
    
	//레코드 개수 계산
	public int countArticle( String search_option, String keyword) throws Exception;
}
