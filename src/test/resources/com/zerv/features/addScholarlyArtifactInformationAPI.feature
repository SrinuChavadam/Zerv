@AddScholarlyArtifactInformationAPI
Feature: Validate the records are created in scholarly_classification table.

@AddScholarlyArtifact_01
Scenario Outline: Verify that able to add ScholarlyArtifact information in "scholarly_classification" Table.
Given am a Bibilo Evaluators "<endPoint>".
When user provides scholarly artifact information "<clarivateDocType>","<providerDocType>" for PublicationId.
Then the user should get a successfull response.
And the system should update the values in scholarly_classification table for "<clarivateDocType>","<providerDocType>" and PublicationId.
Examples:
|endPoint          |clarivateDocType|providerDocType                                    |
|Scholarly_Artifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|
|Scholarly_Artifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|
|Scholarly_Artifact|Reviews         | Review,Review Article,Review Essay                | 
|Scholarly_Artifact|Book Review     | Review,Book Review,In Memoriam                    | 

@AddScholarlyArtifact_02
Scenario Outline: Verify the error message when sends invalid request and data is not saved into "scholarly_classification" Table.
Given am a Bibilo Evaluators "<endPoint>".
When user provides scholarly artifact information "<clarivateDocType>","<providerDocType>" for "<PublicationId>".
Then the user should get error message like invalid format.
Examples:
|endPoint          |clarivateDocType|providerDocType                                    |PublicationId|
|Scholarly_Artifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|9188992992abc|

@AddScholarlyArtifact_03
Scenario Outline: Verify the error message when user sends create request with publication id which is not present in policy evaluation.
Given am a Bibilo Evaluators "<endPoint>".
When user sends request with publication id which is not present in policy evaluation "<clarivateDocType>","<providerDocType>" for "<PublicationId>".
Then the user should get errorMessage like publicationId should be present in Policy Evaluation.
Examples:
|endPoint          |clarivateDocType|providerDocType                                    |PublicationId      |
|Scholarly_Artifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|9188992992999999999|

@AddScholarlyArtifact_04
Scenario Outline: Verify the error message when user sends create request with publication id which is already existed.
Given am a Bibilo Evaluators "<endPoint>".
When user sends request with publication id which is already existed in db "<clarivateDocType>","<providerDocType>".
Then the user should get errorMessage like publicationId is already present.
Examples:
|endPoint          |clarivateDocType|providerDocType                                    |
|Scholarly_Artifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|


@AddBibiloGraphicPolicyEvaluation_05
Scenario Outline: Verify the error message when user sends request with invalid roles in header.
Given am not a Bibilo Evaluator "<endPoint>".
When user sends request with invalid role "<clarivateDocType>","<providerDocType>" for "<PublicationId>".
Then the user should get Access is denied errorMessage.
Examples:
|endPoint          |clarivateDocType|providerDocType                                    |PublicationId    |
|Scholarly_Artifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|9188992992999    |
