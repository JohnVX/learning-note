package netty.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import java.net.InetSocketAddress;

/**
 * 使用 netty 作为 http 调用客户端
 * netty 具有可高度定制化、io 性能高的优点(因为使用了 nio 及线程池等高科技)
 */
public class NettyClient {
    public void start(String host, int port, String uri) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        ClientHandler clientHandler = new ClientHandler(uri);
        try {
            bootstrap.group(group).
                    remoteAddress(new InetSocketAddress(host, port))
                    .channel(NioSocketChannel.class).handler(
                    new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline().addLast(new HttpClientCodec());
                            ch.pipeline().addLast(new HttpContentDecompressor());
                            ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024 * 10));
                            ch.pipeline().addLast(clientHandler);
                        }
                    }
            );
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        }finally{
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        NettyClient nettyClient = new NettyClient();
        nettyClient.start("127.0.0.1", 7139, "/my_uri");
    }
}