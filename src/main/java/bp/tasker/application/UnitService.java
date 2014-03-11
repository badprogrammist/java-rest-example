/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.application;

import bp.tasker.domain.unit.Unit;
import bp.tasker.domain.unit.UnitAdministrator;
import bp.tasker.domain.unit.UnitRepository;
import bp.tasker.domain.user.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ильдар
 */
@Service
public class UnitService {
    
    @Autowired
    private UnitRepository unitRepository;
    
    public Unit createUnit(String name, User administrator) {
        Unit unit = new Unit(name);
        UnitAdministrator unitAdministrator = new UnitAdministrator(administrator, unit);
        unit.getAdministrators().add(unitAdministrator);
        unitRepository.save(unit);
        return unit;
    }
    
    public List<Unit> getUnits(User user) {
        return unitRepository.getUnits(user);
    }
     
}
