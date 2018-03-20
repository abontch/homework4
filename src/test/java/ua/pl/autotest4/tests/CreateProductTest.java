package ua.pl.autotest4.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import ua.pl.autotest4.BaseTest;
import org.testng.annotations.Test;
import ua.pl.autotest4.GeneralActions;
import ua.pl.autotest4.model.ProductData;
import ua.pl.autotest4.utils.Properties;
import ua.pl.autotest4.utils.logging.CustomReporter;

public class CreateProductTest extends BaseTest {
    ProductData product = ProductData.generate();

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new String[][] { { "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw" }};
    }

    @Test(dataProvider = "Authentication")
    public void createNewProduct(String login, String password) {
        CustomReporter.logAction("User Login");
        //GeneralActions actions = new GeneralActions(driver);
        actions.login(login, password);
        CustomReporter.logAction("Creating new product");
        actions.createProduct(product);

    }

    // TODO implement logic to check product visibility on website
   @Test (dependsOnMethods = "createNewProduct")
    public void checkProductVisibility() {
       CustomReporter.logAction("Checking product visibility");
       actions.checkProduct(product);
   }
}
