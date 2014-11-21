package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.objects.PatientData;

public interface PatientDataDao {
	public List<PatientData> getDataForAllPatients();
	
	public PatientData getPatientData(Long id);	
	
	public Boolean addPatientData(PatientData p);
	
	public Boolean updatePatientData(PatientData p);
	
	public Boolean deletePatientData(Long id);
	
	public Boolean deleteMultiplePatientData(Long[] ids);
}
