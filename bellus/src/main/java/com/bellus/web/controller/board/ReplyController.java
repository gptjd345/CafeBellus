package com.bellus.web.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bellus.web.model.board.dto.ReplyDTO;
import com.bellus.web.service.board.Pager;
import com.bellus.web.service.board.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	@Inject
	ReplyService replyService;
	
	
	// 댓글 목록(@RestController방식 :  json으로 전달하여 목록생성)
    // /reply/list/1 => 1번 게시물의 댓글 목록 리턴
    // /reply/list/2 => 2번 게시물의 댓글 목록 리턴
    // @PathVariable : url에 입력될 변수값 지정
    @RequestMapping(value="/list/{bno}/{curPage}", method=RequestMethod.POST)
	@ResponseBody // view가 아닌 데이터 자체를 리턴(json)
    public ModelAndView replyList(@PathVariable("bno") int bno, @PathVariable int curPage, 
    		ModelAndView mav, HttpSession session) {
        // 페이징 처리
        int count = replyService.count(bno); // 댓글 개수
        Pager pager = new Pager(count, curPage); 
        // 현재 페이지의 페이징 시작 번호
        int start = pager.getPageBegin();
        // 현재 페이지의 페이징  끝 번호
        int end = pager.getPageEnd();
        
     System.out.println("ReplyController start : " + start +" / end : "+ end +" / curPage : "+ curPage);
     
        List<ReplyDTO> list = replyService.list(bno, start, end, session);
        // 뷰이름 지정
        mav.setViewName("board/reply_list");
        // 뷰에 전달할 데이터 지정
        mav.addObject("list", list);
        mav.addObject("pager", pager);
        
        // replyList.jsp로 포워딩
        return mav;
    }

	
	/* 1_2. 댓글 입력(@RestControllerRest방식으로 json전달하여 댓글 입력 매핑(insertRest.do) */
	// 댓글 입력시 예외적인 상황을 위해 try catch문에 ResponseEntity로 HTTP 상태 메시지 전송 코드 추가
	//댓글 입력(Rest방식으로 json전달하여 글쓰기)
	//@ResponseEntity // 데이터 + http status code
	@ResponseBody // 객체를 json으로 (json - String)
	@RequestMapping(value="insertRest.do", method=RequestMethod.POST)
	public ResponseEntity<String> insertRest(@RequestBody ReplyDTO dto, HttpSession session){
		ResponseEntity<String> entity = null;
		try {
			String userid = (String)session.getAttribute("userid");
			dto.setReplyer(userid);
			replyService.create(dto);
			// 댓글 입력이 성공하면 성공메시지 저장
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 댓글 입력이 실패하면 실패메시지 저장 
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 입력 처리 HTTP 상태 메시지 리턴
		return entity;
	}
	
	//댓글 상세보기
	//댓글을 수정하거나 삭제할수잇는 폼의 처리
	//url에 입력될 변수 값을 지정하기 위해 @PathVariable을 사용한다.
	@RequestMapping(value="/detail/{rno}",method=RequestMethod.POST)
	@ResponseBody //view가 아닌 데이터 자체를 리턴
	public ModelAndView replyDetail(@PathVariable("rno") int rno, ModelAndView mav) {
		ReplyDTO dto = replyService.detail(rno);
		
		mav.setViewName("board/replyDetail");
		mav.addObject("dto",dto);
		return mav;
	}
	
    // 4. 댓글 수정 처리 - PUT:전체 수정, PATCH:일부수정(여기서 사용 불가) 
    // RequestMethod를 여러 방식으로 설정할 경우 {}안에 작성 
    @RequestMapping(value="/update/{rno}", method=RequestMethod.PUT)
    public ResponseEntity<String> replyUpdate(@PathVariable("rno") int rno, @RequestBody ReplyDTO dto) {
	    ResponseEntity<String> entity = null;
	    
	    try {
	        dto.setRno(rno);
	        replyService.update(dto);
	        // 댓글 수정이 성공하면 성공 상태메시지 저장
	        entity = new ResponseEntity<String>("success", HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 댓글 수정이 실패하면 실패 상태메시지 저장
	        entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    // 수정 처리 HTTP 상태 메시지 리턴
	    return entity;
	}
    
    @RequestMapping(value="/delete/{rno}", method=RequestMethod.DELETE)
    public ResponseEntity<String> replyDelete(@PathVariable("rno") int rno){
    	ResponseEntity<String> entity = null;
    	
    	try {
    		replyService.delete(rno);
    		// 댓글 삭제가 성공하면 성공 상태메시지 저장
    		entity = new ResponseEntity<String>("success",HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		// 댓글 삭제가 실패 상태메시지 저장
    		entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    	// 삭제 처리후 HTTP 상태 메시지 리턴
    	return entity;
    }

	
	
}
