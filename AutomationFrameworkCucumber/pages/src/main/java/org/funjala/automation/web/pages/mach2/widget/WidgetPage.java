package org.funjala.automation.web.pages.mach2.widget;

import org.funjala.automation.web.model.erp.search.ModelSearch;
import org.funjala.automation.web.model.mach2.widget.WidgetModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

/**
 * Created by DavidVallejos on 1/21/2017.
 */
public class WidgetPage {

  private WebDriver driver;
  private WebDriverWait wait;

  @FindBy(xpath = WidgetModel.dropdownIcon)
  private WebElement dropdownIcon;

  @FindBy(xpath = WidgetModel.dropDownList)
  private WebElement dropDownList;

  @FindBy(css = WidgetModel.erpOption)
  private WebElement erpOption;

  @FindBy(xpath = WidgetModel.eiOption)
  private WebElement eiOption;

  @FindBy(xpath = WidgetModel.managerInput)
  private List<WebElement> managerInput;

  @FindBy(css = WidgetModel.saveButton)
  private WebElement saveButton;

  @FindBy(xpath = WidgetModel.listElementTable)
  private List<WebElement> listElementTable;

  @FindBy(css = WidgetModel.erpEngineerInformationOption)
  private WebElement erpEngineerInformationOption;

  @FindBy(css = "a[data-id='advanced-config']")
  private WebElement advanceConfig;

  @FindBy(xpath = "//div[4]/div[2]/div/div[2]/div/i")
  private WebElement jobTitles;

  @FindBy(xpath = "//div[@class='truncated text header']")
  private List<WebElement> listName;


  //Corregir esto!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-----------------------------------------------------
  @FindBy(xpath = ".//a/b[text()='ADVANCED CONFIGURATION']")
  private WebElement advanceConfiguration;

  @FindBy(xpath = ".//*[@id='mach-wizard']/div/div[3]/div/div/div[2]/div[4]/div[2]/div/div[1]/div")
  private WebElement comboboxDepartment;

//  @FindBy(xpath = "//*[@id='mach-wizard']/div/div[3]/div/div/div[2]/div[4]/div[2]/div/div[1]/div/div[2]")
//  private List<WebElement> listOfDepartments;

  @FindBy(xpath = "//body/div[6]/div[3]/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[1]/table")
  private WebElement table;

  public int numberOfItems() {

    return table.findElements(By.cssSelector("tr")).size() - 1;
  }

  private static final String departmentItem = "//div[contains(text(),'%s')]";

  public void clickOnAdvanceConfiguration() {
    wait.until(ExpectedConditions.visibilityOf(advanceConfiguration));
    advanceConfiguration.click();
  }

  public void clickOnComboboxDepartment() {
    comboboxDepartment.click();
  }

  public void setDepartmentName(String departmentName) {
    wait.until(ExpectedConditions.visibilityOf(comboboxDepartment));
    wait.until(ExpectedConditions.elementToBeClickable(comboboxDepartment));

    comboboxDepartment.click();
    driver.findElement(By.xpath(String.format(departmentItem, departmentName))).click();
  }
  public int verifyCantDepartment(String department) {
    int cant = 0;
    for (WebElement element : listElementTable) {
      wait.until(ExpectedConditions.visibilityOf(element));
      if (element.getText().equals(department)) {
        cant++;
      }
    }
    return cant;
  }

  @FindBy(xpath = WidgetModel.menuDivisionsButton)
  WebElement menuDivisionsButton;

  @FindBy(xpath = WidgetModel.accountItem)
  WebElement accountItem;

  @FindBy(xpath = WidgetModel.ListElementListWidget)
  private List<WebElement> listElementListWidget;

  @FindBy(xpath = WidgetModel.selectorDivisions)
  List<WebElement> selectorDivisions;

  public WidgetPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, 10);
  }

  public void addWidget(String type) {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-component='widget']")));
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-component='widget']")));
    driver.findElement(By.xpath("//div[@class='name' and text()=" + "\'" + type + "\'" + "]")).click();
  }

  public void setDivisionName(String divisionName) {
    wait.until(ExpectedConditions.elementToBeClickable(menuDivisionsButton));
    menuDivisionsButton.click();
    for (WebElement element : selectorDivisions) {
      if (element.getText().trim().toUpperCase().equals(divisionName.trim().toUpperCase()))
        element.click();
    }
    menuDivisionsButton.click();
  }

  public void clickOnService(String service) {
    driver.findElement(By.xpath("//span[text()=" + "\'" + service + "\'" + "]")).click();
  }

  public void clickOnSpecificService(String specificService) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mach-wizard\"]//*[@class=\"wizard-provider-panel\"]")));
    driver.findElement(By.xpath("//h4[@class='ui header' and text()=" + "\'" + specificService + "\'" + "]")).click();
  }

  public void clickOnSpecificComboBox(String tag, String value) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='" + tag + "']/parent::*/following-sibling::div/i")));
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[text()='" + tag + "']/parent::*/following-sibling::div/i")));
    driver.findElement(By.xpath("//b[text()='" + tag + "']/parent::*/following-sibling::div/i")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='menu transition visible']/div[text()='" + value + "']")));
    driver.findElement(By.xpath("//div[@class='menu transition visible']/div[text()='" + value + "']")).click();
    driver.findElement(By.xpath("//b[text()='" + tag + "']/parent::*/following-sibling::div/i")).click();
  }


  public void clickOnSpecificCombobox() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mach-wizard\"]/div/div[3]/div/div/div[2]/div[4]/div")));
    driver.findElement(By.xpath("//*[@id=\"mach-wizard\"]/div/div[3]/div/div/div[2]/div[4]/div")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='field' and @data-key='Areas']//div[@class='item' and @data-value='33']")));
    driver.findElement(By.xpath("//div[@class='field' and @data-key='Areas']//div[@class='item' and @data-value='33']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wizard']/div")));
    driver.findElement(By.xpath("//*[@id='wizard']/div")).click();
  }


  public int verifyQtyEmployeeExperienceBySkillsCloud(String skillName) {
    int cant = 0;
    for (WebElement element : listElementListWidget) {
      if (element.getText().toUpperCase().contains(skillName.toUpperCase())) {
        cant++;
      }
    }
    return cant;
  }


  public void selectEiOption() {
    wait.until(ExpectedConditions.visibilityOf(eiOption));
    wait.until(ExpectedConditions.elementToBeClickable(eiOption));
    eiOption.click();
  }

  public void selectErpOption() {
    wait.until(ExpectedConditions.elementToBeClickable(erpOption));
    erpOption.click();
  }


  public void setManagerName(String managerName) {
    wait.until(ExpectedConditions.visibilityOf(dropDownList));
    wait.until(ExpectedConditions.elementToBeClickable(dropDownList));
    dropDownList.click();

    for (WebElement element : managerInput) {
      if (element.getText().equals(managerName)) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
      }
    }
    dropdownIcon.click();
  }


  public int verifyCant(String managerName) {
    int cant = 0;
    for (WebElement element : listElementTable) {
      wait.until(ExpectedConditions.visibilityOf(element));
      if (element.getText().equals(managerName)) {
        cant++;
      }
    }
    return cant;
  }

  public boolean verifyList(String name) {
    boolean result = false;
    for (WebElement element : listName) {
      wait.until(ExpectedConditions.visibilityOf(element));
      if (element.getText().equals(name))
        result = true;
    }
    return result;
  }

  public void clickAdvanceConfiguration() {
    wait.until(ExpectedConditions.elementToBeClickable(advanceConfig));
    advanceConfig.click();
  }



  public void clickSaveButton() {
    wait.until(ExpectedConditions.visibilityOf(saveButton));
    wait.until(ExpectedConditions.elementToBeClickable(saveButton));
    saveButton.click();
  }

  public String[] getListPersons() {
    List<WebElement> persons = driver.findElements(By.xpath("//div[@class='data']/div[@class='content']/child::div[@class='truncated text header']"));
    System.out.println(">>>>>>>Lista personas " + persons.size());
    String[] personsNames = new String[persons.size()];
    int index = 0;

    for (WebElement element : persons) {
      wait.until(ExpectedConditions.visibilityOf(element));
      personsNames[index] = element.getText();
      index++;
    }
    return personsNames;
  }

  public Boolean verifyPersonsByManager(List<ModelSearch> openList, String[] persons) {
    Boolean result = true;
    int index = 0;
    String[] openPersons = new String[openList.size()];

    System.out.println(">>>>>>>Tamaño OpenERP " + openList.size());
    System.out.println(">>>>>>>Tamaño Mach2 " + persons.length);
    Arrays.sort(persons);
    for (ModelSearch element : openList) {
      System.out.println(">>>>>Persona OpenERP " + element.getName());
      System.out.println(">>>>>Persona Mach2 " + persons[index]);
      openPersons[index] = element.getName();
      index++;
    }
    Arrays.sort(openPersons);
    return Arrays.equals(persons, openPersons);
  }
}
