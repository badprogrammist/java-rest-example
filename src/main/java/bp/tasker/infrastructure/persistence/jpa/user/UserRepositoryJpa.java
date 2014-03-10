/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.infrastructure.persistence.jpa.user;

import bp.tasker.domain.user.User;
import bp.tasker.domain.user.UserRepository;
import bp.tasker.infrastructure.persistence.jpa.AbstractRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        Path<String> namePath = root.get("username");
        criteriaQuery.where(builder.equal(namePath, username));

        TypedQuery<User> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        User user = typedQuery.getSingleResult();
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
