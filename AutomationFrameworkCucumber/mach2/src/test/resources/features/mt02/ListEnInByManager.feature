@mt02-feature
Feature: Validate list of Employees with Patricia Villagomez montalvo as Manager

  This scenario should validate Employee Engineers with Patricia Villagomez montalvo as Manager

  Scenario: Add List Widget with Engineer Information
    Given I am login on Mach2 with credentials
      | user                               | password    |
      | anghela.lizarro@fundacion-jala.org | Control123! |
    And I have a board and widget created
    When I select "List" icon on Widget options
    And I select "Open ERP" service
    And I select "Engineer Information" of Open ERP
    And I select "Patricia Villagomez Montalvo" on "Manager" option
    And I click on save button
    Then I have a List widget with the persons who have "Patricia Villagomez Montalvo" as Manager

