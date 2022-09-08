package site.metacoding.red.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@Controller
@RequiredArgsConstructor
public class UsersController {
	
	private final UsersDao usersDao;
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
		Users principal = (Users)session.getAttribute("principal");
		
		if(principal == null) {
			return "redirect:/loginForm";
		} 
		
		model.addAttribute("users", usersPS);
		
		return "users/updateForm";
	}
	
	
	@PostMapping("/users/{id}/update")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
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
	
	
	
	
}
