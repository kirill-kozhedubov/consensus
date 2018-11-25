package iq.ven.portal.consensus.common.util.helpers;

import org.springframework.util.StringUtils;

public class TemplatesHelper {
    public static final String PAGE_TITLE = "pageTitle";


    //mappings constants
    public static final String DASHBOARD_URL = "/user/dashboard";
    public static final String LOGIN_URL = "/sso/login";
    public static final String REGISTRATION_URL = "/sso/registration";

    public static final String PROJECT_URL = "/projects/project/{projectAbbr}";
    public static final String CREATE_PROJECT_URL = "/projects/create";
    public static final String ISSUE_URL = "/issues/{projectKey}-{issueId}";
    public static final String CREATE_ISSUE_URL = "/issues/create";

    public static final String _URL = "/";


    public static Long transformStringToLong(String id) {
        if (StringUtils.isEmpty(id))
            return null;

        if (id.matches("[0-9]+") && id.length() > 0) {
            return Long.parseLong(id);
        }

        return null;
    }


}
