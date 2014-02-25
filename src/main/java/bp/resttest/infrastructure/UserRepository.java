/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.resttest.infrastructure;

import bp.resttest.domain.User;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ильдар
 */
@Repository(value="userRepository")
public class UserRepository {
    private Map<Long,User> users = new HashMap<>();
    
    private static long userNumber = 1;

    public UserRepository() {
        saveUser(new User(getNextId(),"Ildar",new Date()));
        saveUser(new User(getNextId(),"Renata",new Date()));
    }
    
    public final long getNextId() {
        return userNumber++;
    }
    
    public void saveUser(User user) {
        if(user.getId() != 0) {
            users.put(user.getId(),user);
        }
    }
    
    public Collection<User> getAll() {
        return users.values();
    }
    
    public User get(long id) {
        return users.get(id);
    }
    
}
