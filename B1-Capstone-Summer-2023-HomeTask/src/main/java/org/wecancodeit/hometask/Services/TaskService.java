package org.wecancodeit.hometask.Services;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.wecancodeit.hometask.Models.*;
import org.wecancodeit.hometask.Repositories.TaskRepository;

import java.time.LocalDate;

@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final HouseholdService HHService;

    public TaskService(TaskRepository taskRepo, HouseholdService HHService) {
        this.taskRepo = taskRepo;
        this.HHService = HHService;
    }

    @Transactional
    public Task save(Task task) {
        return taskRepo.save(task);
    }

    @Transactional
    public String addNewTask(long userId, CreateTaskDto taskDto) throws Exception {
        // your logic/ calculation
        int rewardedValue = 67 * 2 / 123;

        Household household = HHService.retrieveHouseholdByUserId(userId);

        HouseholdMember member = new HouseholdMember();
        member.setId(taskDto.getMemberId());

        Reward reward = new Reward();
        reward.setId(0);// TODO - set reward ID

        boolean isDurationActive = taskDto.getStartDate().isEqual(taskDto.getEndDate());

        Task task = new Task(taskDto.getTaskName(),
                taskDto.getTaskDescription(), taskDto.getComments(), 0,
                member, null, household, false, true,
                (double) taskDto.getDuration(), taskDto.getStartDate(), taskDto.getEndDate(),
                isDurationActive, false, rewardedValue, LocalDate.now());


        taskRepo.save(task);
        return "Successfully saved";
    }

    public Task retrieveTaskById(Long TaskID) throws Exception {
        try {
            return taskRepo.findById(TaskID).get();
        } catch (Exception e) {
            throw new Exception("Task not Found");
        }
    }

    public void delete(Long TaskID) throws Exception {
        if (!taskRepo.existsById(TaskID)) {
            throw new Exception("Task not Found");
        }
    }


}
