@editTaskChecklistAPI
Feature: Validate the records are edited/Modified in task checklist table.

@Edittaskchecklist_01
Scenario Outline: Verify that able to edit taskLookupId for publication id in Task_Checklist table.
Given am a content acquisition user_editTaskChecklist "<TCEndpoint>".
When user sent edit request with publication id,taskLookupId "<taskLookupId>".
Then the user should get success response_editTaskChecklist.
And the system should save the modified taskLookupId "<taskLookupId>" into the TASK_CHECKLIST table.
Examples:
|TCEndpoint    |taskLookupId|
|Task_Checklist| 2          |
|Task_Checklist| 3          |
|Task_Checklist| 1          |
|Task_Checklist| 2,3        |
|Task_Checklist| 1,2        |    
|Task_Checklist|3           |     

@editTaskChecklistAPI_02
Scenario Outline: Verify the error message when sending edit request with invalid publisherId.
Given am a content acquisition user_editTaskChecklist "<TCEndpoint>".
When user sent edit request with invalid publicationID "<publicationid>" and taskLookupId "<taskLookupId>".
Then the user should get error response code as 400_editTaskChecklist.

Examples:
|TCEndpoint       |publicationid   |taskLookupId|
|Task_Checklist   |916047322319999 |  3     |

@editTaskChecklistAPI_03
Scenario Outline: Verify the error message when sending edit request with invalid taskLookupId.
Given am a content acquisition user_editTaskChecklist "<TCEndpoint>".
When user sent edit request with publication id,invalid taskLookupId "<tasktLookupId>".
Then the user should get error response code 500_editTaskChecklist.

Examples:
|TCEndpoint     |tasktLookupId|
|Task_Checklist |  9        |

@editTaskChecklistAPI_04
Scenario Outline: Verify the error message when sending edit request with invalid Roles.
Given am not a content acquisition user_editTaskChecklist "<TCEndpoint>".
When user sent edit request with publication id,taskLookupId "<taskLookupId>".
Then the user should get error responseCode as 403_editTaskChecklist.

Examples:
|TCEndpoint     |taskLookupId|
|Task_Checklist |  3          |