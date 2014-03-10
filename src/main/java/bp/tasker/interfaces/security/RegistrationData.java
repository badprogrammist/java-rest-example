/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.interfaces.security;

/**
 *
 * @author Ильдар
 */
public class RegistrationData {
    private String fullname;
    private String username;
    private String password;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
