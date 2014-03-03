/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.security.domain;

/**
 *
 * @author Ильдар
 */
public class Role extends AbstractEntity {
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
