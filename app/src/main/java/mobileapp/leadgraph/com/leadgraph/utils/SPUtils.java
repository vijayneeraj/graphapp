package mobileapp.leadgraph.com.leadgraph.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.prefs.PreferenceChangeEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mobileapp.leadgraph.com.leadgraph.rest.ParamName;

/**
 * @author neeraj on 20/12/18.
 */
public class SPUtils {
    public static final String PREFS="leadgraph";
    public static final String LOGIN="login";
    public static final String LOGIN_ID="login_id";
    public static final String USER_ID="user_id";
    public static final String COMPANY_USER_ID="comapny_user_id";
    public static final String EMPLOY_NAME="emplaoy_name";
    public static final String COMPANY_NAME="company_name";
    public static boolean isValidateEmail(String email) {
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;

        }
        return true;
    }
    public static void setLogin(boolean v, Context context){
        SharedPreferences.Editor editor=context.getSharedPreferences(PREFS,Context.MODE_PRIVATE).edit();
        editor.putBoolean(LOGIN,v);
        editor.commit();

    }
    public static boolean getLogin(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(LOGIN,false);
    }
    public static void setString(Context context,String s,String TAG){
        SharedPreferences.Editor editor=context.getSharedPreferences(PREFS,Context.MODE_PRIVATE).edit();
        editor.putString(TAG,s);
        editor.commit();
    }
    public static String getString(Context context,String tag){
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        return sharedPreferences.getString(tag,"");
    }
}
