@editAcquisitionInformation
Feature: Validate the records are edited/Modified in acquisition_Information table.
    
@editAcquisitionInformation_01
Scenario Outline: Validate whether acquisition information is edited/modified into "ACQUISITION_INFO" Table
Given am a Acquisition_Information Team member "<endpoint>" for edit
When user provides a publicationID "<publicationID>" along with "<transmissionTypeLookupId>" and "<receiptMethodLookupId>" and "<categoryLookupId>" and "<ftpTypeLookupId>" and "<filePriorityLookupId>" and "<contentSizeLookupId>" and "<browserLookupId>" and "<triggerLookupId>"values
Then user should get a successful response for Acquisition_Information edit
And record should be edited in Acquisition_Information table and validate aganist Database along with"<transmissionTypeLookupId>" and "<receiptMethodLookupId>" and "<categoryLookupId>" and "<ftpTypeLookupId>" and "<filePriorityLookupId>" and "<contentSizeLookupId>" and "<browserLookupId>" and "<triggerLookupId>"values
Examples:
|endpoint                  |publicationID|transmissionTypeLookupId |receiptMethodLookupId|categoryLookupId|ftpTypeLookupId|filePriorityLookupId|contentSizeLookupId|browserLookupId|triggerLookupId|
|Acquisition_Information   |91604732235  |1                       |1                    |1               | 1             |1                   |1                  |1              |1              |
|Acquisition_Information   |91604732235  | 1                       |2                    |2               | 2             |2                   |2                  |2              |2              |

@editAcquisitionInformation_02
Scenario Outline: Validate whether acquisition information should not edited/modified into "ACQUISITION_INFO" Table
Given am a Acquisition_Information Team member "<endpoint>" for edit
When user provides a publicationID "<publicationID>" along with "<transmissionTypeLookupId>" and "<receiptMethodLookupId>" and "<categoryLookupId>" and "<ftpTypeLookupId>" and "<filePriorityLookupId>" and "<contentSizeLookupId>" and "<browserLookupId>" and "<triggerLookupId>"values
Then user should get a error response for Acquisition_Information edit

Examples:
|endpoint                  |publicationID|transmissionTypeLookupId |receiptMethodLookupId|categoryLookupId|ftpTypeLookupId|filePriorityLookupId|contentSizeLookupId|browserLookupId|triggerLookupId|
|Acquisition_Information   |91604732235  |9                       |9                    |9               | 9             |9                   |1                  |1              |1              |
