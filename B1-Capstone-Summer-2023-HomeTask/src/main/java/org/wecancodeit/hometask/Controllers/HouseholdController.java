package org.wecancodeit.hometask.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.hometask.Models.Household;
import org.wecancodeit.hometask.Models.User;
import org.wecancodeit.hometask.Repositories.HouseholdRepository;
import org.wecancodeit.hometask.Services.HouseholdMemberService;
import org.wecancodeit.hometask.Services.HouseholdService;
import org.wecancodeit.hometask.Services.TaskService;
import org.wecancodeit.hometask.Services.UserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HouseholdController {
    @Resource
    private HouseholdRepository householdRepository;

    @Resource
    private HouseholdService householdService;

    @Resource
    private TaskService taskService;

    @Resource
    private UserService userService;

    @Resource
    private HouseholdMemberService memberService;

    public HouseholdController(HouseholdService householdService, HouseholdMemberService memberService, TaskService taskService, UserService userService) {
        this.householdService = householdService;
        this.taskService = taskService;
        this.userService = userService;
        this.memberService = memberService;
    }

/*
 * Creates household and connects it to the User.
 */
    @PostMapping("/create-household")
    public String createHousehold(@RequestParam String name, HttpServletRequest request) throws Exception {
        Household household = householdService.retrieveHouseholdByName(name);
        long userId = userService.getUserId(request);
        if(userId==0){
            throw new Exception("Not logged in");
        }
        User user = userService.getUserById(userId);
        if (household == null) {
            Household newHousehold = new Household(name, user);
            householdService.save(newHousehold);
        }
        return "redirect:/dashboard";
    }


    

    // @PostMapping("{username}/create-task/")
    // public String createTask(@RequestParam String taskName, @RequestParam String taskDescription, @RequestParam Boolean recurringTask, @PathVariable String username, @RequestParam HouseholdMember member, @RequestParam Reward reward, @RequestParam boolean rootTask, @RequestParam Double timeDuration, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam boolean isDurationActive, @RequestParam boolean isCompleted) throws Exception {
    //     Household household = householdService.retrieveHouseholdByName(username);
    //     boolean isOneTime = true;
    //     if (recurringTask == true ){isOneTime = false;}
    //     Task task = new Task(taskName, taskDescription, recurringTask, household, member, reward, isOneTime, rootTask, timeDuration, startDate, endDate, isDurationActive, isCompleted);
    //     taskService.save(task);
    //     householdService.addTaskToHousehold(task, household);
    //     return "redirect:HomepageView";
    // }
    

}



