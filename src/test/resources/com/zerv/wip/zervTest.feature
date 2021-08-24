@zervTest
Feature: Zerv.

@zervlogin
Scenario: Validate zerv.
Given am a zerv user launch chrome browser
When user access zerv url "https://devaccessportal.zervinc.net/#/"
And user enters username "ZervDev_Hiral_Bhayani" and password "Nellore@123"
And click on Submit button
Then page title should contain "dashboard"
And close browser