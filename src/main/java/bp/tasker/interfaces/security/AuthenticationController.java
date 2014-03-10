/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.interfaces.security;

import bp.tasker.security.AuthenticationService;
import bp.tasker.security.UserTransfer;
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
@RequestMapping("/security")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserTransfer authenticate(@RequestBody Credentials credentials) {
        return authService.authenticate(credentials.getUsername(), credentials.getPassword());
    }
}
