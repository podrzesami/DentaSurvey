package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.dentasurvey.dao.QuestionTypeDao;
import pl.edu.pwr.dentasurvey.objects.QuestionType;

@Service("questionTypeService")
public class QuestionTypeService {
	@Autowired
	QuestionTypeDao questionTypeDao;
	
	@Transactional
	public List<QuestionType> getAllQuestionTypes() {
		return questionTypeDao.getAllQuestionType();
	}

}
