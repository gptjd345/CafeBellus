package com.bellus.web.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bellus.web.model.board.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ReplyDTO> list(int bno, int start, int end) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bno", bno);
		map.put("start", start);
		map.put("end", end);
        return sqlSession.selectList("reply.listReply", map);
	}

	@Override
	public int count(int bno) {
		return sqlSession.selectOne("reply.count",bno);
	}

	//댓글 쓰기    
	@Override
    public void create(ReplyDTO dto) {
        sqlSession.insert("reply.insertReply", dto); 
    }

	//댓글 상세보기 : 댓글 번호를 인자값으로 받아 해당 댓글의 내용을 모두 가져옴
	@Override
	public ReplyDTO detail(int rno) {
		
		return sqlSession.selectOne("reply.detailReply", rno);
	}
	
	//댓글 수정
	@Override
	public void update(ReplyDTO dto) {
		sqlSession.update("reply.updateReply",dto);
		
	}

	@Override
	public void delete(int rno) {
		sqlSession.delete("reply.deleteReply",rno);
		
	}


}
