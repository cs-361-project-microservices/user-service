package org.jaredstaiert.userservice.dev;

import org.jaredstaiert.userservice.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User repository interface.
 */
@Repository
public interface TestAuthRepository extends JpaRepository<TestUser, Integer> {

    List<TestUser> findByEmailAndPasswordHash(String email, String passwordHash);

    /**
     * DEV ONLY raw sql query to check for existence of user.
     * @param email String email
     * @param passwordHash String password value
     * @return List of users who
     */
    @Query(value = "SELECT * FROM \"user\" WHERE email = :email AND password_hash = :passwordHash", nativeQuery = true)
    List<TestUser> rawQuery(@Param("email") String email, @Param("passwordHash") String passwordHash);
}
