package com.example.cekjadwal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.example.cekjadwal.DataAwal.Profil;
import com.example.cekjadwal.lojin.LoginData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String IS_LOGGED_IN="isLoggedIn";
    public static final String USER_ID="user_id";
    public static final String EMAIL="email";
    public static final String NAMA="nama";
    Profil profil;

    public SessionManager(Context context){
        this._context=context;
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        editor=sharedPreferences.edit();
    }
    public void createLoginSession(LoginData user){


        editor.putBoolean(IS_LOGGED_IN,true);
        editor.putString(USER_ID,user.getUserId());
        editor.putString(EMAIL,user.getEmail());
        editor.putString(NAMA,user.getNama());
        editor.commit();
    }
    public void createLoginSession(Profil user){
        profil=user;
        Gson gson=new Gson();
        String a=gson.toJson(user);
        editor.putString("MyObject", a);
        editor.commit();
    }
    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user=new HashMap<>();
        user.put(USER_ID,sharedPreferences.getString(USER_ID,null));
        user.put(EMAIL,sharedPreferences.getString(EMAIL,null));
        user.put(NAMA,sharedPreferences.getString(NAMA,null));
        return user;
    }
    public void logoutSession(){
        editor.clear();
        editor.commit();
    }
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }
    public boolean isNull(){
        return  mboh().getListCount()==0;
    }
    public Profil mboh(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("MyObject", null);

        Type type = new TypeToken<Profil>(){}.getType();
        Profil students = gson.fromJson(json, type);
        return students;
    }

}
