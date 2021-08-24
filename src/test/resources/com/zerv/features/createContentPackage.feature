@addContentPackageAPI
Feature: Validate the records are created/Stored in contentpackage, filenameconvention and folderconvention tables.

@addContentPackaging_01
Scenario Outline: Validate whether  content packaging information is stored into "content_packaging_info","file_name_convention" and "folder_name_convention" tables.
Given am a technicalEvaluator_addContentPackage "<endPoint>"
When user provides content packaging information "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationId
Then the user should get a success response_addContentPackage.
And new record should be created in content_packaging_info,file_name_convention and folder_name_convention Tables with "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationId
Examples:
|endPoint         |zipName|zipNameAccess|zipContent |volume|formatLupId|notes      |matching|issue|folderId|folderCon |folderName |folderType|folderStructure|fileId|fileName|nameCon   |
|Content_Packaging|testzip|testzipAccess|test2Content|1     |1         |testNotes  |    true|issue|1       |test      | testname  |testtype  | teststructure |1     |testfile| testcon  |   
|Content_Packaging|testzip|testzipAccess|test3Content|1     |2         |testNotes2 |    true|issue|2       |test2     | testname2 |testtype  | teststructure |2     |testfile2| testcon2|   
|Content_Packaging|testzip|testzipAccess|test1Content|1     |3         |testNotes3 |    true|issue|3       |test3     | testname3 |testtype  | teststructure |1     |testfile3| testcon3|   
|Content_Packaging|testzip|testzipAccess|test5Content|1     |1         |testNotes4 |    true|issue|4       |test4     | testname4 |testtype  | teststructure |2     |testfile4| testcon4|   
|Content_Packaging|testzip|testzipAccess|test6Content|1     |3         |testNotes5 |    true|issue|5       |test5     | testname5 |testtype  | teststructure |1     |testfile5| testcon5|   
|Content_Packaging|testzip|testzipAccess|test7Content|1     |2         |testNotes6 |    true|issue|6       |test6     | testname6 |testtype  | teststructure |2     |testfile6| testcon6|   

@addContentPackaging_02
Scenario Outline: Validate the error message when user sends create request with invalid publicationId.
Given am a technicalEvaluator_addContentPackage "<endPoint>"
When user provides content packaging information "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationId "<publicationId>"
Then the user should get a error response_addContentPackage.
Examples:
|endPoint         |zipName|zipNameAccess|zipContent |volume|formatLupId|notes     |matching|issue|folderId|folderCon|folderName|folderType|folderStructure|fileId|fileName|nameCon |publicationId|
|Content_Packaging|testzip|testzipAccess|testContent|1     |1          |testNotes |    true|issue1|1       |test12    | testname1 |testtype1  | teststructure |1     |testfile| testcon| 987654abv   |


@addContentPackaging_03
Scenario Outline: Validate the error message when user sends create request with already existing publicationId.
Given am a technicalEvaluator_addContentPackage "<endPoint>"
When user provides content packaging information "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationID
Then the user should get  error response_addContentPackage.
Examples:
|endPoint         |zipName|zipNameAccess|zipContent |volume|formatLupId|notes     |matching|issue|folderId|folderCon|folderName|folderType|folderStructure|fileId|fileName|nameCon |
|Content_Packaging|testzip|testzipAccess|testContent|1     |1          |testNotes |    true|issue|1       |test     | testname |testtype  | teststructure |1     |testfile| testcon|
@addContentPackaging_04
Scenario Outline: Validate the error message when user sends create request with invalid roles.
Given am not a technicalEvaluator_addContentPackage "<endPoint>"
When user provides content packaging information "<zipName>","<zipNameAccess>","<zipContent>","<volume>","<formatLupId>","<notes>","<matching>","<issue>","<folderId>","<folderCon>","<folderName>","<folderType>","<folderStructure>","<fileId>","<fileName>","<nameCon>" for publicationID
Then the user should get Error response_addContentPackage.
Examples:
|endPoint         |zipName|zipNameAccess|zipContent |volume|formatLupId|notes     |matching|issue|folderId|folderCon|folderName|folderType|folderStructure|fileId|fileName|nameCon |
|Content_Packaging|testzip|testzipAccess|testContent|1     |1          |testNotes |    true|issue|1       |test     | testname |testtype  | teststructure |1     |testfile| testcon|

 