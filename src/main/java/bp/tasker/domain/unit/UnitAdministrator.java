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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;

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
            query = "Select c from UnitAdministrator c where c.administrator = :administrator")
})
public class UnitAdministrator extends AbstractEntity {
    
    @JoinColumn(name = "administrator")
    @OneToOne
    private User administrator;

    @ManyToOne
    @JoinColumn(name = "unit")
    @JsonBackReference
    private Unit unit;

    public UnitAdministrator(User user, Unit unit) {
        this.administrator = user;
        this.unit = unit;
    }

    public UnitAdministrator() {
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    
}
