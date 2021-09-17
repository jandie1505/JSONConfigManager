package net.jandie1505.JSONConfigManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

public class JSONSubConfig {
    private String name;
    private Object parentConfig;
    private boolean verboseLogging;

    /**
     * Initialize JSONSubConfig with a JSONConfig as parent config
     * @param keyName
     * @param parent

     */
    public JSONSubConfig(String keyName, JSONFileConfig parent) {
        name = keyName;
        parentConfig = parent;
        setVerbose(false);
        init();
    }

    /**
     * Initialize JSONSubConfig with another JSONSubConfig as parent config
     * @param keyName
     * @param parent
     */
    public JSONSubConfig(String keyName, JSONSubConfig parent) {
        name = keyName;
        parentConfig = parent;
        setVerbose(false);
        init();
    }

    /**
     * Initialize JSONSubConfig with a JSONStringConfig as parent config
     * @param keyName
     * @param parent
     */
    public JSONSubConfig(String keyName, JSONStringConfig parent) {
        name = keyName;
        parentConfig = parent;
        setVerbose(false);
        init();
    }

    /**
     * This method returns the parent config. You need to cast the Object to JSONConfig or JSONSubConfig.
     * @return Object
     */
    public Object getParent(){
        return parentConfig;
    }

    /**
     * Get the JSONObject of the JSONConfig
     * @return JSONObject
     */
    public JSONObject getJSONObject() {
        return load();
    }

    /**
     * Override the JSONObject.
     * Not recommended to use this.
     * @param jsonObject
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
     */
    public Object[] getKeyArray() {
        JSONObject jsonObject = load();
        return jsonObject.keySet().toArray();
    }

    /**
     * Clears the JSONConfig.
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
        try {
            if(parentConfig instanceof JSONFileConfig) {
                if(!((JSONFileConfig) parentConfig).has(name)) {
                    JSONObject jsonObject = new JSONObject();
                    save(jsonObject);
                } else {
                    JSONObject jsonObject = load();
                    save(jsonObject);
                }
            } else if(parentConfig instanceof JSONSubConfig) {
                if(!((JSONSubConfig) parentConfig).has(name)) {
                    JSONObject jsonObject = new JSONObject();
                    save(jsonObject);
                } else {
                    JSONObject jsonObject = load();
                    save(jsonObject);
                }
            } else if(parentConfig instanceof JSONStringConfig) {
                if(!((JSONStringConfig) parentConfig).has(name)) {
                    JSONObject jsonObject = new JSONObject();
                    save(jsonObject);
                } else {
                    JSONObject jsonObject = load();
                    save(jsonObject);
                }
            } else {
                message("An error occured while setting up the JSONConfig");
            }
        } catch(IOException e) {
            System.out.println("IOException in JSONSubConfig");
            e.printStackTrace();
        }
    }

    private JSONObject load() {
        try {
            if(parentConfig instanceof JSONFileConfig) {
                verbose("Loaded subconfig");
                return ((JSONFileConfig) parentConfig).getJSONObject(name);
            } else if(parentConfig instanceof JSONSubConfig) {
                verbose("Loaded subconfig");
                return ((JSONSubConfig) parentConfig).getJSONObject(name);
            } else if(parentConfig instanceof JSONStringConfig) {
                verbose("Loaded subconfig");
                return ((JSONStringConfig) parentConfig).getJSONObject(name);
            } else {
                verbose("An error occured while loading the subconfig");
                return null;
            }
        } catch(IOException e) {
            System.out.println("IOException in JSONSubConfig");
            e.printStackTrace();
        }
        return null;
    }

    private void save(JSONObject jsonObject) {
        try {
            if(parentConfig instanceof JSONFileConfig){
                ((JSONFileConfig) parentConfig).put(name, jsonObject);
                verbose("Saved subconfig");
            } else if(parentConfig instanceof JSONSubConfig){
                ((JSONSubConfig) parentConfig).put(name, jsonObject);
                verbose("Saved subconfig");
            } else if(parentConfig instanceof JSONStringConfig) {
                ((JSONStringConfig) parentConfig).put(name, jsonObject);
                verbose("Saved subconfig");
            } else {
                verbose("An error occured while saving the subconfig");
            }
        } catch(Exception e) {
            System.out.println("IOException in JSONSubConfig");
            e.printStackTrace();
        }
    }

    private void verbose(String message) {
        if(verboseLogging){
            System.out.println("[JSONSubConfig] " + message);
        }
    }

    private static void message(String message) {
        System.out.println("[JSONSubConfig] " + message);
    }
}
