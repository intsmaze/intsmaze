package cn.intsmaze.netty.runtime;//package bhz.netty.runtime;
//
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//
//public class ServerHandler extends ChannelHandlerAdapter{
//
//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//
//	}
//
//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		Request request = (Request)msg;
//		System.out.println("Server : " + request.getId() + ", " + request.getName() + ", " + request.getRequestMessage());
//		Response response = new Response();
//		response.setId(request.getId());
//		response.setName("response" + request.getId());
//		response.setResponseMessage("响应内容" + request.getId());
//		ctx.writeAndFlush(response);//.addListener(ChannelFutureListener.CLOSE);
//	}
//
//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		ctx.close();
//	}
//
//	
//	
//}
