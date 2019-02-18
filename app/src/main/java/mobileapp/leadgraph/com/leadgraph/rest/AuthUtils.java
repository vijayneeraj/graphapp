package mobileapp.leadgraph.com.leadgraph.rest;

import android.util.Base64;

/**
 * Created by ubuntu on 1/8/16.
 */
public class AuthUtils {

    public static String basic( String password) {

        String basicAuth =
                Base64.encodeToString(password.getBytes(),Base64.NO_WRAP);

        return basicAuth;
    }
}
