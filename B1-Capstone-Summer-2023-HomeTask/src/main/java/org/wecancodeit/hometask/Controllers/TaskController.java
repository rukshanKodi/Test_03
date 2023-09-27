package org.wecancodeit.hometask.Controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.hometask.Models.CreateTaskDto;
import org.wecancodeit.hometask.Models.Household;
import org.wecancodeit.hometask.Models.HouseholdMember;
import org.wecancodeit.hometask.Models.Task;
import org.wecancodeit.hometask.Repositories.TaskRepository;
import org.wecancodeit.hometask.Services.HouseholdService;
import org.wecancodeit.hometask.Services.TaskService;
import org.wecancodeit.hometask.Services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TaskController {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private TaskService taskService;
    @Resource
    private HouseholdService HHService;
    @Resource
    private UserService userService;

    @GetMapping("/createTask/{familyMemberId}")
    public String createTask(@PathVariable Long familyMemberId, Model model) {
        // add Get Family Member

        model.addAttribute("task", null);
        return "createTaskView";
    }

    @GetMapping({"/task/{TaskID}"})
    public Task retrieveTaskById(@PathVariable Long TaskID) throws Exception {
        return taskService.retrieveTaskById(TaskID);
    }

    /*
     * Save task from user input
     */
    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        return "redirect:/task";
    }

    @GetMapping("/create-task/{username}")
    public String redirectCreateTask() {
        return "AssignTaskView";
    }

    @GetMapping("/createTask/{durationId}")
    public String createDuration(@PathVariable Long durationId, Model model) {
        // add Get Family Member
        model.addAttribute("duration", null);
        return "createDurationView";
    }

    @PostMapping("/saveDuration")
    public String saveDuration(@ModelAttribute("duration") Task task) {
        return "redirect:/task";
    }


    @RequestMapping({"/taskInfo/"})
    //Will display detailed information about a task
    public String displayTaskDetails(Task info) {
        return "TaskInfoView";
    }

    //RK
    @GetMapping("/add_task")
    public String ShowHousehildwiseMembers(Model model,
                                           @RequestParam(value = "message", required = false) String message,
                                           HttpServletRequest request) throws Exception {
        long userId = userService.getUserId(request);
        if (userId == 0) {
            throw new Exception("Not logged in");
        }
        Household household = HHService.retrieveHouseholdByUserId(userId);

        List<Map<String, Object>> members = new ArrayList<>();
        for (HouseholdMember member : household.getMembers()) {
            Map<String, Object> memberInfo = new HashMap<>();
            memberInfo.put("householdMemberId", member.getHouseholdMemberId());
            memberInfo.put("name", member.getName()); // Replace with the actual 
            members.add(memberInfo);
        }
        model.addAttribute("message", message);
        model.addAttribute("members", members);
        return "AddTask";
    }


    @PostMapping("/tasks")
    public String createTask(@ModelAttribute CreateTaskDto taskDto,
                             HttpServletRequest request) throws Exception {
        // Create a Task object with the form data
        long userId = userService.getUserId(request);
        if (userId == 0) {
            throw new Exception("Not logged in");
        }

        String message = taskService.addNewTask(userId, taskDto);

        return "redirect:/add_task?message=" + message;
    }
}

//malli read bellow comments

// this.TaskName = taskName;
// this.TaskDescription = taskDescription;
// this.Comments = comments;
// this.LinkedTaskID = linkedTaskID; //logic here
// this.member = member; //dropdown memberID
// this.reward = reward; //
// this.household = household; //from loggeduser household ID
// this.IsOneTime = isOneTime; //logic apply you can hardcode now
// this.RootTask = rootTask; //logic apply you can hardcode now
// this.TimeDuration = timeDuration;
// this.StartDate = startDate;
// this.EndDate = endDate;
// this.isDurationActive = isDurationActive; logic apply you can hardcode now
// this.isCompleted = isCompleted;
// this.RewardedValue = rewardedValue; //save 0 for now
// this.CreatedDate = createdDate; //getdate