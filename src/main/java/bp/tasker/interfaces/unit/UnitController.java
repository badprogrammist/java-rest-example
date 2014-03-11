/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.interfaces.unit;

import bp.tasker.application.UnitService;
import bp.tasker.domain.unit.Unit;
import bp.tasker.domain.user.User;
import bp.tasker.security.SecurityContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
    public List<Unit> getUnits() {
        return unitService.getUnits(SecurityContext.getPrincipal());
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addUnit(@RequestBody String name) {
        System.out.println("name "+name);
        unitService.createUnit(name, SecurityContext.getPrincipal());
    }
}
