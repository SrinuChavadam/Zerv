@validateJDRInputFormUI
Feature: Validate the JDRInputForm UI flow.

@validateJDRInputFormUI_01
Scenario Outline: Verify that field order(1,2,3,4,5,6),Processed for(ASHP,BIOSIS,ZR,IDPO,DDF) and Reference Characteristics information is stored into "journal_data_record" Table.
Given am a productionsetup user "<role>" ,"<PublicationId>".
When user provides field order,Reference Characteristics,Indent Values and Processed For data "<data>" and table "<tablename>".
Then field order(1,2,3,4,5,6),Processed for(ASHP,BIOSIS,ZR,IDPO,DDF) and Reference Characteristics information should be saved into journal_data_record table.
Examples:
|role             |PublicationId|data     |tablename          |
|production-setup | 91604787358 |Data1    |JDRInput           |

@validateJDRInputFormUI_02
Scenario Outline: Verify that field order(2,1,4,3,6,5),Processed for(ASHP,BIOSIS,IDPO) and Reference Characteristics information is stored into "journal_data_record" Table.
Given am a productionsetup user "<role>" ,"<PublicationId>".
When user provides field order,Reference Characteristics,Indent Values and Processed For data "<data>" and table "<tablename>".
Then field order(2,1,4,3,6,5),Processed for(ASHP,BIOSIS,IDPO) and Reference Characteristics information should be saved into journal_data_record table.
Examples:
|role             |PublicationId|data     |tablename          |
|production-setup | 91604787359 |Data2    |JDRInput           |

@validateJDRInputFormUI_03
Scenario Outline: Verify that field order(6,5,4,3,2,1),Processed for(ASHP,BIOSIS,ZR,IDPO) and Reference Characteristics information is stored into "journal_data_record" Table.
Given am a productionsetup user "<role>" ,"<PublicationId>".
When user provides field order,Reference Characteristics,Indent Values and Processed For data "<data>" and table "<tablename>".
Then field order(6,5,4,3,2,1),Processed for(ASHP,BIOSIS,ZR,IDPO) and Reference Characteristics information should be saved into journal_data_record table.
Examples:
|role             |PublicationId|data     |tablename          |
|production-setup | 91604787370 |Data3    |JDRInput           |