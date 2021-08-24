@editContentPackageAPI
Feature: Validate the records are edited/Modified in contentpackage, filenameconvention and folderconvention tables.

@editContentPackaging_01
Scenario Outline: Validate whether content packaging information is updated in content_packaging_info,file_name_convention and folder_name_convention tables.
Given am a technicalEvaluator_editContentPackage "<endPoint>"
When user provides content packaging Information "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationId
Then the user should get a success response_editContentPackage.
And data should be created in content_packaging_info,file_name_convention and folder_name_convention Tables with "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationId
Examples:
|endPoint         |zipName|zipNameAccess|zipContent  |volume|formatLupId|notes      |matching|issue|folderId|folderCon |folderName |folderType|folderStructure|fileId|fileName|nameCon   |
|Content_Packaging|testzip|testzipAccess|test5Content|1     |2          |testNotes4 |    true|issue|4       |test4     | testname4 |testtype  | teststructure |2     |testfile4| testcon4|   
|Content_Packaging|testzip|testzipAccess|test6Content|1     |1          |testNotes5 |    true|issue|5       |test5     | testname5 |testtype  | teststructure |1     |testfile5| testcon5|   
|Content_Packaging|testzip|testzipAccess|test7Content|1     |3          |testNotes6 |    true|issue|6       |test6     | testname6 |testtype  | teststructure |2     |testfile6| testcon6|   

@editContentPackaging_02
Scenario Outline: Validate the error message when user sends update request with invalid publicationId.
Given am a technicalEvaluator_editContentPackage "<endPoint>"
When user provides Content packaging information "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationId "<publicationId>"
Then the user should get a error response_editContentPackage.
Examples:
|endPoint         |zipName|zipNameAccess|zipContent |volume|formatLupId|notes     |matching|issue|folderId|folderCon|folderName|folderType|folderStructure    |fileId    |fileName|nameCon     |publicationId|
|Content_Packaging|testzip|testzipAccess|testContent|1     |1          |testNotes |    true|issue1|1      |test12   | testname1|testtype1 | teststructure     |1     |testfile| testcon| 987654abv   |


@editContentPackaging_03
Scenario Outline: Validate the error message when user sends create request with invalid roles.
Given am not a technicalEvaluator_editContentPackage "<endPoint>"
When user provides content packaging Information "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationId
Then the user should get Error response_editContentPackage.
Examples:
|endPoint         |zipName|zipNameAccess|zipContent |volume|formatLupId|notes     |matching|issue|folderId|folderCon|folderName|folderType|folderStructure|fileId|fileName|nameCon |
|Content_Packaging|testzip|testzipAccess|testContent|1     |1          |testNotes |    true|issue|1       |test     | testname |testtype  | teststructure |1     |testfile| testcon|

 