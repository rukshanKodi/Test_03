package org.wecancodeit.hometask.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.hometask.Models.User;
import org.wecancodeit.hometask.Services.HouseholdService;
import org.wecancodeit.hometask.Services.RewardService;
import org.wecancodeit.hometask.Services.UserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {

@Resource
private final UserService userService;

@Resource 
private final HouseholdService householdService;

@Resource
private final RewardService rewardService;

public HomePageController(UserService userService, HouseholdService householdService, RewardService rewardService) {
    this.userService = userService;
    this.householdService = householdService;
    this.rewardService = rewardService;
}

/*
 * Attaches the household attribute to page
 */
    @GetMapping("/dashboard")
    public String displayHousehold(Model model, HttpServletRequest request) throws Exception {
        long userId = userService.getUserId(request);
        User user = userService.getUserById(userId);
        String name = user.getHousehold().getName();
        model.addAttribute("household", householdService.retrieveHouseholdByName(name));
        return "HouseholdFamilyDashboard";
    }

@GetMapping({"/task-list"})
    public String taskListPage(Model model) {
        model.addAttribute("task-list", model);
        return "task-list";
    }

@GetMapping({"/completed-task"})
    public String completedTasksPage(Model model) {
        model.addAttribute("completed-tasks", model);
        return "completed-tasks";
    }

@GetMapping({"/allowance"})
    public String allowancePage(Model model) {
        model.addAttribute("allowance", model);
        return "allowance";
    }

@GetMapping({"/rewards"})
    public String rewardsPage(Model model) {
        model.addAttribute("rewards", rewardService.retrieveAllRewards());
        return "rewards";
    }
    

}
