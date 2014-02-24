/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.resttest.infrastructure;

import bp.resttest.domain.User;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ильдар
 */
@Repository(value="userRepository")
public class UserRepository {
    private Map<UUID,User> users = new HashMap<>();

    public UserRepository() {
        saveUser(new User(UUID.randomUUID(),"Ildar",new Date()));
        saveUser(new User(UUID.randomUUID(),"Renata",new Date()));
    }
    
    public void saveUser(User user) {
        if(user.getId() != null) {
            users.put(user.getId(),user);
        }
    }
    
    public Collection<User> getAll() {
        return users.values();
    }
    
    public User get(UUID id) {
        return users.get(id);
    }
    
}
