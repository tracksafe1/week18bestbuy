Feature: Best Buy Application
@test
Scenario Outline: Store CRUD Test
Given Best buy application is running
When I create a new store by providing the information name "<name>" type "<type>" address "<address>" addresstwo "<address2>" city "<city>" state "<state>" zip "<zip>" lat "<lat>" lng "<lng>" hours "<hours>"
Then I verify that the store with name "<name>" is created
And I update store by providing the information name "<name>" type "<type>" address "<address>" addresstwo "<address2>" city "<city>" state "<state>" zip "<zip>" lat "<lat>" lng "<lng>" hours "<hours>"
And The store with name "<name>" is updated successfully
And I delete the store that created with name "<name>"
Then The store deleted successfully from the application
Examples:
| name    | type     | address      | address2 |    city     | state   | zip    | lat  | lng | hours   |
| choclate |Cadbury  | 20   |  London   | London  |  Harrow |  54507 | 44   | 43  | Tue10to5 |
| choclate1 | Nestle  | 30  | London   | London    |  Wembly |  54505 | 45   | 48  | Fri10to5 |