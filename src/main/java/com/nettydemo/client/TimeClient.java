package com.nettydemo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @Author Real
 * @Data 2021/4/29 13:47
 */
public class TimeClient {

    public void connect(int port, String host) throws Exception{

        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class)
                      .option(ChannelOption.TCP_NODELAY, true)
                      .handler(new ChannelInitializer<SocketChannel>() {

                          @Override
                          protected void initChannel(SocketChannel socketChannel) throws Exception {
                              socketChannel.pipeline().addLast(new TimeClientHandler());
                          }
                      });
            //发起异步连接
            ChannelFuture future = bootstrap.connect(host, port).sync();

            future.channel().closeFuture().sync();

        }finally {
            loopGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception{
        int port = 8080;
        if(args!=null && args.length>0){
            try{
               port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }
        new TimeClient().connect(port,"127.0.0.1");
    }
}
