/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.infrastructure.persistence.jpa.user;

import bp.tasker.domain.user.Role;
import bp.tasker.domain.user.RoleRepository;
import bp.tasker.infrastructure.persistence.jpa.AbstractRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ильдар
 */
@Repository
public class RoleRepositoryJpa extends AbstractRepository<Role> implements RoleRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepositoryJpa() {
        super(Role.class);
    }
    
    @Override
    public Role getByName(String name) {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);

        Root<Role> root = criteriaQuery.from(Role.class);
        Path<String> namePath = root.get("name");
        criteriaQuery.where(builder.equal(namePath, name));

        TypedQuery<Role> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
        

}
