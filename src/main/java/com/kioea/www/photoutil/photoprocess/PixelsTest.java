package com.kioea.www.photoutil.photoprocess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kioea.www.photoutil.photoprocess.util.ProcessUtil;

/**
 * 
 * @author:sekift
 * @time:2015-12-28 下午04:10:34
 * @version:
 */
public class PixelsTest {
	public void setRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
		// int type = image.getType();
		// if (type == BufferedImage.TYPE_INT_ARGB || type ==
		// BufferedImage.TYPE_INT_RGB)
		// image.getRaster().setDataElements(x, y, width, height, pixels);
		// else
		image.setRGB(x, y, width, height, pixels, 0, width);
	}

	// 获取之后再改变
	public void generateNoiseImage(BufferedImage image, int[] rgbData) {
		int index = 0;
		int a = 255;
		int r = 0;
		int g = 0;
		int b = 0;

		int pixel = 0;

		for (int row = 0; row < image.getWidth(); row++) {
			for (int col = 0; col < image.getHeight(); col++) {
				pixel = image.getRGB(row, col);
				// set random color value for each pixel
				/*
				 * int red = (pixel & 0xff0000) >> 16; if (red >= 250) { r = 0;
				 * } else { r = red; }
				 * 
				 * int green = (pixel & 0xff00) >> 8; if (green >= 250) { g = 0;
				 * } else { g = green; }
				 * 
				 * int blue = (pixel & 0xff); if (blue >= 250) { b = 0; } else {
				 * b = blue; }
				 */
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;
				// if (red >= 250 && green >= 250 && blue >= 250) {
				// r = 0;
				// g = 0;
				// b = 0;
				// } else {
				r = red;
				g = green;
				b = blue;
				// }
				System.out.println(red + ":" + green + ":" + blue);

				rgbData[index] = ((ProcessUtil.clamp(a) & 0xff) << 24) | ((ProcessUtil.clamp(r) & 0xff) << 16)
						| ((ProcessUtil.clamp(g) & 0xff) << 8) | ((ProcessUtil.clamp(b) & 0xff));
				index++;
			}
		}

	}

	private BufferedImage getImage(BufferedImage image) {
		int[] rgbData = new int[image.getWidth() * image.getHeight()];
		generateNoiseImage(image, rgbData);
		setRGB(image, 0, 0, image.getWidth(), image.getHeight(), rgbData);
		try {
			ImageIO.write(image, "jpg", new File("D:/orctestdemo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public static void main(String args[]) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("D:/orctest.jpg"));
			PixelsTest pt = new PixelsTest();
			pt.getImage(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
