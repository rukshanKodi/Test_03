package org.wecancodeit.hometask.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.hometask.Models.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
