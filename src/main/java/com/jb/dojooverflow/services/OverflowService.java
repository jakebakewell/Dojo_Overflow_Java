package com.jb.dojooverflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jb.dojooverflow.models.Answer;
import com.jb.dojooverflow.models.Question;
import com.jb.dojooverflow.models.Tag;
import com.jb.dojooverflow.repositories.AnswerRepository;
import com.jb.dojooverflow.repositories.QuestionRepository;
import com.jb.dojooverflow.repositories.TagRepository;

@Service
public class OverflowService {
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final TagRepository tagRepository;
	public OverflowService(AnswerRepository answerRepository, QuestionRepository questionRepository, TagRepository tagRepository) {
		this.answerRepository = answerRepository;
		this.questionRepository = questionRepository;
		this.tagRepository = tagRepository;
	}
	
	public List<Question> allQuestions() {
		return questionRepository.findAll();
	}
	
	public Question createQuestion(Question q, List<String> tagList) {
		String text = q.getQuestionText();
		questionRepository.save(q);
		Question target = questionRepository.findByQuestionTextContains(text);
		ArrayList<Tag> tagArrayList = new ArrayList<Tag>();
		for (int i = 0; i < tagList.size(); i++) {
			String tag = tagList.get(i);
			if (tagRepository.findSpecificTagBySubject(tag) != null) {
				tagArrayList.add(tagRepository.findSpecificTagBySubject(tag));
			}
			else {
				Tag newTag = new Tag();
				newTag.setSubject(tag);
				tagRepository.save(newTag);
				tagArrayList.add(newTag);
			}
		}
		List<Tag> tags = (List<Tag>) tagArrayList;
		target.setTags(tags);
		return questionRepository.save(target);
	}
	
	public Question findQuestion(Long id) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if (optionalQuestion.isPresent()) {
			return optionalQuestion.get();
		}
		else {
			return null;
		}
	}
	
	public void deleteQuestion(Long id) {
		questionRepository.deleteById(id);
	}
	
	public List<Answer> allAnswers() {
		return answerRepository.findAll();
	}
	
	public Answer createAnswer(Answer a) {
		return answerRepository.save(a);
	}
	
	public Answer findAnswer(Long id) {
		Optional<Answer> optionalAnswer = answerRepository.findById(id);
		if (optionalAnswer.isPresent()) {
			return optionalAnswer.get();
		}
		else {
			return null;
		}
	}
	
	public void deleteAnswer(Long id) {
		answerRepository.deleteById(id);
	}
	
	public List<Tag> allTags() {
		return tagRepository.findAll();
	}
	
	public Tag createTag(Tag t) {
		return tagRepository.save(t);
	}
	
	public Tag findTag(Long id) {
		Optional<Tag> optionalTag = tagRepository.findById(id);
		if (optionalTag.isPresent()) {
			return optionalTag.get();
		}
		else {
			return null;
		}
	}
	
	public void deleteTag(Long id) {
		tagRepository.deleteById(id);
	}
}
