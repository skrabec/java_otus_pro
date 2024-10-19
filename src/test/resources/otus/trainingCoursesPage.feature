Feature: Training courses feature

  Scenario: Print the cheapest and the most expensive courses
    Given Open browser "Chrome"
    When Open training courses page
    Then Print cheapest and the most expensive course