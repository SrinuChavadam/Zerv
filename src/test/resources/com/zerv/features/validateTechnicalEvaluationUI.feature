@validateTechnicalEvaluationUI
Feature: validate the technical evalution flow.
@validateTechnicalEvaluationUI_01
Scenario Outline: Validate whether technical evaluation, content packaging information and transform instuctions data getting saved into "format_evaluation","content_packaging_info","file_name_convention" and "folder_name_convention" tables.
Given am a technicalEvaluator "<role>" ,"<PublicationId>".
When user provides Format Evaluation data "<data>" and table "<table>".
And user provides content package Information "<contentData>" and table "<contentTable>".
And user provides transform/ignore files instruction "<instructions>" and table "<instructionstable>"
Then data should be saveb into Database tables.
Examples:
|role             |PublicationId|data     |table              |contentData|contentTable|instructions|instructionstable|
|format-evaluation| 91604787954 |Data1    |FormatEvaluation   |Data1      |Content     |Data1      |FilesInstructions|

@validateTechnicalEvaluationUI_02
Scenario Outline: Validate whether technical evaluation, content packaging information and transform instuctions data getting saved into "format_evaluation","content_packaging_info","file_name_convention" and "folder_name_convention" tables.
Given am a technicalEvaluator "<role>" ,"<PublicationId>".
When user provides Format Evaluation data "<data>" and table "<table>".
And user provides content package Information "<contentData>" and table "<contentTable>".
And user provides transform/ignore files instruction "<instructions>" and table "<instructionstable>"
Then data should be saveb into Database tables.
Examples:
|role             |PublicationId|data     |table              |contentData|contentTable|instructions|instructionstable|
|format-evaluation| 91604787950 |Data2    |FormatEvaluation   |Data2      |Content     |Data2      |FilesInstructions|

@validateTechnicalEvaluationUI_03
Scenario Outline: Validate whether technical evaluation, content packaging information and transform instuctions data getting saved into "format_evaluation","content_packaging_info","file_name_convention" and "folder_name_convention" tables.
Given am a technicalEvaluator "<role>" ,"<PublicationId>".
When user provides Format Evaluation data "<data>" and table "<table>".
And user provides content package Information "<contentData>" and table "<contentTable>".
And user provides transform/ignore files instruction "<instructions>" and table "<instructionstable>"
Then data should be saveb into Database tables.
Examples:
|role             |PublicationId|data     |table              |contentData|contentTable|instructions|instructionstable|
|format-evaluation| 91604787949 |Data3    |FormatEvaluation   |Data3      |Content     |Data3      |FilesInstructions|

@validateTechnicalEvaluationUI_04
Scenario Outline: Validate whether technical evaluation, content packaging information and transform instuctions data getting saved into "format_evaluation","content_packaging_info","file_name_convention" and "folder_name_convention" tables.
Given am a technicalEvaluator "<role>" ,"<PublicationId>".
When user provides Format Evaluation data "<data>" and table "<table>".
And user provides content package Information "<contentData>" and table "<contentTable>".
And user provides transform/ignore files instruction "<instructions>" and table "<instructionstable>"
Then data should be saveb into Database tables.
Examples:
|role             |PublicationId|data     |table              |contentData|contentTable|instructions|instructionstable|
|format-evaluation| 91604787948 |Data4    |FormatEvaluation   |Data4       |Content     |Data4      |FilesInstructions|

@validateTechnicalEvaluationUI_05
Scenario Outline: Validate whether technical evaluation, content packaging information and transform instuctions data getting saved into "format_evaluation","content_packaging_info","file_name_convention" and "folder_name_convention" tables.
Given am a technicalEvaluator "<role>" ,"<PublicationId>".
When user provides Format Evaluation data "<data>" and table "<table>".
And user provides content package Information "<contentData>" and table "<contentTable>".
And user provides transform/ignore files instruction "<instructions>" and table "<instructionstable>"
Then data should be saveb into Database tables.
Examples:
|role             |PublicationId|data     |table              |contentData|contentTable|instructions|instructionstable|
|format-evaluation| 91604787947 |Data5    |FormatEvaluation   |Data5       |Content     |Data5      |FilesInstructions|

@validateTechnicalEvaluationUI_06
Scenario Outline: Validate whether technical evaluation, content packaging information and transform instuctions data getting saved into "format_evaluation","content_packaging_info","file_name_convention" and "folder_name_convention" tables.
Given am a technicalEvaluator "<role>" ,"<PublicationId>".
When user provides Format Evaluation data "<data>" and table "<table>".
And user provides content package Information "<contentData>" and table "<contentTable>".
And user provides transform/ignore files instruction "<instructions>" and table "<instructionstable>"
Then data should be saveb into Database tables.
Examples:
|role             |PublicationId|data     |table              |contentData|contentTable|instructions|instructionstable|
|format-evaluation| 91604787946 |Data6    |FormatEvaluation   |Data6       |Content     |Data6      |FilesInstructions|