package org.wecancodeit.hometask.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.hometask.Models.Household;

public interface HouseholdRepository extends CrudRepository<Household, Long>{
    Household findHouseholdByName(String name);
    Household findHouseholdByUser_id(long id);
    
}
