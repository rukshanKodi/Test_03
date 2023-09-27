package org.wecancodeit.hometask.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.hometask.Models.Reward;
import org.wecancodeit.hometask.Services.RewardService;

@Controller
public class RewardController  {

   /////
   
    private RewardService rewardService;


///John Added Constructor    
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/createReward/{familyMemberId}")
    public String createReward(@PathVariable Long familyMemberId, Model model) {
        // add reward to a Family Member

        model.addAttribute("reward", null);
        return "createRewardView";
    }

    @GetMapping({"/reward/{RewardID}" })
    public Reward retrieveRewardById(@PathVariable Long RewardID) throws Exception {
        return rewardService.retrieveRewardById(RewardID);
    }

    /*
     * Save reward from user input
     */
    @PostMapping("/saveReward")
    public String saveReward(@ModelAttribute("reward") Reward Reward) {
        return "redirect:/reward";
    }

    @GetMapping("/create-reward/{username}")
    public String redirectCreateReward() {
        return "RewardInputFormView";
    }
    
    @RequestMapping({"/rewardInfo/"})
    //Will display detailed information about a reward
    public String displayRewardDetails(Reward info) {
        return "RewardInfoView";
    }
}
