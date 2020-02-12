package com.example.zippy;

import com.example.zippy.api.Useri;
import com.example.zippy.serverresponse.UserResponse;
import com.example.zippy.url.Url;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.when;

public class LoginTestMockito {
    @Test
    public void TestWithMockito() {
//        String status;
//        String token = Url.token;
//        Useri useri = Mockito.mock(Useri.class);
//        final Call<UserResponse> mockCall = Mockito.mock(Call.class);
//        when(useri.login("username","password")).thenReturn(mockCall);
//
//        Mockito.doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                Callback<UserResponse> callback = invocation.getArguments(0, Callback.class);
//                callback.onResponse(mockCall, Response.success(new UserResponse()));
//
//                return_back null;
//            }
//        });
    }

}
