package org.wecancodeit.hometask.Repositories;
import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.hometask.Models.Task;

public interface TaskRepository extends CrudRepository<Task,Long> {
    
}

