package cn.intsmaze.netty.httpfile;//package bhz.netty.httpfile;
//
//import static io.netty.handler.codec.http.HttpHeaderNames.*;
//import static io.netty.handler.codec.http.HttpMethod.*;
//import static io.netty.handler.codec.http.HttpResponseStatus.*;
//import static io.netty.handler.codec.http.HttpVersion.*;
//import static io.netty.handler.codec.http.HttpMethod.GET;
//import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
//import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
//import static io.netty.handler.codec.http.HttpResponseStatus.FOUND;
//import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
//import static io.netty.handler.codec.http.HttpResponseStatus.METHOD_NOT_ALLOWED;
//import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
//import static io.netty.handler.codec.http.HttpResponseStatus.OK;
//import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelProgressiveFuture;
//import io.netty.channel.ChannelProgressiveFutureListener;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.DefaultFullHttpResponse;
//import io.netty.handler.codec.http.DefaultHttpResponse;
//import io.netty.handler.codec.http.FullHttpRequest;
//import io.netty.handler.codec.http.FullHttpResponse;
//import io.netty.handler.codec.http.HttpHeaderUtil;
//import io.netty.handler.codec.http.HttpHeaderValues;
//import io.netty.handler.codec.http.HttpHeaders;
//import io.netty.handler.codec.http.HttpResponse;
//import io.netty.handler.codec.http.HttpResponseStatus;
//import io.netty.handler.codec.http.LastHttpContent;
//import io.netty.handler.stream.ChunkedFile;
//import io.netty.util.CharsetUtil;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.RandomAccessFile;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.util.regex.Pattern;
//
//import javax.activation.MimetypesFileTypeMap;
//
//public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
//    
//	private final String url;
//
//    public HttpFileServerHandler(String url) {
//    	this.url = url;
//    }
//
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
//    	//对请求的解码结果进行判断：
//		if (!request.decoderResult().isSuccess()) {
//			// 400
//		    sendError(ctx, BAD_REQUEST);
//		    return;
//		}
//		//对请求方式进行判断：如果不是get方式（如post方式）则返回异常
//		if (request.method() != GET) {
//			// 405
//		    sendError(ctx, METHOD_NOT_ALLOWED);
//		    return;
//		}
//		//获取请求uri路径
//		final String uri = request.uri();
//		//对url进行分析，返回本地系统
//		final String path = sanitizeUri(uri);
//		//如果 路径构造不合法，则path为null
//		if (path == null) {
//			//403
//		    sendError(ctx, FORBIDDEN);
//		    return;
//		}
//		// 创建file对象
//		File file = new File(path);
//		// 判断文件是否为隐藏或者不存在
//		if (file.isHidden() || !file.exists()) {
//			// 404 
//		    sendError(ctx, NOT_FOUND);
//		    return;
//		}
//		// 如果为文件夹
//		if (file.isDirectory()) {
//		    if (uri.endsWith("/")) {
//		    	//如果以正常"/"结束 说明是访问的一个文件目录：则进行展示文件列表（web服务端则可以跳转一个Controller，遍历文件并跳转到一个页面）
//		    	sendListing(ctx, file);
//		    } else {
//		    	//如果非"/"结束 则重定向，补全"/" 再次请求
//		    	sendRedirect(ctx, uri + '/');
//		    }
//		    return;
//		}
//		// 如果所创建的file对象不是文件类型
//		if (!file.isFile()) {
//			// 403
//		    sendError(ctx, FORBIDDEN);
//		    return;
//		}
//		
//		//随机文件读写类
//		RandomAccessFile randomAccessFile = null;
//		try {
//		    randomAccessFile = new RandomAccessFile(file, "r");// 以只读的方式打开文件
//		} catch (FileNotFoundException fnfe) {
//			// 404
//		    sendError(ctx, NOT_FOUND);
//		    return;
//		}
//		
//		//获取文件长度
//		long fileLength = randomAccessFile.length();
//		//建立响应对象
//		HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
//		//设置响应信息
//		HttpHeaderUtil.setContentLength(response, fileLength);
//		//设置响应头
//		setContentTypeHeader(response, file);
//		//如果一直保持连接则设置响应头信息为：HttpHeaders.Values.KEEP_ALIVE
//		if (HttpHeaderUtil.isKeepAlive(request)) {
//		    response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//		}
//		//进行写出
//		ctx.write(response);
//		
//		//构造发送文件线程，将文件写入到Chunked缓冲区中
//		ChannelFuture sendFileFuture;
//		//写出ChunkedFile
//		sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192), ctx.newProgressivePromise());
//		//添加传输监听
//		sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
//		    @Override
//		    public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) {
//				if (total < 0) { // total unknown
//				    System.err.println("Transfer progress: " + progress);
//				} else {
//				    System.err.println("Transfer progress: " + progress + " / " + total);
//				}
//		    }
//		    @Override
//		    public void operationComplete(ChannelProgressiveFuture future) throws Exception {
//		    	System.out.println("Transfer complete.");
//		    }
//		});
//		
//		//如果使用Chunked编码，最后则需要发送一个编码结束的看空消息体，进行标记，表示所有消息体已经成功发送完成。
//		ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
//		//如果当前连接请求非Keep-Alive ，最后一包消息发送完成后 服务器主动关闭连接
//		if (!HttpHeaderUtil.isKeepAlive(request)) {
//		    lastContentFuture.addListener(ChannelFutureListener.CLOSE);
//		}
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//    	//cause.printStackTrace();
//		if (ctx.channel().isActive()) {
//		    sendError(ctx, INTERNAL_SERVER_ERROR);
//		    ctx.close();
//		}
//    }
//
//    //非法URI正则
//    private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");
//
//    /**
//     * <B>方法名称：</B>解析URI<BR>
//     * <B>概要说明：</B>对URI进行分析<BR>
//     * @param uri netty包装后的字符串对象
//     * @return path 解析结果
//     */
//    private String sanitizeUri(String uri) {
//		try {
//			//使用UTF-8字符集
//		    uri = URLDecoder.decode(uri, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//		    try {
//		    	//尝试ISO-8859-1
//		    	uri = URLDecoder.decode(uri, "ISO-8859-1");
//		    } catch (UnsupportedEncodingException e1) {
//		    	//抛出预想外异常信息
//		    	throw new Error();
//		    }
//		}
//		// 对uri进行细粒度判断：4步验证操作
//		// step 1 基础验证
//		if (!uri.startsWith(url)) {
//		    return null;
//		}
//		// step 2 基础验证
//		if (!uri.startsWith("/")) {
//		    return null;
//		}
//		// step 3 将文件分隔符替换为本地操作系统的文件路径分隔符
//		uri = uri.replace('/', File.separatorChar);
//		// step 4 二次验证合法性
//		if (uri.contains(File.separator + '.')
//			|| uri.contains('.' + File.separator) || uri.startsWith(".")
//			|| uri.endsWith(".") || INSECURE_URI.matcher(uri).matches()) {
//		    return null;
//		}
//		//当前工程所在目录 + URI构造绝对路径进行返回 
//		return System.getProperty("user.dir") + File.separator + uri;
//    }
//    
//    //文件是否被允许访问下载验证
//    private static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");
//
//    private static void sendListing(ChannelHandlerContext ctx, File dir) {
//    	// 设置响应对象
//		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
//		// 响应头
//		response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
//		// 追加文本内容
//		StringBuilder ret = new StringBuilder();
//		String dirPath = dir.getPath();
//		ret.append("<!DOCTYPE html>\r\n");
//		ret.append("<html><head><title>");
//		ret.append(dirPath);
//		ret.append(" 目录：");
//		ret.append("</title></head><body>\r\n");
//		ret.append("<h3>");
//		ret.append(dirPath).append(" 目录：");
//		ret.append("</h3>\r\n");
//		ret.append("<ul>");
//		ret.append("<li>链接：<a href=\"../\">..</a></li>\r\n");
//		
//		// 遍历文件 添加超链接
//		for (File f : dir.listFiles()) {
//			//step 1: 跳过隐藏或不可读文件 
//		    if (f.isHidden() || !f.canRead()) {
//		    	continue;
//		    }
//		    String name = f.getName();
//		    //step 2: 如果不被允许，则跳过此文件
//		    if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
//		    	continue;
//		    }
//		    //拼接超链接即可
//		    ret.append("<li>链接：<a href=\"");
//		    ret.append(name);
//		    ret.append("\">");
//		    ret.append(name);
//		    ret.append("</a></li>\r\n");
//		}
//		ret.append("</ul></body></html>\r\n");
//		//构造结构，写入缓冲区
//		ByteBuf buffer = Unpooled.copiedBuffer(ret, CharsetUtil.UTF_8);
//		//进行写出操作
//		response.content().writeBytes(buffer);
//		//重置写出区域
//		buffer.release();
//		//使用ctx对象写出并且刷新到SocketChannel中去 并主动关闭连接(这里是指关闭处理发送数据的线程连接)
//		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
//    }
//
//    //重定向操作
//    private static void sendRedirect(ChannelHandlerContext ctx, String newUri) {
//    	//建立响应对象
//		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
//		//设置新的请求地址放入响应对象中去
//		response.headers().set(LOCATION, newUri);
//		//使用ctx对象写出并且刷新到SocketChannel中去 并主动关闭连接(这里是指关闭处理发送数据的线程连接)
//		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
//    }
//
//    //错误信息
//    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
//    	//建立响应对象
//    	FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, Unpooled.copiedBuffer("Failure: " + status.toString()+ "\r\n", CharsetUtil.UTF_8));
//    	//设置响应头信息
//    	response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
//    	//使用ctx对象写出并且刷新到SocketChannel中去 并主动关闭连接(这里是指关闭处理发送数据的线程连接)
//    	ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
//    }
//
//    private static void setContentTypeHeader(HttpResponse response, File file) {
//    	//使用mime对象获取文件类型
//    	MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
//    	response.headers().set(CONTENT_TYPE, mimeTypesMap.getContentType(file.getPath()));
//    }
//}
