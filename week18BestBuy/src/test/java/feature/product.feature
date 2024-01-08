Feature: Best Buy Application

  As a user I want to test Best Buy Application

  @test
  Scenario Outline: Product CRUD Test
    Given Best buy application is running
    When I create a new product by providing the information name "<name>" type "<type>" price "<price>" shipping "<shipping>" upc "<upc>" description "<description>" manufacturer "<manufacturer>" model "<model>" url "<url>" image "<image>"
 #  And I verify that the product with name "<name>" is created
    And I update product by providing the information name "<name>" type "<type>" price "<price>" shipping "<shipping>" upc "<upc>" description "<description>" manufacturer "<manufacturer>" model "<model>" url "<url>" image "<image>"
    #And The product with name "<name>" is updated successfully
    And I delete the product that created with name "<name>"
 #  Then The product deleted successfully from the application
    Examples:
      | name                        | type     | price | shipping | upc | description                                   | manufacturer | model | url    | image  |
      | Duracell-Batteries(12-Pack) | HarGood  | 100   | 5        | dpd | Compatible with any devices                   | CanCompany   | MR22  | String | String |
      | Duracell-Batteries          | SoftGood | 200   | 4        | abc | Not compatible with select electronic devices | BangCompany  | SS23  | String | String |



