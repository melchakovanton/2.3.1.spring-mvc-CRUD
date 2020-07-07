package web.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import web.models.User;
import web.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public String getUsers(ModelMap model) throws SQLException {
        List<User> list = userService.getAllUsers();
        model.addAttribute("list", list);
        return "users";
    }

    @PostMapping("users/add")
    public String addUser(User user) throws SQLException {
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("users/delete")
    public String deleteUser(User user) throws SQLException {
        userService.deleteUser(user.getId());
        return "redirect:/users";
    }

    @GetMapping("users/update")
    public String updateUserForm(ModelMap model, User user) throws SQLException {
        model.addAttribute("user", userService.getUserById(user.getId()));
        return "updateUser";
    }

    @PostMapping("users/update")
    public String updateUser(User user) throws SQLException {
        userService.updateUser(user.getLogin(), user.getPassword(), user.getEmail(), user.getId());
        return "redirect:/users";
    }





}
