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
    private int age;
    private Date birthday;

    public User() {
        
    }
    
    public User(long id) {
        this.id = id;
        this.birthday = new Date();
    }
    
    public User(long id,String name, int age, Date birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    
    
    
}
