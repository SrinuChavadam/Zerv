@EditTransformInstruction
Feature: Validate the records are Edited/Updated in Transform Instruction table.
    
@EditTransformInstruction_01
Scenario Outline: Validate whether Transform Instruction is edited into "Transform Instruction" Table
Given am a Transform_Instruction Team member "<endpoint>" for edit
When user provides a publicationID along with Transform Instructions Publication id "<Publication_ID>"and "<fileNames>" and "<transforms>" and "<deliveredFolder>" values for edit
Then user should get a successful response for Transform_Instruction edit
And new record should be updated in Transform_Instruction table and validate aganist Database along with"<fileNames>" and "<transforms>" and "<deliveredFolder>"values
Examples:
|endpoint                |fileNames                                |transforms                                        |deliveredFolder|Publication_ID|
|Transform_Instruction   | .xml                                    |transform                                         |1              |91604786698|
|Transform_Instruction   | .xml,readfpdf,mediafile,source.pdf,.mov |ignore,ignore,ignore,ignore,ignore,ignore         |1,2,3,4,5      |91604732255|
|Transform_Instruction   | .xml,readfpdf,mediafile,source.pdf,.mov |ignore,transform,ignore,transform,ignore,transform|1,2,3,4,5      |91604732255|

    
@EditTransformInstruction_02
Scenario Outline: Validate whether Transform Instruction is edited into "Transform Instruction" Table for invalid values
Given am a Transform_Instruction Team member "<endpoint>" for edit
When user provides a publicationID along with Transform Instructions Publication id "<Publication_ID>"and "<fileNames>" and "<transforms>" and "<deliveredFolder>" values for edit
Then user should get a error response for Transform_Instruction for edit

Examples:
|endpoint                |fileNames      |transforms          |deliveredFolder|Publication_ID|
|Transform_Instruction   | .xml,readfpdf |transform,transform |1,6            |91604732255|