package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.objects.Role;

public interface RoleDao {
	public List<Role> getAllRole();
	
	public Role getRole(Long id);	
	
	public Role getRole(String role);
}
