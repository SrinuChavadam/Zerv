@CreateTransformInstruction
Feature: Validate the records are created/Stored in Transform Instruction table.
    
@CreateTransformInstruction_01
Scenario Outline: Validate whether Transform Instruction is stored/updated into "Transform Instruction" Table
Given am a Transform_Instruction Team member "<endpoint>"
When user provides a publicationID along with Transform Instructions "<fileNames>" and "<transforms>" and "<deliveredFolder>" values
Then user should get a successful response for Transform_Instruction
And new record should be created in Transform_Instruction table and validate aganist Database along with"<fileNames>" and "<transforms>" and "<deliveredFolder>"values
Examples:
|endpoint                |fileNames                                |transforms                                        |deliveredFolder|
|Transform_Instruction   | .xml,readfpdf,mediafile,source.pdf,.mov |transform,transform,transform,transform,transform |1,2,3,4,5      |
#|Transform_Instruction   | .xml,readfpdf,mediafile,source.pdf,.mov |ignore,ignore,ignore,ignore,ignore,ignore         |1,2,3,4,5      |
#|Transform_Instruction   | .xml,readfpdf,mediafile,source.pdf,.mov |ignore,transform,ignore,transform,ignore,transform|1,2,3,4,5      |

    
@CreateTransformInstruction_02
Scenario Outline: Validate whether Transform Instruction is stored/updated into "Transform Instruction" Table
Given am a Transform_Instruction Team member "<endpoint>"
When user provides a publicationID along with Transform Instructions "<fileNames>" and "<transforms>" and "<deliveredFolder>" values
Then user should get a error response for Transform_Instruction

Examples:
|endpoint                |fileNames      |transforms          |deliveredFolder|
|Transform_Instruction   | .xml,readfpdf |transform,transform |1,6     |