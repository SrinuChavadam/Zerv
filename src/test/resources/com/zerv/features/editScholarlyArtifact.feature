@EditScholarlyArtifactInformation
Feature: Validate the records are edited/Modified in scholarly_classification table.

@EditScholarlyArtifact_01
Scenario Outline: Verify that able to edit ScholarlyArtifact information in "scholarly_classification" Table.
Given am a Bibilo Evaluator "<endPoint>".
When user provides scholarly artifact information "<clarivateDocType>","<providerDocType>" for PublicationId "<PublicationId>".
Then the user should get a success response.
And the system should updated the values in scholarly_classification table "<clarivateDocType>","<providerDocType>" for the given PublicationId "<PublicationId>".
Examples:
|endPoint         |clarivateDocType|providerDocType                                    |PublicationId|
|scholarlyArtifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|98978978973|
|scholarlyArtifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|98978978973|
|scholarlyArtifact|Reviews         | Review,Review Article,Review Essay                |98978978973|
|scholarlyArtifact|Book Review     | Review,Book Review,In Memoriam                    |98978978973|

@EditScholarlyArtifact_02
Scenario Outline: Verify that able to edit ScholarlyArtifact information in "scholarly_classification" Table for invalid PublicationId.
Given am a Bibilo Evaluator "<endPoint>".
When user provides scholarly artifact information "<clarivateDocType>","<providerDocType>" for PublicationId "<PublicationId>".
Then the user should get a error response for invalid PublicationId.

Examples:
|endPoint         |clarivateDocType|providerDocType                                    |PublicationId|
|scholarlyArtifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|ggbbjkbjjh|

@EditScholarlyArtifact_03
Scenario Outline: Verify that able to edit ScholarlyArtifact information in "scholarly_classification" Table for Not present PublicationId.
Given am a Bibilo Evaluator "<endPoint>".
When user provides scholarly artifact information "<clarivateDocType>","<providerDocType>" for PublicationId "<PublicationId>".
Then the user should get a error response for Not present PublicationId.

Examples:
|endPoint         |clarivateDocType|providerDocType                                    |PublicationId|
|scholarlyArtifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|534534636333|

@EditScholarlyArtifact_04
Scenario Outline: Verify that able to edit ScholarlyArtifact information in "scholarly_classification" for invalid roles.
Given am a Bibilo Evaluator with invalid role "<endPoint>".
When user provides scholarly artifact information "<clarivateDocType>","<providerDocType>" for PublicationId "<PublicationId>".
Then the user should get a error response for invalid role.

Examples:
|endPoint         |clarivateDocType|providerDocType                                    |PublicationId|
|scholarlyArtifact|Letter          | Letter,Comment,Letter to the Editor,Commentarytrue|534534636333|