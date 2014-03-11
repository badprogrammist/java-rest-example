/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.infrastructure.persistence.jpa.unit;

import bp.tasker.domain.unit.Unit;
import bp.tasker.domain.unit.UnitAdministrator;
import bp.tasker.domain.unit.UnitRepository;
import bp.tasker.domain.user.User;
import bp.tasker.infrastructure.persistence.jpa.AbstractRepositoryJPA;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ильдар
 */
@Repository
public class UnitRepositoryJPA extends AbstractRepositoryJPA<Unit> implements UnitRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    public UnitRepositoryJPA() {
        super(Unit.class);
    }

    @Override
    @Transactional
    public List<Unit> getUnits(User user) {
        List<UnitAdministrator> administrators = entityManager
                .createNamedQuery("UnitAdministrator.findAllByUser",UnitAdministrator.class)
                .setParameter("user", user)
                .getResultList();
        List<Unit> units = new ArrayList<>();
        for (UnitAdministrator admin : administrators) {
            units.add(admin.getUnit());
        }
        return units;
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    
    
}
