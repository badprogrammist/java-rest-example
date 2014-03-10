/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain.user;

import bp.tasker.domain.Repository;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Ильдар
 */
public interface UserRepository extends Repository<User>,UserDetailsService {
    
}
