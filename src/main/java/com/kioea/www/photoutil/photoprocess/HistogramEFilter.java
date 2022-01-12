package com.kioea.www.photoutil.photoprocess;

/**
 * 
 * @author:Administrator
 * @time:2015-12-28 下午03:21:06
 * @version:
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kioea.www.photoutil.photoprocess.impl.AbstractBufferedImageOp;

public class HistogramEFilter extends AbstractBufferedImageOp{  
  
    @Override  
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {  
        int width = src.getWidth();  
        int height = src.getHeight();  
  
        if ( dest == null ) {
            dest = createCompatibleDestImage(src, null);
        }
        int[] inPixels = new int[width*height];  
        int[] outPixels = new int[width*height];  
        getRGB( src, 0, 0, width, height, inPixels );  
        int[][] rgbhis = new int[3][256]; // RGB  
        int[][] newrgbhis = new int[3][256]; // after HE  
        for(int i=0; i<3; i++) {  
            for(int j=0; j<256; j++) {  
                rgbhis[i][j] = 0;  
                newrgbhis[i][j] = 0;  
            }  
        }  
        int index = 0;  
        int totalPixelNumber = height * width;  
        for(int row=0; row<height; row++) {  
            int tr = 0, tg = 0, tb = 0;  
            for(int col=0; col<width; col++) {  
                index = row * width + col;  
                tr = (inPixels[index] >> 16) & 0xff;  
                tg = (inPixels[index] >> 8) & 0xff;  
                tb = inPixels[index] & 0xff;  
  
                // generate original source image RGB histogram  
                rgbhis[0][tr]++;  
                rgbhis[1][tg]++;  
                rgbhis[2][tb]++;  
            }  
        }  
          
        // generate original source image RGB histogram  
        generateHEData(newrgbhis, rgbhis, totalPixelNumber, 256);  
        for(int row=0; row<height; row++) {  
            int ta = 0, tr = 0, tg = 0, tb = 0;  
            for(int col=0; col<width; col++) {  
                index = row * width + col;  
                ta = (inPixels[index] >> 24) & 0xff;  
                tr = (inPixels[index] >> 16) & 0xff;  
                tg = (inPixels[index] >> 8) & 0xff;  
                tb = inPixels[index] & 0xff;  
  
                // get output pixel now...  
                tr = newrgbhis[0][tr];  
                tg = newrgbhis[1][tg];  
                tb = newrgbhis[2][tb];  
                  
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;  
            }  
        }  
        setRGB( dest, 0, 0, width, height, outPixels );  
        return dest;  
    }  
    /** 
     *  
     * @param newrgbhis 
     * @param rgbhis 
     * @param totalPixelNumber 
     * @param grayLevel [0 ~ 255] 
     */  
    private void generateHEData(int[][] newrgbhis, int[][] rgbhis, int totalPixelNumber, int grayLevel) {  
        for(int i=0; i<grayLevel; i++) {  
            newrgbhis[0][i] = getNewintensityRate(rgbhis[0], totalPixelNumber, i);  
            newrgbhis[1][i] = getNewintensityRate(rgbhis[1], totalPixelNumber, i);  
            newrgbhis[2][i] = getNewintensityRate(rgbhis[2], totalPixelNumber, i);  
        }  
    }  
      
    private int getNewintensityRate(int[] grayHis, double totalPixelNumber, int index) {  
        double sum = 0;  
        for(int i=0; i<=index; i++) {  
            sum += ((double)grayHis[i])/totalPixelNumber;  
        }  
        return (int)(sum * 255.0);  
    }  
  
    public static void main(String[] args) {
		BufferedImage biRead;
		try {
			//读入图像
			biRead = ImageIO.read(new File("C:\\Users\\yinzhang.lu\\Pictures\\e5.png"));
			//对象
			HistogramEFilter sf = new HistogramEFilter();
			//输出图像(处理后的图像，格式，路径)
			ImageIO.write(sf.filter(biRead, null), "PNG",
                    new File("C:\\Users\\yinzhang.lu\\Pictures\\e5h.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}  

