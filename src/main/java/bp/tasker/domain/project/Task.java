/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain.project;

import bp.tasker.domain.AbstractEntity;
import bp.tasker.domain.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "tasks")
@NamedQueries({
    @NamedQuery(name = "Task.findAll",
            query = "Select c from Task c")})
public class Task extends AbstractEntity {
    
    @Lob
    @Column(name = "description")
    private String description;
    
    @Column(name = "fulfilled")
    private Boolean fulfilled = false;
    
    @Column(name = "owner")
    @ManyToOne
    private User owner;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    
    
}
