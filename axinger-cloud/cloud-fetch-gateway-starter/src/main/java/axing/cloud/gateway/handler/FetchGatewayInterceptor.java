package axing.cloud.gateway.handler;

import axing.cloud.gateway.bean.FetchGatewayProperties;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;


public class FetchGatewayInterceptor implements HandlerInterceptor {

    private final FetchGatewayProperties properties;

    public FetchGatewayInterceptor(FetchGatewayProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        if (!properties.getMustFetchGateway()) {
            return true;
        }
        // 判断请求头中,是否包含忽略key
        String token = request.getHeader(properties.getFetchGatewayKey());
        String gatewayToken = properties.getFetchGatewayValue();
        if (ObjectUtil.equals(gatewayToken, token)) {
            return true;
        } else {
            throw new RuntimeException("请通过网关访问资源");
        }
    }
}

