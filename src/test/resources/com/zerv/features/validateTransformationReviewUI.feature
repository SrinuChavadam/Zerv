@ValidateTransformationReviewFeedbackUI
Feature: Validate the XML Setup XML Review UI.

@ValidateTransformationReviewFeedbackUI_01
Scenario Outline: Validate able to change the defect status to complete and pass.
Given am a XML Reviewer on Transformation Review feedback page."<url>","<publicationID>"
When User updates defect status from open or inprogress to completed, attached ten files "<data>","<table>" and save the page.
Then Defects data should be saved into defect_details table.
Examples:
|url             |publicationID|data      |table  |
|xml-cps-setup   |91604787778  |  Data1   |Review | 

@ValidateTransformationReviewFeedbackUI_02
Scenario Outline: Validate able to delete the attached file.
Given am a XML Reviewer on Transformation Review feedback page."<url>","<publicationID>"
When User uploaded any file and clicked on delete icon "<data>","<table>".
Then file name should be removed from attachments_details table.
Examples:
|url             |publicationID|data      |table  |
|xml-cps-setup   |91604787778  |  Data2   |Review | 