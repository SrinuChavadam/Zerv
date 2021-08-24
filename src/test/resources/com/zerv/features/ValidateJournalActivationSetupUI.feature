@Validate_JournalActivationReviewSetupFlow
Feature: Validate the Journal Activation Review Process setup UI screens.

@Validate_JournalActivationReview_Setup_1
Scenario Outline:  Validate the Journal Activation Reject Review Process setup UI Screen.

Given am on content journalactivation user "<role>" ,"<PublicationId>" 
When user clicks on Review Process left hand submenu and Journal Activation Reject popup displayed
When user provides JournalActivationReject Info  data "<JournalReject_Table>" and table "<JournalReject_Data>".
When user clicks on Reject button 
Then system should be saved into database value

Examples:
|role               |PublicationId|JournalReject_Table    |JournalReject_Data|
|journal-activation |91604787572  |JournalReject          |Data1             |


@Validate_JournalActivationReview_Setup_2
Scenario Outline:  Validate the Journal Activation Reject popup radion option

Given am on content journalactivation user "<role>" ,"<PublicationId>" 
When user clicks on Review Process left hand submenu and Journal Activation Reject popup displayed
Then user checks the radio button options in  Journal Activation Reject Popup


Examples:
|role               |PublicationId|JournalReject_Table    |JournalReject_Data|
|journal-activation |91604787572  |JournalReject          |Data1             |

