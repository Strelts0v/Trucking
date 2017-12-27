package com.itechart.trucking.auth.encoder;

import com.itechart.trucking.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for the SHA256PasswordEncoder.
 *
 * @see SHA256PasswordEncoder
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class SHA256PasswordEncoderTest {

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void encodeShouldReturnCorrespondString() throws Exception {
        final String originStr = "test";
        final String encodeStr = encoder.encode(originStr);
        final String expectedStr = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";

        final String errorMessage = "Expected and actual strings are different";
        Assert.assertEquals(errorMessage, expectedStr, encodeStr);
    }

}