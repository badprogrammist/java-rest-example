/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain.unit;

import bp.tasker.domain.AbstractEntity;
import bp.tasker.domain.user.User;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "units_administrators")
@NamedQueries({
    @NamedQuery(name = "UnitAdministrator.findAll",
            query = "Select c from UnitAdministrator c"),
    @NamedQuery(name = "UnitAdministrator.findAllByUser",
            query = "Select c from UnitAdministrator c where c.user = :user")
})
public class UnitAdministrator extends AbstractEntity {
    
    @JoinColumn(name = "user")
    @ManyToOne
    private User user;
    
    @JoinColumn(name = "unit")
    @ManyToOne
    private Unit unit;

    public UnitAdministrator(User user, Unit unit) {
        this.user = user;
        this.unit = unit;
    }

    public UnitAdministrator() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    
}
