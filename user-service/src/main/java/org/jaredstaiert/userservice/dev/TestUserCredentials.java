package org.jaredstaiert.userservice.dev;

/**
 * Test user email / password DTO.
 * @param email String email.
 * @param passwordHash String password.
 */
public record TestUserCredentials(String email, String passwordHash) {}
