@CreateAcquisitionInformation
Feature: Validate the records are created/Stored in acquisition_Information table.
    
@CreateAcquisitionInformation_01
Scenario Outline: Validate whether acquisition information is stored/updated into "ACQUISITION_INFO" Table
Given am a Acquisition_Information Team member "<endpoint>"
When user provides a publicationID along with "<transmissionTypeLookupId>" and "<receiptMethodLookupId>" and "<categoryLookupId>" and "<ftpTypeLookupId>" and "<filePriorityLookupId>" and "<contentSizeLookupId>" and "<browserLookupId>" and "<triggerLookupId>"values
Then user should get a successful response for Acquisition_Information
And new record should be created in Acquisition_Information table and validate aganist Database along with"<transmissionTypeLookupId>" and "<receiptMethodLookupId>" and "<categoryLookupId>" and "<ftpTypeLookupId>" and "<filePriorityLookupId>" and "<contentSizeLookupId>" and "<browserLookupId>" and "<triggerLookupId>"values
Examples:
|endpoint                  |transmissionTypeLookupId |receiptMethodLookupId|categoryLookupId|ftpTypeLookupId|filePriorityLookupId|contentSizeLookupId|browserLookupId|triggerLookupId|
|Acquisition_Information   | 1                       |1                    |1               | 1             |1                   |1                  |1              |1              |
|Acquisition_Information   | 1                       |2                    |2               | 2             |2                   |2                  |2              |2              |

@CreateAcquisitionInformation_02
Scenario Outline: Validate whether acquisition information should not stored/updated into "ACQUISITION_INFO" Table
Given am a Acquisition_Information Team member "<endpoint>"
When user provides a publicationID along with "<transmissionTypeLookupId>" and "<receiptMethodLookupId>" and "<categoryLookupId>" and "<ftpTypeLookupId>" and "<filePriorityLookupId>" and "<contentSizeLookupId>" and "<browserLookupId>" and "<triggerLookupId>"values
Then user should get a error response for Acquisition_Information

Examples:
|endpoint                  |transmissionTypeLookupId |receiptMethodLookupId|categoryLookupId|ftpTypeLookupId|filePriorityLookupId|contentSizeLookupId|browserLookupId|triggerLookupId|
|Acquisition_Information   | 9                       |9                    |9               | 9             |9                   |1                  |1              |1              |
