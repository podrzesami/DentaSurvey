package pl.edu.pwr.dentasurvey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pwr.dentasurvey.objects.User;
import pl.edu.pwr.dentasurvey.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/manage/user/add", method = RequestMethod.GET)
	public ModelAndView addUser(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		model.setViewName("user/userAdd");
	 
		return model;
	}
	@RequestMapping(value = "/manage/user/add", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User u) {
		ModelAndView model = new ModelAndView();

		userService.addUser(u);
		model.setViewName("user/user");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/user/update", method = RequestMethod.GET)
	public ModelAndView updateUser(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		model.addObject("survey", userService.getUser(id));
		model.setViewName("user/userUpdate");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/user/update", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User u) {
		ModelAndView model = new ModelAndView();

		userService.updateUser(u);
		model.setViewName("user/user");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/user/delete", method = RequestMethod.POST)
	public ModelAndView deleteUser(@ModelAttribute Long id) {
		ModelAndView model = new ModelAndView();

		userService.deleteUser(id);
		model.setViewName("user/user");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/user/multipleDelete", method = RequestMethod.POST)
	public ModelAndView deleteMultipleUser(@ModelAttribute Long[] ids) {
		ModelAndView model = new ModelAndView();

		userService.deleteMultipleUsers(ids);
		model.setViewName("user/user");
	 
		return model;
	}
}
