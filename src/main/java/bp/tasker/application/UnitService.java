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

    public void saveUnit(Unit unit, User administrator) {
        unit.getAdministrators().add(createUnitAdministrator(unit, administrator));
        unitRepository.save(unit);
    }

    public Unit createUnit(String name, User administrator) {
        Unit unit = new Unit(name);
        unit.getAdministrators().add(createUnitAdministrator(unit, administrator));
        unitRepository.save(unit);
        return unit;
    }

    public UnitAdministrator createUnitAdministrator(Unit unit, User administrator) {
        UnitAdministrator unitAdministrator = new UnitAdministrator(administrator, unit);
        return unitAdministrator;
    }

    public void updateUnit(Unit unit) {
        unitRepository.edit(unit);
    }
    
    public List<Unit> getUnits(User user) {
        return unitRepository.getUnits(user);
    }

    public Unit get(long id) {
        return unitRepository.get(id);
    }
    
    public void remove(long id) {
        unitRepository.remove(get(id));
    }
    
}
