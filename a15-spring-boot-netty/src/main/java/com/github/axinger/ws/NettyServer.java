package com.github.axinger.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyServer {

    /**
     * 端口号
     */
    @Value("${webSocket.netty.port:8889}")
    int port;

    EventLoopGroup bossGroup;
    EventLoopGroup workGroup;

    @Autowired
    WsMessageChannel wsMessageChannel;

    @PostConstruct
    public void start() throws InterruptedException {
        new Thread(() -> {
            bossGroup = new NioEventLoopGroup();
            workGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            // bossGroup辅助客户端的tcp连接请求, workGroup负责与客户端之前的读写操作
            bootstrap.group(bossGroup, workGroup);
            // 设置NIO类型的channel
            bootstrap.channel(NioServerSocketChannel.class);
            // 设置监听端口
            bootstrap.localAddress(new InetSocketAddress(port));
            // 设置管道
            bootstrap.childHandler(wsMessageChannel);

            // 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = null;
            try {
                channelFuture = bootstrap.bind().sync();
                log.info("Server started and listen on:{}", channelFuture.channel().localAddress());
                // 对关闭通道进行监听
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 释放资源
     */
    @PreDestroy
    public void destroy() throws InterruptedException {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().sync();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully().sync();
        }
    }
}
