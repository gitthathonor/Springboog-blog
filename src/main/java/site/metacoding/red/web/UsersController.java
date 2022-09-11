package site.metacoding.red.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@Controller
@RequiredArgsConstructor
public class UsersController {
	
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	private final HttpSession session; // spring이 서버 시작 시에 IoC 컨테이너에 보관함
	
	
	@PostMapping("/join")
	public String join(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return "redirect:/loginForm";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
	
	@PostMapping("/login")
	public String login(LoginDto loginDto) {
		Users usersPS = usersDao.login(loginDto);
		if(usersPS != null ) { //인증됨
			session.setAttribute("principal", usersPS);
			return "redirect:/";
		} else {
			return "redirect:/loginForm";
		}
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/users/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		
		Users usersPS = usersDao.findById(id);
		
		// 인증
		Users principal = (Users)session.getAttribute("principal");
		
		if(principal == null) {
			return "redirect:/loginForm";
		} 
		
		model.addAttribute("users", usersPS);
		
		return "users/updateForm";
	}
	
	
	@PostMapping("/users/{id}/update")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		
		// 인증
		Users principal = (Users)session.getAttribute("principal");
		
		if(principal == null) {
			return "redirect:/loginForm";
		} 
		
		
		Users usersPS = usersDao.findById(id);
		usersPS.회원정보수정(updateDto);
		usersDao.update(usersPS);
		session.setAttribute("principal", usersPS);
		return "redirect:/";
	}
	
	
	@PostMapping("/users/{id}/delete")
	public String delete(@PathVariable Integer id) {
		
		// 인증
		Users principal = (Users)session.getAttribute("principal");
		
		if(principal == null) {
			return "redirect:/loginForm";
		}
		
		// boards테이블에서 usersId 0으로 변경(boards)
		// 게시글 목록에서 username에 익명으로 보이게 하기(MainDto를 매개변수로 받아서 업데이트)
		
		
		
		
		// users테이블에서 회원 데이터 삭제
		usersDao.delete(id);
		
		// session.invalidate();
		session.invalidate();
		
		return "redirect:/";
	}
	
	
}
