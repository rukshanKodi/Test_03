package org.wecancodeit.hometask.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.hometask.Models.HouseholdMember;

public interface HouseholdMemberRepository extends CrudRepository<HouseholdMember, Long> {
    
}
