package cn.intsmaze.io.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class EchoOutHandler2 extends ChannelOutboundHandlerAdapter {

	 @Override
	    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
	        System.out.println("out2");
	        ctx.write(msg);
	        // super.write(ctx, msg, promise);
	    }

}
