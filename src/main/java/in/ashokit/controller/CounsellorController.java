package in.ashokit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.entity.CounsellorEntity;
import in.ashokit.service.ICounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	private ICounsellorService counSer;
	public CounsellorController(ICounsellorService counSer) {
		this.counSer = counSer;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("counsellor", new CounsellorEntity());
		return "login";
	}
	
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute("counsellor") CounsellorEntity c, HttpServletRequest req , Model model) {
		CounsellorEntity loginCheck = counSer.loginCheck(c.getCounsellorEmail(), c.getCounsellorPassword());
		if(loginCheck == null) {
			model.addAttribute("msg", "Invaild credential");
			return "login";
		}
		HttpSession session = req.getSession(true);
		session.setAttribute("counsellorId", loginCheck.getCounsellorId());
		return "redirect:dashboard";
	}
	
	@GetMapping("/register")
	public String regPage(Model model) {
		model.addAttribute("counsellor", new CounsellorEntity());
		return "registerView";
	}
	
	@PostMapping("/register")
	public String handleRegistration(@ModelAttribute("counsellor") CounsellorEntity c, Model model) {
		boolean saveCounsellor = counSer.saveCounsellor(c);
		if(saveCounsellor) {
			model.addAttribute("sucmsg", "Registration successfully");
			return "registerView.html";
		}
		model.addAttribute("errmsg", "Duplicate email");
		return "registerView.html";
	}
	
	@GetMapping("/forget-password")
	public String recoverPage(Model model) {
		return "forget-password";
	}
	
	@GetMapping("/forget-passwords")
	public String handleForgetPassword(@RequestParam("email") String email, Model model) {
		boolean recoverPassword = counSer.recoverPassword(email);
		if(recoverPassword) {
			model.addAttribute("sucmsg", "Password sent to your email");
		}else {
			model.addAttribute("errmsg", "Invaild email");
		}
		return "forget-password";
	}
	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			return "redirect:/";
		}
		Integer counId = (Integer)session.getAttribute("counsellorId");
		DashboardResponse dashboardInfo = counSer.getDashboardInfo(counId);
		model.addAttribute("dashboard", dashboardInfo);
		return "dashboard";
	}
}
