package ua.beast.oun.rest.appmanager;

public class ApplicationManager {

    private SessionHelper sessionHelper = new SessionHelper();

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
