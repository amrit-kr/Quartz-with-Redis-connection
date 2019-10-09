package com.quartz;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter { // (1)

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		// Discard the received data silently.
		((ByteBuf) msg).release(); // (3)
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Server channel active");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}