package org.eyeseetea.connect2connectexampleapp.model;

import java.util.HashMap;

public class ConnectVoucher {
    private auth auth;
    private HashMap<String, String> values;

    public ConnectVoucher() {
    }

    public ConnectVoucher(org.eyeseetea.connect2connectexampleapp.model.auth auth,
            HashMap<String, String> values) {
        this.auth = auth;
        this.values = values;
    }

    public org.eyeseetea.connect2connectexampleapp.model.auth getAuth() {
        return auth;
    }

    public HashMap<String, String> getValues() {
        return values;
    }
}
