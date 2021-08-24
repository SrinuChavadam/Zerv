@CreateFormatInformation
Feature: Validate the records are created in Format_Information table.
    
@CreateFormatInformation_01
Scenario Outline: Validate whether format information is stored into "AVAILABLE_FORMAT" ,"PROCESSED_FORMAT" , & "CONTENT_TYPE" Tables
Given am a Format_Information Team member "<endpoint>"
When user provides a publicationID along with "<processedLookupId>" and "<contentTypeLookupId>" and "<availableLookupId>" values
Then user should get a successful response for Format_Information
And new record should be created in Format_Information table and validate aganist Database along with "<processedLookupId>" and "<contentTypeLookupId>" and "<availableLookupId>" values
Examples:
|endpoint             |processedLookupId |contentTypeLookupId|availableLookupId|
|Format_Information   | 1                |1                  |1                | 
|Format_Information  | 4                |3                  |8                | 
|Format_Information  | 3                |2                  |7                | 
|Format_Information  | 2                |1                  |6                |


@CreateFormatInformation_02
Scenario Outline: Validate whether format information should not stored/updated into "AVAILABLE_FORMAT" ,"PROCESSED_FORMAT" , & "CONTENT_TYPE" Tables for Invalid values
Given am a Format_Information Team member "<endpoint>"
When user provides a publicationID along with "<processedLookupId>" and "<contentTypeLookupId>" and "<availableLookupId>" values
Then user should get a Error response for Format_Information

Examples:
|endpoint            |processedLookupId|contentTypeLookupId|availableLookupId|
|Format_Information  |5                |4               |9                  | 
|Format_Information  |3                |9               |8                  | 