package net.bearcreekconsulting.geektrek.user.gtspringuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bearcreekconsulting.geektrek.user.gtspringuser.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Created by David on 8/23/2019. Copyright (c) 2019 Bear Creek Consulting LLC.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

        User findByUsername(String username);

        @Query("select u.name from User u where u.id in (:pIdList)")
        List<String> findUserNames(List<UUID> idList);

}
