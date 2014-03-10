/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.security;

import bp.tasker.application.UserService;
import bp.tasker.domain.user.Role;
import bp.tasker.domain.user.User;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ильдар
 */
@Service
public class SecurityService {

    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(String fullname, String username, String password) {
        UserDetails userDetails = this.userService.getUserDetailsByUsername(username);
        if (userDetails == null) {
            String encodedPassword = passwordEncoder.encode(password);
            Role userRole = userService.createRole("user");
            if (userRole != null) {
                User user = userService.createUser(fullname, username, encodedPassword, userRole);
                if (user != null) {
                    return true;
                }
            }
        }
        return false;

    }

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
