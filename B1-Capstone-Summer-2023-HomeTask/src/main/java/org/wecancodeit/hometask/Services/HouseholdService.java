package org.wecancodeit.hometask.Services;

import org.springframework.stereotype.Service;
import org.wecancodeit.hometask.Models.Household;
import org.wecancodeit.hometask.Models.Task;
import org.wecancodeit.hometask.Repositories.HouseholdRepository;

@Service
public class HouseholdService {
    
    private final HouseholdRepository householdRepository;

    public HouseholdService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    public Household save(Household household) {
        return householdRepository.save(household);
    }

    public Iterable<Household> retrieveAllHouseholds() {
        return householdRepository.findAll();
    }
    
    public Household retrieveHouseholdById(Long id) throws Exception {
        try {
            return householdRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception("Household not found");
        }
    }
    
    public Household retrieveHouseholdByName(String name) throws Exception {
        try{
            Household household = householdRepository.findHouseholdByName(name);
            return household;
        } catch (Exception e) {
            throw new Exception ("Household not found");
        }
    }

    public Household retrieveHouseholdByUserId(long id) throws Exception {
        try{
            Household household = householdRepository.findHouseholdByUser_id(id);
            return household;
        } catch (Exception e) {
            throw new Exception ("Household not found");
        }
    }

    public void addTaskToHousehold(Task task, Household household) {
        task.setHousehold(household);
        householdRepository.save(household);
    }



    // public String retrieveHouseholdByPassword(String password) throws Exception{
    //     try{
    //         Household household = householdRepository.findHouseholdByPassword(password);
    //     }catch(Exception e){
    //         throw new Exception ("Household not found");
    //     }
    //     return password;
    // }
    
}
