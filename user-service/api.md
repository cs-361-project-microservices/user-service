# TestAuthController — Quick API Reference

This is a quick developer-only API reference for TestAuthController (org.jaredstaiert.userservice.dev.TestAuthController). Both endpoints are DEV ONLY and accept the same JSON payload.

Common request JSON (use this body for both endpoints)
```json
{
  "email": "email",
  "passwordHash": "test"
}
```

Base path
- /users

Endpoints

1) POST /users/testAuth
- Purpose: DEV ONLY endpoint to check presence of a user in the database and validate credentials.
- Request:
    - Content-Type: application/json
    - Body: see "Common request JSON" above.
- Successful response:
    - HTTP 200 OK
    - Body: the validated user credential object (echo of the submitted credentials). Example:
      ```json
      {
        "email": "alice@example.com",
        "passwordHash": "test"
      }
      ```
- Failure response:
    - HTTP 401 Unauthorized
    - Body: plain text "Invalid credentials"
- Example curl:
  ```
  curl -X POST "http://localhost:8080/users/testAuth" \
    -H "Content-Type: application/json" \
    -d '{"email":"alice@example.com","passwordHash":"test"}'
  ```

2) POST /users/testAddUser
- Purpose: DEV ONLY endpoint to add a user to the database.
- Request:
    - Content-Type: application/json
    - Body: see "Common request JSON" above.
- Successful response:
    - HTTP 201 Created
    - Body: created user object. The controller returns a TestUser instance — common fields you may see:
      ```json
      {
        "id": 1,
        "email": "alice@example.com",
        "passwordHash": "test"
      }
      ```
      (Exact TestUser fields depend on your TestUser class; adjust example accordingly.)
- Failure response:
    - HTTP 400 Bad Request
    - Body: empty
- Example curl:
  ```
  curl -X POST "http://localhost:8080/users/testAddUser" \
    -H "Content-Type: application/json" \
    -d '{"email":"alice@example.com","passwordHash":"test"}'
  ```

Notes & security
- These endpoints are explicitly labeled DEV ONLY in the controller. They should serve for the class project but I intend to implement something a bit more robust.

Changelog
- Generated from TestAuthController (org.jaredstaiert.userservice.dev.TestAuthController) — reflects its current behavior: testAuth returns validated credentials or 401; testAddUser returns created TestUser or 400.
