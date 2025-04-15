package utils.data;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "validCredentials")
    public static Object[][] validUserData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}


        };
    }

    @DataProvider(name = "invalidCredentials")
    public static Object[][] invalidUserData() {
        return new Object[][] {
                {"", ""},
                {"jorge", "wrong_pass"},
                {"locked_out_user", "secret_sauce"}
        };
    }
}
