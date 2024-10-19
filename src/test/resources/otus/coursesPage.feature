Feature: Course Page

  Scenario: Search course by name
    Given Open browser "Chrome"
    When Open courses page
    Then Search course "Криптографическая защита информации" page
    Then Check "Криптографическая защита информации" page is opened