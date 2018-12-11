package com.jeysine.services.common.constants;

/**
 * @author yaojx
 * @date 2018-10-09
 */
public class CommonConstants {
    public final static String SESSION_USERNAME = "LOGIN_NAME";
    public final static String SESSION_USER_ID = "USER_ID";
    public final static String SESSION_ACCID = "ACCID";
    /**
     * app 请求头部携带的token
     */
    public final static String APP_TOKEN = "TtTravel-Token";
    /**
     * 存放客服状态
     */
    public final static String REDIS_KEY_CUSTOM_STATUS = "CUSTOM_STATUS";

    /**
     * 存放盲人状态
     */
    public final static String REDIS_KEY_BLIND_STATUS = "BLIND_STATUS";

    /**
     * Redis锁后缀
     */
    public final static String REDIS_LOCK_SUFFIX = "_MUTEX";

    /**
     * 盲人协助请求接受锁, 解决并发接受盲人协助的问题
     */
    public final static String REDIS_LOCK_BLIND_HELP = "BLIND_HELP" + REDIS_LOCK_SUFFIX;

    /**
     * 存放盲人对应亲属的帐号
     */
    public final static String REDIS_KEY_BLIND_RELATIVES = "BLIND_RELATIVES";

    /**
     * 存放志愿者帐号
     */
    public final static String REDIS_KEY_VOLUNTEER = "VOLUNTEER_LIST";

    /**
     * 存放客服列表
     */
    public final static String REDIS_KEY_CUSTOM = "CUSTOM_LIST";

    /**
     * 通用数组分隔符
     */
    public final static String ARRAY_SPLIT = "##";

    public final static String HELP_QUEUE = "chat";

    public enum CustomStatus {
        /**
         * 客服忙碌
         */
        BUSY
        /**
         * 客服空闲
         */
        , FREE
    }

    public static enum GlassUserCategoryEnum {
        /**
         *  盲人
         */
        BLIND("1")
        /**
         * 亲属
         */
        , RELATIVES("2")
        /**
         * 客服
         */
        , CUSTOMER("8")
        /**
         * 志愿者
         */
        , VOLUNTEER("4")

        /**
         * 亲属和志愿者
         */
        , RELATIVES_AND_VOLUNTEER("6")
        ;
        private String value;

        GlassUserCategoryEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static GlassUserCategoryEnum getByValue(String value) {
            GlassUserCategoryEnum[] categoryEnums = GlassUserCategoryEnum.values();
            for (GlassUserCategoryEnum one: categoryEnums) {
                if (value.equals(one.getValue())) {
                    return one;
                }
            }
            return null;
        }
    }

    public static enum GenderEnum {
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

    public enum NotificationHandleType {
        /**
         * 客服
         */
        CUSTOM

        /**
         * 親屬
         */
        , FAMILY

        /**
         * 志願者
         */
        , VOLUNTEER
    }
}
