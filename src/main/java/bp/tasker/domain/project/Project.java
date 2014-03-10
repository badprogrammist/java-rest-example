/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain.project;

import bp.tasker.domain.AbstractEntity;
import bp.tasker.domain.user.User;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "projects")
@NamedQueries({
    @NamedQuery(name = "Project.findAll",
            query = "Select c from Project c")})
public class Project extends AbstractEntity {
    @Lob
    @Column(name = "description")
    private String description;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany
    private List<User> participants = Collections.emptyList();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
    
    
    
}
