package org.funjala.automation.web.mach2.steps.mt02;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.funjala.automation.web.common.drivers.Driver;
import org.funjala.automation.web.common.utilities.Log;
import org.funjala.automation.web.model.erp.search.ModelSearch;
import org.funjala.automation.web.pages.erp.home.OEHomePage;
import org.funjala.automation.web.pages.erp.login.OELoginPage;
import org.funjala.automation.web.pages.erp.search.OESearch;
import org.funjala.automation.web.pages.mach2.dashboard.MyDashboard;
import org.funjala.automation.web.pages.mach2.menu.TopMenuPage;
import org.funjala.automation.web.pages.mach2.widget.WidgetPage;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by David on 1/28/2017.
 */
public class ListEnInByManager {
  @And("^I click on Manager combobox and select \"([^\"]*)\"$")
  public void iClickOnManagerComboboxAndSelect(String managerName) {
    Log log = Log.getInstance();
    WebDriver driver = Driver.getDriver().getWebDriver();
    WidgetPage widget = new WidgetPage(driver);
    log.info("Step", "I click on Manager combobox and select " + managerName, "Select Manager");
    widget.setManagerName(managerName);
  }

  @Then("^I have a List widget with the persons who have \"([^\"]*)\" as Manager$")
  public void iHaveAListWidgetWithThePersonsWhoHaveAsManager(String managerName) throws IOException, InterruptedException {
    Log log = Log.getInstance();
    WebDriver driver = Driver.getDriver().getWebDriver();
    WidgetPage widget = new WidgetPage(driver);
    MyDashboard dashboard = new MyDashboard(driver);
    TopMenuPage topMenuPage = new TopMenuPage(driver);
    log.info("Step", "Verification on Mach2 and Open ERP", "Verification of datas");
    String[] actualResult = widget.getListPersons();

    //Clean up Widget and Board
    log.info("Clean", "Delete Widget and Board Mach2", "Clean up: Delete Mach2");
    dashboard.deleteBoard();

    //Logout Mach2
    log.info("Clean", "Logout Mach2", "Clean up: Logout");
    topMenuPage.clickOnLogOut();

    //Login OPEN ERP
    driver = Driver.getDriver().openBrowser(Driver.OpenERP);
    OELoginPage loginERP = new OELoginPage(driver);
    loginERP.setUserName("jose6");
    loginERP.setPassword("jose6");
    OEHomePage homeERP = loginERP.clickBtnSubmit();

    //Go to Human Resources
    homeERP.clickHumanResources();
    OESearch searchERP = homeERP.oeSearch();

    //Go to Search
    searchERP.clickSearchArrow();
    searchERP.clickAdvancedSearch();

    //Make a search by manager
    searchERP.foundAndClickAdvancedFilterOptions("manager", "is equal to", managerName);
    searchERP.clickApplySearch();
    searchERP.clickSwitchList();

    //Get elements of the list
    searchERP.clickNumberElement();
    searchERP.clickQuantityButton();
    searchERP.clickUnlimitedOption();
    List<ModelSearch> listManager = searchERP.getResultOfSearch();

    log.info("Verification", "Verification on Mach2 and Open ERP", "Verification of Mach2 with OpenERP");

    //Logout Open ERP page
    log.info("Clean", "Logout OpenERP", "Clean up: Logout");
    homeERP.clickUserAccount();
    homeERP.clickLogOut();

    assertTrue(widget.verifyPersonsByManager(listManager, actualResult));
  }
}
