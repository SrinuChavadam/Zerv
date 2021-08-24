@ValidateReceiptingRulesUI
Feature: Validate the Receipting rules UI.

@ValidateReceiptingRulesUI_01
Scenario Outline: Validate whether able to create rule without end value in Receipting Rules page.
Given am a productionSetup user "<role>" ,"<PublicationId>".
When user provides Receipting Rules data "<data>" and table "<tablename>".
Then data should be saved into receipting_rules table.
Examples:
|role             |PublicationId|data     |tablename          |
|production-setup | 91604679440 |Data1    |Rules              |
#|production-setup | 91604679441 |Data2    |Rules              |
#|production-setup | 91604679442 |Data3    |Rules              |
#|production-setup | 91604678837 |Data4    |Rules              |

@ValidateReceiptingRulesUI_02
Scenario Outline: Verify the error message when creating rule with end value in Receipting Rules page.
Given am a productionSetup user "<role>" ,"<PublicationId>".
When user provides Receipting Rules data and entered End field value "<data>" and table "<tablename>".
Then warning message should display like At most one rule should have an open ending.
Examples:
|role             |PublicationId|data     |tablename          |
|production-setup | 91604679443 |Data5    |Rules              |
#|production-setup | 91604679444 |Data6    |Rules              |
#|production-setup | 91604679445|Data7    |Rules              |

@ValidateReceiptingRulesUI_03
Scenario Outline: Verify the error message when end value is less than begin value in Receipting Rules page.
Given am a productionSetup user "<role>" ,"<PublicationId>".
When user provides end value less than begin value "<data>" and table "<tablename>".
Then warning message should display like Begin value must be less than or equal to End value.
Examples:
|role             |PublicationId|data      |tablename          |
|production-setup | 91604787761 |Data11    |Rules              |
#|production-setup | 91604679445 |Data12    |Rules              |


@ValidateReceiptingRulesUI_04
Scenario Outline: Validate whether able to create rule with end value in Receipting Rules page.
Given am a productionSetup user "<role>" ,"<PublicationId>".
When user provides Receipting Rules data with end value "<data>" and table "<tablename>".
Then data should be saved into receipting_rules table.
Examples:
|role             |PublicationId|data     |tablename          |
|production-setup | 91604679446 |Data8    |Rules              |
#|production-setup | 91604679447 |Data10    |Rules              |
#|production-setup | 91604679448 |Data9    |Rules              |

@ValidateReceiptingRulesUI_05
Scenario Outline: Validate whether user able to delete the rule in Receipting Rules page.
Given am a productionSetup user "<role>" ,"<PublicationId>".
When user clicks on delete icon on Receipting Rules page".
Then Rule should be deleted.
Examples:
|role             |PublicationId|
|production-setup | 91604787771 |




