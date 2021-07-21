package com.kioea.www.photoutil.photoprocess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.kioea.www.photoutil.photoprocess.impl.AbstractBufferedImageOp;
import com.kioea.www.photoutil.photoprocess.util.ProcessUtil;

/**
 * 图像处理之应用卷积一实现噪声消去 http://blog.csdn.net/jia20003/article/details/7294460
 * 均值滤波对于高斯噪声的效果比较好，中值滤波对于椒盐噪声的效果比较好
 * 
 * @author:Administrator
 * @time:2015-12-28 上午11:07:15
 * @version:
 */
public class SmoothFilter extends AbstractBufferedImageOp {
	/**
	 * 均值滤波，是图像处理中最常用的手段，从频率域观点来看均值滤波是一种低通滤波器，高
	 * 频信号将会去掉，因此可以帮助消除图像尖锐噪声，实现图像平滑，模糊等功能。理想的均
	 * 值滤波是用每个像素和它周围像素计算出来的平均值替换图像中每个像素。
	 * 
	 */
	public final static int MEAN_FILTER_TYPE = 1;
	/**
	 * 中值滤波也是消除图像噪声最常见的手段之一，特别是消除椒盐噪声，中值滤波的效果要比
	 * 均值滤波更好。中值滤波是跟均值滤波唯一不同是，不是用均值来替换中心每个像素，而是 将周围像素和中心像素排序以后，取中值.
	 */
	public final static int MEADIAN_FILTER_TYPE = 2;
	/**
	 * 最大最小值滤波是一种比较保守的图像处理手段，与中值滤波类似，首先要排序周围像素和
	 * 中心像素值，然后将中心像素值与最小和最大像素值比较，如果比最小值小，则替换中心像 素为最小值，如果中心像素比最大值大，则替换中心像素为最大值。
	 */
	public final static int MIN_MAX_FILTER_TYPE = 4;

	private int repeats = 3; // default 1
	private int kernel_size = 3; // default 3 
	private int type = 2; // default mean type

	public int getRepeat() {
		return repeats;
	}

	public void setRepeat(int repeat) {
		this.repeats = repeat;
	}

	public int getKernelSize() {
		return kernel_size;
	}

	public void setKernelSize(int kernelSize) {
		this.kernel_size = kernelSize;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

		// pick up one filter from here!!!
		if (this.type == MEAN_FILTER_TYPE) {
			for (int i = 0; i < repeats; i++) {
				performMeanFilter(width, height, inPixels, outPixels);
				System.arraycopy(outPixels, 0, inPixels, 0, inPixels.length);
			}
		} else if (this.type == MEADIAN_FILTER_TYPE) {
			performMedianFilter(width, height, inPixels, outPixels);
		} else if (this.type == MIN_MAX_FILTER_TYPE) {
			performMinMaxFilter(width, height, inPixels, outPixels);
		}

		// return result
		setRGB(dest, 0, 0, width, height, outPixels);
		return dest;
	}

	/**
	 * <p>
	 * perform convolution filter
	 * </p>
	 * 
	 * @param width
	 * @param height
	 * @param inPixels
	 * @param outPixels
	 */
	public void performMeanFilter(int width, int height, int[] inPixels, int[] outPixels) {

		int rows2 = kernel_size / 2;
		int cols2 = kernel_size / 2;
		int index = 0;
		int index2 = 0;
		float total = kernel_size * kernel_size;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				float r = 0, g = 0, b = 0, a = 0;
				for (int row = -rows2; row <= rows2; row++) {
					int rowoffset = y + row;
					if (rowoffset < 0 || rowoffset >= height) {
						rowoffset = y;
					}
					// System.out.println("rowoffset == " + rowoffset);
					for (int col = -cols2; col <= cols2; col++) {
						int coloffset = col + x;
						if (coloffset < 0 || coloffset >= width) {
							coloffset = x;
						}
						index2 = rowoffset * width + coloffset;
						int rgb = inPixels[index2];
						a += ((rgb >> 24) & 0xff);
						r += ((rgb >> 16) & 0xff);
						g += ((rgb >> 8) & 0xff);
						b += (rgb & 0xff);
					}
				}
				int ia = 0xff;
				int ir = ProcessUtil.clamp((int) (r / total));
				int ig = ProcessUtil.clamp((int) (g / total));
				int ib = ProcessUtil.clamp((int) (b / total));
				outPixels[index++] = (ia << 24) | (ir << 16) | (ig << 8) | ib;
			}
		}
	}

	/**
	 * <p>
	 * perform median filter
	 * </p>
	 * 
	 * @param width
	 * @param height
	 * @param src
	 * @param inPixels
	 * @param outPixels
	 */
	public void performMedianFilter(int width, int height, int[] inPixels, int[] outPixels) {
		int rows2 = kernel_size / 2;
		int cols2 = kernel_size / 2;
		int index = 0;
		int index2 = 0;
		float total = kernel_size * kernel_size;
		int[] matrix = new int[(int) total];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int count = 0;
				for (int row = -rows2; row <= rows2; row++) {
					int rowoffset = y + row;
					if (rowoffset < 0 || rowoffset >= height) {
						rowoffset = y;
					}

					for (int col = -cols2; col <= cols2; col++) {
						int coloffset = col + x;
						if (coloffset < 0 || coloffset >= width) {
							coloffset = x;
						}
						index2 = rowoffset * width + coloffset;
						int rgb = inPixels[index2];
						matrix[count] = rgb;
						count++;
					}
				}
				Arrays.sort(matrix);

				int ia = 0xff;
				int ir = ((matrix[count / 2] >> 16) & 0xff);
				int ig = ((matrix[count / 2] >> 8) & 0xff);
				int ib = (matrix[count / 2] & 0xff);
				outPixels[index++] = (ia << 24) | (ir << 16) | (ig << 8) | ib;
			}
		}
	}

	/**
	 * <p>
	 * perform min/max pixel filter
	 * </p>
	 * 
	 * @param width
	 * @param height
	 * @param src
	 * @param inPixels
	 * @param outPixels
	 */
	public void performMinMaxFilter(int width, int height, int[] inPixels, int[] outPixels) {
		int rows2 = kernel_size / 2;
		int cols2 = kernel_size / 2;
		int index = 0;
		int index2 = 0;
		float total = kernel_size * kernel_size;
		int[] matrix = new int[(int) total];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int count = 0;
				for (int row = -rows2; row <= rows2; row++) {
					int rowoffset = y + row;
					if (rowoffset < 0 || rowoffset >= height) {
						rowoffset = y;
					}

					for (int col = -cols2; col <= cols2; col++) {
						int coloffset = col + x;
						if (coloffset < 0 || coloffset >= width) {
							coloffset = x;
						}
						index2 = rowoffset * width + coloffset;
						int rgb = inPixels[index2];
						matrix[count] = rgb;
						count++;
					}
				}
				int ia = 0xff;
				int oldPixel = matrix[count / 2];
				int targetRGB = findNewPixel(matrix, oldPixel);
				int ir = ((targetRGB >> 16) & 0xff);
				int ig = ((targetRGB >> 8) & 0xff);
				int ib = (targetRGB & 0xff);
				outPixels[index++] = (ia << 24) | (ir << 16) | (ig << 8) | ib;
			}
		}
	}

	private int findNewPixel(int[] matrix, int oldPixel) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] == oldPixel) {
				continue;
			}
			list.add(matrix[i]);
		}
		int[] filterData = new int[list.size()];
		int index = 0;
		for (Integer rgb : list) {
			filterData[index++] = rgb;
		}
		Arrays.sort(filterData);

		if (filterData.length == 0) {
			return oldPixel;
		}
		return (oldPixel > filterData[0]) ? filterData[0]
				: (oldPixel < filterData[filterData.length - 1]) ? filterData[filterData.length - 1] : oldPixel;
	}

	public static void main(String[] args) {
		BufferedImage biRead;
		try {
			//读入图像
			biRead = ImageIO.read(new File("D:/orctestcoty.jpg"));
			//对象
			SmoothFilter sf = new SmoothFilter();
			//输出图像(处理后的图像，格式，路径)
			ImageIO.write(sf.filter(biRead, null), "JPEG", new File("D:/orctestfiltersmoothcopy.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
