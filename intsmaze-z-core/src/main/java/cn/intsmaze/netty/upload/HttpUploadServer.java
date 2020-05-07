package cn.intsmaze.netty.upload;//
//package bhz.netty.upload;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.LoggingHandler;
//import io.netty.handler.ssl.SslContext;
//import io.netty.handler.ssl.util.SelfSignedCertificate;
//
///**
// * A HTTP server showing how to use the HTTP multipart package for file uploads and decoding post data.
// */
//public final class HttpUploadServer {
//
//    static final boolean SSL = System.getProperty("ssl") != null;
//    static final int PORT = Integer.parseInt(System.getProperty("port", SSL? "8443" : "8080"));
//
//    public static void main(String[] args) throws Exception {
//        // Configure SSL.
//        final SslContext sslCtx;
//        if (SSL) {
//            SelfSignedCertificate ssc = new SelfSignedCertificate();
//            sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
//        } else {
//            sslCtx = null;
//        }
//
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup);
//            b.channel(NioServerSocketChannel.class);
//            b.handler(new LoggingHandler(LogLevel.INFO));
//            b.childHandler(new HttpUploadServerInitializer(sslCtx));
//
//            Channel ch = b.bind(PORT).sync().channel();
//
//            System.err.println("Open your web browser and navigate to " +
//                    (SSL? "https" : "http") + "://127.0.0.1:" + PORT + '/');
//
//            ch.closeFuture().sync();
//        } finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//    }
//}