package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.objects.Language;

public interface LanguageDao {
	public List<Language> getAllLanguage();
	
	public Language getLanguage(Long id);	
	
	public Language getLanguage(String lang);
}
