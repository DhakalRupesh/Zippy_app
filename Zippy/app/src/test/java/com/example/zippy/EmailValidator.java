package com.example.zippy;
import com.example.zippy.helper.EmailValidatorInput;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmailValidator {

    EmailValidatorInput emailValidatorInput=new EmailValidatorInput();
    @Test
    public void email_valid() {
        assertTrue(emailValidatorInput.isEmailValid("rupesh@email.com"));
        assertTrue(emailValidatorInput.isEmailValid("rupesh@gmail.com"));

    }
    @Test
    public void email_invalid() {
        assertFalse(emailValidatorInput.isEmailValid("email.com"));
        assertFalse(emailValidatorInput.isEmailValid("@email.com"));

    }

}
