/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.domain.unit;

import bp.tasker.domain.Repository;
import bp.tasker.domain.user.User;
import java.util.List;

/**
 *
 * @author Ильдар
 */
public interface UnitRepository extends Repository<Unit> {
    public List<Unit> getUnits(User user);
}
