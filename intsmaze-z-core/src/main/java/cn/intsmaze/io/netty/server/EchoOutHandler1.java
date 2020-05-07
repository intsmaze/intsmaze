package cn.intsmaze.io.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class EchoOutHandler1 extends ChannelOutboundHandlerAdapter {
	@Override
    // 向client发送消息
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("out1");
        System.out.println(msg);
        
        ctx.write(msg);
        ctx.flush();
       }
}
