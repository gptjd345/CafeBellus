package com.bellus.web.model.view.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bellus.web.model.board.dto.BoardDTO;
import com.bellus.web.model.view.dto.ViewDTO;

@Repository
public class ViewDAOImpl implements ViewDAO {
	
	@Inject
	SqlSession sqlSession;
	
	//pikicast 테이블의 모든 내용 가져옴
	@Override
	public List<ViewDTO> listAll() throws Exception {

		return sqlSession.selectList("view.listAll");
	}
	
	//pikicast 상세정보 가져오기
	@Override
	public ViewDTO detail(int pnum) {
		
		return sqlSession.selectOne("view.detail", pnum);
	}
	
	//pikicast 수정하기
	@Override
	public void update(ViewDTO dto) throws Exception {
		sqlSession.selectOne("view.update",dto);
		
	}
	//pikicast 해당 로우 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.selectOne("view.delete",bno);
		
	}
	
	@Override
	public void insert(ViewDTO dto) throws Exception {

		sqlSession.selectOne("view.insert",dto);
		
	}
	
	@Override
	public void deleteFile(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAttach(String fullName, int bnoCur) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAttach(String fullName, int bno) {
		// TODO Auto-generated method stub

	}

	@Override
	public int create(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	


	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
