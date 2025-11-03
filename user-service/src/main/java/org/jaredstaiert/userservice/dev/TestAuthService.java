package org.jaredstaiert.userservice.dev;

import org.jaredstaiert.userservice.TestUser;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Service class for user. Most logic here.
 */
@Service
public class TestAuthService {

    private final TestAuthRepository testAuthRepository;

    public TestAuthService(TestAuthRepository testAuthRepository) {
        this.testAuthRepository = testAuthRepository;
    }

    /**
     * DEV ONLY service method that validates the existence of a user in db.
     * @param email String email
     * @param passwordHash String passwordHash value
     * @return TestUserCredentials DTO or null if not existent.
     */
    public TestUserCredentials validateUser(String email, String passwordHash) {

        List<TestUser> userExists = testAuthRepository.rawQuery(email, passwordHash);

        if (userExists.isEmpty()) {
            return null;
        }
        return new TestUserCredentials(userExists.getFirst().getEmail(), userExists.getFirst().getPasswordHash());
    }

    /**
     * DEV ONLY service method that adds user to db.
     * @param email String email
     * @param passwordHash String passwordHash value
     * @return TestUserCredentials DTO
     */
    public TestUser addUser(String email, String passwordHash) {
        try {
            TestUser user = new TestUser();

            user.setEmail(email);
            user.setPasswordHash(passwordHash);
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()).toInstant());

            return testAuthRepository.save(user);
        } catch (Exception e) {
            return null;
        }

    }
}
