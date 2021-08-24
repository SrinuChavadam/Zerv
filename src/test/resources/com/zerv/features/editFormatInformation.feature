@editFormatInformation
Feature: Validate the records are Edited/Updated in Format_Information table.
    
@editFormatInformation_01
Scenario Outline: Validate whether format information is edit/Modify into "AVAILABLE_FORMAT" ,"PROCESSED_FORMAT" , & "CONTENT_TYPE" Tables
Given am a Format_Information Team member "<endpoint>" for edit
When user provides a "<publicationID>" along with "<processedLookupId>" and "<contentTypeLookupId>" and "<availableLookupId>" values
Then user should get a successful response for edit Format_Information
And new record should be modified in Format_Information table and validate aganist Database along with "<processedLookupId>" and "<contentTypeLookupId>" and "<availableLookupId>" values
Examples:
|endpoint            |publicationID|processedLookupId|contentTypeLookupId|availableLookupId|
|Format_Information  | 91604732271  |1                |1               |1,2            | 
|Format_Information  | 91604732271  |4               |3                |1,2,3,4,5,6,7,8| 
|Format_Information  | 91604732271  |3                |2               |5,6,7,8    | 


@editFormatInformation_02
Scenario Outline: Validate whether format information is edit/Modify into "AVAILABLE_FORMAT" ,"PROCESSED_FORMAT" , & "CONTENT_TYPE" Tables
Given am a Format_Information Team member "<endpoint>" for edit
When user provides a "<publicationID>" along with "<processedLookupId>" and "<contentTypeLookupId>" and "<availableLookupId>" values
Then user should get error response for edit Format_Information

Examples:
|endpoint            |publicationID|processedLookupId|contentTypeLookupId|availableLookupId|
|Format_Information  | 91604732271  |5                |5               |9            | 

