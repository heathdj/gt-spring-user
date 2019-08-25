package net.bearcreekconsulting.geektrek.user.gtspringuser.bootstrap;

import lombok.RequiredArgsConstructor;
import net.bearcreekconsulting.geektrek.user.gtspringuser.model.Role;
import net.bearcreekconsulting.geektrek.user.gtspringuser.model.User;
import net.bearcreekconsulting.geektrek.user.gtspringuser.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by David on 8/24/2019. Copyright (c) 2019 Bear Creek Consulting LLC.
 */
@RequiredArgsConstructor
@Component
public class UserLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            loadUserObjects();
        }
    }

    private void loadUserObjects() {
        User u1 = User.builder()
                .name("admin")
                .username("admin")
                .role(Role.ADMIN)
                .password("admin")
                .build();

        User u2 = User.builder()
                .name("test")
                .username("test")
                .password("test")
                .role(Role.USER)
                .build();

        userRepository.save(u1);
        userRepository.save(u2);
    }
}
