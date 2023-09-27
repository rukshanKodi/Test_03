package org.wecancodeit.hometask.Services;

import org.springframework.stereotype.Service;
import org.wecancodeit.hometask.Models.HouseholdMember;
import org.wecancodeit.hometask.Repositories.HouseholdMemberRepository;

@Service
public class HouseholdMemberService {
    
    private final HouseholdMemberRepository memberRepository;

    public HouseholdMemberService(HouseholdMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public HouseholdMember save(HouseholdMember member) {
        return memberRepository.save(member);
    }

    public Iterable<HouseholdMember> retrieveAllMembers() {
        return memberRepository.findAll();
    }

    public HouseholdMember retrieveMemberById(Long id) throws Exception {
        try {
            return memberRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception("Member not found.");
        }
    }


}
