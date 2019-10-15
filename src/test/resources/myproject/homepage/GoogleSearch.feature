@focus
Feature: Demo of Searching Keyword (Cheese / Zeno in data table) in Google
As a site user
If I search for keyword
The page title should contains the keyword

Scenario Outline:
  Given The web site is opened as "<ScreenSize>"

  # Enter the Keyword in the Google Search Bar
  Then I fill in "q" by name "<Keyword>"

  # You can wait for 2 seconds
  Then I wait for "2" seconds

  # Hit Submit button in the Google Search Bar
  Then I submit form by form name "q"

  # verify if the keyword appear in page title
  Then The page title should contains "<Keyword>"

 Examples:
    | ScreenSize | Keyword|
    | desktop | cheese|
    | mobile | zeno yu|
