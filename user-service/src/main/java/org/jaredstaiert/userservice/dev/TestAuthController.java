package org.jaredstaiert.userservice.dev;

import org.jaredstaiert.userservice.TestUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 */
@RestController()
@RequestMapping("/users")
public class TestAuthController {

    private final TestAuthService testAuthService;

    public TestAuthController(TestAuthService testAuthService) {
        this.testAuthService = testAuthService;
    }

    /**
     * DEV ONLY endpoint to check presence of user on db
     * @param user JSON {email: email, passwordHash: passwordHash}
     * @return HTTP Response 200 OK user / 401 Unauthorized
     */
    @PostMapping("/testAuth")
    public ResponseEntity<?> testAuth(@RequestBody TestUserCredentials user) {
        TestUserCredentials validUser = testAuthService.validateUser(user.email(), user.passwordHash());

        if (validUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } else {
            return ResponseEntity.ok(validUser);
        }
    }

    /**
     * DEV ONLY endpoint to add user to db
     * @param user JSON {email: email, passwordHash: passwordHash}
     * @return HTTP Response 200ok user / 400 Bad Request
     */
    @PostMapping("/testAddUser")
    public ResponseEntity<?> testAddUser(@RequestBody TestUserCredentials user) {
        TestUser savedUser = testAuthService.addUser(user.email(), user.passwordHash());
        if (!(savedUser == null)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
