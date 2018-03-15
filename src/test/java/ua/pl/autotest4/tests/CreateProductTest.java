package ua.pl.autotest4.tests;

import org.testng.annotations.DataProvider;
import ua.pl.autotest4.BaseTest;
import org.testng.annotations.Test;

public class CreateProductTest extends BaseTest {

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        return new Object[][] { { "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw" }};

    }

    @Test(dataProvider = "Authentication")
    public void createNewProduct(String login, String password) {
        // TODO implement test for product creation

        // actions.login(login, password);
        // ...
    }

    // TODO implement logic to check product visibility on website
}
