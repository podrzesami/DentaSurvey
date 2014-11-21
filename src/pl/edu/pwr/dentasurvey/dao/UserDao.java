package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.objects.User;

public interface UserDao {
	public List<User> getAllUsers();
	
	public User getUser(Long id);	
	
	public Boolean addUser(User u);
	
	public Boolean updateUser(User u);
	
	public Boolean deleteUser(Long id);
	
	public Boolean deleteMultipleUsers(Long[] ids);
}
