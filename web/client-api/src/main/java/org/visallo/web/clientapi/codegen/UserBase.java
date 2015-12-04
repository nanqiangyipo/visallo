package org.visallo.web.clientapi.codegen;

import org.json.JSONObject;
import org.visallo.web.clientapi.CategoryBase;
import org.visallo.web.clientapi.VisalloApi;
import org.visallo.web.clientapi.model.*;
import org.visallo.web.clientapi.util.*;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

public abstract class UserBase extends CategoryBase {
    public UserBase(VisalloApi visalloApi) {
        super(visalloApi);
    }

    /**
     */
    public ClientApiUser getMe(
        
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        return getVisalloApi().execute("GET", "/user/me", parameters, ClientApiUser.class);
    }

    /**
     * @param q OPTIONAL
     * @param workspaceId OPTIONAL
     * @param userIds OPTIONAL
     * @param status OPTIONAL
     * @param skip OPTIONAL
     * @param limit OPTIONAL
     */
    public ClientApiUsers getAll(
        @Optional(name = "q") String q,
        @Optional(name = "workspaceId") String workspaceId,
        @Optional(name = "userIds[]") String[] userIds,
        @Optional(name = "status") String status,
        @Optional(name = "skip", defaultValue = "0") int skip,
        @Optional(name = "limit", defaultValue = "100") int limit
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        parameters.add(new VisalloApiBase.Parameter("q", q));
        parameters.add(new VisalloApiBase.Parameter("workspaceId", workspaceId));
        parameters.add(new VisalloApiBase.Parameter("userIds[]", userIds));
        parameters.add(new VisalloApiBase.Parameter("status", status));
        parameters.add(new VisalloApiBase.Parameter("skip", skip));
        parameters.add(new VisalloApiBase.Parameter("limit", limit));
        return getVisalloApi().execute("GET", "/user/all", parameters, ClientApiUsers.class);
    }

    /**
     * @param userName REQUIRED
     */
    public ClientApiUser get(
        @Required(name = "user-name") String userName
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        parameters.add(new VisalloApiBase.Parameter("user-name", userName));
        return getVisalloApi().execute("GET", "/user", parameters, ClientApiUser.class);
    }

    /**
     * @param uiPreferences OPTIONAL
     * @param name OPTIONAL
     * @param value OPTIONAL
     */
    public JSONObject postUiPreferences(
        @Optional(name = "ui-preferences") String uiPreferences,
        @Optional(name = "name") String name,
        @Optional(name = "value") String value
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        parameters.add(new VisalloApiBase.Parameter("ui-preferences", uiPreferences));
        parameters.add(new VisalloApiBase.Parameter("name", name));
        parameters.add(new VisalloApiBase.Parameter("value", value));
        return getVisalloApi().execute("POST", "/user/ui-preferences", parameters, JSONObject.class);
    }

    /**
     * @param q OPTIONAL
     * @param workspaceId OPTIONAL
     * @param userIds OPTIONAL
     * @param status OPTIONAL
     * @param skip OPTIONAL
     * @param limit OPTIONAL
     */
    public ClientApiUsers postAll(
        @Optional(name = "q") String q,
        @Optional(name = "workspaceId") String workspaceId,
        @Optional(name = "userIds[]") String[] userIds,
        @Optional(name = "status") String status,
        @Optional(name = "skip", defaultValue = "0") int skip,
        @Optional(name = "limit", defaultValue = "100") int limit
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        parameters.add(new VisalloApiBase.Parameter("q", q));
        parameters.add(new VisalloApiBase.Parameter("workspaceId", workspaceId));
        parameters.add(new VisalloApiBase.Parameter("userIds[]", userIds));
        parameters.add(new VisalloApiBase.Parameter("status", status));
        parameters.add(new VisalloApiBase.Parameter("skip", skip));
        parameters.add(new VisalloApiBase.Parameter("limit", limit));
        return getVisalloApi().execute("POST", "/user/all", parameters, ClientApiUsers.class);
    }

    /**
     * @param userName REQUIRED
     * @param auth REQUIRED
     */
    public JSONObject postAuthAdd(
        @Required(name = "user-name") String userName,
        @Required(name = "auth") String auth
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        parameters.add(new VisalloApiBase.Parameter("user-name", userName));
        parameters.add(new VisalloApiBase.Parameter("auth", auth));
        return getVisalloApi().execute("POST", "/user/auth/add", parameters, JSONObject.class);
    }

    /**
     * @param userName REQUIRED
     * @param auth REQUIRED
     */
    public JSONObject postAuthRemove(
        @Required(name = "user-name") String userName,
        @Required(name = "auth") String auth
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        parameters.add(new VisalloApiBase.Parameter("user-name", userName));
        parameters.add(new VisalloApiBase.Parameter("auth", auth));
        return getVisalloApi().execute("POST", "/user/auth/remove", parameters, JSONObject.class);
    }

    /**
     * @param userName REQUIRED
     */
    public void postDelete(
        @Required(name = "user-name") String userName
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        parameters.add(new VisalloApiBase.Parameter("user-name", userName));
        getVisalloApi().execute("POST", "/user/delete", parameters, null);
    }

    /**
     * @param userName REQUIRED
     * @param privileges REQUIRED
     */
    public JSONObject postPrivilegesUpdate(
        @Required(name = "user-name") String userName,
        @Required(name = "privileges") String privileges
    ) {
        List<VisalloApiBase.Parameter> parameters = new ArrayList<VisalloApiBase.Parameter>();
        parameters.add(new VisalloApiBase.Parameter("user-name", userName));
        parameters.add(new VisalloApiBase.Parameter("privileges", privileges));
        return getVisalloApi().execute("POST", "/user/privileges/update", parameters, JSONObject.class);
    }

}