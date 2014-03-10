/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.application;

import bp.tasker.domain.user.Role;
import bp.tasker.domain.user.RoleRepository;
import bp.tasker.domain.user.User;
import bp.tasker.domain.user.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ильдар
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    public List<User> getUsers() {
        return userRepository.getAll();
    }
    
    public User createUser(String fullname, String username, String encodedPassword, Role role) {
        User user = new User(fullname, username, encodedPassword);
        user.getRoles().add(role);
        userRepository.save(user);
        return user;
    }
    
    public User get(long id) {
        return userRepository.get(id);
    }
    
    public Role getRoleByName(String name) {
        return roleRepository.getByName(name);
    }
    
    public Role createRole(String name) {
        Role role = roleRepository.getByName(name);
        if(role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
        return role;
    }
    
    public UserDetails getUserDetailsByUsername(String username) {
        return userRepository.loadUserByUsername(username);
    }
    
    public void updateUser(User user) {
        userRepository.edit(user);
    }
    
    public void remove(long id) {
        userRepository.remove(get(id));
    }
    
    public void remove(User user) {
        userRepository.remove(user);
    }
    
}
