package com.kioea.www.photoutil.photoprocess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kioea.www.photoutil.photoprocess.impl.AbstractBufferedImageOp;

/**
 * 
 * @author:Administrator
 * @time:2015-12-28 下午02:37:33
 * @version:
 */
public class CopyFilter extends AbstractBufferedImageOp {

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		int width = src.getWidth();
		int height = src.getHeight();

		if (dest == null) {
			dest = createCompatibleDestImage(src, null);
		}
		// src = super.filter(src, dest);

		int[] inPixels = new int[width * height];
		int[] outPixels = new int[width * height];
		getRGB(src, 0, 0, width, height, inPixels);

		// calculate means of pixel
		int index = 0;
		for (int row = 0; row < height; row++) {
			int ta = 0, tr = 0, tg = 0, tb = 0;
			for (int col = 0; col < width; col++) {
				index = row * width + col;
				ta = (inPixels[index] >> 24) & 0xff;
				tr = (inPixels[index] >> 16) & 0xff;
				tg = (inPixels[index] >> 8) & 0xff;
				tb = inPixels[index] & 0xff;
				if (tr >= 255 && tg >= 255 && tb >= 255) {
					tr = 255;
					tg = 255;
					tb = 255;
				}
				outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
			}
		}
		
		for (int row = 0; row < height; row++) {
			int ta = 0, tr = 0, tg = 0, tb = 0;
			for (int col = 0; col < width; col++) {
				index = row * width + col;
				ta = (outPixels[index] >> 24) & 0xff;
				tr = (outPixels[index] >> 16) & 0xff;
				tg = (outPixels[index] >> 8) & 0xff;
				tb = outPixels[index] & 0xff;
				if (!(tr <= 180 && tg <= 180 && tb >= 80)) {
					tr = 255;
					tg = 255;
					tb = 255;
				}
				outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
			}
		}
		setRGB(dest, 0, 0, width, height, outPixels);
		return dest;
	}

	public static void main(String[] args) {
		BufferedImage biRead;
		try {
			// 读入图像
			biRead = ImageIO.read(new File("D:/123.jpg"));
			// 对象
			CopyFilter sf = new CopyFilter();
			// 输出图像(处理后的图像，格式，路径)
			ImageIO.write(sf.filter(biRead, null), "JPEG", new File("D:/123copy.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
