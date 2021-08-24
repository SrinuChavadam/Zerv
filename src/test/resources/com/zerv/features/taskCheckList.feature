@taskCheckListAPI
Feature: Validate the records are created/Stored in task checklist table.

@taskCheckListAPI_01
Scenario Outline:Validate whether taskchecklist information is stored/updated into TASK_CHECKLIST table
Given Am a content acqisition Team member_taskchecklist "<TCEndpoint>"
When user provides publicationID,taskchecklistID "<taskChecklistLookupId>".
Then User should get a successful response_taskChecklist
And new record should be created in TASK_CHECKLIST table for taskChecklistLookupId "<taskChecklistLookupId>"
Examples:
|TCEndpoint        |taskChecklistLookupId|
|Task_Checklist    |1,2,3                |

@taskCheckListAPI_02
Scenario Outline:Validate error code 400 when sending request with existing publicationID
Given Am a content acqisition Team member_taskchecklist "<TCEndpoint>"
When user provides publicationID which is already existed,taskchecklistID "<taskChecklistLookupId>".
Then User should get error message like publicationID is already present.
Examples:
|TCEndpoint        |taskChecklistLookupId|
|Task_Checklist    |1                    |

@taskCheckListAPI_03
Scenario Outline:Validate error message when sending request with publicationID which is not present in acquisition info table.
Given Am a content acqisition Team member_taskchecklist "<TCEndpoint>"
When user provides publicationID which is not existed in acquisition info table "<publicationID>",taskchecklistID "<taskChecklistLookupId>".
Then User should get error message like publicationid is not present in acquisition information.
Examples:
|TCEndpoint        |publicationID|taskChecklistLookupId|
|Task_Checklist    |    987      |1                    |

@taskCheckListAPI_04
Scenario Outline:Validate error message when sending request with invalid publicationID.
Given Am a content acqisition Team member_taskchecklist "<TCEndpoint>"
When user provides invalid publicationID "<publicationID>",taskchecklistID "<taskChecklistLookupId>".
Then User should get error message like publicationid is invalid.
Examples:
|TCEndpoint        |publicationID   |taskChecklistLookupId|
|Task_Checklist    |    987abc      |1                    |

@taskCheckListAPI_05
Scenario Outline:validate status code 403 when sending request without mandatory Roles
Given Am not a content acqisition Team member_taskchecklist "<TCEndpoint>"
When user provides publicationID,taskchecklistID "<taskChecklistLookupId>".
Then the User should get Error Response_taskChecklist
Examples:
|TCEndpoint       |taskChecklistLookupId|
|Task_Checklist   |1                    |