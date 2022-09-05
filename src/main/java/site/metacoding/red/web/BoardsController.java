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
	
	private final BoardsDao boardsDao;
	private final HttpSession session;
	
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
	public String writeForm() {
		Users principal = (Users)session.getAttribute("principal");
		if(principal == null) {
			return "redirect:/loginForm";
		}
		
		return "boards/writeForm";
	}
	
	
	@PostMapping("/boards")
	public String writeBoards(WriteDto writeDto) {
		// 1번 세션에 접근해서 세션 값을 확인한다. 그 때 Users로 다운캐스팅하고 key값은 principal로 한다.
		Users principal = (Users)session.getAttribute("principal");
		// 2번 principal이 null인지 확인하고 null이면 loginForm을 redirection해준다.
		if(principal != null) {
			return "redirect:/loginForm";
		}
		
		// 3번 BoardsDao에 접근해서 insert 메서드를 호출한다.
		
		boardsDao.insert(writeDto.toEntity(principal.getId()));
		// 조건 : dto를 entity로 변환해서 인수로 담아준다.
		// 조건 : entity에는 세션의 principal에 getId가 필요하다.
		
		return "redirect:/";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Users principal = (Users) session.getAttribute("principal");
//		
//		if(principal == null) {
//			return "redirect:/loginForm";
//		}
//		
//		boardsDao.insert(writeDto.toEntity(principal.getId()));
//		return "redirect:/";
	}
	
}
