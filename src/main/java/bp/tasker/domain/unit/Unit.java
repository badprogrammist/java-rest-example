/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain.unit;

import bp.tasker.domain.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "units")
@NamedQueries({
    @NamedQuery(name = "Unit.findAll",
            query = "Select c from Unit c")
})
public class Unit extends AbstractEntity {
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "unit",cascade = CascadeType.ALL)
    private List<UnitAdministrator> administrators = new ArrayList<>();

    public Unit(String name) {
        this.name = name;
    }

    public Unit() {
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<UnitAdministrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<UnitAdministrator> administrators) {
        this.administrators = administrators;
    }
    
}
