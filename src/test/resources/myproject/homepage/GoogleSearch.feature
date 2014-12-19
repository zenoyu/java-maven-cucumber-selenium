@focus
Feature: Demo of Searching Keyword (Cheese / Zeno in data table) in Google
As a site user
If I search for keyword
The page title should contains the keyword

Scenario Outline:
  Given The web site page type "<PageType>" is opened as "<ScreenSize>"
  When I search "<Keyword>"
  Then The page title should contains "<Keyword>"

 Examples:
    | PageType | ScreenSize | Keyword|
    | search | desktop | cheese|
    | search | mobile | zeno yu|
