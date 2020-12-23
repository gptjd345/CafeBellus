package com.bellus.web.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.bellus.web.model.board.dao.ReplyDAO;
import com.bellus.web.model.board.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	ReplyDAO replyDao;
	
	//댓글 목록
	@Override
    public List<ReplyDTO> list(int bno, int start, int end, HttpSession session) {
        List<ReplyDTO> items = replyDao.list(bno, start, end);
        //세션에서 현재 사용자 id값 저장
        String userid = (String)session.getAttribute("userid");
        for(ReplyDTO dto : items) {
        	//댓글 목록 중에 비밀 댓글이 있을경우
        	if(dto.getSecret_reply().equals("y")) {
        		dto.setReplytext("비밀 댓글입니다.");
			} 
        }
		return items; //원래 그냥 replyDao에서 받아온 리스트를 컨트롤러에 보낼것을 한번 확인하고 복사본을 보내는 느낌
    }

	@Override
	public int count(int bno) {
		return replyDao.count(bno);
	}

	//댓글 쓰기    
	@Override
    public void create(ReplyDTO dto) {
        replyDao.create(dto);
    }

	//댓글상세 보기 댓글을 수정삭제 할수있는 폼에 해당 댓글 내용가져옴
	@Override
	public ReplyDTO detail(int rno) {	
		return replyDao.detail(rno);
	}
	
	@Override
	public void update(ReplyDTO dto) {
		replyDao.update(dto);
		
	}

	@Override
	public void delete(int rno) {
		replyDao.delete(rno);
		
	}


}
