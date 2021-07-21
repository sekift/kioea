package com.kioea.www.photoutil.photoprocess;

/**
 * 滤镜效果
 * @author:Administrator
 * @time:2015-12-28 下午02:50:48
 * @version:
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kioea.www.photoutil.photoprocess.impl.AbstractBufferedImageOp;
import com.kioea.www.photoutil.photoprocess.util.ProcessUtil;

/**
 * 
 * @author gloomy-fish
 * @date 2012-06-11
 * 
 *       prewitt operator X-direction -1, 0, 1 -1, 0, 1 -1, 0, 1
 * 
 *       Y-direction -1, -1, -1 0, 0, 0 1, 1, 1
 * 
 *       sobel operator X-direction -1, 0, 1 -2, 0, 2 -1, 0, 1
 * 
 *       Y-direction -1, -2, -1 0, 0, 0 1, 2, 1
 * 
 */
public class GradientFilter extends AbstractBufferedImageOp {

	// prewitt operator
	public final static int[][] PREWITT_X = new int[][] { { -1, 0, 1 }, { -1, 0, 1 }, { -1, 0, 1 } };
	public final static int[][] PREWITT_Y = new int[][] { { -1, -1, -1 }, { 0, 0, 0 }, { 1, 1, 1 } };

	// sobel operator
	public final static int[][] SOBEL_X = new int[][] { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
	public final static int[][] SOBEL_Y = new int[][] { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };

	// direction parameter
	public final static int X_DIRECTION = 0;
	public final static int Y_DIRECTION = 2;
	public final static int XY_DIRECTION = 4;
	private int direction;
	private boolean isSobel;

	public GradientFilter() {
		direction = XY_DIRECTION;
		isSobel = true;
	}

	public void setSoble(boolean sobel) {
		this.isSobel = sobel;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		int width = src.getWidth();
		int height = src.getHeight();

		if (dest == null) {
			dest = createCompatibleDestImage(src, null);
		}
		int[] inPixels = new int[width * height];
		int[] outPixels = new int[width * height];
		getRGB(src, 0, 0, width, height, inPixels);
		int index = 0, index2 = 0;
		double xred = 0, xgreen = 0, xblue = 0;
		double yred = 0, ygreen = 0, yblue = 0;
		int newRow, newCol;
		for (int row = 0; row < height; row++) {
			int ta = 255, tr = 0, tg = 0, tb = 0;
			for (int col = 0; col < width; col++) {
				index = row * width + col;
				for (int subrow = -1; subrow <= 1; subrow++) {
					for (int subcol = -1; subcol <= 1; subcol++) {
						newRow = row + subrow;
						newCol = col + subcol;
						if (newRow < 0 || newRow >= height) {
							newRow = row;
						}
						if (newCol < 0 || newCol >= width) {
							newCol = col;
						}
						index2 = newRow * width + newCol;
						tr = (inPixels[index2] >> 16) & 0xff;
						tg = (inPixels[index2] >> 8) & 0xff;
						tb = inPixels[index2] & 0xff;

						if (isSobel) {
							xred += (SOBEL_X[subrow + 1][subcol + 1] * tr);
							xgreen += (SOBEL_X[subrow + 1][subcol + 1] * tg);
							xblue += (SOBEL_X[subrow + 1][subcol + 1] * tb);

							yred += (SOBEL_Y[subrow + 1][subcol + 1] * tr);
							ygreen += (SOBEL_Y[subrow + 1][subcol + 1] * tg);
							yblue += (SOBEL_Y[subrow + 1][subcol + 1] * tb);
						} else {
							xred += (PREWITT_X[subrow + 1][subcol + 1] * tr);
							xgreen += (PREWITT_X[subrow + 1][subcol + 1] * tg);
							xblue += (PREWITT_X[subrow + 1][subcol + 1] * tb);

							yred += (PREWITT_Y[subrow + 1][subcol + 1] * tr);
							ygreen += (PREWITT_Y[subrow + 1][subcol + 1] * tg);
							yblue += (PREWITT_Y[subrow + 1][subcol + 1] * tb);
						}
					}
				}

				double mred = Math.sqrt(xred * xred + yred * yred);
				double mgreen = Math.sqrt(xgreen * xgreen + ygreen * ygreen);
				double mblue = Math.sqrt(xblue * xblue + yblue * yblue);
				if (XY_DIRECTION == direction) {
					outPixels[index] = (ta << 24) | (ProcessUtil.clamp((int) mred) << 16)
							| (ProcessUtil.clamp((int) mgreen) << 8) | ProcessUtil.clamp((int) mblue);
				} else if (X_DIRECTION == direction) {
					outPixels[index] = (ta << 24) | (ProcessUtil.clamp((int) yred) << 16)
							| (ProcessUtil.clamp((int) ygreen) << 8) | ProcessUtil.clamp((int) yblue);
				} else if (Y_DIRECTION == direction) {
					outPixels[index] = (ta << 24) | (ProcessUtil.clamp((int) xred) << 16)
							| (ProcessUtil.clamp((int) xgreen) << 8) | ProcessUtil.clamp((int) xblue);
				} else {
					// as default, always XY gradient
					outPixels[index] = (ta << 24) | (ProcessUtil.clamp((int) mred) << 16)
							| (ProcessUtil.clamp((int) mgreen) << 8) | ProcessUtil.clamp((int) mblue);
				}

				// cleanup for next loop
				newRow = newCol = 0;
				xred = xgreen = xblue = 0;
				yred = ygreen = yblue = 0;

			}
		}
		setRGB(dest, 0, 0, width, height, outPixels);
		return dest;
	}

	public static void main(String[] args) {
		BufferedImage biRead;
		try {
			// 读入图像
			biRead = ImageIO.read(new File("D:/orctest.jpg"));
			// 对象
			GradientFilter sf = new GradientFilter();
			// 输出图像(处理后的图像，格式，路径)
			ImageIO.write(sf.filter(biRead, null), "JPEG", new File("D:/orctestfiltergradien.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
