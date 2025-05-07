Feature: Return of rented cars should be possible to ensure sustainability of the
  rental company.
  As an owner of a car rental company
  I want to make cars available for renting
  and get them returned
  So I can make money
  Scenario Outline: Find and rent a car
    Given there are <availableCars> cars available for rental
    When I rent one
    And I return one
    Then there will only be <expectedAvailableCars> cars available for rental
    Examples:
      | availableCars | expectedAvailableCars |
      | 18 | 18 |