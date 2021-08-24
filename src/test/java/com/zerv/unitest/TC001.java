package com.zerv.unitest;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.BiblioPolicyEvaluationPage;
import com.zerv.page.ScholarlyArtifactPage;
import com.zerv.web.utils.Webutils;



public class TC001 {

	public static void main(String[] args) throws Exception {
		BiblioPolicyEvaluationPage cbpe = new BiblioPolicyEvaluationPage(Webutils.getDriver());
		ScholarlyArtifactPage csap=new ScholarlyArtifactPage(Webutils.getDriver());
		SpreadSheetBaseFunction sbf = new SpreadSheetBaseFunction();
		ReadProperty read = new ReadProperty();
		String browser = "chrome";
		String env = "QA";		
		cbpe.initiateBrowser(browser);
		cbpe.launchOnboardingUrl(env, "biblio-evaluation", "91604724547");
		cbpe.insertJavaScript(read.getProperty("newsession"));
		cbpe.launchOnboardingUrl(env, "biblio-evaluation", "91604724547");
		cbpe.pause(3000);
		cbpe.clickOnBiblioEvaluationPolicyLink();
		cbpe.pause(5000);
		cbpe.ClickOnPass();
		cbpe.pause(2000);
		cbpe.enterEvaluationResult("EvaluationResult");
		cbpe.enterProcessingTeam("ProcessingTeam");
		cbpe.clickOnComplete();
		Map<String, String> ScholarlyData;
		String data="data2";
		List<String>li1=new ArrayList<String>();
		ScholarlyData = sbf.getTableValues("ScholarlyArtifact", "ScholarlyArtifact", Integer.parseInt(data.substring(4)));
		
		 String[] article_data=ScholarlyData.get("Article").split(",");
		 String[] BookReview_data=ScholarlyData.get("Book Review").split(",");  
		         
		   for (int i = 0; i < article_data.length; i++) 
		   {
				csap.ClickAddButton(1);	 
				csap.enterProvidedValue(article_data[i], i+1);	
				csap.ClickOnArticleCheckButton(i+1);
		   }
		   
		   for (int i = 0; i < BookReview_data.length; i++) 
		   {
				csap.ClickAddButton(6);	 
				csap.enterProvidedValue(BookReview_data[i], i+6);	
				csap.ClickOnArticleCheckButton(i+6);
		   }
		
		
		
		/*
		for (String key : ScholarlyData.keySet()) {			
			li1.add(key);
		}		
		
		for (int i = 0; i <=ScholarlyData.size(); i++) {
			if(i+1==ScholarlyData.size()+1)
			{
				break;
			}
			csap.ClickAddButton(i+1);	
			System.out.println(li1.get(i));
			csap.enterProvidedValue(ScholarlyData.get(li1.get(i)), i+1);			
			csap.ClickonCheckButton(i+1);
				
		}*/
				
		
		csap.ClickOnPass();
		Map<String,String> db_map=new LinkedHashMap<String,String>();
		String query="select * from scholarly_classification where publication_id='91604724547 '";	
		DatabaseUtils db = new DatabaseUtils();	
		List<String> dbListOfclarivate_doc = db.getDbListOfValues(query, "clarivate_doc");
		
		List<String> dbListOfprovider_doc = db.getDbListOfValues(query, "provider_doc");
		for (int i = 0; i < dbListOfclarivate_doc.size(); i++) {
			db_map.put(dbListOfclarivate_doc.get(i), dbListOfprovider_doc.get(i));
		}
		
		ScholarlyData.forEach((key,value_ui)->
				{					
					String value_db=db_map.get(key);
					Assert.assertEquals(value_db, value_ui, "value is not matching for the key:"+key);
			
				});
			   
	}

	}
