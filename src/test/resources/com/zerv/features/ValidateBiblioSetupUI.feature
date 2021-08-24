@Validate_BibilioSetupFlow
Feature: Validate the Bibliographic Policy Evaluation setup UI screens.

@Validate_Bibliographic_Setup_1
Scenario Outline:  Validate whether user can perform Bibliographic Policy Evaluation, when user clicks on Pass button
 
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>".
Then user clicks pass button
Then system should be saved into database

Examples:
|role               |PublicationId|Bibilio_Table      |Bibilio_Data|
|biblio-evaluation  |91604680124  |BibilioEvaluation  |Data1      |
|biblio-evaluation  |91604679575  |BibilioEvaluation  |Data2      |
|biblio-evaluation  |91604732245  |BibilioEvaluation  |Data3      |
|biblio-evaluation  |91604732251  |BibilioEvaluation  |Data4      |

@Validate_Bibliographic_Setup_2
Scenario Outline:  Validate whether user can perform Bibliographic Policy Evaluation, when user clicks on Reject button
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>".
Then user clicks Reject button
Then system should not saved into database
Examples:
|role               |PublicationId|Bibilio_Table      |Bibilio_Data|
|biblio-evaluation  |91604679575  |BibilioEvaluation  |Data1      |
