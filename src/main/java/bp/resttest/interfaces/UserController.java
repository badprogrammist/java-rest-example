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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        currentUser = userService.createEmpty();
        return currentUser;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("currentUser") User user) {
        userService.addUser(user);
        return "redirect:/user";
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public String getUser(@PathVariable("id") String id, Model model) {
//        model.addAttribute("user", userService.get(Long.valueOf(id)));
//        return "/view.jsp";
//    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getUser(@PathVariable("id") String id) {
        return userService.get(Long.valueOf(id));
    }
    
}
