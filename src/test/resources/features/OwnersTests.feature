@regression @ownersTests
Feature: Api tests for /owners rest endpoint

  Background:
    Given Pet clinic application is up and running

  @dev2
  Scenario: Get owners (valid GET /owners).
    When I get all existing owners
    Then The response status code equals 200
    And The response contains list


  Scenario: Create a new owner and delete it (valid POST, DELETE /owners/id).
    When I create following owner
      | firstName | Michael          |
      | lastName  | Enes             |
      | address   | Market Street 12 |
      | city      | New York         |
      | telephone | 6085551023       |
    Then The response status code equals 201
    And The response contains the following
      | firstName | Michael          |
      | lastName  | Enes             |
      | address   | Market Street 12 |
      | city      | New York         |
      | telephone | 6085551023       |
    When I get owner created in previous step
    Then The response status code equals 200
    And The response contains the following
      | firstName | Michael          |
      | lastName  | Enes             |
      | address   | Market Street 12 |
      | city      | New York         |
      | telephone | 6085551023       |
    When I delete owner created in previous step
    Then The response status code equals 204

  Scenario: Create and edit existing owner (PUT /owners/id)
    When I create following owner
      | firstName | George             |
      | lastName  | Franklin           |
      | address   | 110 W. Liberty St. |
      | city      | Madison            |
      | telephone | 6085551020         |
    Then The response status code equals 201
    And The response contains the following
      | firstName | George             |
      | lastName  | Franklin           |
      | address   | 110 W. Liberty St. |
      | city      | Madison            |
      | telephone | 6085551020         |
    When I update owner created in previous step with following data
      | firstName | George             |
      | lastName  | Franklin           |
      | address   | 110 W. Liberty St. |
      | city      | New Jersey         |
      | telephone | 6090551020         |
    Then The response status code equals 204

  Scenario Outline: Edit NOT existing owner (PUT /owners/id)
    When I update owner with id <notExistingId> using following data
      | firstName | George             |
      | lastName  | Mc Guide           |
      | address   | 110 W. Liberty St. |
      | city      | New Jersey         |
      | telephone | 6090551020         |
    Then The response status code equals <expectedStatusCode>

    Examples:
      | notExistingId      | expectedStatusCode |
      | 332211             | 404                |
      | 230192838098098098 | 400                |
