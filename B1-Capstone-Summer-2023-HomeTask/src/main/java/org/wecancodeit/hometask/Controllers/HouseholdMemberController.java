package org.wecancodeit.hometask.Controllers;


    import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.hometask.Services.HouseholdMemberService;

import jakarta.annotation.Resource;

@Controller
public class HouseholdMemberController {

    @Resource
    private HouseholdMemberService memberService;


    public HouseholdMemberController(HouseholdMemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/")
    public String collectAllMembers(Model model) {
        model.addAttribute("members", memberService.retrieveAllMembers());
        return "memberView";// add household member view
    }

    @RequestMapping("/member/{id}")
    public String collectOneMember(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("member", memberService.retrieveMemberById(id));
        return "member";// add household member view
    }

    @RequestMapping("/family")
    public String displayTaskDetails(Model model){
        model.addAttribute("familyPage");
        return "DisplayHouseholdMembers";
    }

}



