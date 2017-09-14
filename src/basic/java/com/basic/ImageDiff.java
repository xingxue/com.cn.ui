package com.basic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.appium.TimeNow;

import io.appium.java_client.android.AndroidDriver;

/**
 * 图片比较功能
 * @author xingxue
 *
 */
public class ImageDiff {
	
    //不同的像素标记为红色
    public  final int RGB_RED = 16711680;
 
    //允许的Red，Green，Blue单个维度的像素差值
    public  final int DIFF_ALLOW_RANGE = 5;

    //不同像素点统计值
    public  int diffPointCount = 0;
    
    public static  AndroidDriver<?> driver;
    
    //定义截屏路径
    public  String pathcompare;
 



	public  ImageDiff(AndroidDriver<?> driver) {
    	 this.driver=driver;
    	pathcompare = System.getProperty("user.dir") + "//snapshot-compare//" + new TimeNow().getTimeymdhms() + ".png";
    }
    
    //从rgb值中抽取red
    public  int getRed(int rgbValue){
        return rgbValue & 0xff0000 >> 16;
    }
 
    //从rgb值中抽取green
    public  int getGreen(int rgbValue){
        return rgbValue & 0xff00 >> 8;
    }
 
    //从rgb值中抽取blue
    public  int getBlue(int rgbValue){
        return rgbValue & 0xff;
    }
    
    public  Logger log = Logger.getLogger(ImageDiff.class.getName());
    
    /**
     * 比较两图片，并用红色标出不同的像素点，然后保存差异图片到本地，打印匹配率
     * @param srcImgPath
     * @param targetImgPath
     * @return 
     */
    public  double compareImages(String srcImgPath,String targetImgPath){
        try {
            BufferedImage srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage targetImg = ImageIO.read(new File(targetImgPath));
 
            diffPointCount = 0;
            BufferedImage diffImg = srcImg;
 
            int srcHeight = srcImg.getHeight();
            int srcWidth = srcImg.getWidth();
 
            //修改待比较图片的尺寸以适应源图片的尺寸
            targetImg = changeImageSize(targetImg,srcHeight,srcWidth);
 
            int srcRgb;
            int targetRgb;
 
            for(int h = 0;h<srcHeight;h++){
                for(int w=0;w<srcWidth;w++){
                    srcRgb = srcImg.getRGB(w,h);
                    targetRgb = targetImg.getRGB(w,h);
                    if( Math.abs(getRed(srcRgb) - getRed(targetRgb))>DIFF_ALLOW_RANGE ||
                            Math.abs(getGreen(srcRgb) - getGreen(targetRgb))>DIFF_ALLOW_RANGE||
                            Math.abs(getBlue(srcRgb) - getBlue(targetRgb))>DIFF_ALLOW_RANGE){
                        diffImg.setRGB(w,h, RGB_RED);
                        diffPointCount++;
                    }
                }
            }
 
            //保存差异图片
            ImageIO.write(diffImg,"png",new File(pathcompare));
            log.info("对比截图保存的路径:" + pathcompare);
 
            //计算相似度(保留小数点后四位）
            int totalPixel = srcHeight*srcWidth;
            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            double matchRate = (totalPixel-diffPointCount)/(totalPixel*1.0);
            String alike = decimalFormat.format(matchRate);
            log.info("图片相似度为： "+ Double.valueOf(alike).doubleValue()*100 +"%");
            return Double.valueOf(alike).doubleValue()*100;
 
        }catch (Exception ex){
            ex.printStackTrace();
            return (Double) null;
        }
        

    }

	/**
     * 修改BufferedImage中的图片尺寸，以便和源图片进行比较
     * @param image
     * @param newHeight
     * @param newWidth
     * @return
     */
    public static BufferedImage changeImageSize(BufferedImage image,int newHeight,int newWidth){
        Image img = image.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        int width = img.getWidth(null);
        int height = img.getHeight(null);
 
        //获取新图片的BufferedImage实例
        BufferedImage newBufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = newBufferedImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return newBufferedImage;
    }
 
    public static void main(String[] args){
    	new ImageDiff(driver).compareImages("C:\\Users\\xingxue\\eclipse-workspace\\com.cn.ui//snapshot-compare//ff.jpg","C:\\Users\\xingxue\\eclipse-workspace\\com.cn.ui//snapshot-compare//99.jpg");
    }
}
