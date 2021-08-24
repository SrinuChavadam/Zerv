@Validate_XMLSetupFlow
Feature: Validate the XMLSetup Screens.

@Validate_XML_Setup_1
Scenario Outline:  Validate whether user can input the data XMLSetup page
 
Given am XMl Setup user "<role>" and "<PublicationId>" 
When user provides XMLsetup data "<XMLSetup_Table>" ,table "<XMLSetup_Table2>" and table "<XMLSetup_Table_Data1>" and table"<XMLSetup_Table2_Data2>".
Then user click on pass button in XMl setup page
Then user upload the mapping document and click on pass button in upload document mapping page


Examples:
|role         |PublicationId|XMLSetup_Table     |XMLSetup_Table2|XMLSetup_Table_Data1|XMLSetup_Table2_Data2|
|xml-setup    |91604786813  |XMLSetup           |XMLSetup1     |Data1               |Data1                |

