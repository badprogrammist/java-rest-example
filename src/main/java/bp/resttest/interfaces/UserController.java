/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.resttest.interfaces;

import bp.resttest.application.UserService;
import bp.resttest.domain.User;
import java.io.Serializable;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;
    private User currentUser;

    @RequestMapping(value = "/")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users.jsp";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser() {
        return "add.jsp";
    }
    //для связывания с формой

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        currentUser = new User(UUID.randomUUID());
        return currentUser;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("currentUser") User user) {
        userService.addUser(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPayment(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("payment", userService.get(id));
        return "/view.jsp";
    }
}
