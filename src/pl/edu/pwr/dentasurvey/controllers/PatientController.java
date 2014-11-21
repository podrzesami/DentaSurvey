package pl.edu.pwr.dentasurvey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pwr.dentasurvey.objects.PatientData;
import pl.edu.pwr.dentasurvey.services.PatientService;

@Controller
public class PatientController {

	@Autowired
	PatientService patientService;	
	
	@RequestMapping(value = "/manage/patient/update", method = RequestMethod.GET)
	public ModelAndView updatePatientData(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		model.addObject("patient", patientService.getPatientData(id));
		model.setViewName("patient/patientUpdate");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/patient/update", method = RequestMethod.POST)
	public ModelAndView updatePatientData(@ModelAttribute PatientData p) {
		ModelAndView model = new ModelAndView();

		patientService.updatePatientData(p);
		model.setViewName("patient/patient");
	 
		return model;
	}
	@RequestMapping(value = "/manage/patient/delete", method = RequestMethod.POST)
	public ModelAndView deletePatient(@ModelAttribute Long id) {
		ModelAndView model = new ModelAndView();

		patientService.deletePatientData(id);
		model.setViewName("patient/patient");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/patient/multipleDelete", method = RequestMethod.POST)
	public ModelAndView deletePatient(@ModelAttribute Long[] ids) {
		ModelAndView model = new ModelAndView();

		patientService.deleteMultiplePatientDatas(ids);
		model.setViewName("patient/patient");
	 
		return model;
	}
}
