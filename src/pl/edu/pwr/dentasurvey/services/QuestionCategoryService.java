package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.dentasurvey.dao.QuestionCategoryDao;
import pl.edu.pwr.dentasurvey.objects.QuestionCategory;

@Service("questionCategoryService")
public class QuestionCategoryService {
	@Autowired
	QuestionCategoryDao questionCategoryDao;
	
	@Transactional
	public List<QuestionCategory> getAllQuestionCategorys() {
		return questionCategoryDao.getAllQuestionCategory();
	}

}
