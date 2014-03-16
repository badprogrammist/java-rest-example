/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.interfaces.unit;

import bp.tasker.application.UnitService;
import bp.tasker.domain.unit.Unit;
import bp.tasker.security.SecurityContext;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/unit")
public class UnitController {
    
    @Autowired
    private UnitService unitService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Unit> getUnits() {
        return unitService.getUnits(SecurityContext.getPrincipal());
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addUnit(@RequestBody Unit unit) {
        unitService.saveUnit(unit, SecurityContext.getPrincipal());
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Unit getUnit(@PathVariable("id") String id) {
        return unitService.get(Long.valueOf(id));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteUnit(@PathVariable("id") String id) {
        unitService.remove(Long.valueOf(id));
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateUnit(@RequestBody Unit unit) {
        unitService.updateUnit(unit);
    }
}
