# Booking API Test Automation

This project is designed to perform automated tests on the Booking API hosted on Heroku. It leverages the `RestAssured` framework for API testing and uses the `Page Object Model (POM)` for structuring API requests and responses.

## Project Overview

The project performs several API tests to verify the Booking service's functionality. The tests are structured using TestNG annotations and include API health checks, user authentication, and booking management tests.

## Prerequisites

To run this project, ensure you have the following installed:

- Java 21 or later
- Maven 3.x or later
- IntelliJ IDEA (or any Java IDE)

## Setup and Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/NerkaKiss/restful-booker-herokuapp.git
    ```

2. Navigate to the project directory:
    ```bash
    cd booking-api-test
    ```

3. Install the required dependencies:
    ```bash
    mvn clean install
    ```

## Project Structure

- `src/test/java/api/endpoints/` - Contains endpoint classes for Authorization, Booking, and HealthCheck.
- `src/test/java/api/payload/` - Contains classes for creating payloads (e.g., `User` and `BookingDates`).
- `src/test/java/api/test/` - Contains the test classes, such as `BookingTests`.

## Running Tests

1. To run the tests using Maven and TestNG:
    ```bash
    mvn test
    ```

2. Test results will be generated in the `reports` directory in `ExtentReports` format.

## Key Tests

### 1. Health Check (`@BeforeSuite`)

This test ensures that the API service is available before executing any other tests. If the API is down, all tests will be skipped.

### 2. Create Token (`@BeforeClass`)

A token is generated before any tests are run to authenticate requests.

### 3. Get Bookings (`testGetBookings`)

This test retrieves all bookings from the API.

### 4. Create Booking (`testCreateBooking`)

This test creates a new booking using randomly generated user data and verifies the booking is created successfully.

### 5. Get Booking (`testGetBooking`)

This test retrieves a specific booking using the booking ID created in the previous test.

## Dependencies

- `Rest-Assured` for API testing.
- `TestNG` for test execution and management.
- `Faker` for generating fake user data.
- `ExtentReports` for generating detailed test reports.
- `Log4j` for logging.

## Logging

The project uses `Log4j` for logging the test execution process. Logs are printed to the console and can be configured to be saved to a file.

## Example Output

```bash
[INFO] Running BookingTests
[INFO] Checking API Health
[INFO] API IS LIVE
[INFO] Getting Token
[INFO] Token created
[INFO] Creating fake user data
...
