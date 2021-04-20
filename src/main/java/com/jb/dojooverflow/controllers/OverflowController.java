package com.jb.dojooverflow.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jb.dojooverflow.models.Answer;
import com.jb.dojooverflow.models.Question;
import com.jb.dojooverflow.services.OverflowService;

@Controller
public class OverflowController {
	private final OverflowService overflowService;
	public OverflowController(OverflowService overflowService) {
		this.overflowService = overflowService;
	}
	
	@RequestMapping("/")
	public String dashboard(Model model) {
		List<Question> questions = overflowService.allQuestions();
		model.addAttribute("questions", questions);
		return "dashboard.jsp";
	}
	
	@RequestMapping("/questions/new")
	public String newQuestion(Model model) {
		model.addAttribute("quest", new Question());
		return "newQuestion.jsp";
	}
	
	@RequestMapping(value="/questions/new", method=RequestMethod.POST)
    public String createQuestion(@Valid @ModelAttribute("quest") Question question, BindingResult result, @RequestParam("tagList") String tags, RedirectAttributes redirect) {
		List<String> tagList = Arrays.asList((tags).split(","));
		if (tagList.size() > 3) {
			String sizeError = "Question can only have 3 tags";
			redirect.addFlashAttribute("sizeError", sizeError);
			return "redirect:/questions/new";
		}
		else if (tags.length() == 0) {
			String noTag = "Question must have at least 1 tag";
			redirect.addFlashAttribute("noTag", noTag);
			return "redirect:/questions/new";
		}
		else if (result.hasErrors()) {
            return "newQuestion.jsp";
        }
        else {
            overflowService.createQuestion(question, tagList);
            return "redirect:/";
        }
    }
	
	@RequestMapping("/questions/{id}")
	public String showQuestion(Model model, @PathVariable("id") Long id) {
		Question question = overflowService.findQuestion(id);
		model.addAttribute("question", question);
		model.addAttribute("answer", new Answer());
		return "question.jsp";
	}
	
	@RequestMapping(value="/answers/new", method=RequestMethod.POST)
	public String createAnswer(@Valid @ModelAttribute("answer") Answer answer, BindingResult result, @RequestParam("question") Long id) {
		String redirect = String.format("redirect:/questions/%d", id);
		if (result.hasErrors()) {
            return "question.jsp";
        }
        else {
            overflowService.createAnswer(answer);
            return redirect;
        }
	}
}
