package com.kioea.www.photoutil;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author: sekift
 * @Create: 2014-5-16 下午04:55:33
 * @Description: 二维码生成
 */

public class QRCodeGeneratorUtil {
	
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;
	
	private static Logger logger = Logger.getLogger(QRCodeGeneratorUtil.class);
	
	public static BufferedImage creatQRImage(String url, int width, int height, String colorStr, boolean noMargin){
		BitMatrix matrix = createQRBitMartrix(url, width, height, noMargin);
		BufferedImage qrImage = toBufferedImage(matrix, colorStr);
		return logoMatrix(qrImage);
	}
	
	public static BufferedImage toBufferedImage(BitMatrix matrix, String colorStr) {
		if(matrix == null){
			return null;
		}
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int qrColor = BLACK;
		if(colorStr != null && colorStr.length() == 6){
			Color color = new Color(Integer.parseInt(colorStr, 16));
			qrColor = color.getRGB();
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) == true ? qrColor : WHITE);
			}
		}
		return image;
	}
	
    public static BitMatrix createQRBitMartrix(String url, int width, int height, boolean noMargin) {
        try {
            if (url == null || "".equals(url) || url.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            if(noMargin) {
				hints.put(EncodeHintType.MARGIN, 0);
			}
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    } else {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            return bitMatrix;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
    * 设置 logo  
    * 
    * @author: sekift
    * @date: 2017-12-27 下午02:19:26
    * @param matrixImage 源二维码图片 
    * @return 返回带有logo的二维码图片 
    * @exception IOException 
    */
    public static BufferedImage logoMatrix(BufferedImage matrixImage){
    	if(matrixImage == null) {
			return null;
		}
    	
    	try {
	    	//读取二维码图片，并构建绘图对象
	    	Graphics2D g2 = matrixImage.createGraphics();
	    	
	    	int matrixWidth = matrixImage.getWidth();
	    	int matrixHeigh = matrixImage.getHeight();
	    	
	    	//读取Logo图片
	    	String fileName = QRCodeGeneratorUtil.class.getClassLoader().getResource("../images/logo_100.png").getPath();
	    	BufferedImage logo = ImageIO.read(new File(fileName));
	    	
	    	// 开始绘制图片
	    	g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);
	    	
	    	// 指定弧度的圆角矩形
	    	BasicStroke stroke = new BasicStroke(matrixWidth / 50, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	    	g2.setStroke(stroke);
	    	int arc = (int)(matrixWidth / 5 * 0.45);
	    	RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, arc, arc);
	    	g2.draw(round);// 绘制圆弧矩形
	    	
	    	/**
	    	//g2.setColor(Color.white);
	    	// 设置logo 有一道灰色边框
	    	BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	    	g2.setStroke(stroke2);// 设置笔画对象
	    	RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 15, 15);
	    	g2.draw(round2);// 绘制圆弧矩形
	    	*/
	    	
	    	g2.dispose();
	    	matrixImage.flush();
	    	return matrixImage;
    	}catch (IOException e) {
    		logger.error("生成图标二维码失败", e);
		}
    	return matrixImage;
    }
     
}
