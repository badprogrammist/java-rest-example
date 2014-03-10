/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.application;

import bp.tasker.domain.user.User;
import bp.tasker.domain.user.UserRepository;
import java.util.Collection;
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
    private UserRepository repository;
    
    public Collection<User> getUsers() {
        return repository.getAll();
    }
    
    public void addUser(User user) {
        repository.save(user);
    }
    
    public User get(long id) {
        return repository.get(id);
    }
    
    public UserDetails getUserDetailsByUsername(String username) {
        return repository.loadUserByUsername(username);
    }
    
    public void updateUser(User user) {
        remove(user);
        addUser(user);
    }
    
    public void remove(long id) {
        User user = get(id);
        if (user != null) {
            remove(user);
        }
    }
    
    public void remove(User user) {
        repository.remove(user);
    }
    
    public User createEmpty() {
        return new User();
    }
    
}
