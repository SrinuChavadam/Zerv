package com.backup.stepdefinition;

import java.io.File;
import java.io.IOException;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.zerv.web.utils.Webutils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import ru.yandex.qatools.ashot.Screenshot;

public class Hooks {

	@After
	public void afterScenario(Scenario scenario) {
		String os = System.getProperty("os.name").toLowerCase();
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				// This takes a screenshot from the driver at save it to the specified location
				Screenshot takeScreenShot = Webutils.takeScreenShot();
				File sourcePath = Webutils.screenshotFile(takeScreenShot, screenshotName);

				// Building up the destination path for the screenshot to save
				// Also make sure to create a folder 'screenshots' with in the cucumber-report
				// folder
				File destinationPath = new File(System.getProperty("user.dir") + "/output/" + screenshotName + ".jpg");

				// Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath);
				String path = "";
				// This attach the specified screenshot to the test
				if (os.contains("windows")) {
					path = (System.getProperty("user.dir") + "/output/" + screenshotName + ".jpg");
				} else if (os.contains("linux")) {
					String buildurl = System.getenv("BUILD_URL");
					System.out.println("Build Url "+buildurl);
					path = buildurl.substring(0, 68)+"ws" + "/output/"
							+ screenshotName + ".jpg";
				}
				Reporter.addScreenCaptureFromPath(path, screenshotName);

			} catch (IOException e) {
			}
		}
	}

}