/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.infrastructure.persistence.jpa.user;

import bp.tasker.domain.user.User;
import bp.tasker.domain.user.UserRepository;
import bp.tasker.infrastructure.persistence.jpa.AbstractRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ильдар
 */
@Repository(value = "userRepository")
public class UserRepositoryJpa extends AbstractRepository<User> implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryJpa() {
        super(User.class);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user;
        user = entityManager.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", username)
                .getResultList();
        if (user != null && user.size() == 1) {
            return user.iterator().next();
        } else {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
