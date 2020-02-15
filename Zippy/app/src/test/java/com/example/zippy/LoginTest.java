package com.example.zippy;

import com.example.zippy.bbl.LoginBBL;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    String username;
    String password;
    @Test
    public void testLogin(){
        LoginBBL loginBBL = new LoginBBL();
        boolean result = loginBBL.checkUser("username", "password");
        assertEquals(true, result);
    }

    public void testRegister() {

    }
}

