@editTechnicalEvaluationAPI
Feature: Validate the records are edited/Modified in format_evaluation table.

@editTechnicalEvaluationAPI_01
Scenario Outline:Validate whether formatEvaluation information is getting updated in FORMAT_EVALUATION table.
Given Am a technical evaluator_editTechnicalEvaluation "<Endpoint>".
When user sends data "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" for publicationId.
Then User should get a successful response_editTechnicalEvaluation.
And data should be updated in FORMAT_EVALUATION table with "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" data for publicationId
Examples:
|Endpoint        |attachments|fileAccessLookupId|notes    |passwordProvided|pdfSecurityLookupId|processedAsPrint|timeLinessLookupId|
|Tech_Evaluation |test123    |2                 |testnotes|true            |1                  |true            |1                 |
|Tech_Evaluation |test123    |1                 |testnotes|true            |2                  |true            |2                 |
|Tech_Evaluation |test123    |3                 |testnotes|true            |2                  |true            |3                 |

@editTechnicalEvaluationAPI_02
Scenario Outline:Validate the error message when user sends updaterequest with invalid data.
Given Am a technical evaluator_editTechnicalEvaluation "<Endpoint>".
When user provides invalid Data "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" for publicationId.
Then User should get EntityNotFoundException_500_editTechnicalEvaluation.
Examples:
|Endpoint        |attachments|fileAccessLookupId|notes    |passwordProvided|pdfSecurityLookupId|processedAsPrint|timeLinessLookupId|
|Tech_Evaluation |test123    |9                 |testnotes|true            |2                  |true            |2                 |
|Tech_Evaluation |test123    |1                 |testnotes|true            |9                  |true            |2                 |
|Tech_Evaluation |test123    |2                 |testnotes|true            |2                  |true            |9                 |

@editTechnicalEvaluationAPI_03
Scenario Outline:Validate the error message when user sends updaterequest with invalid pblicationId.
Given Am a technical evaluator_editTechnicalEvaluation "<Endpoint>".
When user provides Data "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" for invalid publicationId "<publicationId>".
Then User should get type issue_400_editTechnicalEvaluation.
Examples:
|Endpoint        |attachments|fileAccessLookupId|notes    |passwordProvided|pdfSecurityLookupId|processedAsPrint|timeLinessLookupId|publicationId|
|Tech_Evaluation |test123    |1                 |testnotes|true            |2                  |true            |2                 |12133abc     |

@editTechnicalEvaluationAPI_04
Scenario Outline:Validate the error message when user sends updaterequest with invalid Roles.
Given Am not a technical evaluator_editTechnicalEvaluation "<Endpoint>".
When user sends data "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" for publicationId.
Then User should get access denied error response_403_editTechnicalEvaluation.
Examples:
|Endpoint        |attachments|fileAccessLookupId|notes    |passwordProvided|pdfSecurityLookupId|processedAsPrint|timeLinessLookupId|
|Tech_Evaluation |test123    |1                 |testnotes|true            |2                  |true            |2                 |
