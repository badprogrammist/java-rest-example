/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.resttest.domain;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Ильдар
 */
public class User {
    private long id;
    private String name;
    private Date birthday;

    public User(long id) {
        this.id = id;
        this.birthday = new Date();
    }
    
    public User(long id,String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public long getId() {
        return id;
    }
    
    
    
    
}
