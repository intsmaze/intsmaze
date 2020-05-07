package cn.intsmaze.netty.upload;//
//package bhz.netty.upload;
//
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.codec.http.HttpContentCompressor;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpResponseEncoder;
//import io.netty.handler.ssl.SslContext;
//
//public class HttpUploadServerInitializer extends ChannelInitializer<SocketChannel> {
//
//    private final SslContext sslCtx;
//
//    public HttpUploadServerInitializer(SslContext sslCtx) {
//        this.sslCtx = sslCtx;
//    }
//
//    @Override
//    public void initChannel(SocketChannel ch) {
//        ChannelPipeline pipeline = ch.pipeline();
//
//        if (sslCtx != null) {
//            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
//        }
//
//        pipeline.addLast(new HttpRequestDecoder());
//        pipeline.addLast(new HttpResponseEncoder());
//
//        // Remove the following line if you don't want automatic content compression.
//        pipeline.addLast(new HttpContentCompressor());
//
//        pipeline.addLast(new HttpUploadServerHandler());
//    }
//}