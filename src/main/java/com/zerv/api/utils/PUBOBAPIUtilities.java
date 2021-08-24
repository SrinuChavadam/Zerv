package com.zerv.api.utils;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PUBOBAPIUtilities {

	ReadProperty prop = new ReadProperty();
	// String env = System.getProperty("env");
	String env = "qa";

	/**
	 * Author: Satya Method sets base URL and headers for given request and returns
	 * request
	 * 
	 * @param baseurl
	 * @param sessiontoken
	 * @param roles
	 * @param contenttype
	 * @return
	 */

	public RequestSpecification getRequestwithHeaderDetails(String sessiontoken, String roles, String contenttype,
			String EndPoint) {

		RequestSpecification request = null;
		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("SessionToken", sessiontoken);
			headers.put("Roles", roles);
			headers.put("Content-Type", contenttype);
			if (env.equals("qa")) {
				RestAssured.baseURI = prop.getProperty("Stable_base_url") + EndPoint;

			} else if (env.equals("prod")) {
				RestAssured.baseURI = prop.getProperty("Prod_base_url") + EndPoint;
			}

			request = RestAssured.given().headers(headers);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	/**
	 * Description: To send the json request with publicationid, format id and get
	 * the json response Author :Satya
	 * 
	 * @param request
	 * @param publicationid
	 * @param format
	 * @return
	 * @throws JSONException
	 */
	public String createRequestForTaskCheckList(String taskLookupId) throws JSONException {

		String[] formats = null;
		String prettyJsonString = null;
		if (taskLookupId.contains(",") || taskLookupId.length() == 1) {
			formats = taskLookupId.split(",");
			JSONArray ja = new JSONArray();
			for (int k = 0; k < formats.length; k++) {

				JSONObject jo1 = new JSONObject();

				jo1.put("taskChecklistLookupId", formats[k]);
				jo1.put("taskChecklistLookupValue", "string");
				ja.put(jo1);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonParser jp = new JsonParser();
				JsonElement je = jp.parse(ja.toString());
				prettyJsonString = gson.toJson(je);
				System.out.println(prettyJsonString);

			}
		}
		return prettyJsonString;
	}

	/**
	 * Author: Satya Purpose: to generate Random number
	 * 
	 * @return
	 */

	public String genarateRandomNumber() {
		int random = (int) (Math.random() * 800000000 + 1);
		String publicationId = Integer.toString(random);
		return publicationId;
	}

	/**
	 * Author= Parthiban.G
	 * 
	 * @param httpRequest
	 * @param publicationID
	 * @return
	 */
	public Response viewFormatInfoWithPublicationID(RequestSpecification httpRequest, String publicationID) {
		Response response = httpRequest.request(Method.GET, publicationID);
		return response;
	}

	/**
	 * Author:Manimaran
	 * 
	 * @param request
	 * @param publicationId
	 * @param processedLookupId
	 * @param contentTypeLookupId
	 * @param availableLookupId
	 * @param CheckedValue
	 * @return
	 */
	public String createRequestFormatInfo(String processedLookupId, String contentTypeLookupId,
			String availableLookupId) {

		JSONObject jo1 = new JSONObject();
		JSONArray ja1 = new JSONArray();
		String prettyJsonString = null;

		try {

			String[] formats = null;
			int k = 0;

			if (processedLookupId.contains(",") || processedLookupId.length() == 1) {
				formats = processedLookupId.split(",");
				for (k = 0; k < formats.length; k++) {

					JSONObject jo4 = new JSONObject();

					jo4.put("processedLookupId", formats[k]);
					jo4.put("processedLookupValue", "string");
					jo1.put("processedFormat", jo4);
				}
			}

			if (contentTypeLookupId.contains(",") || contentTypeLookupId.length() == 1) {
				formats = contentTypeLookupId.split(",");
				for (k = 0; k < formats.length; k++) {

					JSONObject jo3 = new JSONObject();

					jo3.put("contentTypeLookupId", formats[k]);
					jo3.put("contentTypeLookupValue", "string");
					jo1.put("contentType", jo3);
				}
			}

			if (availableLookupId.contains(",") || availableLookupId.length() == 1) {
				formats = availableLookupId.split(",");
				for (k = 0; k < formats.length; k++) {
					JSONObject jo2 = new JSONObject();

					jo2.put("availableLookupId", formats[k]);
					jo2.put("availableLookupValue", "string");
					ja1.put(jo2);

					jo1.put("availableFormats", ja1);

					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					JsonParser jp = new JsonParser();
					JsonElement je = jp.parse(jo1.toString());
					prettyJsonString = gson.toJson(je);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return prettyJsonString;
	}

	/**
	 * Author:Manimaran
	 * 
	 * @param transmissionTypeLookupId
	 * @param receiptMethodLookupId
	 * @param categoryLookupId
	 * @param ftpTypeLookupId
	 * @param filePriorityLookupId
	 * @param contentSizeLookupId
	 * @param browserLookupId
	 * @param triggerLookupId
	 * @return
	 * @throws JSONException
	 */

	public String createAcquisitionInfoRequest(String transmissionTypeLookupId, String receiptMethodLookupId,
			String categoryLookupId, String ftpTypeLookupId, String filePriorityLookupId, String contentSizeLookupId,
			String browserLookupId, String triggerLookupId) throws JSONException

	{
		JSONObject jo1 = new JSONObject();

		JSONObject jo2 = new JSONObject();
		jo2.put("transmissionTypeLookupId", transmissionTypeLookupId);
		jo2.put("transmissionTypeLookupValue", "string");
		jo1.put("transmissionType", jo2);

		JSONObject jo3 = new JSONObject();
		jo3.put("receiptMethodLookupId", receiptMethodLookupId);
		jo3.put("receiptMethodLookupValue", "string");
		jo1.put("receiptMethod", jo3);

		JSONObject jo4 = new JSONObject();
		jo4.put("categoryLookupId", categoryLookupId);
		jo4.put("categoryLookupValue", "string");
		jo1.put("publisherCategory", jo4);

		JSONObject jo5 = new JSONObject();
		jo5.put("ftpTypeLookupId", ftpTypeLookupId);
		jo5.put("ftpTypeLookupValue", "string");
		jo1.put("ftpType", jo5);

		JSONObject jo6 = new JSONObject();
		jo6.put("filePriorityLookupId", filePriorityLookupId);
		jo6.put("filePriorityLookupValue", "string");
		jo1.put("filePriority", jo6);

		JSONObject jo7 = new JSONObject();
		jo7.put("contentSizeLookupId", contentSizeLookupId);
		jo7.put("contentSizeLookupValue", "string");
		jo1.put("contentSize", jo7);

		JSONObject jo8 = new JSONObject();
		jo8.put("browserLookupId", browserLookupId);
		jo8.put("browserLookupValue", "string");
		jo1.put("browserType", jo8);

		JSONObject jo9 = new JSONObject();
		jo9.put("triggerLookupId", triggerLookupId);
		jo9.put("triggerLookupValue", "string");
		jo1.put("acquisitionTrigger", jo9);

		JSONArray ja = new JSONArray();
		ja.put("string");
		jo1.put("attachments", ja);

		jo1.put("batchProcessingNeeded", "true");
		jo1.put("checkDuplicates", "true");
		jo1.put("contentMetadata", "true");
		jo1.put("cronExpression", "string");
		jo1.put("dataRetention", "string");
		jo1.put("exceptions", "string");
		jo1.put("paidContent", "true");
		jo1.put("password", "string");
		jo1.put("publisherCode", "strin");
		jo1.put("publisherSchemaVersion", "string");
		jo1.put("receiptUrl", "string");
		jo1.put("resolutionTime", "2019-05-16T04:52:09.505Z");
		jo1.put("restrictTimezone", "true");
		jo1.put("sensitiveData", "true");
		jo1.put("sleepTime", "string");
		jo1.put("trustCertificate", "true");
		jo1.put("userId	", "string");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jo1.toString());
		String prettyJsonString = gson.toJson(je);

		return prettyJsonString;

	}

	/**
	 * Author:Manimaran
	 * 
	 * @return
	 * @throws JSONException
	 */

	public String createTransformInstructions(String fileNames, String transforms, String deliveredFolder)
			throws JSONException {

		JSONArray ja = new JSONArray();
		String formats[] = null;
		String formatss[] = null;
		String formatsss[] = null;
		/*
		 * String fileNames=".xml,readfpdf,mediafile,source.pdf,.mov"; String
		 * transforms="transform,transform,transform,transform,transform"; String
		 * deliveredFolder="1,2,3,4";
		 */
		JSONArray ja1 = new JSONArray();

		if (fileNames.contains(",") || fileNames.length() == 1 || transforms.contains(",")
				|| transforms.length() == 1) {
			formats = fileNames.split(",");
			formatss = transforms.split(",");
			for (int k = 0; k < formats.length; k++) {

				JSONObject jo1 = new JSONObject();
				jo1.put("fileName", formats[k]);
				jo1.put("transform", formatss[k]);
				ja1.put(jo1);
			}
		}

		if (deliveredFolder.contains(",") || deliveredFolder.length() == 1) {
			formatsss = deliveredFolder.split(",");
			for (int i = 0; i < formatsss.length; i++) {

				JSONObject jo = new JSONObject();
				JSONObject jo2 = new JSONObject();
				jo2.put("deliveredFolderLookupId", formatsss[i]);
				jo.put("deliveredFolderLookup", jo2);
				jo.put("fileDetailsDTOS", ja1);
				ja.put(jo);
			}
		}

		JSONObject root_jo = new JSONObject();
		root_jo.put("transformInstructionsDTOS", ja);
		root_jo.put("fileTypes", "string");
		root_jo.put("filesIncluded", "true");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(root_jo.toString());
		String prettyJsonString = gson.toJson(je);

		return prettyJsonString;

	}

	/**
	 * Author Satya
	 * 
	 * @param timeLinessLKPId
	 * @param processedAsPrint
	 * @param pdfSecurityLkpId
	 * @param passwordProvided
	 * @param password
	 * @param notes
	 * @param fileAccessLookupId
	 * @param attachments
	 * @return String
	 * @throws JSONException
	 */
	public String createTechnicalEvaluationRequest(String attachments, String fileAccessLookupId, String notes,
			String password, String passwordProvided, String pdfSecurityLkpId, String processedAsPrint,
			String timeLinessLKPId) throws JSONException

	{
		JSONObject jo1 = new JSONObject();

		JSONObject jo2 = new JSONObject();
		jo2.put("timeLinessLookupId", timeLinessLKPId);
		jo2.put("timeLinessLookupValue", "string");
		jo1.put("timeLiness", jo2);

		jo1.put("processedAsPrint", processedAsPrint);

		JSONObject jo3 = new JSONObject();
		jo3.put("pdfSecurityLookupId", pdfSecurityLkpId);
		jo3.put("pdfSecurityLookupValue", "string");
		jo1.put("pdfSecurity", jo3);

		jo1.put("passwordProvided", passwordProvided);
		jo1.put("password", password);
		jo1.put("notes", notes);

		JSONObject jo4 = new JSONObject();
		jo4.put("fileAccessLookupId", fileAccessLookupId);
		jo4.put("fileAccessLookupValue", "string");
		jo1.put("fileAccess", jo4);

		JSONArray ja = new JSONArray();
		ja.put(attachments);
		jo1.put("attachments", ja);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jo1.toString());
		String prettyJsonString = gson.toJson(je);

		return prettyJsonString;

	}

	/**
	 * Author : Satya
	 * 
	 * @param zipName
	 * @param zipNameAccess
	 * @param zipContent
	 * @param volume
	 * @param formatLupId
	 * @param notes
	 * @param matching
	 * @param issue
	 * @param folderId
	 * @param FolderStructure
	 * @return
	 * @throws JSONException
	 */

	public String createContentPackageRequest(String zipName, String zipNameAccess, String zipContent, String volume,
			String formatLupId, String notes, String matching, String issue, String folderId, String folderCon,
			String folderName, String folderType, String FolderStructure, String fileId, String fileName,
			String nameCon) throws JSONException

	{
		JSONObject jo1 = new JSONObject();

		jo1.put("zipNameRegular", zipName);
		jo1.put("zipNameEarlyAccess", zipNameAccess);
		jo1.put("zipContent", zipContent);
		jo1.put("volume", volume);

		JSONObject jo2 = new JSONObject();
		jo2.put("formatLookupId", formatLupId);
		jo2.put("formatLookupValue", "string");
		jo1.put("packagingFormat", jo2);

		jo1.put("notes", notes);
		jo1.put("matchingName", matching);
		jo1.put("issue", issue);

		JSONArray ja = new JSONArray();

		JSONObject jo3 = new JSONObject();

		jo3.put("folderContents", folderCon);
		jo3.put("folderNameId", folderId);
		jo3.put("folderNaming", folderName);
		jo3.put("folderType", folderType);
		ja.put(jo3);
		jo1.put("folders", ja);

		jo1.put("folderStructure", FolderStructure);

		JSONArray ja1 = new JSONArray();

		JSONObject jo4 = new JSONObject();

		jo4.put("fileNameId", fileId);
		jo4.put("fileNaming", fileName);
		jo4.put("nameConvention", nameCon);

		ja1.put(jo4);
		jo1.put("files", ja1);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jo1.toString());
		String prettyJsonString = gson.toJson(je);

		return prettyJsonString;

	}

	/**
	 * Author:Manimaran
	 * 
	 * @return
	 * @throws JSONException
	 */

	public String createScholarlyArtifact(String clarivateDocType, String providerDocType) throws JSONException {

		JSONObject jo2 = new JSONObject();

		jo2.put("clarivateDocType", clarivateDocType);
		jo2.put("providerDocType", providerDocType);

		JSONArray ja = new JSONArray();
		ja.put(jo2);
		JSONObject jo1 = new JSONObject();
		jo1.put("documents", ja);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jo1.toString());
		String prettyJsonString = gson.toJson(je);

		return prettyJsonString;
	}

}
