package com.backup.stepdefinition;

import java.util.*;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.ContentPackagingPage;
import com.zerv.page.FilesInstructionsPage;
import com.zerv.page.FormatEvaluationPage;
import com.zerv.runner.TestRunner;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateTechnicalEvaluationUI extends TestRunner {
	
	FormatEvaluationPage cfep=new FormatEvaluationPage(Webutils.getDriver());
	ContentPackagingPage cpp=new ContentPackagingPage(Webutils.getDriver());
	FilesInstructionsPage fip=new FilesInstructionsPage(Webutils.getDriver());
	SpreadSheetBaseFunction sbf=new SpreadSheetBaseFunction();
	ReadProperty read = new ReadProperty();
	String browser = "chrome";
	String env = "QA";	
	Logger log = Logger.getLogger(ValidateTechnicalEvaluationUI.class);
	String publicationID="";
	Map<String, String> formatData;
	Map<String, String> contentPackagingData;
	Map<String, String> transformInstructionsData;
	Set<String> publicationIDs;
	SoftAssert sa=new SoftAssert();
	DatabaseUtils db=new DatabaseUtils();
	
	
	@Given("^am a technicalEvaluator \"(.*?)\" ,\"(.*?)\"\\.$")
	public void am_a_technicalEvaluator(String role, String Publication_Id) throws Throwable {
		cfep.initiateBrowser(browser);
		publicationID=Publication_Id;
		//String query="select publication_id from task_checklist " + "except " + "select publication_id from format_evaluation;select publication_ID from task_checklist";
		//publicationID=db.getDbSingleValue(query,"publication_id");
		 
			cfep.launchOnboardingUrl(env,role,Publication_Id);		
			cfep.insertJavaScript(read.getProperty("newsession"));
			cfep.launchOnboardingUrl(env,role,Publication_Id);	
		    cfep.pause(8000);
		}

	@When("^user provides Format Evaluation data \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Format_Evaluation_data_and_table(String datacolumn, String tablename) throws Throwable {
		cfep.clickOnFormatEvaluationLink();
		formatData = sbf.getTableValues("FormatEvaluation", tablename,Integer.parseInt(datacolumn.substring(4)));
		//System.out.println(formatData);
		//System.out.println(formatData.get("FileAccess"));
		cfep.ClickOnFileAccessOption(formatData.get("file_access_ui"));
		cfep.pause(2000);
		if(formatData.get("file_access_ui").equalsIgnoreCase("Slow retrieval")||formatData.get("file_access_ui").equalsIgnoreCase("Access denied"))
		{
		cfep.ClickOnPDFSecurity(formatData.get("pdf_security_ui"));
		cfep.ClickOnPreviouslyProcessedInPrint();
		cfep.pause(1000);
		cfep.ClickOnTimeliness(formatData.get("timeliness_ui"));
		}
		if(formatData.get("pdf_security_ui").equalsIgnoreCase("password"))
		{
		cfep.ClickOnPasswordProvided();
		cfep.enterPassword(formatData.get("password"));
		}
		cfep.enterNoteForProcessingTeam(formatData.get("notes"));
		
		cfep.clickOnButton("Pass"); 
		cfep.pause(2000);
		cfep.clickOnButton("Yes,capture");
		cfep.pause(2000);
		/*cfep.enterEvaluationResultComment(formatData.get("Comment"));
		cfep.pause(1000);
		cfep.ClickOnCompleteButton();*/
				
	}

	@When("^user provides content package Information \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_content_package_Information_and_table(String datacolumn, String tablename) throws Throwable {
		contentPackagingData = sbf.getTableValues("ContentPackaging", tablename,Integer.parseInt(datacolumn.substring(4)));
		cpp.selectPackagingFormat(contentPackagingData.get("PackFormat"));
		cpp.enterZipNameRegular(contentPackagingData.get("ZipRegular"));
		cpp.enterZipNameEarlyAccess(contentPackagingData.get("ZipEarlyAccess"));
		cpp.enterVolume(contentPackagingData.get("Volume"));
		cpp.enterIssue(contentPackagingData.get("Issuedata"));
		cpp.pause(1000);
		cpp.enterNotes(contentPackagingData.get("Notes"));
		cpp.enterFolderStructure(contentPackagingData.get("FolderStructure"));
		cpp.enterZipContent(contentPackagingData.get("ZipContent"));
		//cpp.ClickOnClearIcon();
		cpp.clickOnAddFolderButton();
		cpp.pause(1000);
		cpp.clickOnFolderClearIcon();
		if(contentPackagingData.get("Type").equalsIgnoreCase("Article"))
		cpp.selectFolderTypeArticle();
		if(contentPackagingData.get("Type").equalsIgnoreCase("Issue"))
	    cpp.selectFolderTypeIssue();		
		cpp.enterFolderNaming(contentPackagingData.get("Naming"));
		cpp.enterFoldeContents(contentPackagingData.get("Contents"));
		//cpp.ClickOnFilesClearIcon();
		cpp.clickOnAddFileTypeButton();
		cpp.pause(1000);
		cpp.clickOnFilesClearIcon();
		cpp.enternaming(contentPackagingData.get("FileNaming"));
		cpp.selectFileType(contentPackagingData.get("FileType"));
		cpp.pause(2000);
								
		cfep.clickOnButton("Pass");
		cfep.enterEvaluationResultComment(contentPackagingData.get("Comment"));
		cfep.pause(2000);
		cfep.clickOnButton("Complete");
		
		
		
	}

	@When("^user provides transform/ignore files instruction \"(.*?)\" and table \"(.*?)\"$")
	public void user_provides_transform_ignore_files_instruction_and_table(String datacolumn, String tablename) throws Throwable {
	    
		transformInstructionsData = sbf.getTableValues("FilesInstructions", tablename,Integer.parseInt(datacolumn.substring(4)));
		//DF
		//fip.ClickOnClearIcon_DF();
		fip.clickOn_AddFileType_DF();
		fip.AddFileType_DF(transformInstructionsData.get("TOC_FileType"));
		fip.ClickOnCheckIcon_DF();
		fip.ClickOnTransform_DF();
		//Support
		//fip.ClickOnClearIcon_Source();
		fip.clickOn_AddFileType_Source();
		fip.AddFileType_Source(transformInstructionsData.get("ArticleSource_FileType"));
		fip.ClickOnCheckIcon_Source();
		fip.ClickOnTransform_Source();
		//Supplement
		
		//fip.ClickOnClearIcon_Supplement();
		fip.clickOn_AddFileType_Supplement();
		fip.AddFileType_Supplement(transformInstructionsData.get("ArticleSupplement_FileType"));
		fip.ClickOnCheckIcon_Supplement();
		fip.ClickOnTransform_Supplement();
		
		//Others
		//fip.ClickOnClearIcon_Others();
		fip.clickOn_AddFileType_Others();
		fip.AddFileType_Others(transformInstructionsData.get("Others_FileType"));
		fip.ClickOnCheckIcon_Others();
		fip.ClickOnTransform_Others();
		
		//Images
		//fip.ClickOnClearIcon_Images();
		fip.clickOn_AddFileType_Images();
		fip.AddFileType_Images(transformInstructionsData.get("Images_FileType"));
		fip.pause(1000);
		fip.ClickOnCheckIcon_Images();
		fip.ClickOnTransform_Images();
		
		fip.ClickOnSupplementalFiles();
		fip.enterFileTypes(transformInstructionsData.get("SupplementalFileTypes"));
		fip.ClickOnPassButton();
		cfep.enterEvaluationResultComment(contentPackagingData.get("Comment"));
		cfep.pause(2000);
		cfep.clickOnButton("Complete");
		
	}

	

	@Then("^data should be saveb into Database tables\\.$")
	public void data_should_be_saveb_into_Database_tables() throws Throwable {
		
		
		//Set<String> contentpackagingistValues=new TreeSet<String>();
		//Set<String> processedFormatListValues=new TreeSet<String>();
		List<String> formatEvaluationData_DB=new ArrayList<String>();
		List<String> contentPackagingData_DB=new ArrayList<String>();
			
		String TI_TOC_Data_UI="";
		String TI_Source_Data_UI="";
		String TI_Supplement_Data_UI="";
		String TI_Others_Data_UI="";
		String TI_Images_Data_UI="";
				
		List<String> formatEvaluationData_UI=new ArrayList<String>();
		List<String> contentPackagingData_UI=new ArrayList<String>();
				
			 String query1="select file_access,pdf_security,timeliness from format_evaluation where publication_id= '" + publicationID + "'";
			 Map<String, String> actual_Data = db.getRowMap(query1);
				for(Entry<String, String> data:actual_Data.entrySet())
		        {
		            String db_data=data.getValue();
		            String ui_data=formatData.get(data.getKey());
		            //System.out.println(ui_data+"is matched to"+db_data);
		            sa.assertEquals(db_data, ui_data, "DB value is not matching for field:" +data.getKey() +"  " +"Actual:"+db_data +"Expected:" +ui_data);
		         }
			 			 
			 //Content packaging
             String content_packaging_query="select * from content_packaging_info where publication_id='" + publicationID + "'";
			 
			 String packaging_format_DB = db.getStringDbValue(content_packaging_query, "packaging_format");
			 String zip_name_regular_DB = db.getStringDbValue(content_packaging_query, "zip_name_regular");
			 String zip_name_early_access_DB = db.getStringDbValue(content_packaging_query, "zip_name_early_access");
			 String volume_DB = db.getStringDbValue(content_packaging_query, "volume");
			 String issue_DB = db.getStringDbValue(content_packaging_query, "issue");
			 //String contentnotes_DB = db.getStringDbValue(content_packaging_query, "notes");
			 String folder_structure_DB = db.getStringDbValue(content_packaging_query, "folder_structure");
			 String zip_content_DB = db.getStringDbValue(content_packaging_query, "zip_content");
			 //folder
             String folder_name_query="select * from folder_name_convention where publication_id='" + publicationID + "'";
			 
			 String folder_type_DB = db.getStringDbValue(folder_name_query, "folder_type");
			 String folder_naming_DB = db.getStringDbValue(folder_name_query, "folder_naming");
			 String folder_contents_DB = db.getStringDbValue(folder_name_query, "folder_contents");
			 
			 //file name
             String fileNaming_query="select * from file_name_convention where publication_id='" + publicationID + "'";
			 
			 String name_convention_DB = db.getStringDbValue(fileNaming_query, "name_convention");
			 String file_naming_DB = db.getStringDbValue(fileNaming_query, "file_naming");
			 
			 contentPackagingData_DB.add(packaging_format_DB);
			 contentPackagingData_DB.add(zip_name_regular_DB);
			 contentPackagingData_DB.add(zip_name_early_access_DB);
			 contentPackagingData_DB.add(volume_DB);
			 contentPackagingData_DB.add(issue_DB);
			 //contentPackagingData_DB.add(contentnotes_DB);
			 contentPackagingData_DB.add(folder_structure_DB);
			 contentPackagingData_DB.add(zip_content_DB);
			 contentPackagingData_DB.add(folder_type_DB);
			 contentPackagingData_DB.add(folder_naming_DB);
			 contentPackagingData_DB.add(folder_contents_DB);
			 contentPackagingData_DB.add(file_naming_DB);
			 contentPackagingData_DB.add(name_convention_DB);
			 
			 
			 
			 //packagingformat
			 switch (contentPackagingData.get("PackFormat")) {
				case "One zip file/folder per journal":
					contentPackagingData_UI.add("1");
					break;
				case "One zip file/folder per multiple journals":
					contentPackagingData_UI.add("2");
					break;
				case "N/A":
					contentPackagingData_UI.add("3");
					break;
				}
			 			 		 
			 contentPackagingData_UI.add(contentPackagingData.get("ZipRegular"));
			 contentPackagingData_UI.add(contentPackagingData.get("ZipEarlyAccess"));
			 contentPackagingData_UI.add(contentPackagingData.get("Volume"));
			 contentPackagingData_UI.add(contentPackagingData.get("Issuedata"));
			//contentPackagingData_UI.add(contentPackagingData.get("Notes"));
			 contentPackagingData_UI.add(contentPackagingData.get("FolderStructure"));
			 contentPackagingData_UI.add(contentPackagingData.get("ZipContent"));
			 contentPackagingData_UI.add(contentPackagingData.get("Type"));
			 contentPackagingData_UI.add(contentPackagingData.get("Naming"));
			 contentPackagingData_UI.add(contentPackagingData.get("Contents"));
			 contentPackagingData_UI.add(contentPackagingData.get("FileType"));
			 contentPackagingData_UI.add(contentPackagingData.get("FileNaming"));
			 
			 //Transform instructions
			 //TOC section
             String transform_toc_query="select * from transformation_instructions where publication_id='" + publicationID + "'"+"and delivered_folder='1'";
            			 
			 String file_name_toc_DB = db.getStringDbValue(transform_toc_query, "file_name");
			 String transform_toc_DB = db.getStringDbValue(transform_toc_query, "transform");
			 TI_TOC_Data_UI=transformInstructionsData.get("TOC_FileType");
			 //TI_TOC_Data_UI.add(transformInstructionsData.get("TOC"));
			 
			 //Source section
			
             String transform_source_query="select * from transformation_instructions where publication_id='" + publicationID + "'"+"and delivered_folder='2'";
            			 
			 String file_name_source_DB = db.getStringDbValue(transform_source_query, "file_name");
			 String transform_source_DB = db.getStringDbValue(transform_source_query, "transform");
			 
			 
			 
			 TI_Source_Data_UI=transformInstructionsData.get("ArticleSource_FileType");
			 //TI_TOC_Data_UI.add(transformInstructionsData.get("ArticleSource"));
			 
			 //supplement scetion
			 String transform_supplement_query="select * from transformation_instructions where publication_id='" + publicationID + "'"+"and delivered_folder='3'";
			 
			 String file_name_supplement_DB = db.getStringDbValue(transform_supplement_query, "file_name");
			 String transform_supplement_DB = db.getStringDbValue(transform_supplement_query, "transform");
			 
			 
			 
			 TI_Supplement_Data_UI=transformInstructionsData.get("ArticleSupplement_FileType");
			 //TI_TOC_Data_UI.add(transformInstructionsData.get("ArticleSupplement"));
			 
			 //Others section
             String transform_others_query="select * from transformation_instructions where publication_id='" + publicationID + "'"+"and delivered_folder='4'";
			 
			 String file_name_others_DB = db.getStringDbValue(transform_others_query, "file_name");
			 String transform_others_DB = db.getStringDbValue(transform_others_query, "transform");
			 
			 
			 
			 TI_Others_Data_UI=transformInstructionsData.get("Others_FileType");
			 //TI_TOC_Data_UI.add(transformInstructionsData.get("Others"));
			 
			 //Images section
             String transform_images_query="select * from transformation_instructions where publication_id='" + publicationID + "'"+"and delivered_folder='5'";
			 
			 String file_name_images_DB = db.getStringDbValue(transform_images_query, "file_name");
			 String transform_images_DB = db.getStringDbValue(transform_images_query, "transform");
			 
			 
			 
			 TI_Images_Data_UI=transformInstructionsData.get("Images_FileType");
			 //TI_TOC_Data_UI.add(transformInstructionsData.get("Images"));
			 
			 SoftAssert sa=new SoftAssert();
			 //Reporter.addStepLog("value from ui is :"+formatEvaluationUiList);
			 //Reporter.addStepLog("value from ui is :"+formatEvaluationData_DB);
			 sa.assertEquals(formatEvaluationData_DB, formatEvaluationData_UI, "format evaluation values are mismatched");
			 sa.assertEquals(contentPackagingData_DB, contentPackagingData_UI, "content packaging values are mismatched");
			 sa.assertEquals(file_name_toc_DB, TI_TOC_Data_UI, "transform instructions TOC folder values are mismatched");
			 sa.assertEquals(file_name_source_DB, TI_Source_Data_UI, "transform instructions source folder values are mismatched");
			 sa.assertEquals(file_name_supplement_DB, TI_Supplement_Data_UI, "transform instructions supplement folder values are mismatched");
			 sa.assertEquals(file_name_others_DB, TI_Others_Data_UI, "transform instructions Others folder values are mismatched");
			 sa.assertEquals(file_name_images_DB, TI_Images_Data_UI, "transform instructions Images folder values are mismatched");
			 sa.assertAll();
			 
			 			 
		if (transformInstructionsData.get("TOC").equalsIgnoreCase("Transform"))
			sa.assertEquals(transform_toc_DB, "Y", "transform value Mismatched with database value");
		else if (transformInstructionsData.get("TOC").equalsIgnoreCase("Ignore"))
			sa.assertEquals(transform_toc_DB, "N", "transform value Mismatched with database value");

		if (transformInstructionsData.get("ArticleSource").equalsIgnoreCase("Transform"))
			sa.assertEquals(transform_source_DB, "Y", "transform value Mismatched with database value");
		else if (transformInstructionsData.get("ArticleSource").equalsIgnoreCase("Ignore"))
			sa.assertEquals(transform_source_DB, "N", "transform value Mismatched with database value");

		if (transformInstructionsData.get("ArticleSupplement").equalsIgnoreCase("Transform"))
			sa.assertEquals(transform_supplement_DB, "Y", "transform value Mismatched with database value");
		else if (transformInstructionsData.get("ArticleSupplement").equalsIgnoreCase("Ignore"))
			sa.assertEquals(transform_supplement_DB, "N", "transform value Mismatched with database value");

		if (transformInstructionsData.get("Others").equalsIgnoreCase("Transform"))
			sa.assertEquals(transform_others_DB, "Y", "transform value Mismatched with database value");
		else if (transformInstructionsData.get("Others").equalsIgnoreCase("Ignore"))
			sa.assertEquals(transform_others_DB, "N", "transform value Mismatched with database value");
		
		if (transformInstructionsData.get("Images").equalsIgnoreCase("Transform"))
			sa.assertEquals(transform_images_DB, "Y", "transform value Mismatched with database value");
		else if (transformInstructionsData.get("Images").equalsIgnoreCase("Ignore"))
			sa.assertEquals(transform_images_DB, "N", "transform value Mismatched with database value");
			 
			 			 
				    
	}

	}