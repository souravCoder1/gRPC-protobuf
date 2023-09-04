package com.sourav.protobuf;

import com.sourav.protobuf.models.Credentials;
import com.sourav.protobuf.models.EmailCredentials;
import com.sourav.protobuf.models.PhoneOTP;

public class OneOfCredentials {
    public static void main(String[] args) {
        EmailCredentials emailCredentials = EmailCredentials.newBuilder()
                .setEmail("nobody@gmail.com")
                .setPassword("admin123")
                .build();

        PhoneOTP phoneOTP = PhoneOTP.newBuilder()
                .setNumber(1231231234)
                .setCode(456)
                .build();

        Credentials credentials = Credentials.newBuilder()
                .setEmailMode(emailCredentials)
                .setPhoneMode(phoneOTP) // If u set both, the last one will be picked
                .build();

        login(credentials);
    }

    private static void login(Credentials credentials) {
        switch (credentials.getModeCase()) {
            case EMAILMODE:
                System.out.println(credentials.getEmailMode());
                break;
            case PHONEMODE:
                System.out.println(credentials.getPhoneMode());
                break;
        }
    }
}
