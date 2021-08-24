package com.zerv.unitest;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.BiblioPolicyEvaluationPage;
import com.zerv.page.JobNameAuthorityFilePage;
import com.zerv.page.PDFXMLElementInventoryPage;
import com.zerv.page.ScholarlyArtifactPage;
import com.zerv.page.TitlingAbbreviationsPage;
import com.zerv.web.utils.Webutils;



public class TC2 {

	public static void main(String[] args) throws Exception {
		BiblioPolicyEvaluationPage cbpe = new BiblioPolicyEvaluationPage(Webutils.getDriver());
		ScholarlyArtifactPage csap=new ScholarlyArtifactPage(Webutils.getDriver());
		PDFXMLElementInventoryPage peip=new PDFXMLElementInventoryPage(Webutils.getDriver());
		JobNameAuthorityFilePage jafp=new JobNameAuthorityFilePage(Webutils.getDriver());
		SpreadSheetBaseFunction sbf = new SpreadSheetBaseFunction();
		ReadProperty read = new ReadProperty(); 
		String browser = "chrome";
		String env = "QA";		
		cbpe.initiateBrowser(browser);
		cbpe.launchOnboardingUrl(env, "biblio-evaluation", "91604678879");
		cbpe.insertJavaScript(read.getProperty("newsession"));
		cbpe.launchOnboardingUrl(env, "biblio-evaluation", "91604678879");
		cbpe.pause(3000);
		cbpe.clickOnBiblioEvaluationPolicyLink();
		cbpe.pause(5000);
		cbpe.ClickOnPass();
		cbpe.pause(2000);
		//cbpe.enterEvaluationResult("EvaluationResult");
		//cbpe.enterProcessingTeam("ProcessingTeam");
		cbpe.clickOnComplete();
		cbpe.pause(4000);
		Map<String, String> PDFXMLElementInventory;
		Map<String, String> JobNameAuthorityFile;
		String data="data2";
		PDFXMLElementInventory = sbf.getTableValues("PDFXMLElementInventory", "PDFXMLElementInventory", Integer.parseInt(data.substring(4)));
		JobNameAuthorityFile = sbf.getTableValues("JobNameAuthorityFile", "JobNameAuthorityFile", Integer.parseInt(data.substring(4)));	
		//csap.ClickAddButton(14);
		//csap.enterReprint("case");		
		csap.ClickOnPass();
		csap.pause(4000);
		jafp.clickDropdownJobName();
		//jafp.enterDropDownValue("ELIFE");
		
		String query="select content_type_lookup_value from content_type_lookup where content_type_lookup_id in (select content_type_lookup_id from format_to_process where publication_id='91604678879')";
		jafp.selectDropDownValue("XBIOSIS");
		jafp.clickOnConfirmButton();
		jafp.pause(2000);
		String templateValue = jafp.getTemplateValue();		System.out.println(templateValue);
		jafp.clickOnUpdateButton();
		jafp.switchToFrame("iframe");
		jafp.pause(2000);
		jafp.enterTextInTemplate("Sample test need to update");
        jafp.switchToDefault();
		jafp.clickOnNextButton();
		jafp.pause(2000);
		List<String> result = PDFXMLElementInventory.entrySet().stream().filter(x -> !x.getValue().equals(""))
				.map(x -> x.getKey()).collect(Collectors.toList());
		for (String keyvalue : result) {
			peip.selectPDFXMLElementInventoryValue(PDFXMLElementInventory.get(keyvalue));
		}
		peip.clickOnDoneBtn();
		Map<String,String>map1=new HashMap<String,String>();
		Map<String,String>map2=new HashMap<String,String>();
		Map<String,String>map3=new HashMap<String,String>();
		Map<String,String>map4=new HashMap<String,String>();
		
		PDFXMLElementInventory.forEach((key, value) -> {
		if(!value.equals("") && (key.contains("PDF")))
		{
	      map1.put(key.substring(key.indexOf("#")+1, key.length()), "Y");
		}
		else if(value.equals("") && (key.contains("PDF")))
		{
			 map1.put(key.substring(key.indexOf("#")+1, key.length()), "N");
		}
		else if(!value.equals("") && (key.contains("XML")))
		{
	      map2.put(key.substring(key.indexOf("#")+1, key.length()), "Y");
		}
		else if(value.equals("") && (key.contains("XML")))
		{
			 map2.put(key.substring(key.indexOf("#")+1, key.length()), "N");
		}				

		});
		
		System.out.println(map1);
		System.out.println(map2);
		DatabaseUtils db = new DatabaseUtils();
		
		String query1 = "select * from biblio_format_inventory where publication_id= '91604678879'";
		
		List<String> questlookupDB = db.getDbListOfValues(query1, "quest_lookup_id");
		List<String> presentpdfDb = db.getDbListOfValues(query1, "present_pdf");
		List<String> presentxmlDb = db.getDbListOfValues(query1, "present_xml");
		
		for (int i = 0; i < questlookupDB.size(); i++) {
			map3.put(questlookupDB.get(i), presentpdfDb.get(i));
			map4.put(questlookupDB.get(i), presentxmlDb.get(i));
			
		}
		
		System.out.println(map3);
		System.out.println(map4);

		
		
	}
}
	
