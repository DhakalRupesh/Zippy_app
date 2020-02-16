package com.example.zippy;

import com.example.zippy.bbl.LoginBBL;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    @Test
    public void testLogin(){
        LoginBBL loginBBL = new LoginBBL();
        boolean result = loginBBL.checkUser("rd55", "rd55");
        assertEquals(false, result);
    }

}

