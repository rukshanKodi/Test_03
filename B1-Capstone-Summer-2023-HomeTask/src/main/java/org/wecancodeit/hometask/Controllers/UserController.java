package org.wecancodeit.hometask.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.wecancodeit.hometask.Models.LoginDto;
import org.wecancodeit.hometask.Models.User;
import org.wecancodeit.hometask.Services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

/*
 * Main page after starting application
 */

    @GetMapping({"","/","/login"})
    public String loginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

/*
 * Login control and cookie creation
 */

    @PostMapping({"/loginUser"})
    public String LoginUser(@ModelAttribute("loginDto") LoginDto login, HttpServletResponse response){
    User user = userService.getUserByUsername(login.getUsername());
    if(user==null){
        return "redirect:/";
    }
    //confirm Password
    Cookie jwtTokenCookie = new Cookie("user-id", "" + user.getId());
    Cookie nameCookie = new Cookie("username", ""+user.getUsername());
    response.addCookie(jwtTokenCookie);
    response.addCookie(nameCookie);
    return "redirect:/logon";
    }

    @GetMapping("/logon")
    public String homePage(Model model, HttpServletRequest request) throws Exception {
    long userId = userService.getUserId(request);
    if(userId==0){
        throw new Exception("Not logged in");
    }
    User user = userService.getUserById(userId);
    model.addAttribute("username", user.getUsername());
    return "home";
    }

    /*
     * Delete cookie to log out
     */
    @GetMapping({"/logout"})
    public String logout(HttpServletResponse response) {
    Cookie jwtTokenCookie = new Cookie("user-id", "null");
    Cookie nameCookie = new Cookie("username", "null");
    nameCookie.setMaxAge(0);
    jwtTokenCookie.setMaxAge(0);
    response.addCookie(nameCookie);
    response.addCookie(jwtTokenCookie);
    return "login";
    }

    /*
     * Creates new model for user
     */
    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    /*
     * Completes registration with input from form
     */
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
    
}
