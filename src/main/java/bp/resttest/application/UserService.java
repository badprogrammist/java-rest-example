/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.resttest.application;

import bp.resttest.domain.User;
import bp.resttest.infrastructure.UserRepository;
import java.util.Collection;
import javax.annotation.Resource;
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
        repository.saveUser(user);
    }
    
    public User get(long id) {
        return repository.get(id);
    }
    
    public User createEmpty() {
        long id = repository.getNextId();
        return new User(id);
    }
    
}
