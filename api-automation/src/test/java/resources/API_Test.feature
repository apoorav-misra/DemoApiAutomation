
Feature: API automaton over Demo Pet Store
Background: I want to test different API operations on this API

@get
 Scenario Outline: Fetching all pets with status available
    When I hit get API with <status> status
    Then I verify success status code 
    Then I verify response body is not blank
 
   Examples: 
      | status    |
      | available |
      | pending   |
      | sold      |
       
@post
 Scenario Outline: Adding a new available pet to the store.
    When I hit post API with values <id> <name> <status>
    Then I verify successful creation status code 
    Then I verify response body is not blank
    Then I verify that the pet with <id> <name> <status> has been added
    
    Examples: 
      | id   | name     | status    |
      | 5000 | BoxerMilo| available |
      
@put
 Scenario Outline: Updating the newly added pet in the store.
    When I hit put API with values <id> <name> <status>
    Then I verify success status code 
    Then I verify response body is not blank
    Then I verify that the pet's status with <id> <name> has been updated to <status>
    
    Examples: 
      | id   | name     | status    |
      | 5000 | BoxerMilo| sold      |
      
@delete
 Scenario Outline: Deleting the updated pet from the store.
    When I hit delete API with <id> id
    Then I verify success status code 
    Then I verify response body is not blank
    
    Examples: 
      | id   | 
      | 5000 |