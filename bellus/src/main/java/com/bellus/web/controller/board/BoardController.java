package com.bellus.web.controller.board;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bellus.web.service.board.BoardService;
import com.bellus.web.model.board.dto.BoardDTO;
import com.bellus.web.service.board.Pager;

@Controller
@RequestMapping("board*")
public class BoardController {
	
	
	@Inject
	BoardService boardService ;
	
	@RequestMapping(value={""})
	public ModelAndView list(//RequestParam으로 현재 페이지, 옵션, 키워드의 기본값을 각각 설정해준다.
	@RequestParam(defaultValue="1") int curPage,
	@RequestParam(defaultValue="all") String search_option,
	@RequestParam(defaultValue="") String keyword)
	//defaultValue를 설정하지 않으면 null point Exception이 발생할수 있기 때문에 기본값을 세팅
			 throws Exception {
		//레코드 갯수 계산 이건 일단 임의로 지정한 값임 나중에 계산할거
		int count = boardService.countArticle(search_option, keyword);
		
		//페이지 관련 설정, 시작 페이지 번호와 끝 페이지 번호를 구해서 각각 변수에 저장함
		Pager pager = new Pager(count, curPage); //레코드 개수와 현재 페이지 번호로 초기화
		int start = pager.getPageBegin(); // where rn between ? and ?; 에 넣어줄값임( mybatis쿼리의 마지막 부분 )
		int end = pager.getPageEnd();
		
		//게시물 목록을 출력하기 위해 <BoardDTO>타입에 list변수에 게시물 목록관련 값들을 저장함
		//넣어야할 값들이 여러게 있으므로 HASHMAP을 사용하여 값들을 담고 그 hashmap 변수를 ModelAndView 객체에 저장
		
		List<BoardDTO> list = boardService.listAll(start, end, search_option, keyword);
		//문제 발생시 이거 파라미터 확인할것!!!!
		
		System.out.println("list:"+list);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("list", list); //map에 자료저장
		map.put("count", count); //게시글이 총 몇개인지 알려줌
		map.put("pager", pager); //페이지관련 내용과 각종메소드를 가지고있는 객체(우리가 만들 Pager클래스)
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		
		ModelAndView mav = new ModelAndView(); //데이터를 담아놓은 map 객체 담고 페이지 포워딩도 해야하므로 선언
		
		mav.setViewName("board/list"); //포워딩할 뷰의 이름
		mav.addObject("map",map); //ModelAndView에 map을 저장
		return mav;

	}
	
	
	  //view.do를 detail.do로 바꿨음
	  @RequestMapping(value="/detail.do", method=RequestMethod.GET) 
	  public ModelAndView view(
			  		@RequestParam(defaultValue="1") int bno,
			  		@RequestParam(defaultValue="1") int curPage,
			  		@RequestParam(defaultValue="all") String search_option,
			  		@RequestParam(defaultValue="") String keyword,
			  		HttpSession session) throws Exception 
	  {
		  boardService.increaseViewcnt(bno,session);	
		  
		  ModelAndView mav = new ModelAndView();
		  mav.setViewName("board/detail");
		  mav.addObject("dto",boardService.read(bno));
		  mav.addObject("curPage",curPage);
		  mav.addObject("search_option",search_option);
		  mav.addObject("keyword",keyword);
		  return mav; 
	 
	  }
	  
	  	//게시글 수정
		@RequestMapping(value="update.do")
		public String update(@ModelAttribute BoardDTO dto,
				HttpServletRequest request) throws Exception{
			
			boardService.update(dto);
			
			//게시글 수정후 게시판 페이지로 이동
			
			return "redirect:/board/list.do";
		}
	
		//첨부파일 목록을 리턴
	    //ArrayList를 json 배열로 변환하여 리턴
	    //view에서 넘긴 bon를 경로값 (url에 포함된 변수)로 받아서 맵핑한다.
	    @RequestMapping("getAttach/{bno}")
	    @ResponseBody //view가 아닌 데이터 자체를 리턴할 때 사용하는 어노테이션
	                  //(즉, 화면이 바뀌는게 아니라 데이터가 넘어간다는 의미.. 리턴한 값)
	    
	    public List<String> getAttach(@PathVariable("bno") int bno){
	        return boardService.getAttach(bno); 
	    }
	    //글쓰기 버튼 클릭시 글쓰기 페이지로 이동
	    @RequestMapping("write.do")
		public String write() {
			return "board/write";
		}
	    
	  //write.jsp에서 입력한 내용들이 BoardDTO에 저장됨
		@RequestMapping("insert.do")
		public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception {
			// 로그인한 사용자의 아이디
			String writer = (String)session.getAttribute("userid");
			
//			if(writer==null) { 로그인처리 유무는 인텁셉터로 처리한다.
//				
//			}
			dto.setWriter(writer);
			// 레코드가 저장됨
			boardService.create(dto); // 인터셉터로 로그인한 유저만 글쓰기가 가능하도록 만들기 
			// 목록 갱신
			return "redirect:/board/list.do";
		}

		//게시글 삭제 처리
		 @RequestMapping("delete.do")
	    public String delete(int bno) throws Exception {
	        boardService.delete(bno); //삭제 처리
	        return "redirect:/board/list.do"; //목록으로 이동
	    }
}
