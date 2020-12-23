package com.bellus.web.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bellus.web.model.board.dto.BoardDTO;
import com.bellus.web.model.board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDao;
	
	//게시글 리스트 받아오기
	@Override
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
		//시작번호, 끝번호, 옵션, 키워드
		return boardDao.listAll(start, end, search_option, keyword);
	}
	
	//게시글 개수 받아오기
	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		
		return boardDao.countArticle(search_option, keyword);
	}
	
	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
	}
	
	@Transactional //트랜잭션 처리 method
	//코드 수정과 첨부파일을 첨부하는 기능이 동시에 같이 들어가야함
	//만약 두개중 하나라도 안되면 작업을 취소시키고, 롤백
	@Override
	public void update(BoardDTO dto) throws Exception {
		//board 테이블 수정
		boardDao.update(dto);
		//attach 테이블 수정
		String[] files = dto.getFiles();
		if(files==null) {System.out.println("첨부파일을 가져오지 못했습니다."); return;}
		for(String name : files) {
			System.out.println("첨부파일 이름: "+name);
			boardDao.updateAttach(name, dto.getBno());
		}
		
	}
	
	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}
	
	//첨부파일 레코드 삭제
	@Override
	public void deleteFile(String fullName) {
		boardDao.deleteFile(fullName);
	}
	

	//게시물 작성과 파일 업로드가 둘다 완료가 된 상태에서만 글을 써야하기떄문에 트랜잭션 처리를 해주어야함
	@Transactional 
	@Override
	public void create(BoardDTO dto) throws Exception {
			
		//board 테이블에 레코드 추가 먼저
		 boardDao.create(dto);
		
		//attach 테이블에 레코드 추가
		String[] files = dto.getFiles(); //첨부파일 이름 배열
	
		if(files==null) return; //첨부파일이 없으면 skip
		for(String name : files) {
			boardDao.addAttach(name,dto.getBno()); //attach 테이블에 insert
			//아직 글번호가 확정되지 않은 상태이기 때문에 (글번호는 작성을 해야 생기기 때문) 글번호를 넣는
			//파일 업로드는 게시글이 작성되지 않으면 업로드가 되지 않게 해야 에러가 발생되지 않는다. 
		}

	}


	@Transactional
	@Override
	public void delete(int bno) throws Exception {
		//reply 레코드 삭제
        //attach 레코드 삭제
        //첨부파일 삭제
        //board 레코드 삭제
        boardDao.delete(bno); 

	}


	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		//세션을 이용한 조회수 중복 증가 방지 
				long update_time=0;
		        if(session.getAttribute("update_time_"+bno)!=null) {
		            //최근에 조회수를 올린 시간
		            update_time=
		                    (Long) session.getAttribute("update_time_"+bno);
		        }
		        long current_time=System.currentTimeMillis();
		        //일정 시간이 경과한 후 조회수 증가 처리
		        if(current_time - update_time > 60*1000*60*24) {
		            //조회수 증가 처리
		            boardDao.increaseViewcnt(bno);
		            //조회수를 올린 시간 저장
		            session.setAttribute("update_time_"+bno, current_time);
		        }
		
	}


}
