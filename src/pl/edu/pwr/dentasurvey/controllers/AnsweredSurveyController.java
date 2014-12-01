package pl.edu.pwr.dentasurvey.controllers;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
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
import pl.edu.pwr.dentasurvey.objects.Answer;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;
import pl.edu.pwr.dentasurvey.objects.PatientData;
import pl.edu.pwr.dentasurvey.services.AnswerService;
import pl.edu.pwr.dentasurvey.services.AnsweredSurveyService;
import pl.edu.pwr.dentasurvey.services.PatientDataService;

@Controller
public class AnsweredSurveyController {

	@Autowired
	PatientDataService patientDataService;
	
	@Autowired
	AnsweredSurveyService answeredSurveyService;

	@Autowired
	AnswerService answerService;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/manage/answeredSurvey/answeredSurveys", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody SearchResponse getAllSurveys(
			@RequestParam(value="id", required=true) Long id,
			@ModelAttribute SearchRequest req
			) {		
		SearchResponse resp = answeredSurveyService.getAnsweredSurveysForJqgrid(id,req);
		return resp;
	}	
	
	@RequestMapping(value = "/manage/answeredSurvey/get", method = RequestMethod.GET)
	public ModelAndView getSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		AnsweredSurvey answeredSurvey = answeredSurveyService.getAnsweredSurvey(id);
		model.addObject("answeredSurvey", answeredSurvey);
		model.setViewName("answeredSurvey/answeredSurveyGet");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/answeredSurvey/delete", method = RequestMethod.GET)
	public ModelAndView deleteSurvey(
			@RequestParam(value="patientId", required=true) Long patientId,
			@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		answeredSurveyService.deleteAnsweredSurvey(id);
		model.addObject("patient", patientDataService.getPatientData(patientId));
		model.setViewName("patient/patientGet");
	 
		return model;
	}

	@RequestMapping(value = "/manage/answeredSurvey/export", method = RequestMethod.GET)
    public String export(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value="patientId", required=true) Long patientId,
			@RequestParam(value="id", required=true) Long id) {
		
		String context = servletContext.getRealPath("resources");

        try {
            // set output header
            ServletOutputStream os = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"report.xlsx\"");
             
            List<Answer> answers = answerService.getAnswersForSurvey(id);
            PatientData p = patientDataService.getPatientData(patientId);
            List<PatientData> patient = new ArrayList<PatientData>();
            Map<String, List<?>> beans = new HashMap<String, List<?>>();
            patient.add(p);
            List<AnsweredSurvey> ans = new ArrayList<AnsweredSurvey>();
            ans.add(answeredSurveyService.getAnsweredSurvey(id));
            beans.put("answers", answers);
            beans.put("patient", patient);
            beans.put("answeredSurvey", ans);   
            XLSTransformer transformer = new XLSTransformer();
 
            Workbook workbook = transformer.transformXLS(new FileInputStream(context + "/excel/report.xlsx"), beans);
            workbook.write(os);
            os.flush();
 
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
	@RequestMapping(value = "/manage/answeredSurvey/multipleDelete", method = RequestMethod.GET)
	public ModelAndView deleteMultipleSurvey(
			@RequestParam(value="patientId", required=true) Long patientId,
			@RequestParam(value="id", required=true) Long ids[]) {
		ModelAndView model = new ModelAndView();

		answeredSurveyService.deleteMultipleAnsweredSurveys(ids);
		model.addObject("patient", patientDataService.getPatientData(patientId));
		model.setViewName("patient/patientGet");
	 
		return model;
	}
}
