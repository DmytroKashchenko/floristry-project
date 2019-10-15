package ua.dmytrokashchenko.floristry.view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {
    INSTANCE;
    private ResourceBundle resourceBundle;
    private final String resourceName = "text";
    private ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
        System.out.println(resourceBundle);
    }
    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }
    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
