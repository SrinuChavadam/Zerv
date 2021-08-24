@PubobTaskInbox
Feature: Publication Onboarding - Task Inbox 


@taskinbox_01
Scenario Outline: Validate the Task inbox navigation
Given I navigate to Task Inbox.
When  I select publication onboarding option from application dropdown "<application>"
When  I search for Tasks based on Tasktype "<tasktype>".
When  I get the Publication id and double cick on Task
Then  I should be navigated to Acquisition setup page
When user provides Format Info data "<FormatInfo_Table>" and table "<FormatInfo_Data>".
When user provides Acquisition Info data "<AcquisitionInfo_Table>" and table "<AcquisitionInfo_Data>".
When user provides Task Check List data "<TaskChecklist_Table>" and table "<TaskChecklist_Data>".
#And The system should save the selected values into the Database
Examples:
|application           |tasktype         |FormatInfo_Table  |FormatInfo_Data|AcquisitionInfo_Table|AcquisitionInfo_Data|TaskChecklist_Table|TaskChecklist_Data|
|publication onboarding|acquisition.setup|FormatInfo        |Data1          |AcquisitionInfo      | Data1              |TaskChecklist      |Data1             |

@taskinbox_02
Scenario Outline: Validate the Task inbox navigation
Given I navigate to Task Inbox.
When  I select publication onboarding option from application dropdown "<application>"
When  I search for Tasks based on Tasktype "<tasktype>".
When  I get the Publication id and double cick on Task
Then  I should be navigated to Acquisition setup page
When user provides Format Info data "<FormatInfo_Table>" and table "<FormatInfo_Data>".
When user provides Acquisition Info data "<AcquisitionInfo_Table>" and table "<AcquisitionInfo_Data>".
When user provides Task Check List data "<TaskChecklist_Table>" and table "<TaskChecklist_Data>".
#And The system should save the selected values into the Database
Examples:
|application           |tasktype         |FormatInfo_Table  |FormatInfo_Data|AcquisitionInfo_Table|AcquisitionInfo_Data|TaskChecklist_Table|TaskChecklist_Data|
|publication onboarding|acquisition.setup|FormatInfo        |Data1          |AcquisitionInfo      | Data1              |TaskChecklist      |Data1             |
