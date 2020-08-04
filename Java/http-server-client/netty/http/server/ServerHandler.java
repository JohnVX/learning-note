package netty.http.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) {
        if(fullHttpRequest.uri().equals("/my_uri")) {
            byte[] bytes = new byte[1024];
            ByteBuf byteBuf = fullHttpRequest.content();
            byteBuf.readBytes(bytes, byteBuf.readerIndex(), byteBuf.readableBytes());
            System.out.println(new String(bytes));
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    Unpooled.copiedBuffer("OK", CharsetUtil.UTF_8));
            response.headers().set(HttpHeaderNames.USER_AGENT, "shuwei-netty-agent");
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            sendError(ctx);
        }
    }

    private static void sendError(ChannelHandlerContext context){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SERVICE_UNAVAILABLE,
                Unpooled.copiedBuffer("Failure: " + HttpResponseStatus.SERVICE_UNAVAILABLE.toString() + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        context.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
