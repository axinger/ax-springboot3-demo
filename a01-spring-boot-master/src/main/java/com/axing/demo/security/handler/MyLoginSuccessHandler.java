// package com.ax.master.security.handler;
//
// import com.alibaba.fastjson2.JSON;
// import com.ax.master.context.UserinfoContext;
// import com.ax.master.entity.IpLog;
// import com.ax.master.entity.Userinfo;
// import com.ax.master.util.axUtil.AxResultStateEnum;
// import lombok.SneakyThrows;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
// import org.springframework.stereotype.Component;
//
// import javax.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
//
//@Component
// public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @SneakyThrows
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//
//        logger.info("授权登录成功 = " + authentication.getPrincipal());
//
//        Map map = new HashMap();
//        map.put("code","200");
//
//        if (authentication.getPrincipal() instanceof Userinfo) {
//          /**获取user*/
//            Userinfo userinfo = (Userinfo) authentication.getPrincipal();
//
//            map.put("body",userinfo);
//
//            System.out.println("userinfo = " + userinfo.getToken());
//        } else {
//
//            throw new Exception("Authentication 用户类型错误");
//        }
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(JSON.toJSONString(map));
//
//    }
//}