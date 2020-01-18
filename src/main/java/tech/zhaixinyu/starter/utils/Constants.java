package tech.zhaixinyu.starter.utils;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.utils
 * ClassName: Constant
 * Description: Description
 * Created by @author Xinyu on 1/18/20 21:36
 */
public class Constants {
    /**
     * Token
     */
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_ISSUER = "auth0";
    public static final String SECRET_KEY = "tech.zhaixinyu";
    public static final Long EXPIRED_ACCESS = 900000L;
    public static final Long EXPIRED_REFRESH = 30 * 24 * 3600000L;

    /**
     * Security
     */
    public static final String LOGIN_ENDPOINT = "/api/auth/login";
    public static final String RESOURCE_ENDPOINT = "/api/resource/";
    public static final String ERROR_ENDPOINT = "/error";
}
