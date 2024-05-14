@regression @petsTests
Feature: Api tests for /pets rest endpoint

  Background:
    Given Pet clinic application is up and running

  Scenario: Get pets (valid GET /pets).
    When I get all existing pets
    Then The response status code equals 200
    And The response contains list

  @dev
  Scenario: Create a new pets and delete it (valid POST, DELETE /pets/id).
    When I create following pet
      | name      | Gator      |
      | birthDate | 2022-04-22 |
      | typeName  | cat        |
    Then The response status code equals 201
    And The response contains the following
      | name      | Gator      |
      | birthDate | 2022-04-22 |
      | typeName  | cat        |
    When I delete pet created in previous step
    Then The response status code equals 204

  Scenario: Create and edit existing pet (PUT /pets/id)
    When I create following pet
      | name      | Max        |
      | birthDate | 2022-01-20 |
      | typeName  | dog        |
    Then The response status code equals 201
    And The response contains the following
      | name      | Max        |
      | birthDate | 2022-01-20 |
      | typeName  | dog        |
    When I update pet created in previous step with following data
      | name      | Min        |
      | birthDate | 2022-01-22 |
      | typeName  | dog        |
    Then The response status code equals 204

  Scenario Outline: Edit NOT existing pet (PUT /pets/id)
    When I update pet with id <notExistingId> using following data
      | name      | Min        |
      | birthDate | 2020-03-22 |
      | typeName  | snake      |
    Then The response status code equals <expectedStatusCode>

    Examples:
      | notExistingId      | expectedStatusCode |
      | 332211             | 404                |
      | 230192838098098098 | 400                |
