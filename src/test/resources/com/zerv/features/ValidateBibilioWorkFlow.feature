@Validate_ScholarlyArtifactSetupFlow
Feature: Validate the Biblio Evaluation workflow setup UI screens.

 
@Validate_ScholarlyArtifact_Setup_1
Scenario Outline:  Validate whether BiBilio workflow working fine and able to select the default values of "Provider Document Type" field in Scholarly Artifact screen and also updated successfully in database
 
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>". 
When user provides Provided Document Type as Article,Letter and Editorial material data "<ScholarlyArtifact_Table>" and table "<ScholarlyArtifact_Data>".
When user provides input data for JobNameAuthorityFile "<JobNameAuthorityFile_Table>" and table "<JobNameAuthorityFile_Data>" for update the template.
Then user provides input data for PDFXMLElementInventory "<PDFXMLElementInventory_Table>" and table "<PDFXMLElementInventory_Data>".
Then system should be saved UI inputs into database

Examples:
|role                   |PublicationId|ScholarlyArtifact_Table      |ScholarlyArtifact_Data|Bibilio_Table      |Bibilio_Data|JobNameAuthorityFile_Table|JobNameAuthorityFile_Data|PDFXMLElementInventory_Table|PDFXMLElementInventory_Data|
|biblio-evaluation      |91604787711  |ScholarlyArtifact            |Data2                 | BibilioEvaluation |Data1       |JobNameAuthorityFile      |Data2                    |PDFXMLElementInventory    |Data1                      |


@Validate_ScholarlyArtifact_Setup_2
Scenario Outline:  Validate whether BiBilio workflow working fine and Scholarly Artifact screen able to add new values in "Clarivate Document Type" and correseponding "Provider Document Type"  and values are updated successfully in database
 
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>". 
When user provides Clarivate Document Type and Provided Document Type data "<ScholarlyArtifact_Table>" and table "<ScholarlyArtifact_Data>".
Then system should be saved UI inputs into database

Examples:
|role                   |PublicationId|ScholarlyArtifact_Table      |ScholarlyArtifact_Data|Bibilio_Table      |Bibilio_Data|
|biblio-evaluation      |91604787756  |ScholarlyArtifact            |Data3                 | BibilioEvaluation |Data1       |



@Validate_ScholarlyArtifact_Setup_3
Scenario Outline:  Validate whether BiBilio workflow working fine and check whether that JobNames are updated with Prefix with "E" or "X" or "None"  as per option processed format selected in format infor screen. 
 
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>". 
When user provides Provided Document Type value Reprint data "<ScholarlyArtifact_Table>" and table "<ScholarlyArtifact_Data>".
When user provides input data for JobNameAuthorityFile "<JobNameAuthorityFile_Table>" and table "<JobNameAuthorityFile_Data>"


Examples:
|role                   |PublicationId|ScholarlyArtifact_Table      |ScholarlyArtifact_Data|Bibilio_Table      |Bibilio_Data|JobNameAuthorityFile_Table|JobNameAuthorityFile_Data|
|biblio-evaluation      |91604678879  |ScholarlyArtifact            |Data4                 | BibilioEvaluation |Data3       |JobNameAuthorityFile      |Data2| 

@Validate_ScholarlyArtifact_Setup_4
Scenario Outline:  Validate whether BiBilio workflow working fine and check whether Bibilio Team member able to update template name based on Job Name of Authority File Screen. 
 
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>". 
When user provides Provided Document Type value Reprint data "<ScholarlyArtifact_Table>" and table "<ScholarlyArtifact_Data>".
When user provides input data for JobNameAuthorityFile "<JobNameAuthorityFile_Table>" and table "<JobNameAuthorityFile_Data>" for all possible Job Name.


Examples:
|role                   |PublicationId|ScholarlyArtifact_Table      |ScholarlyArtifact_Data|Bibilio_Table      |Bibilio_Data|JobNameAuthorityFile_Table|JobNameAuthorityFile_Data|
|biblio-evaluation      |91604678879  |ScholarlyArtifact            |Data4                 | BibilioEvaluation |Data3       |JobNameAuthorityFile      |Data2| 


@Validate_ScholarlyArtifact_Setup_5
Scenario Outline:  Validate whether BiBilio workflow working fine and check whether Bibilio Team member able to update template name based on Job Name of Authority File Screen. 
 
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>". 
When user provides Provided Document Type value Reprint data "<ScholarlyArtifact_Table>" and table "<ScholarlyArtifact_Data>".
Then user provides input data for JobNameAuthorityFile "<JobNameAuthorityFile_Table>" and table "<JobNameAuthorityFile_Data>" for all possible Job Name.


Examples:
|role                   |PublicationId|ScholarlyArtifact_Table      |ScholarlyArtifact_Data|Bibilio_Table      |Bibilio_Data|JobNameAuthorityFile_Table|JobNameAuthorityFile_Data|
|biblio-evaluation      |91604678879  |ScholarlyArtifact            |Data4                 | BibilioEvaluation |Data3       |JobNameAuthorityFile      |Data2|

@Validate_ScholarlyArtifact_Setup_6
Scenario Outline:  Validate whether BiBilio workflow working fine and check whether Bibilio Team member able to update the template name based on Job Name of Authority File Screen. 
 
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>". 
When user provides Provided Document Type value Reprint data "<ScholarlyArtifact_Table>" and table "<ScholarlyArtifact_Data>".
When user provides input data for JobNameAuthorityFile "<JobNameAuthorityFile_Table>" and table "<JobNameAuthorityFile_Data>" for update the template.


Examples:
|role                   |PublicationId|ScholarlyArtifact_Table      |ScholarlyArtifact_Data|Bibilio_Table      |Bibilio_Data|JobNameAuthorityFile_Table|JobNameAuthorityFile_Data|
|biblio-evaluation      |91604678879  |ScholarlyArtifact            |Data4                 | BibilioEvaluation |Data3       |JobNameAuthorityFile      |Data2|  



@Validate_ScholarlyArtifact_Setup_7
Scenario Outline:  Validate whether BiBilio workflow working fine.
Given am content Biblio user "<role>" and "<PublicationId>" 
When user provides Bibliographic Policy Evaluation data "<Bibilio_Table>" and table "<Bibilio_Data>". 
When user provides Provided Document Type value Reprint data "<ScholarlyArtifact_Table>" and table "<ScholarlyArtifact_Data>".
When user provides input data for JobNameAuthorityFile "<JobNameAuthorityFile_Table>" and table "<JobNameAuthorityFile_Data>" for update the template.
Then user provides input data for PDFXMLElementInventory "<PDFXMLElementInventory_Table>" and table "<PDFXMLElementInventory_Data>".
Then system should be saved UI inputs into database
Examples:
|role                   |PublicationId|ScholarlyArtifact_Table      |ScholarlyArtifact_Data|Bibilio_Table      |Bibilio_Data|JobNameAuthorityFile_Table|JobNameAuthorityFile_Data|PDFXMLElementInventory_Table|PDFXMLElementInventory_Data|
|biblio-evaluation      |91604787921  |ScholarlyArtifact            |Data4                 | BibilioEvaluation |Data3       |JobNameAuthorityFile      |Data2                    |PDFXMLElementInventory    |Data1                      |
