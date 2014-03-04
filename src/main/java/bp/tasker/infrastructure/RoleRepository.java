/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.infrastructure;

import bp.tasker.domain.user.Role;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ильдар
 */
@Repository(value="roleRepository")
public class RoleRepository extends AbstractRepository<Role> {
    private Map<Long,Role> roles = new HashMap<>();
    
    private static long roleNumber = 1;

    public RoleRepository() {
        save(new Role(getNextId(),"superadmin"));
        save(new Role(getNextId(),"admin"));
        save(new Role(getNextId(),"user"));
    }
    
    @Override
    public final long getNextId() {
        return roleNumber++;
    }
    
    public Role getByName(String name) {
        for(Role role : getAll()) {
            if(role.getName().equals(name)) {
                return role;
            }
        }
        return null;
    }
        

}
