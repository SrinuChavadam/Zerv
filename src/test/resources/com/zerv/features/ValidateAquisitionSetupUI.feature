@Validate_AcquisitionSetupFlow
Feature: Validate the Acquisition setup UI screens.

@Validate_Acquisition_Setup_1
Scenario Outline:Validate that Acquisition setup flow works properly for Publisher Category as NLM and Receipt Method as FTP
 
Given am on content acquisition user "<role>" ,"<PublicationId>" 
When user provides Format Info data "<FormatInfo_Table>" and table "<FormatInfo_Data>".
When user provides Acquisition Info data "<AcquisitionInfo_Table>" and table "<AcquisitionInfo_Data>".
When user provides Task Check List data "<TaskChecklist_Table>" and table "<TaskChecklist_Data>".
And The system should save the selected values into the Database
Examples:
|role               |PublicationId|FormatInfo_Table  |FormatInfo_Data|AcquisitionInfo_Table|AcquisitionInfo_Data|TaskChecklist_Table|TaskChecklist_Data|
|acquisition-setup  |91604787572  |FormatInfo        |Data1          |AcquisitionInfo      | Data1              |TaskChecklist      |Data1             |


@Validate_Acquisition_Setup_2
Scenario Outline:Validate that Acquisition setup flow works properly for Publisher Category as Non NLM and Receipt Method as Web
 
Given am on content acquisition user "<role>" ,"<PublicationId>" 
When user provides Format Info data "<FormatInfo_Table>" and table "<FormatInfo_Data>".
When user provides Acquisition Info data "<AcquisitionInfo_Table>" and table "<AcquisitionInfo_Data>" for Receipt Method as Web.
When user provides Task Check List data "<TaskChecklist_Table>" and table "<TaskChecklist_Data>".
And The system should save the selected values into the Database
Examples:
|role               |PublicationId|FormatInfo_Table  |FormatInfo_Data|AcquisitionInfo_Table|AcquisitionInfo_Data|TaskChecklist_Table|TaskChecklist_Data|
|acquisition-setup  |91604787571  |FormatInfo        |Data2          |AcquisitionInfo      | Data2              |TaskChecklist      |Data2             |


@Validate_Acquisition_Setup_3
Scenario Outline:Validate that Acquisition setup flow works properly for Publisher Category as Non NLM and Receipt Method as Web and transmission Type as Pull
 
Given am on content acquisition user "<role>" ,"<PublicationId>" 
When user provides Format Info data "<FormatInfo_Table>" and table "<FormatInfo_Data>".
When user provides Acquisition Info data "<AcquisitionInfo_Table>" and table "<AcquisitionInfo_Data>" for Receipt Method as Web and transmission Type as Pull.
When user provides Task Check List data "<TaskChecklist_Table>" and table "<TaskChecklist_Data>".
And The system should save the selected values into the Database
Examples:
|role               |PublicationId|FormatInfo_Table  |FormatInfo_Data|AcquisitionInfo_Table|AcquisitionInfo_Data|TaskChecklist_Table|TaskChecklist_Data|
|acquisition-setup  |91604787570  |FormatInfo        |Data3          |AcquisitionInfo      | Data3              |TaskChecklist      |Data2             |


@Validate_Acquisition_Setup_4
Scenario Outline:Validate that Acquisition setup flow works properly for Publisher Category as Non NLM and Receipt Method as FTP and transmission Type as Pull
 
Given am on content acquisition user "<role>" ,"<PublicationId>" 
When user provides Format Info data "<FormatInfo_Table>" and table "<FormatInfo_Data>".
When user provides Acquisition Info data "<AcquisitionInfo_Table>" and table "<AcquisitionInfo_Data>" for Receipt Method as FTP and transmission Type as Pull.
When user provides Task Check List data "<TaskChecklist_Table>" and table "<TaskChecklist_Data>".
And The system should save the selected values into the Database
Examples:
|role               |PublicationId|FormatInfo_Table  |FormatInfo_Data|AcquisitionInfo_Table|AcquisitionInfo_Data|TaskChecklist_Table|TaskChecklist_Data|
|acquisition-setup  |91604787569  |FormatInfo        |Data3          |AcquisitionInfo      | Data4              |TaskChecklist      |Data2             |


@Validate_Acquisition_Setup_5
Scenario Outline:Validate that Acquisition setup flow works properly for Publisher Category as Non NLM and Receipt Method as FTP and transmission Type as Pull
 
Given am on content acquisition user "<role>" ,"<PublicationId>" 
When user provides Format Info data "<FormatInfo_Table>" and table "<FormatInfo_Data>".
When user provides Acquisition Info data "<AcquisitionInfo_Table>" and table "<AcquisitionInfo_Data>" for Receipt Method as Email.
When user provides Task Check List data "<TaskChecklist_Table>" and table "<TaskChecklist_Data>".
And The system should save the selected values into the Database
Examples:
|role               |PublicationId|FormatInfo_Table  |FormatInfo_Data|AcquisitionInfo_Table|AcquisitionInfo_Data|TaskChecklist_Table|TaskChecklist_Data|
|acquisition-setup  |91604787568  |FormatInfo        |Data3          |AcquisitionInfo      | Data5              |TaskChecklist      |Data2             |




@Validate_Acquisition_Setup_6
Scenario Outline:Validate that Acquisition setup flow works properly for Format Type to be Processed as PDF Only and Receipt Method as FTP and transmission Type as Push
 
Given am on content acquisition user "<role>" ,"<PublicationId>" 
When user provides Format Info data "<FormatInfo_Table>" and table "<FormatInfo_Data>".
When user provides Acquisition Info data "<AcquisitionInfo_Table>" and table "<AcquisitionInfo_Data>" for Format Type to be Processed as PDF Only and Receipt Method as FTP and transmission Type as Push.
When user provides Task Check List data "<TaskChecklist_Table>" and table "<TaskChecklist_Data>".
And The system should save the selected values into the Database
Examples:
|role               |PublicationId|FormatInfo_Table  |FormatInfo_Data|AcquisitionInfo_Table|AcquisitionInfo_Data|TaskChecklist_Table|TaskChecklist_Data|
|acquisition-setup  |91604787533  |FormatInfo        |Data4          |AcquisitionInfo      | Data6              |TaskChecklist      |Data2             |
|acquisition-setup  |91604787532  |FormatInfo        |Data5          |AcquisitionInfo      | Data6              |TaskChecklist      |Data2             |




