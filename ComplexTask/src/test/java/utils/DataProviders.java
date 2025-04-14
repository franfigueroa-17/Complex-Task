package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "validCredentials")
    public static Object[][] validUserData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
//                {"locked_out_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidCredentials")
    public static Object[][] invalidUserData() {
        return new Object[][] {
                {"", ""},
                {"pepito", "wrong_pass"},
        };
    }
}
