package ua.pl.autotest4;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import ua.pl.autotest4.model.ProductData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.pl.autotest4.utils.Properties;
import ua.pl.autotest4.utils.logging.CustomReporter;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        driver.navigate().to(Properties.getBaseAdminUrl());
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".maintab")));
    }

    public void createProduct(ProductData newProduct) {
        String productName = newProduct.getName();
        String productQTY = newProduct.getQty().toString();
        String productPrice = newProduct.getPrice();

        Actions actions = new Actions(driver);
        By catalogMenuLocator = By.id("subtab-AdminCatalog");
        By productsMenuLocator = By.id("subtab-AdminProducts");
        By addProductBtnLocator =  By.id("page-header-desc-configuration-add");
        By nameFieldLocator = By.id("form_step1_name_1");
        By qtyFieldLocator = By.id("form_step1_qty_0_shortcut");
        By priceFieldLocator = By.id("form_step1_price_shortcut");
        By toastLocator = By.xpath("//div[contains(@class,'growl-message')]");
        By toastCloseBtnLocator = By.xpath("//div[contains(@class,'growl-close')]");
        By saveBtnLocator = By.id("submit");

        WebElement catalogMenuItem = driver.findElement(catalogMenuLocator);


        actions.moveToElement(catalogMenuItem).build().perform();

        waitForContentLoad(productsMenuLocator);
        catalogMenuItem.findElements(By.cssSelector("li")).get(0).click();

        waitForContentLoad(addProductBtnLocator);
        WebElement addProductBtn = driver.findElement(addProductBtnLocator);
        addProductBtn.click();


        waitForContentLoad(nameFieldLocator);
        driver.findElement(nameFieldLocator).sendKeys(productName);
        driver.findElement(qtyFieldLocator).sendKeys(productQTY);
        driver.findElement(priceFieldLocator).sendKeys(Keys.chord(Keys.CONTROL, "a"), productPrice);

        //Actions actions2 = new Actions(driver);
        WebElement switchBtn = driver.findElement(By.className("switch-input"));
        actions.moveToElement(switchBtn).build().perform();
        switchBtn.click();
        waitForContentLoad(toastLocator);
        driver.findElement(toastCloseBtnLocator).click();
        driver.findElement(saveBtnLocator).click();
        waitForContentLoad(toastLocator);
        driver.findElement(toastCloseBtnLocator).click();
    }

    public void checkProduct(ProductData productData) {
        driver.navigate().to(Properties.getBaseUrl());
        //waitForContentLoad(By.xpath(".//*[text()='Все товары']/.."));
        //driver.findElement(By.xpath(".//*[text()='Все товары']/..")).click();

    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
