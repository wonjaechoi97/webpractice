package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import survey.AnsweredData;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	@GetMapping
	public String form() {
		return "survey/surveyForm";
	}
	
	@PostMapping
	public String submit( @ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
}
