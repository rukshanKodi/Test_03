package org.wecancodeit.hometask.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wecancodeit.hometask.Models.User;
import org.wecancodeit.hometask.Repositories.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long getUserId(HttpServletRequest request){
    long retValue=0;
    Cookie[] cookies = request.getCookies();
    for(int x =0;x<cookies.length;x++){
        if(cookies[x].getName().equals("user-id")){
            retValue = Long.parseLong(cookies[x].getValue());
            break;
        }
    }
        return retValue;
    }

    //user registration
    public void registerUser(User user) {
        userRepository.save(user);
    }

    //user retrieval
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

}
