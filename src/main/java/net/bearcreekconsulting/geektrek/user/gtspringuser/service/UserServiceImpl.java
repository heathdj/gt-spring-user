package net.bearcreekconsulting.geektrek.user.gtspringuser.service;

import lombok.RequiredArgsConstructor;
import net.bearcreekconsulting.geektrek.user.gtspringuser.model.User;
import net.bearcreekconsulting.geektrek.user.gtspringuser.repository.UserRepository;
import net.bearcreekconsulting.geektrek.user.gtspringuser.web.model.UserDto;
import net.bearcreekconsulting.geektrek.user.gtspringuser.web.model.UserPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by David on 8/24/2019. Copyright (c) 2019 Bear Creek Consulting LLC.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


//    @Override
//    public UserPagedList listUsers(String name, PageRequest pageRequest, Boolean showRole) {
//        return null;
//    }

    /**
     * <p> Get User by ID value and optionally show the roles</p>
     * @param userId
     * @param showRole
     * @return
     */
    @Override
    public User getById(UUID userId, Boolean showRole) {
        User tmpUser = null;
        if (userRepository.findById(userId).isPresent()){
            tmpUser = userRepository.findById(userId).get();
        }
        return tmpUser;
    }

    /**
     * <p>Saves the New User</p>
     * @param user
     */
    @Override
    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    /**
     * <p> Find the User by the Username</p>
     *
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }


    /**
     * <p> Update an existing User</p>
     * @param userId
     * @param user
     * @return
     */
    @Override
    public User updateUser(UUID userId, User user) {
        return null;
    }

    /**
     * <p>Find the Usernames of the UUIDs presented</p>
     * @param uuidList
     * @return
     */
    @Override
    public List<String> findUsers(List<UUID> uuidList) {
        return userRepository.findUserNames(uuidList);
    }
}
