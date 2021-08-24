@addTechnicalEvaluationAPI
Feature: Validate the records are created/Stored in format_evaluation table.

@addTechnicalEvaluationAPI_01
Scenario Outline:Validate whether formatEvaluation information is stored into FORMAT_EVALUATION table.
Given Am a technical evaluator_addTechnicalEvaluation "<Endpoint>".
When user provides "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" for publicationId.
Then User should get a successful response_addTechnicalEvaluation.
And new record should be created in FORMAT_EVALUATION table with "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" data for publicationId
Examples:
|Endpoint        |attachments|fileAccessLookupId|notes    |passwordProvided|pdfSecurityLookupId|processedAsPrint|timeLinessLookupId|
|Tech_Evaluation |test123    |1                 |testnotes|true            |2                  |true            |2                 |

@addTechnicalEvaluationAPI_02
Scenario Outline:Validate the error message when user sends request with invalid data.
Given Am a technical evaluator_addTechnicalEvaluation "<Endpoint>".
When user provides invalid data "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" for publicationId.
Then User should get EntityNotFoundException_500_addTechnicalEvaluation.
Examples:
|Endpoint        |attachments|fileAccessLookupId|notes    |passwordProvided|pdfSecurityLookupId|processedAsPrint|timeLinessLookupId|
|Tech_Evaluation |test123    |9                 |testnotes|true            |2                  |true            |2                 |
|Tech_Evaluation |test123    |1                 |testnotes|true            |9                  |true            |2                 |
|Tech_Evaluation |test123    |2                 |testnotes|true            |2                  |true            |9                 |

@addTechnicalEvaluationAPI_03
Scenario Outline:Validate the error message when user sends request with invalid pblicationId.
Given Am a technical evaluator_addTechnicalEvaluation "<Endpoint>".
When user provides data "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" for invalid publicationId "<publicationId>".
Then User should get type issue_400_addTechnicalEvaluation.
Examples:
|Endpoint        |attachments|fileAccessLookupId|notes    |passwordProvided|pdfSecurityLookupId|processedAsPrint|timeLinessLookupId|publicationId|
|Tech_Evaluation |test123    |1                 |testnotes|true            |2                  |true            |2                 |12133abc     |

@addTechnicalEvaluationAPI_04
Scenario Outline:Validate the error message when user sends request with invalid Roles.
Given Am not a technical evaluator_addTechnicalEvaluation "<Endpoint>".
When user provides "<attachments>","<fileAccessLookupId>","<notes>","<password>","<passwordProvided>","<pdfSecurityLookupId>","<processedAsPrint>","<timeLinessLookupId>" for publicationId.
Then User should get access denied error response_403_addTechnicalEvaluation.
Examples:
|Endpoint        |attachments|fileAccessLookupId|notes    |passwordProvided|pdfSecurityLookupId|processedAsPrint|timeLinessLookupId|
|Tech_Evaluation |test123    |1                 |testnotes|true            |2                  |true            |2                 |

