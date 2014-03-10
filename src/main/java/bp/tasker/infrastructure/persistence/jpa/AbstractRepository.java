/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.infrastructure.persistence.jpa;

import bp.tasker.domain.AbstractEntity;
import bp.tasker.domain.Repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Ильдар
 */
public abstract class AbstractRepository<E extends AbstractEntity> implements Repository<E> {

    protected abstract EntityManager getEntityManager();
    private Class<E> entityClass;

    public AbstractRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(E entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public List<E> getAll() {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<E> criteriaQuery = builder.createQuery(this.entityClass);
        criteriaQuery.from(this.entityClass);
        TypedQuery<E> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public E get(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public void remove(E entity) {
        getEntityManager().remove(entity);
    }
}
