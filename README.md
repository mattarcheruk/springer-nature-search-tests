# Springer Link automated tests (version 0.1)
This repository contains a small selection of tests that demonstrate how the Springer Link website could be tested in a headless manner, using a combination of Maven, JUnit and JSoup.

In terms of tests, it specifically contains;
- Tests related to the happy path (assumed to be when search results are returned).
- Tests related to the unhappy path (assumed to be when **no** search results are returned).
- Tests that need further investigation to understand the desired behaviour.

## How to run the tests
- Download this repository.
- Navigate to your download location and run `mvn clean test`.

## Future improvements
The tests included in this version (0.1) serve to demonstrate the approach and could be expanded upon to increase the level of test coverage.  That said, before greatly increasing the number of tests, it would be worth investigating how test data is managed and whether any neccessary test data could be brought under the control of the tests.