@regression @specialtiesTests
Feature: Api tests for /specialties rest endpoint

  Background:
    Given Pet clinic application is up and running

  Scenario: Get specialties (valid GET /specialties).
    When I get all existing specialties
    Then The response status code equals 200
    And The response contains list

  Scenario: Create a new specialty and delete it (valid POST, DELETE /specialties/id).
    When I create following specialty
      | name | pathology |
    Then The response status code equals 201
    And The response contains the following
      | name | pathology |
    When I delete specialty created in previous step
    Then The response status code equals 204

  Scenario: Create and edit existing specialty (PUT /specialties/id)
    When I create following specialty
      | name | pathology |
    Then The response status code equals 201
    And The response contains the following
      | name | pathology |
    When I update specialty created in previous step with following data
      | name | pathology |
    Then The response status code equals 204

  Scenario Outline: Edit NOT existing specialty (PUT /specialties/id)
    When I update specialty with id <notExistingId> using following data
      | name | pathology |
    Then The response status code equals <expectedStatusCode>

    Examples:
      | notExistingId      | expectedStatusCode |
      | 332211             | 404                |
      | 230192838098098098 | 400                |
