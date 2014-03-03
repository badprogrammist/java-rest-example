/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.security.application;

import bp.tasker.security.domain.UserTransfer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ильдар
 */
@Service
public class AuthenticationService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;
    
    public UserTransfer authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, Boolean> roles = new HashMap<>();
        /*
         * Reload user as password of authentication principal will be null after authorization and
         * password is needed for token generation
         */
        UserDetails userDetails = this.userService.getUserDetailsByUsername(username);
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.toString(), Boolean.TRUE);
        }
        return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
    }
}
