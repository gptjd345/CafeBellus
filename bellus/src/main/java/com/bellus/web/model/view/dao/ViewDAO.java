package com.bellus.web.model.view.dao;

import java.util.List;

import com.bellus.web.model.board.dto.BoardDTO;
import com.bellus.web.model.view.dto.ViewDTO;

public interface ViewDAO {
	//pikicast 테이블에 담긴모든 내용을 list에 담아옴
	public List<ViewDTO> listAll() throws Exception;
	
	//pikicast 테이블에서 해당 번호에 맞는 글 가져오기
	public ViewDTO detail(int pnum);
	
	//pikicast 수정하기
	public void update(ViewDTO dto) throws Exception; // 글수정
	
	//pikicast 추가하기
	public void insert(ViewDTO dto) throws Exception; // 글추가
	
	public void deleteFile(String fullName); // 첨부파일 삭제
	public List<String> getAttach(int bno); // 첨부파일 정보
//	public void addAttach(String fullName); // 첨부파일 저장
	public void addAttach(String fullName, int bnoCur); // 첨부파일 저장
	public void updateAttach(String fullName, int bno); // 첨부파일 수정
	
	public int create(BoardDTO dto) throws Exception; // 글쓰기 
	
	public void delete(int bno) throws Exception; // 글삭제
    
    
	//레코드 개수 계산
	public int countArticle(String search_option, String keyword) throws Exception;
    
	//레코드 조회
	public BoardDTO read(int bno) throws Exception;
}
