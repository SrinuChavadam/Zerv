@ValidateXMLReceiptingRulesUI
Feature: Validate the XML Setup XML Review UI.

@ValidateXMLReceiptingRulesUI_01
Scenario Outline: Validate whether Defect Information is stored into "defect_details" Table.
Given am a XML Reviewer on Transformation Review page."<url>","<publicationID>"
When user provides Defect information "<defect Desc>","<priority>","<status>","<remarks>" and save the page.
Then "<defect Desc>","<priority>","<status>","<remarks>" data should be saved into defect_details table.
Examples:
|url             |defect Desc|priority|status|remarks     |publicationID|
|xml-setup-review|Test desc  |Medium  |Open  |test remarks|91604787779  |


@ValidateXMLReceiptingRulesUI_02
Scenario Outline: Validate user is able to update Defect Information
Given am a XML Reviewer on Transformation Review page."<url>","<publicationID>"
When user clicks on existing defect and updates "<defect Desc>","<priority>","<status>","<remarks>" information and save the page.
Then updated "<defect Desc>","<priority>","<status>","<remarks>" data should be saved into defect_details table.
Examples:
|url             |defect Desc              |priority|status      |remarks                       |publicationID|
|xml-setup-review|Test desc updated        |High    |InProgress  |  status changed to inprogress|91604787779  |


@ValidateXMLReceiptingRulesUI_03
Scenario Outline: Validate user is able to change status to complete
Given am a XML Reviewer on Transformation Review page."<url>","<publicationID>"
When user changed status to complete and updates "<defect Desc>","<priority>","<status>","<remarks>" information and save the page.
Then updated "<defect Desc>","<priority>","<status>","<remarks>" data should be saved into defect_details table.
Examples:
|url             |defect Desc              |priority|status      |remarks                       |publicationID|
|xml-setup-review|Test desc second update  |Low     |Complete    |  status changed to complete  |91604787779  |
