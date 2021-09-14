package net.jandie1505.JSONConfigManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

public class JSONRawConfig {
    private boolean verboseLogging;
    private String jsonString;

    /**
     * Creates JSONConfig by File Object
     * @throws IOException
     */
    public JSONRawConfig() {
        setVerbose(false);
        init();
    }
    
    public JSONRawConfig(String jsonString) {
        setVerbose(false);
        init();
        JSONObject jsonObject = new JSONObject(jsonString);
        save(jsonObject);
    }

    /**
     * Returns the raw JSON String
     * @return JSON String (String)
     */
    public String getJSONString(){
        return jsonString;
    }

    /**
     * Get the JSONObject of the JSONConfig
     * @return JSONObject
     * @throws IOException
     */
    public JSONObject getJSONObject() {
        return load();
    }

    /**
     * Override the JSONObject.
     * Not recommended to use this.
     * @param jsonObject
     * @throws IOException
     * @deprecated Use overrideJSONObject instead
     */
    @Deprecated
    public void setJSONObject(JSONObject jsonObject) {
        save(jsonObject);
    }
    /**
     * Override the JSONObject.
     * Not recommended to use this.
     * @param jsonObject
     * @throws IOException
     */
    public void overrideJSONObject(JSONObject jsonObject) {
        save(jsonObject);
    }

    public void put(String key, Object value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, int value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, boolean value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, String value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, double value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, long value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, float value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, Map value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, Collection value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, JSONObject value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, JSONArray value) {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }

    public Object get(String key) {
        JSONObject jsonObject = load();
        return jsonObject.get(key);
    }
    public String getString(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getString(key);
    }
    public int getInt(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getInt(key);
    }
    public boolean getBoolean(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getBoolean(key);
    }
    public float getFloat(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getFloat(key);
    }
    public double getDouble(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getDouble(key);
    }
    public JSONArray getJSONArray(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getJSONArray(key);
    }
    public BigDecimal getBigDecimal(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getBigDecimal(key);
    }
    public BigInteger getBigInteger(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getBigInteger(key);
    }
    public JSONObject getJSONObject(String key) {
        JSONObject jsonObject = load();
        return jsonObject.getJSONObject(key);
    }

    public boolean has(String key) {
        JSONObject jsonObject = load();
        return jsonObject.has(key);
    }

    /**
     * Removes a key from the JSONConfig.
     * @param key
     * @throws IOException
     */
    public void remove(String key) {
        JSONObject jsonObject = load();
        jsonObject.remove(key);
        save(jsonObject);
    }

    /**
     * Returns the JSONObject as a String.
     * @return String
     */
    @Override
    public String toString(){
        JSONObject jsonObject = null;
        jsonObject = load();
        return jsonObject.toString();
    }

    /**
     * Returns an Array with the keys in it (for for each loops).
     * @return Object[] keyArray
     * @throws IOException
     */
    public Object[] getKeyArray() {
        JSONObject jsonObject = load();
        return jsonObject.keySet().toArray();
    }

    /**
     * Clears the JSONConfig.
     * @throws IOException
     */
    public void clear() {
        JSONObject jsonObject = load();
        jsonObject.clear();
        save(jsonObject);
    }

    /**
     * Enable/Disable verbose logging.
     * @param verbose
     */
    public void setVerbose(boolean verbose) {
        verboseLogging = verbose;
    }

    private void init() {
        JSONObject jsonObject = new JSONObject();
        jsonString = jsonObject.toString();
        load();
        verbose("Initialized JSONConfig");
    }

    private JSONObject load() {
        JSONObject jsonObject = new JSONObject(jsonString);
        verbose("Loaded JSON String");
        return jsonObject;
    }

    private void save(JSONObject jsonObject) {
        jsonString = jsonObject.toString();
        verbose("Saved JSON String");
    }

    private void verbose(String message) {
        if(verboseLogging){
            System.out.println("[JSONConfig] " + message);
        }
    }

    private static void message(String message) {
        System.out.println("[JSONConfig] " + message);
    }
}
