# TODO

## Next Steps for Personal Finance Manager Backend

- [X] Implement Update Transaction API
    - PUT `/transactions/{id}`
    - Allow updating category, amount, and description only
    - Validate existence of transaction before update

- [X] Add Input Validation and Exception Handling
    - Validate category enum correctness
    - Validate amount is positive number
    - Validate date presence and correctness
    - Use `@Valid` annotations in DTO
    - Use global `@ControllerAdvice` for handling exceptions and returning meaningful errors

- [X] Enforce Date Restrictions
    - Transaction date must be exactly today's date
    - Disallow adding transactions with future dates
    - Return HTTP 400 error with clear message if date validation fails

- [ ] Additional settings

- [ ] Migrate from H2 to MySQL Database
    - Update datasource configuration in `application.properties`
    - Add MySQL dependency to `pom.xml` or `build.gradle`
    - Test connection and schema migration

## Bonus for Extended Features (Later)

- [ ] Add pagination and filtering to transaction listing API
- [ ] Add category management APIs (add, list, delete categories)
- [ ] Add monthly summary/report APIs for finance insights
- [ ] Secure APIs using Spring Security, JWT authentication
