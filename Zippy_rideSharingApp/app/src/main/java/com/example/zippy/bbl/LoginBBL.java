package com.example.zippy.bbl;

import com.example.zippy.interface_zippy.Useri;
import com.example.zippy.serverresponse.SignUpResponse;
import com.example.zippy.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBBL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {
        Useri useri = Url.getInstance().create(Useri.class);
        Call<SignUpResponse> signUpResponseCall =useri.login(username, password);

        try {
            Response<SignUpResponse> loginResponse = signUpResponseCall.execute();
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
