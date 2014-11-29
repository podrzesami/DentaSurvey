package pl.edu.pwr.dentasurvey.controllers;

import java.util.List;

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
import pl.edu.pwr.dentasurvey.objects.Role;
import pl.edu.pwr.dentasurvey.objects.User;
import pl.edu.pwr.dentasurvey.services.RoleService;
import pl.edu.pwr.dentasurvey.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/userConfigurafion/all", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody SearchResponse getAllSurveys(
			@ModelAttribute SearchRequest req
			) {		
		SearchResponse resp = userService.getUsersForJqgrid(req);
		return resp;
	}	
	
	@RequestMapping(value = "/userConfigurafion/add", method = RequestMethod.GET)
	public ModelAndView addUser() {
		ModelAndView model = new ModelAndView();
		List<Role> roles = roleService.getAllRole();

		model.addObject("roles", roles);
		model.addObject("user", new User());
		model.setViewName("user/userAdd");
	 
		return model;
	}
	@RequestMapping(value = "/userConfigurafion/add", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user) {
		ModelAndView model = new ModelAndView();

		userService.addUser(user);
		model.setViewName("user/user");
	 
		return model;
	}
	
	@RequestMapping(value = "/userConfigurafion/update", method = RequestMethod.GET)
	public ModelAndView updateUser(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();
		List<Role> roles = roleService.getAllRole();

		model.addObject("roles", roles);
		model.addObject("user", userService.getUser(id));
		model.setViewName("user/userUpdate");
	 
		return model;
	}	
	
	@RequestMapping(value = "/userConfigurafion/update", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user) {
		ModelAndView model = new ModelAndView();

		userService.updateUser(user);
		model.setViewName("user/user");
	 
		return model;
	}	
	
	@RequestMapping(value = "/userConfigurafion/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		userService.deleteUser(id);
		model.setViewName("user/user");
	 
		return model;
	}
	
	@RequestMapping(value = "/userConfigurafion/multipleDelete", method = RequestMethod.GET)
	public ModelAndView deleteMultipleUser(@RequestParam(value="id", required=true) Long[] ids) {
		ModelAndView model = new ModelAndView();

		userService.deleteMultipleUsers(ids);
		model.setViewName("user/user");
	 
		return model;
	}
}
