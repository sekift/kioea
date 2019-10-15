package com.kioea.www;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分配内存测试类
 * @author:sekift
 * @time:2017-7-20 上午10:29:00
 * @version:
 */
public class ByteBuffer_Allocate {
	public static void main(String args[]) {
		try {
			FileInputStream fis = new FileInputStream("resources/test.txt");
			FileChannel fc = fis.getChannel();//Returns the unique FileChannel object associated with this file input stream.
			ByteBuffer buffer = ByteBuffer.allocate(16);// 以字节为单位,分配多少个字节就只能储存多少个。
			// Read data into rest of buffer  	从channel中读取字节序列到buffer
			fc.read(buffer);
			/*
			 * 反转此缓冲区。首先将限制设置为当前位置，然后将位置设置为 0。如果已定义了标记，则丢弃该标记。 在一系列通道读取或放置
			 * 操作之后，调用此方法为一系列通道写入或相对获取 操作做好准备。例如：
			 */
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get());
			}
			System.out.println();
			System.out.print(buffer);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
