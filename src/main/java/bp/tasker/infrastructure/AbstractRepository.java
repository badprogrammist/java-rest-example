/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.infrastructure;

import bp.tasker.domain.AbstractEntity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ильдар
 */
public abstract class AbstractRepository<E extends AbstractEntity> {
    private Map<Long,E> entities = new HashMap<>();
    
    public abstract long getNextId();
    
    public void save(E entity) {
        if(entity.getId() == 0) {
            entity.setId(getNextId());
        }
        entities.put(entity.getId(),entity);
    }
    
    public Collection<E> getAll() {
        return entities.values();
    }
    
    public E get(long id) {
        return entities.get(id);
    }
    
    public void remove(E entity) {
        entities.remove(entity.getId());
    }
    
}
