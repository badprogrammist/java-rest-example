/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain.user;

import bp.tasker.domain.Repository;

/**
 *
 * @author Ильдар
 */
public interface RoleRepository extends Repository<Role> {
    public Role getByName(String name);
}
