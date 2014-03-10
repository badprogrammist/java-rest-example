/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain.user;

import bp.tasker.domain.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "roles", schema = "user")
public class Role extends AbstractEntity {
    
    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(long id,String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
