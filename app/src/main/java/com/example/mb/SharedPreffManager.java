package com.example.mb;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SharedPreffManager {
    SharedPreferences sharedPreferences;
    SharedPreferences emailPreferences;
    SharedPreferences settings;
    SharedPreferences historyPrefs;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor emailEditor;
    SharedPreferences.Editor settingsEditor;
    SharedPreferences.Editor historyEditor;
    public static final String FieldsRecords = "fields";
    public static final String EmailRecords = "emails";
    public static final String Settings = "settings";
    public static final String HistoryRecords = "history";

    public SharedPreffManager(Context _context){
        sharedPreferences = _context.getSharedPreferences(FieldsRecords, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        emailPreferences = _context.getSharedPreferences(EmailRecords,Context.MODE_PRIVATE);
        emailEditor = emailPreferences.edit();
        settings = _context.getSharedPreferences(Settings,Context.MODE_PRIVATE);
        settingsEditor = settings.edit();
        historyPrefs = _context.getSharedPreferences(HistoryRecords,Context.MODE_PRIVATE);
        historyEditor = historyPrefs.edit();
    }
    // colors
    public void AddValue(String _fieldName,float _fieldValue){
        editor.putFloat(_fieldName,_fieldValue);
        editor.commit();
    }
    public void DeleteValue(String _filedName){
        editor.remove(_filedName);
        editor.commit();
    }
    public void EditValue(String _fieldName,float _fieldValue){
        editor.putFloat(_fieldName,_fieldValue);
        editor.commit();
    }
    public SharedPreferences GetPrefs(){
        // грешен резултат !!!!
        return sharedPreferences;
    }

    public void AddEmail(String email){
        int id = emailPreferences.getAll().size();
        emailEditor.putString(Integer.toString(id+1),email);
        emailEditor.commit();
    }
    public void RemoveEmail(String id){
        emailEditor.remove(id);
        emailEditor.commit();
    }
    public void EditEmail(String id,String email){
        emailEditor.putString(id,email);
        emailEditor.commit();
    }
    public SharedPreferences GetEmailPrefs(){
        return emailPreferences;
    }

    // settings management
    public void SetWarningValue(float value){
        settingsEditor.putFloat("warningValue",value);
        settingsEditor.commit();
    }
    public float GetWarningValue(){
        return settings.getFloat("warningValue",0.0f);
    }
    public void SaveCheckButtonState(String docType,boolean state){
        settingsEditor.putBoolean(docType,state);
        settingsEditor.commit();
    }
    public boolean getCheckButtonState(String docType){
        return settings.getBoolean(docType,false);
    }

    // history
    public void AddToHistory(String key, String fileUri){
        historyEditor.putString(key,fileUri);
        historyEditor.commit();
    }
    public SharedPreferences getHistory(){
        return historyPrefs;
    }

    public void ResetAll(){
        historyEditor.clear();
        historyEditor.commit();
        settingsEditor.clear();
        settingsEditor.commit();
        emailEditor.clear();
        emailEditor.commit();
        editor.clear();
        editor.commit();
    }
}
