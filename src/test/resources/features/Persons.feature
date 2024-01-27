Feature:  Testing Persons

  Scenario:  Adding a person
    Given I want to add a person with name "John" and email "john@gmail.com"
    When I enter the information
    Then that person is added to my Person database table