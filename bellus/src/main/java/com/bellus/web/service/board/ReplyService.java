package com.bellus.web.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.bellus.web.model.board.dto.ReplyDTO;

public interface ReplyService {

	List<ReplyDTO> list(int bno, int start, int end, HttpSession session);
	public int count(int bno);
	public void create(ReplyDTO dto);
	public void update(ReplyDTO dto);
	public void delete(int rno);
	public ReplyDTO detail(int rno);
	

}
