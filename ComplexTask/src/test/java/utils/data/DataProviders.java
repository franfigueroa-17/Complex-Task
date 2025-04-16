package utils.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProviders {

    public static Stream<Arguments> validCredentials() {
        return Stream.of(
                Arguments.of("standard_user", "secret_sauce"),
                Arguments.of("problem_user", "secret_sauce"),
                Arguments.of("performance_glitch_user", "secret_sauce"),
                Arguments.of("error_user", "secret_sauce"),
                Arguments.of("visual_user", "secret_sauce")
        );
    }

    public static Stream<Arguments> invalidCredentials() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("jorge", "wrong_pass"),
                Arguments.of("locked_out_user", "secret_sauce")
        );
    }
}
