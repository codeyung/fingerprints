package com.example.demo;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2018/7/3.下午2:07
 */
@RestController
public class TestController {


    private static Map<String, String> clients = new HashMap();

    /**
     * 生成指纹
     *
     * @param request
     * @param sw      设备宽度
     * @param sh      设备高度
     * @param sp      设备像素比
     * @param gv      OpenGL 版本
     * @param gr      显卡渲染器
     * @param time    时间
     * @param values  json
     * @return
     */
    @PostMapping("/test")
    public String fingerprints(HttpServletRequest request,
                               @RequestParam("sw") String sw,
                               @RequestParam("sh") String sh,
                               @RequestParam("sp") String sp,
                               @RequestParam("gv") String gv,
                               @RequestParam("gr") String gr,
                               @RequestParam("time") String time,
                               @RequestParam("values") String values
    ) {
        System.out.println("ip : " + getIpAddress(request));
        System.out.println("User-Agent : " + request.getHeader("User-Agent"));
        System.out.println("sw : " + sw);
        System.out.println("sh : " + sh);
        System.out.println("sp : " + sp);
        System.out.println("gv : " + gv);
        System.out.println("gr : " + gr);
        System.out.println("time : " + time);
        System.out.println("values : " + values);
        String key = getIpAddress(request) + sw + sh + sp + gv + gr;

        System.out.println("key : " + key + "    value:" + values);

        clients.put(key, values);

        return key + "   fingerprints success";
    }

    /**
     * 分析指纹
     *
     * @param request
     * @param sw      设备宽度
     * @param sh      设备高度
     * @param sp      设备像素比
     * @param gv      OpenGL 版本
     * @param gr      显卡渲染器
     * @param time    时间
     * @return
     */
    @GetMapping("/test")
    public String analyses(HttpServletRequest request,
                           @RequestParam("sw") String sw,
                           @RequestParam("sh") String sh,
                           @RequestParam("sp") String sp,
                           @RequestParam("gv") String gv,
                           @RequestParam("gr") String gr,
                           @RequestParam("time") String time
    ) {
        System.out.println("ip : " + getIpAddress(request));
        System.out.println("User-Agent : " + request.getHeader("User-Agent"));
        System.out.println("sw : " + sw);
        System.out.println("sh : " + sh);
        System.out.println("sp : " + sp);
        System.out.println("gv : " + gv);
        System.out.println("gr : " + gr);
        System.out.println("time : " + time);

        String key = getIpAddress(request) + sw + sh + sp + gv + gr;
        System.out.println("key : " + key);

        if (clients.containsKey(key)) {
            String value = clients.get(key);
            clients.remove(key);
            System.out.println("value : " + value);
            return value;
        }

        return "not found";
    }


    /**
     * @param request
     * @return
     * @Title: getIpAddress
     * @Description: TODO
     * @return: String
     */
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("x-real-ip");
        if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getRemoteAddr();
        return ip;
        // String ip = request.getHeader("x-forwarded-for");
        // if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        // {
        // ip = request.getHeader("Proxy-Client-IP");
        // }
        // if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        // {
        // ip = request.getHeader("WL-Proxy-Client-IP");
        // }
        // if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        // {
        // ip = request.getHeader("HTTP_CLIENT_IP");
        // }
        // if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        // {
        // ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        // }
        // if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        // {
        // ip = request.getRemoteAddr();
        // }
        // return ip;
    }


}
