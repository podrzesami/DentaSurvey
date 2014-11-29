package pl.edu.pwr.dentasurvey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.PatientData;
import pl.edu.pwr.dentasurvey.services.PatientDataService;

@Controller
public class PatientController {

	@Autowired
	PatientDataService patientService;	

	@RequestMapping(value = "/manage/patient/all", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody SearchResponse getAllSurveys(
			@ModelAttribute SearchRequest req
			) {		
		SearchResponse resp = patientService.getPatientsForJqgrid(req);
		return resp;
	}	
	
	@RequestMapping(value = "/manage/patient/get", method = RequestMethod.GET)
	public ModelAndView getPatientData(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		model.addObject("patient", patientService.getPatientData(id));
		model.setViewName("patient/patientGet");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/patient/update", method = RequestMethod.GET)
	public ModelAndView updatePatientData(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		model.addObject("patient", patientService.getPatientData(id));
		model.setViewName("patient/patientUpdate");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/patient/update", method = RequestMethod.POST)
	public ModelAndView updatePatientData(@ModelAttribute PatientData patient) {
		ModelAndView model = new ModelAndView();

		patientService.updatePatientData(patient);
		model.setViewName("patient/patient");
	 
		return model;
	}
	@RequestMapping(value = "/manage/patient/delete", method = RequestMethod.GET)
	public ModelAndView deletePatient(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		patientService.deletePatientData(id);
		model.setViewName("patient/patient");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/patient/multipleDelete", method = RequestMethod.GET)
	public ModelAndView deletePatient(@RequestParam(value="id", required=true) Long[] ids) {
		ModelAndView model = new ModelAndView();

		patientService.deleteMultiplePatientDatas(ids);
		model.setViewName("patient/patient");
	 
		return model;
	}
}
