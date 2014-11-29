package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.pwr.dentasurvey.dao.PatientDataDao;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.PatientData;

@Service("patientService")
public class PatientDataService {
	
	@Autowired 
	private PatientDataDao patientDataDao;
	
	@Transactional
	public List<PatientData> getDataForAllPatients() {
		return patientDataDao.getDataForAllPatients();
	}
	
	@Transactional
	public PatientData getPatientData(Long id) {
		return patientDataDao.getPatientData(id);
	}
	
	@Transactional
	public Boolean addPatientData(PatientData p) {
		return patientDataDao.addPatientData(p);
	}
	
	@Transactional
	public Boolean updatePatientData(PatientData p) {
		return patientDataDao.updatePatientData(p);
	}
	
	@Transactional
	public Boolean deletePatientData(Long id) {
		return patientDataDao.deletePatientData(id);
	}
	
	@Transactional
	public Boolean deleteMultiplePatientDatas(Long[] ids) {
		return patientDataDao.deleteMultiplePatientData(ids);
	}
	
	@Transactional
	public Boolean updateOrSavePatientData(PatientData p) {
		return patientDataDao.updateOrSavePatientData(p);
	}

	public SearchResponse getPatientsForJqgrid(SearchRequest req){
		return patientDataDao.getPatientsForJqgrid(req);
	}
}
