package org.wecancodeit.hometask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.hometask.Models.Household;
import org.wecancodeit.hometask.Models.HouseholdMember;
import org.wecancodeit.hometask.Models.Reward;
import org.wecancodeit.hometask.Models.Task;
import org.wecancodeit.hometask.Models.User;
import org.wecancodeit.hometask.Services.HouseholdMemberService;
import org.wecancodeit.hometask.Services.HouseholdService;
import org.wecancodeit.hometask.Services.RewardService;
import org.wecancodeit.hometask.Services.TaskService;
import org.wecancodeit.hometask.Services.UserService;

@Component 
public class Populator implements CommandLineRunner {

    private final HouseholdService householdService;
    private final TaskService taskService;
    private final HouseholdMemberService householdMemberService;
    private final UserService userService;
    private final RewardService rewardService;

    
    public Populator(HouseholdService householdService, TaskService taskService, UserService userService,
        HouseholdMemberService householdMemberService, RewardService rewardService) {
      this.householdService = householdService;
      this.taskService = taskService;
      this.householdMemberService = householdMemberService;
      this.userService = userService;
      this.rewardService = rewardService;
    }


    @Override
    public void run(String... args) throws Exception {


        // Creating default user and admin accounts
    User admin = new User("admin", "password", "admin");
    User user = new User("user","user", "user");
    userService.registerUser(admin);
        userService.registerUser(user);

        // Creating Household Family Details
    Household family_1 = new Household("Marcus", admin);
    householdService.save(family_1);
    Household family_2 = new Household("user", user);
    householdService.save(family_2);

    // Creating Family Member Details
    HouseholdMember HHmember_1 = new HouseholdMember("John Peter", family_1, 200);
    householdMemberService.save(HHmember_1);
    HouseholdMember HHmember_2 = new HouseholdMember("Natalia laz", family_2, 20);
    householdMemberService.save(HHmember_2);
    HouseholdMember HHmember_3 = new HouseholdMember("Jane Peterson", family_2, 1);
    householdMemberService.save(HHmember_3);
    HouseholdMember HHmember_4 = new HouseholdMember("Axel Rose", family_2, 15);
    householdMemberService.save(HHmember_4);
    HouseholdMember HHmember_5 = new HouseholdMember("Peter McGriffin", family_2, 5);
    householdMemberService.save(HHmember_5);

    Reward FiveDollars = new Reward("Five dollars", family_1);
    Reward TenDollars = new Reward("Ten dollars", family_1);
    Reward FifteenDollars = new Reward("Fifteen dollars", family_1);
    Reward TwentyDollars = new Reward("Twenty dollars", family_1);

    rewardService.save(TenDollars);
    rewardService.save(FiveDollars);
    rewardService.save(FifteenDollars);
    rewardService.save(TwentyDollars);
    // Creating a main task1
    Task task_01 = new Task("School", "main task_01", 0, HHmember_1, FiveDollars, family_1, false, true,
        5.0, LocalDate.now(), LocalDate.now().plusDays(0),
        false, // identify start and end dates active //we can enactive datetime using this
               // flag
        false, 0, // keep rewarded value against task
        LocalDate.now());
    // Creating a main task between days
    Task task_02 = new Task("School", "main task_02", 0, HHmember_1, FiveDollars, family_2, true, true,
        null, LocalDate.now(), LocalDate.now().plusDays(2), true, false, 0, LocalDate.now());
    // Creating a sub task
    Task task_03 = new Task("School", "sub task on main task_01 ", 1, HHmember_1, FiveDollars, family_1, false, false,
        null, LocalDate.now(), LocalDate.now().plusDays(0), true, false, 0, LocalDate.now());

    taskService.save(task_01);
    taskService.save(task_02);
    taskService.save(task_03);
    // taskService.save(task_04);

    // Creating Rewards

  }
}
