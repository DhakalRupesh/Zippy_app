package com.example.zippy.bbl;

import com.example.zippy.api.Useri;
import com.example.zippy.serverresponse.UserResponse;
import com.example.zippy.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBBL {
//    private final String username;
//    private final String password;
    boolean isSuccess = false;

//    public LoginBBL(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    public boolean checkUser(String username, String password) {
        Useri useri = Url.getInstance().create(Useri.class);
        Call<UserResponse> signUpResponseCall =useri.login(username, password);

        try {
            Response<UserResponse> loginResponse = signUpResponseCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
