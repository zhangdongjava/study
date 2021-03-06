package com.zzz.study.netty.client;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zzz.study.proto.MessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MessageProto.PersonReq req = MessageProto.PersonReq.newBuilder()
                .setName("test").setAge(18).build();
        ctx.writeAndFlush(req);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        MessageProto.PersonResp resp = (MessageProto.PersonResp) msg; // (1)
        String message = resp.getMessage();

        System.out.println("server message:" + message);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        MessageProto.PersonReq req = MessageProto.PersonReq.newBuilder()
                .setName("test").setAge(18).build();
        System.out.println(req);
        MessageProto.PersonReq req2 = MessageProto.PersonReq.parseFrom(req.toByteArray());
        System.out.println(req2.getName()+":"+req2.getAge());
    }
}
