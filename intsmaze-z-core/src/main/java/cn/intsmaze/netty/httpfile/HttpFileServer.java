package cn.intsmaze.netty.httpfile;//package bhz.netty.httpfile;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.http.HttpObjectAggregator;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpResponseEncoder;
//import io.netty.handler.stream.ChunkedWriteHandler;
//
//public class HttpFileServer {
//
//    private static final String DEFAULT_URL = "/sources/";
//
//    public void run(final int port, final String url) throws Exception {
//    	EventLoopGroup bossGroup = new NioEventLoopGroup();
//    	EventLoopGroup workerGroup = new NioEventLoopGroup();
//		try {
//		    ServerBootstrap b = new ServerBootstrap();
//		    b.group(bossGroup, workerGroup)
//			    .channel(NioServerSocketChannel.class)
//			    .childHandler(new ChannelInitializer<SocketChannel>() {
//				@Override
//				protected void initChannel(SocketChannel ch)
//					throws Exception {
//					// 加入http的解码器
//				    ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
//				    // 加入ObjectAggregator解码器，作用是他会把多个消息转换为单一的FullHttpRequest或者FullHttpResponse
//				    ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
//				    // 加入http的编码器
//				    ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
//				    // 加入chunked 主要作用是支持异步发送的码流（大文件传输），但不专用过多的内存，防止java内存溢出
//				    ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
//				    // 加入自定义处理文件服务器的业务逻辑handler
//				    ch.pipeline().addLast("fileServerHandler",
//					    new HttpFileServerHandler(url));
//				}
//			    });
//		    ChannelFuture future = b.bind("192.168.1.200", port).sync();
//		    System.out.println("HTTP文件目录服务器启动，网址是 : " + "http://192.168.1.200:"  + port + url);
//		    future.channel().closeFuture().sync();
//		} finally {
//		    bossGroup.shutdownGracefully();
//		    workerGroup.shutdownGracefully();
//		}
//    }
//
//    public static void main(String[] args) throws Exception {
//		int port = 8765;
//		String url = DEFAULT_URL;
//		new HttpFileServer().run(port, url);
//    }
//}
