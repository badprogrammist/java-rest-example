/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.security.application;

import bp.tasker.security.domain.User;
import bp.tasker.security.infrastructure.UserRepository;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ильдар
 */
@Service(value="userService")
public class UserService {
    
    @Resource(name = "userRepository")
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
        long id = repository.getNextId();
        return new User(id);
    }
    
}
