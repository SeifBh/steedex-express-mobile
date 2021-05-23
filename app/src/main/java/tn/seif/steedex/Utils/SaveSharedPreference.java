package tn.seif.steedex.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

import static tn.seif.steedex.Utils.PreferencesUtility.ADRESSE_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.EMAIL_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.FISCALE_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.FRAIS_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.ID_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.LOGGED_IN_PREF;
import static tn.seif.steedex.Utils.PreferencesUtility.NOM_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.PASSWORD_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.PRENOM_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.ROLES_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.TEL_USER;
import static tn.seif.steedex.Utils.PreferencesUtility.USERNAME_USER;



public class SaveSharedPreference {
    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }
    public static void setUserCredentials(Context context,String id, String nom,String tel,String roles,String prenom,String adresse,String email,String frais,String username,String fiscale,String password) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(ID_USER, id);
        editor.putString(NOM_USER, nom);
        editor.putString(PRENOM_USER, prenom);
        editor.putString(ADRESSE_USER, adresse);
        editor.putString(EMAIL_USER, email);
        editor.putString(TEL_USER, tel);
        editor.putString(ROLES_USER, roles);
        editor.putString(FRAIS_USER, frais);
        editor.putString(USERNAME_USER, username);
        editor.putString(PASSWORD_USER, password);
        editor.apply();
    }


    public static void removeUserC(Context context,String id) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.remove(id);
        editor.apply();
    }


    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserFiscale(Context context) {
        return getPreferences(context).getString(FISCALE_USER,"fiscale");
    }


    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserId(Context context) {
        return getPreferences(context).getString(ID_USER,"id");
    }



    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserFrais(Context context) {
        return getPreferences(context).getString(FRAIS_USER,"frais");
    }




    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserAdresse(Context context) {
        return getPreferences(context).getString(ADRESSE_USER,"adresse");
    }


    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserTel(Context context) {
        return getPreferences(context).getString(TEL_USER,"tel");
    }


    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserEmail(Context context) {
        return getPreferences(context).getString(EMAIL_USER,"email");
    }

    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserPrenom(Context context) {
        return getPreferences(context).getString(PRENOM_USER,"prenom");
    }


    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserPassword(Context context) {
        return getPreferences(context).getString(PASSWORD_USER,"password");
    }


    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserNom(Context context) {
        return getPreferences(context).getString(NOM_USER,"nom");
    }


    /**
     * Get the ID value
     * @param context
     * @return boolean: id value
     */
    public static String getUserName2(Context context) {
        return getPreferences(context).getString(USERNAME_USER,"username");
    }




    /**
     * Get the roles value
     * @param context
     * @return boolean: id value
     */
    public static String getUserRoles(Context context) {
        return getPreferences(context).getString(ROLES_USER,"roles");
    }


}
