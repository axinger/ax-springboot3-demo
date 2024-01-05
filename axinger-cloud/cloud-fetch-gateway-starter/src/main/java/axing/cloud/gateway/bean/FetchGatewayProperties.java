package axing.cloud.gateway.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SpringCloud 微服务之间调用是否必须走网关,引入默认必须走网关,不能直接调用
 */
@Data
@ConfigurationProperties(prefix = "axing.cloud")
public class FetchGatewayProperties {

    /**
     * 是否只能通过网关获取资源
     * 默认为True
     */
    private Boolean mustFetchGateway = Boolean.TRUE;

    /**
     * 经过网关秘钥key
     */
    private String fetchGatewayKey = "fetchGateway";

    /**
     * 经过网关秘钥value
     */
    private String fetchGatewayValue = "fetchGateway";
}

