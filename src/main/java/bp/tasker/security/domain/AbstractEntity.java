/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.security.domain;

/**
 *
 * @author Ильдар
 */
public class AbstractEntity {
    private long id;

    public AbstractEntity() {

    }
    
    public AbstractEntity(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
