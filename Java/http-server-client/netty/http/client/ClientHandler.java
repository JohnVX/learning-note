package netty.http.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.nio.charset.StandardCharsets;


public class ClientHandler extends ChannelInboundHandlerAdapter {

    private String uri;

    ClientHandler(String uri){
        this.uri = uri;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        FullHttpResponse fullHttpResponse = (FullHttpResponse)msg;
        ByteBuf content = fullHttpResponse.content();
        HttpHeaders headers = fullHttpResponse.headers();
        System.out.println(headers.toString());
        System.out.println(content.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)throws Exception{
        URI uri = new URI(this.uri);
        String msg = "hello, I am a client.";
        FullHttpRequest fullHttpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_0, HttpMethod.GET, uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes(StandardCharsets.UTF_8)));
        fullHttpRequest.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8")
                .set(HttpHeaderNames.CONTENT_LENGTH, fullHttpRequest.content().readableBytes());
        ctx.writeAndFlush(fullHttpRequest);
    }
}
