@ValidateCPSInputFormUI
Feature: Validate the CPS Input Form UI.

@ValidateCPSInputFormUI_01
Scenario Outline: Validate whether receipt url,username,password and additional_info is stored into "cps_information" Table.
Given am a cps user "<role>" ,"<PublicationId>".
When user provides CPS information "<receipt_url>","<username>","<password>","<comment>".
Then CPS information "<receipt_url>","<username>","<password>","<comment>" should be saved into cps_information table.
Examples:
|role                 |PublicationId|receipt_url              |username|password    |comment         |
|cps-acquisition-setup| 91604678855 |ftp://pubonboarding/test |User1   | password123|cps test comment|
