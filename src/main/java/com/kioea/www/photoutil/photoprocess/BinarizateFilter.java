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
public class BinarizateFilter extends AbstractBufferedImageOp {

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		int width = src.getWidth();
		int height = src.getHeight();

		if (dest == null)
			dest = createCompatibleDestImage(src, null);
		//src = super.filter(src, dest);

		int[] inPixels = new int[width * height];
		int[] outPixels = new int[width * height];
		getRGB(src, 0, 0, width, height, inPixels);

		// calculate means of pixel
		int index = 0;
		double redSum = 0, greenSum = 0, blueSum = 0;
		double total = height * width;
		for (int row = 0; row < height; row++) {
			int tr = 0, tg = 0, tb = 0;
			for (int col = 0; col < width; col++) {
				index = row * width + col;
				tr = (inPixels[index] >> 16) & 0xff;
				tg = (inPixels[index] >> 8) & 0xff;
				tb = inPixels[index] & 0xff;
				redSum += tr;
				greenSum += tg;
				blueSum += tb;
			}
		}
		int means = (int) (redSum / total);
		System.out.println(" threshold average value = " + means);

		// dithering
		for (int row = 0; row < height; row++) {
			int ta = 0, tr = 0, tg = 0, tb = 0;
			for (int col = 0; col < width; col++) {
				index = row * width + col;
				ta = (inPixels[index] >> 24) & 0xff;
				tr = (inPixels[index] >> 16) & 0xff;
				tg = (inPixels[index] >> 8) & 0xff;
				tb = inPixels[index] & 0xff;
				if (tr >= means) {
					tr = tg = tb = 255;
				} else {
					tr = tg = tb = 0;
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
			//读入图像
			biRead = ImageIO.read(new File("D:/orctest.jpg"));
			//对象
			BinarizateFilter sf = new BinarizateFilter();
			//输出图像(处理后的图像，格式，路径)
			ImageIO.write(sf.filter(biRead, null), "JPEG", new File("D:/orctestbinarizatefilter.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
