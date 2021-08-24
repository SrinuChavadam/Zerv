@Validate_TitlingAbbreviationsSetupFlow
Feature: Validate the Titling Abbreviations setup UI screens.

@Validate_TitlingAbbreviations_Setup_1
Scenario Outline:  Validate whether user can input Title information in Title information page
 
Given am content TitlingAbbreviations user "<role>" and "<PublicationId>" 
When user provides TitlingAbbreviations data "<TitlingAbbreviations_Table>" and table "<TitlingAbbreviations_Data>".
Then user click on pass button
Then system should be saved into TitlingAbbreviations database

Examples:
|role                   |PublicationId|TitlingAbbreviations_Table      |TitlingAbbreviations_Data|
|titling-abbreviations  |91604787915  |Titling                         |Data1                    |

@Validate_TitlingAbbreviations_Setup_2
Scenario Outline:  Check whether user can validate Title Eleven Error messages in Title information page
 
Given am content TitlingAbbreviations user "<role>" and "<PublicationId>" 
When user provides TitlingAbbreviations data "<TitlingAbbreviations_Table>" and table "<TitlingAbbreviations_Data>" for Title Eleven error validation.
Then user checks the valid error message


Examples:
|role                   |PublicationId|TitlingAbbreviations_Table      |TitlingAbbreviations_Data|
|titling-abbreviations  |91604709981  |Titling                         |Data2                    |


@Validate_TitlingAbbreviations_Setup_3
Scenario Outline:  Check whether user can validate Title Twenty Error messages in Title information page
 
Given am content TitlingAbbreviations user "<role>" and "<PublicationId>" 
When user provides TitlingAbbreviations data "<TitlingAbbreviations_Table>" and table "<TitlingAbbreviations_Data>" for Title Twenty error validation.
Then user checks the valid error message for Title Twenty


Examples:
|role                   |PublicationId|TitlingAbbreviations_Table      |TitlingAbbreviations_Data|
|titling-abbreviations  |91604709981  |Titling                         |Data2                    |


@Validate_TitlingAbbreviations_Setup_4
Scenario Outline:  Check whether user can validate Title Full Error messages in Title information page
 
Given am content TitlingAbbreviations user "<role>" and "<PublicationId>" 
When user provides TitlingAbbreviations data "<TitlingAbbreviations_Table>" and table "<TitlingAbbreviations_Data>" for Title Full error Validation.
Then user checks the valid error message for Title Full


Examples:
|role                   |PublicationId|TitlingAbbreviations_Table      |TitlingAbbreviations_Data|
|titling-abbreviations  |91604709981  |Titling                         |Data2                    |

@Validate_TitlingAbbreviations_Setup_5
Scenario Outline:  Check whether user can validate Full Title and Publication Search History in Title information page
 
Given am content TitlingAbbreviations user "<role>" and "<PublicationId>" 
When user provides TitlingAbbreviations data "<TitlingAbbreviations_Table>" and table "<TitlingAbbreviations_Data>" for Publication search hisory validation.
Then validate aganist TitlingAbbreviations database 


Examples:
|role                   |PublicationId|TitlingAbbreviations_Table      |TitlingAbbreviations_Data|
|titling-abbreviations  |91604732283  |Titling                         |Data4                    |

