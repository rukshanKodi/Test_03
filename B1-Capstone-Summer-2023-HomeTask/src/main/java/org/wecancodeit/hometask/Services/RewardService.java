package org.wecancodeit.hometask.Services;

import org.springframework.stereotype.Service;
import org.wecancodeit.hometask.Models.Reward;
import org.wecancodeit.hometask.Repositories.RewardRepository;

@Service
public class RewardService {
    
    private final RewardRepository rewardRepository;

/*
 * Injection of repository into service
 */

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }


/*
 * Save service
 */

    public Reward save(Reward reward) {
        return rewardRepository.save(reward);
    }

/*
 * Delete service
 */

    public void delete(Long id) throws Exception {
        if (!rewardRepository.existsById(id)){
            throw new Exception("Reward not found");
        }
        rewardRepository.deleteById(id);
    }

/*
 * Retrieve by id
 */

    public Reward retrieveRewardById(Long id) throws Exception {
        try {
            return rewardRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception("Reward not found");
        }
    }


    public Iterable<Reward> retrieveAllRewards() {
        return rewardRepository.findAll();
    }

    
}

