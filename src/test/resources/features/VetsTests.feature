@regression @vetsTests
Feature: Api tests for /vets rest endpoint

  Background:
    Given Pet clinic application is up and running

  Scenario: Get vets (valid GET /vets).
    When I get all existing vets
    Then The response status code equals 200
    And The response contains list

  Scenario: Create a new vet and delete it (valid POST, DELETE /vets/id).
    When I create following vet
      | firstName     | James     |
      | lastName      | Jackson   |
      | specialtyName | radiology |
    Then The response status code equals 201
    And The response contains the following
      | firstName           | James     |
      | lastName            | Jackson   |
      | specialties[0].name | radiology |
    When I delete vet created in previous step
    Then The response status code equals 204

  Scenario: Create and edit existing vet (PUT /vets/id)
    When I create following vet
      | firstName     | John     |
      | lastName      | Holloway |
      | specialtyName | surgery  |
    Then The response status code equals 201
    And The response contains the following
      | firstName           | John     |
      | lastName            | Holloway |
      | specialties[0].name | surgery  |
    When I update vet created in previous step with following data
      | firstName     | John      |
      | lastName      | Holloway  |
      | specialtyName | radiology |
    Then The response status code equals 204

  Scenario Outline: Edit NOT existing vet (PUT /vets/id)
    When I update vet with id <notExistingId> using following data
      | firstName     | Adam      |
      | lastName      | Smith     |
      | specialtyName | radiology |
    Then The response status code equals <expectedStatusCode>

    Examples:
      | notExistingId      | expectedStatusCode |
      | 332211             | 404                |
      | 230192838098098098 | 400                |
