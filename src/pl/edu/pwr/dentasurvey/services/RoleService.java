package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.dentasurvey.dao.RoleDao;
import pl.edu.pwr.dentasurvey.objects.Role;

@Service("roleService")
public class RoleService {
	@Autowired
	RoleDao roleDao;
	
	@Transactional
	public List<Role> getAllRole() {
	    return roleDao.getAllRole();
	}

	@Transactional
	public Role getRole(Long id) {
		return roleDao.getRole(id);
	}
	
	@Transactional
	public Role getRole(String role) {
		return roleDao.getRole(role);
	}
}
