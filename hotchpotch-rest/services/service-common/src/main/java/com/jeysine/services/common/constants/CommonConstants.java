package com.jeysine.services.common.constants;

/**
 * @author yaojx
 * @date 2018-10-09
 */
public class CommonConstants {
    public final static String SESSION_USERNAME = "LOGIN_NAME";
    public final static String SESSION_USER_ID = "USER_ID";
    /**
     * app 请求头部携带的token
     */
    public final static String APP_TOKEN = "JEYSINE_TOKEN";

    /**
     * Redis锁后缀
     */
    public final static String REDIS_LOCK_SUFFIX = "_MUTEX";

    /**
     * 通用数组分隔符
     */
    public final static String ARRAY_SPLIT = "##";

    public final static Integer USER_TOKEN_EXPIRE = 2 * 60 * 60 * 1000;

    public enum GenderEnum {
        /**
         * 男人
         */
        MALE(1, "男")
        /**
         * 女人
         */
        , FEMALE(2, "女");

        private Integer value;

        private String description;

        GenderEnum(Integer value, String description) {
            this.value = value;
            this.description = description;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public enum AdminUserStatusEnum {
        /**
         * 未激活
         */
        NOT_ACTIVE(0)
        /**
         * 激活
         */
        , ACTIVE(1)
        /**
         * 凍結
         */
        , FROZEN(2)
        ;
        private Integer value;

        AdminUserStatusEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public enum RedisKeyEnum {
        USER_TOKEN("USER_TOKEN_");

        private String code;

        RedisKeyEnum(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
