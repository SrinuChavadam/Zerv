@ViewGetFormatApi
Feature: Get process for viewLookup
   

@viewAvailableFormatInfo_1
Scenario Outline:Validate whether availableFormats information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the table column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|format_lookup_id|

@viewProcessedFormatInfo_2
Scenario Outline:Validate whether processedFormats information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the processed_format table column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|format_lookup_id|

@viewContentTypes_3
Scenario Outline:Validate whether contentType information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the content_type table column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|content_type_lookup_id|

@viewTaskCheckList_4
Scenario Outline:Validate whether taskChecklist information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the taskchecklist table column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|task_checklist_lookup_id|

@viewPublisherCategory_5
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table publisher_category column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|publisher_category|

@viewRecieptMethod_6
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table receipt_method column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|receipt_method|

@viewTransmissionType_6
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table transmission_type column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|transmission_type|

@viewFtpType_7
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table ftp_type column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|ftp_type|

@viewContent_Size_8
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table content_size column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|content_size|

@viewFilePriority_9
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table file_priority column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|file_priority|

@viewAquisitionTrigger_10
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table aquisition_trigger column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|aquisition_trigger|

@viewBrowserType_11
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table browser_type column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint       |ColumnName|
|91604732234  |View_Acquisition|browser_type|

@viewPublisherSchemaVersion_12
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table publisher_schema_version column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|publisher_schema_version|

@viewPublisherCode_13
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table publisher_code column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|publisher_code|

@viewSleepTime_14
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table sleep_time column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|resolution_time|

@viewData_Retention_15
Scenario Outline:Validate whether Acquistion  information is viewed as response
Given am a content acqisition team members for get method "<endPoint>"
When user provides existing publicationID "<PublicationId>" 
Then the user should get success responses
And the system should validate information aganist the database for the acquisition_info table data_retention column "<ColumnName>" and "<PublicationId>"
Examples:
|PublicationId|endPoint        |ColumnName|
|91604732234  |View_Acquisition|data_retention|

