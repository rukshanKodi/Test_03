package org.wecancodeit.hometask.Services;


import org.springframework.stereotype.Service;
import org.wecancodeit.hometask.Models.Task;
import org.wecancodeit.hometask.Repositories.TaskRepository;

@Service
public class TaskService {
    
    private final TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task save(Task task){
        return taskRepo.save(task);
    }
    public Task retrieveTaskById(Long TaskID)throws Exception{
        try{
            return taskRepo.findById(TaskID).get();
        }catch(Exception e){
            throw new Exception("Task not Found");
        }
    }
    public void delete(Long TaskID)throws Exception{
        if(!taskRepo.existsById(TaskID)){
            throw new Exception("Task not Found");
        }
    }

    
    
    

    
}
