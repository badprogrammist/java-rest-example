/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.infrastructure.persistence.jpa.user;

import bp.tasker.domain.user.Role;
import bp.tasker.domain.user.RoleRepository;
import bp.tasker.infrastructure.persistence.jpa.AbstractRepositoryJPA;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ильдар
 */
@Repository
public class RoleRepositoryJpa extends AbstractRepositoryJPA<Role> implements RoleRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepositoryJpa() {
        super(Role.class);
    }
    
    @Override
    @Transactional
    public Role getByName(String name) {
        List<Role> roles = entityManager.createNamedQuery("Role.findByName",Role.class)
                .setParameter("name", name)
                .getResultList();
        if (roles == null || roles.isEmpty()) {
            return null;
        } else {
            return roles.iterator().next();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
        

}
