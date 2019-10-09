package com.quartz;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

	public static void run() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap clientBootstrap = new Bootstrap();

			clientBootstrap.group(group);
			clientBootstrap.channel(NioSocketChannel.class);
			clientBootstrap.remoteAddress(new InetSocketAddress("localhost", 7000));
			clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(new ClientHandler());
				}
			});
			ChannelFuture channelFuture = clientBootstrap.connect().sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}

	}
}
