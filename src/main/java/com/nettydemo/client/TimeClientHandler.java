package com.nettydemo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author Real
 * @Data 2021/4/29 14:00
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private final ByteBuf fistMessage;

    public TimeClientHandler() {
        byte[] req = "query time".getBytes();
        fistMessage = Unpooled.buffer(req.length);
        fistMessage.writeBytes(req);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(fistMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("Now,is:" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("cause"+cause.getMessage());
        ctx.close();
    }
}
