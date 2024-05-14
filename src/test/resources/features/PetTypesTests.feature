@regression
Feature: Api tests for /pettypes rest endpoint

  Background:
    Given Pet clinic application is up and running

  Scenario: Get pet types (valid GET /pettypes).
    When I get all existing pet types
    Then The response status code equals 200
    And The response contains list

  Scenario: Create new pet type and delete it (valid POST, DELETE /pettypes/id).
    When Create pet type "cow"
    Then The response status code equals 201
    And The response contains the following
      | name | cow |
    When Delete pet type "cow"
    Then The response status code equals 204
    And The response contains the following
      | name | cow |

  Scenario: Edit existing pet type (PUT /pettypes/id)
    When Create pet type "horse"
    Then The response status code equals 201
    And The response contains the following
      | name | horse |
    When Update pet type "horse" and set "bat"
    Then The response status code equals 204
    And The response contains the following
      | name | elephant |

  Scenario Outline: Edit NOT existing pet type (PUT /pettypes/id)
    When Update pet type with id <notExistingId> and set "cow"
    Then The response status code equals <expectedStatusCode>

    Examples:
      | notExistingId      | expectedStatusCode |
      | 332211             | 404                |
      | 230192838098098098 | 400                |
