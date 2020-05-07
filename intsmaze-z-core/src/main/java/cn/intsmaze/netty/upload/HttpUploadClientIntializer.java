package cn.intsmaze.netty.upload;//
//package bhz.netty.upload;
//
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.codec.http.HttpClientCodec;
//import io.netty.handler.codec.http.HttpContentDecompressor;
//import io.netty.handler.ssl.SslContext;
//import io.netty.handler.stream.ChunkedWriteHandler;
//
//public class HttpUploadClientIntializer extends ChannelInitializer<SocketChannel> {
//
//    private final SslContext sslCtx;
//
//    public HttpUploadClientIntializer(SslContext sslCtx) {
//        this.sslCtx = sslCtx;
//    }
//
//    @Override
//    public void initChannel(SocketChannel ch) {
//        ChannelPipeline pipeline = ch.pipeline();
//
//        if (sslCtx != null) {
//            pipeline.addLast("ssl", sslCtx.newHandler(ch.alloc()));
//        }
//
//        pipeline.addLast("codec", new HttpClientCodec());
//
//        // Remove the following line if you don't want automatic content decompression.
//        pipeline.addLast("inflater", new HttpContentDecompressor());
//
//        // to be used since huge file transfer
//        pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
//
//        pipeline.addLast("handler", new HttpUploadClientHandler());
//    }
//}