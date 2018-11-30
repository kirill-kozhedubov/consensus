package iq.ven.portal.consensus.common.util.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

public class CookieHelper {


    protected static final Logger logger = LoggerFactory.getLogger(CookieHelper.class);
    public static final String SESSION_COOKIE = "session_cookie";
    public static final String DEFAULT_COOKIES_ENCODING = "UTF-8";


    public static void addCookie(HttpServletResponse response, String name, String value, boolean needsEncoding, Integer maxAge) {
        if (response == null || StringUtils.isEmpty(name)) {
            return;
        }

        try {
            String safeCookieName = needsEncoding ? encode(name) : name;
            String safeCookieValue = needsEncoding ? encode(value) : value;
            Cookie cookie = new Cookie(safeCookieName, safeCookieValue);
            cookie.setPath("/");
            if (maxAge != null) {
                cookie.setMaxAge(maxAge);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            logger.error("addCookie error", e);
        }
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        addCookie(response, name, "", false, 0);
    }

    public static void removeCookies(HttpServletResponse response, List<String> names) {
        if (names != null) {
            for (String name : names) {
                addCookie(response, name, "", false, 0);
            }
        }
    }

    public static void removeRequestCookieValues(HttpServletRequest request, List<String> names) {
        if (request != null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (names.contains(cookie.getName())) {
                    cookie.setValue("");
                }
            }
        }
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            try {
                //decoded cookie name (can be not the same as in browser)
                String unSafeCookieName = decode(cookie.getName());
                if (name.equals(unSafeCookieName)) {
                    return new Cookie(unSafeCookieName, decode(cookie.getValue()));
                }
            } catch (UnsupportedEncodingException e) {
                logger.error("getCookie decoding error", e);
            }
        }
        return null;
    }

    public static Cookie getSessionCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            try {
                String unSafeCookieName = decode(cookie.getName());
                if (SESSION_COOKIE.equals(unSafeCookieName)) {
                    return new Cookie(unSafeCookieName, decode(cookie.getValue()));
                }
            } catch (UnsupportedEncodingException e) {
                logger.error("getCookie decoding error", e);
            }
        }
        return null;
    }

    public static String encode(String string) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, DEFAULT_COOKIES_ENCODING);
    }

    public static String decode(String string) throws UnsupportedEncodingException {
        return URLDecoder.decode(string, DEFAULT_COOKIES_ENCODING);
    }


}
