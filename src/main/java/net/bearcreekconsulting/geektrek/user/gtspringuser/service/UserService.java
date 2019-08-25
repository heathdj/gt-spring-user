package net.bearcreekconsulting.geektrek.user.gtspringuser.service;

import net.bearcreekconsulting.geektrek.user.gtspringuser.model.User;
import net.bearcreekconsulting.geektrek.user.gtspringuser.web.model.UserDto;
import net.bearcreekconsulting.geektrek.user.gtspringuser.web.model.UserPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

/**
 * Created by David on 8/24/2019. Copyright (c) 2019 Bear Creek Consulting LLC.
 */
public interface UserService {

    //UserPagedList listUsers(String name, PageRequest pageRequest, Boolean showRole);

    /**
     * <p> Get User by ID value and optionally show the roles</p>
     * @param userId
     * @param showRole
     * @return
     */
    User getById(UUID userId, Boolean showRole);

    /**
     * <p>Saves the New User</p>
     * @param user
     */
    void saveNewUser(User user);

    /**
     * <p> Find the User by the Username</p>
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * <p> Update an existing User</p>
     * @param userId
     * @param user
     * @return
     */
    User updateUser(UUID userId, User user);

    /**
     * <p>Find the Usernames of the UUIDs presented</p>
     * @param uuidList
     * @return
     */
    List<String> findUsers(List<UUID> uuidList);

}
