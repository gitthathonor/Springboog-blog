package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;

@Controller
@RequiredArgsConstructor
public class BoardsController {
	
	private final HttpSession session;
	private final BoardsDao boardsDao;
	
	// @PostMapping("/boards/{id}/delete")
	// @PostMapping("/boards/{id}/update")

	@GetMapping({"/","boards"})
	public String getBoardList() {
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id) {
		return "boards/detail";
	}
	
	@GetMapping("/boards/writeForm")
	public String writeForm(){
		Users principal = (Users)session.getAttribute("principal");
		if(principal == null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}
	
	@PostMapping("/write")
	public String write(WriteDto writeDto) {
		Users principal = (Users)session.getAttribute("principal");
		
		if(principal == null) {
			return "redirect:/loginForm";
		}
		
		// 1. Boards 객체 생성해서 직접 setter로 넣어주기
//		Boards boards =  new Boards();
//		boards.setTitle(writeDto.getTitle());
//		boards.setContent(writeDto.getContent());
//		boards.setUserId(principal.getId());
//		boardsDao.insert(boards);
		
		// 2. 코드 리팩토링
		
		
		
		
		return "redirect:/";
	}
	
	
}
