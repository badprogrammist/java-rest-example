/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.security.infrastructure;

import bp.tasker.security.domain.User;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ильдар
 */
@Repository(value="userRepository")
public class UserRepository extends AbstractRepository<User> implements UserDetailsService {
    private Map<Long,User> users = new HashMap<>();
    
    private static long userNumber = 1;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;

    public UserRepository() {
        
    }
    
    @PostConstruct
    private void init() {
        User user = new User(getNextId(),"user",passwordEncoder.encode("user"));
        user.getRoles().add(roleRepository.getByName("user"));
        save(user);
        
        User admin = new User(getNextId(),"admin",passwordEncoder.encode("admin"));
        admin.getRoles().add(roleRepository.getByName("admin"));
        save(admin);

    }
    
    @Override
    public final long getNextId() {
        return userNumber++;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (User user : getAll()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
}
