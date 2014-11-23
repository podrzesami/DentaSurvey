package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.dentasurvey.dao.LanguageDao;
import pl.edu.pwr.dentasurvey.objects.Language;

@Service("languageService")
public class LanguageService {
	@Autowired
	LanguageDao languageDao;
	
	@Transactional
	public List<Language> getAllLanguages() {
		return languageDao.getAllLanguage();
	}

}
