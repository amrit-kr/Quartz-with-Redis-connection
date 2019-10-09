package com.quartz;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext channelHandlerContext) {
		//channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8));
		System.out.println("Client successfully connected!");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
		cause.printStackTrace();
		channelHandlerContext.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext channelHandlerContext, Object arg1) throws Exception {

		ByteBuf in = (ByteBuf) arg1;
		System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));

	}

}