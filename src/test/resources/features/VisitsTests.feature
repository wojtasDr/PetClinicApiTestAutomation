@regression @visitsTests
Feature: Api tests for /visits rest endpoint

  Background:
    Given Pet clinic application is up and running

  Scenario: Get visits (valid GET /visits).
    When I get all existing visits
    Then The response status code equals 200
    And The response contains list

  Scenario: Create a new visit and delete it (valid POST, DELETE /visits/id).
    When I create following visit
      | date        | 2024-02-17  |
      | description | rabies shot |
    Then The response status code equals 201
    And The response contains the following
      | date        | 2024-02-17  |
      | description | rabies shot |
    When I delete visit created in previous step
    Then The response status code equals 204

  Scenario: Create and edit existing visit (PUT /visits/id)
    When I create following visit
      | date        | 2023-12-10    |
      | description | health review |
    Then The response status code equals 201
    And The response contains the following
      | date        | 2023-12-10    |
      | description | health review |
    When I update visit created in previous step with following data
      | date        | 2023-12-11    |
      | description | health review |
    Then The response status code equals 204

  Scenario Outline: Edit NOT existing visit (PUT /visits/id)
    When I update visit with id <notExistingId> using following data
      | date        | 2023-10-12      |
      | description | health review 2 |
    Then The response status code equals <expectedStatusCode>

    Examples:
      | notExistingId      | expectedStatusCode |
      | 332211             | 404                |
      | 230192838098098098 | 400                |
