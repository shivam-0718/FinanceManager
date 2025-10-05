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

- [X] Add GET transaction by ID endpoint
    - GET /api/transactions/{id}
    - Return 404 if transaction not found
    - Handle TransactionNotFoundException properly

- [ ] Add DELETE transaction endpoint
    - DELETE /api/transactions/{id}
    - Return success message with deleted transaction ID
    - Validate transaction exists before deletion

- [X] Migrate from H2 to MySQL Database
    - Update datasource configuration in `application.properties`
    - Add MySQL dependency to `pom.xml` or `build.gradle`
    - Create database schema: finance_manager
    - Test connection and verify data persistence
    - Update Hibernate dialect to MySQL

- [X] Implement transaction filtering
    - GET /api/transactions/filter?category={CATEGORY}
    - GET /api/transactions/date-range?startDate={date}&endDate={date}
    - Add repository methods: findByCategory(), findByDateRange()
    - Support multiple filter combinations

- [X] Add sorting capabilities
    - Sort by date (newest/oldest first)
    - Sort by amount (highest/lowest first)
    - Sort by category

- [ ] Summary and Analytics
    - Implement basic analytics endpoints
      - GET /api/transactions/summary/category/{category} - Total spending per category
      - GET /api/transactions/summary/monthly?month={month}&year={year} - Monthly breakdown
      - GET /api/transactions/summary/total - Overall spending summary
      - Return data in structured format (category-wise totals)

- [ ] Add timestamp tracking to Transaction entity
    - Add createdAt field (auto-populated on creation)
    - Add updatedAt field (auto-updated on modification)
    - Use @CreatedDate and @LastModifiedDate annotations
    - Enable JPA Auditing in main application class

## Bonus for Extended Features (Later)

- [ ] Add pagination and filtering to transaction listing API
- [ ] Add category management APIs (add, list, delete categories)
- [ ] Add monthly summary/report APIs for finance insights
- [ ] Secure APIs using Spring Security, JWT authentication
