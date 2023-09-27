package org.wecancodeit.hometask.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.hometask.Models.Reward;

/*
 * Created a repository for reward
 */

public interface RewardRepository extends CrudRepository<Reward, Long> { 
   
}
