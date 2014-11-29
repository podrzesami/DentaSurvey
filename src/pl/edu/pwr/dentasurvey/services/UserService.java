package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.pwr.dentasurvey.dao.UserDao;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.User;

@Service("userService")
public class UserService {
	
	@Autowired 
	private UserDao userDao;
	
	@Transactional
	public SearchResponse getUsersForJqgrid(SearchRequest req) {
		return userDao.getUsersForJqgrid(req);
	}
	
	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	@Transactional
	public User getUser(Long id) {
		return userDao.getUser(id);
	}
	
	@Transactional
	public Boolean addUser(User u) {
		return userDao.addUser(u);
	}
	
	@Transactional
	public Boolean updateUser(User u) {
		return userDao.updateUser(u);
	}
	
	@Transactional
	public Boolean deleteUser(Long id) {
		return userDao.deleteUser(id);
	}
	
	@Transactional
	public Boolean deleteMultipleUsers(Long[] ids) {
		return userDao.deleteMultipleUsers(ids);
	}	
}
