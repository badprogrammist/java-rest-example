/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain;

import java.util.List;

/**
 *
 * @author Ильдар
 */
public interface Repository<E extends AbstractEntity> {
    
    public void save(E entity);
    public List<E> getAll();
    public E get(Long id);
    public void remove(E entity);
}
