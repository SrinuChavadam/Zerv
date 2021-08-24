package com.zerv.unitest;

import java.util.Map;

import com.zerv.api.utils.PUBOBAPIUtilities;
import com.zerv.api.utils.ReadProperty;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.AcquisitionInfoPage;
import com.zerv.page.BiblioPolicyEvaluationPage;
import com.zerv.page.FormatInfoPage;
import com.zerv.page.ScholarlyArtifactPage;
import com.zerv.page.TaskChecklistPage;
import com.zerv.web.utils.Webutils;

public class Tc3 {
	public static void main(String[] args) {
		
		FormatInfoPage cbpe = new FormatInfoPage(Webutils.getDriver());
		AcquisitionInfoPage csap=new AcquisitionInfoPage(Webutils.getDriver());
		TaskChecklistPage ctcl=new TaskChecklistPage(Webutils.getDriver());
		SpreadSheetBaseFunction sbf = new SpreadSheetBaseFunction();
		ReadProperty read = new ReadProperty();
		String browser = "chrome";
		String env = "QA";		
		cbpe.initiateBrowser(browser);
		cbpe.launchOnboardingUrl(env, "acquisition-setup", "91604787512");
		cbpe.insertJavaScript(read.getProperty("newsession"));
		cbpe.launchOnboardingUrl(env, "acquisition-setup", "91604787512");
		cbpe.pause(3000);
		cbpe.clickOnFormatTab();
		cbpe.pause(3000);
		Map<String, String> formatInfo;
		String data="data1";
		formatInfo = sbf.getTableValues("FormatInfo", "FormatInfo", Integer.parseInt(data.substring(4)));
	    cbpe.AvailableFormatValue(formatInfo.get("FormatTypeAvailable"));
	    cbpe.processedFormatValue(formatInfo.get("FormatTypeToProcessed"));
	    cbpe.xmlOnlyValue(formatInfo.get("XMLType"));
	    cbpe.ClickOnNextButton();	    
	    cbpe.pause(3000);
	    csap.PublisherCategoryValue("NLM");
	    csap.enterSchemaOne("test");
	    csap.ReceiptMethodValue("FTP");
	    csap.TransmissionTypeValue("Pull");
	    csap.enterOnFTPPath("ftp://testpath");
	    csap.enterOnUserId("mania");
	    csap.enterOnPassword("pass");
	    csap.acquisitionTriggerValue("Scheduled");
	    csap.FTPReceiptMethodValue("FTPS");
	    csap.ClickOnSchedule_Info();
	   csap.ClickOnRepeat_Everyday();
	   csap.repeatEverydayValue("Daily");
	    csap.clickOnSummaryInfo();
	    csap.clickOnPriority();
	    csap.priorityValue("Special-Large Files");
	    csap.summaryInfoValue("Sensitive Data,Paid,Timezone Restriction,Duplicated Check,Content Metadata");
	    csap.ClickOnNextButton_Acquisiation();
	    ctcl.taskChecklistValue("Open Access,Configure Watcher");
	    
	    
		
	}

}
