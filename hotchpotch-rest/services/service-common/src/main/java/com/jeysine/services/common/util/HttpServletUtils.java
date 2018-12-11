package com.jeysine.services.common.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jxyao
 * @date 2018-5-15
 */
public class HttpServletUtils {

    private final static String REQ_SOURCE_WECHAT = "wechat";
    private final static String REQ_SOURCE_APP = "app";
    private final static String REQ_SOURCE_WEB = "web";
    private final static String REQ_SOURCE_UNKNOW = "unknow";

    /**
     * 获取客户端地址，优先拿x-real-ip，然后是xff，然后是socket的地址 在SLB部署的情况下，nginx proxy的设置不能使用
     * proxy_set_header X-Real-IP $remote_addr; 因为nginx的remote_addr是SLB的？
     *
     * 无SLB的情况，不能使用XFF，因为有可能有客户端故意使用XFF来设置假地址
     *
     * @param request http请求
     * @return ip
     */
    public static String getRealIp(HttpServletRequest request) {
        String realIp = request.getHeader("X-Real-IP");
        String xff = request.getHeader("X-Forwarded-For");
        // nginx 前边如果有SLB就不设置realIP，有realIP说明没有用SLB
        if (!StringUtils.isEmpty(realIp)) {
            return realIp;
        }
        if (!StringUtils.isEmpty(xff)) {
            // XFF header format X-Forwarded-For: client, SLB
            String[] forwardIps = xff.split(",");
            if (forwardIps.length > 0) {
                return forwardIps[0];
            } else {
                return request.getRemoteAddr();
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 获得请求的设备来源
     *
     * @param request http请求
     * @return 设备来源
     */
    public static String getDeviceType(HttpServletRequest request) {
        String agent = request.getHeader("user-agent");

        if (StringUtils.isNotEmpty(agent)) {
            agent = agent.toLowerCase().trim();
        } else {
            return REQ_SOURCE_UNKNOW;
        }

        if (agent.contains("micromessenger")) {
            return REQ_SOURCE_WECHAT;
        }

        String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile" };

        if (ArrayUtils.contains(mobileAgents, agent)) {
            return REQ_SOURCE_APP;
        }

        return REQ_SOURCE_WEB;
    }

    /**
     * 获取Host，优先拿X-Forwarded-Proto，Host
     *
     * @param request 请求
     * @return host
     */
    public static String getRealHost(HttpServletRequest request) {
        String realProto = request.getHeader("X-Forwarded-Proto");
        return realProto + "://" + request.getServerName();
    }

}
