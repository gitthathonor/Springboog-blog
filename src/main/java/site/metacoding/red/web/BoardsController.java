package site.metacoding.red.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@Controller
@RequiredArgsConstructor
public class BoardsController {

	private final BoardsDao boardsDao;
	private final HttpSession session;

	@PostMapping("/boards/{id}/delete")
	public String deleteBoards(@PathVariable Integer id) {
		// 영속화를 시키는 것이 좋다. -> 이유는 트랜젝션을 적게 하기 위해서
		Boards boardsPS = boardsDao.findById(id);

		// 공통 로직 시작

		// if는 비정상 로직을 타게 해서 걸러내는 필터 역할을 하는게 좋다.
		if (boardsPS == null) {
			System.out.println("boardsPS가 null입니다.");
			return "errors/badPage";
		}

		// 1. 인증체크
		Users principal = (Users) session.getAttribute("principal");
		if (principal == null) {
			System.out.println("principal이 null입니다.");
			return "redirect:/loginForm";
		}

		// 2. 권한체크 (pincipal.getId()와 boardsPS의 usersId를 비교)
		if (principal.getId() != boardsPS.getUsersId()) {
			return "redirect:/boards/" + id;
		}

		// 공통 로직 끝(모든 메서드마다 대부분 나온다.)

		boardsDao.delete(id); // delete의 핵심 로직
		return "redirect:/";

	}

	@GetMapping("/boards/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Boards boardsPS = boardsDao.findById(id);
		Users principal = (Users) session.getAttribute("principal");
		// 공통 로직 시작
		// if는 비정상 로직을 타게 해서 걸러내는 필터 역할을 하는게 좋다.
		if (boardsPS == null) {
			return "errors/badPage";
		}
		// 1. 인증체크
		if (principal == null) {
			return "redirect:/loginForm";
		}
		// 2. 권한체크 (pincipal.getId()와 boardsPS의 usersId를 비교)
		if (principal.getId() != boardsPS.getUsersId()) {
			return "errors/badPage";
		}
		// 공통 로직 끝(모든 메서드마다 대부분 나온다.)
		model.addAttribute("boards", boardsPS);
		return "boards/updateForm";
	}
	
	
	// update만 영속화 - 변경 - 수정
	@PostMapping("/boards/{id}/update")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		
		// 1.영속화
		Boards boardsPS = boardsDao.findById(id);
		Users principal = (Users) session.getAttribute("principal");
		if (boardsPS == null) {
			return "errors/badPage";
		}
		
		//인증체크
		if (principal == null) {
			return "redirect:/loginForm";
		}
		// 권한체크
		if (principal.getId() != boardsPS.getUsersId()) {
			return "errors/badPage";
		}
		
		// 2.변경
				boardsPS.글수정(updateDto);
		
		// 3.수정
		boardsDao.update(boardsPS);
		return "redirect:/boards/" + id; // 사용자의 UX에 따라서 변화가 있을 수 있다.
	}

	
	
	// http://localhost:8000/
	// http://localhost:8000/?page=
	@GetMapping({ "/", "boards" })
	public String getBoardList(Model model, Integer page) { // 0 -> 0, 1 - > 10, 2 -> 20
		if (page == null)
			page = 0;
		System.out.println("=========================");
		System.out.println("page: " + page);
		System.out.println("=========================");
		int startNum = page * 3;

		List<MainDto> boardsList = boardsDao.findAll(startNum);
		PagingDto paging = boardsDao.paging(page);

		// paging.set머시기로 dto 완성
		// 수정함
//		final int blockCount = 5;
//		
//		int currentBlock = page/blockCount;
//		int startPageNum = 1 + blockCount*currentBlock;
//		int lastPageNum = 5 + blockCount*currentBlock;
//		
//		if(paging.getTotalPage() < lastPageNum) {
//			lastPageNum = paging.getTotalPage();
//		}
//		
//		paging.setBlockCount(blockCount);
//		//paging.setCurrentBlock(currentBlock);
//		paging.setStartPageNum(startPageNum);
//		paging.setLastPageNum(lastPageNum);

		model.addAttribute("boardsList", boardsList);
		model.addAttribute("paging", paging);

		return "boards/main";
	}

	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}

	@PostMapping("/boards")
	public String writeBoards(WriteDto writeDto) {
		// 1번 세션에 접근해서 세션 값을 확인한다. 그 때 Users로 다운캐스팅하고 key값은 principal로 한다.
		Users principal = (Users) session.getAttribute("principal");
		// 2번 principal이 null인지 확인하고 null이면 loginForm을 redirection해준다.
		if (principal == null) {
			return "redirect:/loginForm";
		}
		// 3번 BoardsDao에 접근해서 insert 메서드를 호출한다.
		boardsDao.insert(writeDto.toEntity(principal.getId()));
		// 조건 : dto를 entity로 변환해서 인수로 담아준다.
		// 조건 : entity에는 세션의 principal에 getId가 필요하다.
		return "redirect:/";
	}

	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		Boards boards = boardsDao.findById(id);
		model.addAttribute("boards", boards);
		return "boards/detail";
	}

}
