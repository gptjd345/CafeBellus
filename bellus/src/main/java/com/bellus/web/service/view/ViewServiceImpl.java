package com.bellus.web.service.view;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.bellus.web.model.board.dao.BoardDAO;
import com.bellus.web.model.board.dto.BoardDTO;
import com.bellus.web.model.board.dto.ReplyDTO;
import com.bellus.web.model.view.dao.ViewDAO;
import com.bellus.web.model.view.dto.ViewDTO;

@Service
public class ViewServiceImpl implements ViewService {
	
	@Inject
	ViewDAO viewDao;
	
	//pikicast 테이블의 모든 것을 가져온다.
	@Override
	public List<ViewDTO> listAll() throws Exception {
	
		return viewDao.listAll();
	}
	
	//댓글상세 보기 댓글을 수정삭제 할수있는 폼에 해당 댓글 내용가져옴
	@Override
	public ViewDTO detail(int pnum) {	
		return viewDao.detail(pnum);
	}

	@Override
	public void update(ViewDTO dto) throws Exception {
		
		//만약 수정시에 이미지를 변경하지 않았다면 pnum 값을 이용하여 원래 이미지 경로를 찾아서 저장한다.
		if(dto.getImagepath() == null)
		{
			String imagePath = viewDao.detail(dto.getPnum()).getImagepath();
			dto.setImagepath(imagePath);
			System.out.println("Service imagePath: "+imagePath);
		}
		viewDao.update(dto);
		
	}
	
	//pikicast 테이블의 로우 삭제 
	@Override
	public void delete(int bno) throws Exception {
		viewDao.delete(bno);
	}
	@Override
	public void insert(ViewDTO dto) throws Exception {
		 viewDao.insert(dto);
		
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
	public void create(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	


	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}



}
