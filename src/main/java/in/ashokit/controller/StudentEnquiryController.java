package in.ashokit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.binding.SearchCriteria;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.service.IStudentEnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentEnquiryController {
	private IStudentEnquiryService stuEnq;
	public StudentEnquiryController(IStudentEnquiryService stuEnq) {
		this.stuEnq = stuEnq;
	}
	
	@GetMapping("/add-enquiry")
	public String enqPage(HttpServletRequest req ,Model model) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			return "redirect:/";
		}
		model.addAttribute("studentEnq", new StudentEnqEntity());
		return "addEnquiry";
	}
	
	@PostMapping("/add-enquiry")
	public String addEnquiry(@ModelAttribute("studentEnq") StudentEnqEntity s,HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer counId = (Integer)session.getAttribute("counsellorId");
		s.setCounsellorId(counId);
		boolean enq = stuEnq.addEnq(s);
		if(enq) {
			model.addAttribute("sucMsg", "Record Inserted successfully");
		}else {
			model.addAttribute("errMsg", "Faild to inserte the record");
		}
		return "addEnquiry";
	}
	
	@GetMapping("/view-enquiry")
	public String viewEnquiry(HttpServletRequest req ,Model model) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			return "redirect:/";
		}
		Integer counserllorId = (Integer)session.getAttribute("counsellorId");
		List<StudentEnqEntity> enquiries = stuEnq.getEnquiries(counserllorId, new SearchCriteria());
		model.addAttribute("enquiries", enquiries);
		return "viewEnquiry";
	}
	
	@GetMapping("/view-enquirys")
	@ResponseBody
	public List<StudentEnqEntity> filterEnquiries(HttpServletRequest req,@ModelAttribute SearchCriteria sc) {
		HttpSession session = req.getSession(false);
		Integer counserllorId = (Integer)session.getAttribute("counsellorId");
		List<StudentEnqEntity> enquiries = stuEnq.getEnquiries(counserllorId, sc);
		return enquiries;
	}
}