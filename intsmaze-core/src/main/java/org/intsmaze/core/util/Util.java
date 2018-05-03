package org.intsmaze.core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


public class Util {

	/**
	 * 获取国际化对应的值
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	public static String getLocaleName(String name, HttpServletRequest request,
			SessionLocaleResolver resolver) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages",
				resolver.resolveLocale(request));
		String name1 = bundle.getString(name);
		if (name1 != null) {
			return name1.trim();
		} else {
			return name;
		}
	}

//	/**
//	 * 压缩图片
//	 * @param imgsrc 原始文件路径
//	 * @param imgdist 目标文件路径
//	 * @param widthdist 目标长度
//	 * @param heightdist 目标高度
//	 */
//	public static void reduceImg(String imgsrc, String imgdist, int widthdist,
//			int heightdist) {
//		try {
//			File srcfile = new File(imgsrc);
//			if (!srcfile.exists()) {
//				return;
//			}
//			Image src = javax.imageio.ImageIO.read(srcfile);
//
//			BufferedImage tag = new BufferedImage((int) widthdist,
//					(int) heightdist, BufferedImage.TYPE_INT_RGB);
//
//			tag.getGraphics().drawImage(
//					src.getScaledInstance(widthdist, heightdist,
//							Image.SCALE_SMOOTH), 0, 0, null);
//			// tag.getGraphics().drawImage(src.getScaledInstance(widthdist,
//			// heightdist, Image.SCALE_AREA_AVERAGING), 0, 0, null);
//
//			FileOutputStream out = new FileOutputStream(imgdist);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			encoder.encode(tag);
//			out.close();
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
	
	/**
	 * 去除字符串中的回车
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

	/**
	 * 获取唯一键 UUID
	 * @return
	 */
	public static String generateUUID(){
		return UUID.randomUUID().toString();
	}
	
//	public static String lFillZero(String str, int targetLength)
//	{
//		if(str==null || "".equals(str))
//		{
//			throw new FDFBRuntimeException("UUID generated failed");
//		}
//		else
//		{
//			int length = str.length();
//			for(int i=0; i<(targetLength-length); i++)
//			{
//				str = "0" + str;
//			}
//			return str;
//		}
//		
//	}
	
	/** 
     * @Description <p>获取到对象中属性为null的属性名  </P> 
     * @param source 要拷贝的对象 
     * @return 
     */  
    public static String[] getNullPropertyNames(Object source) {  
        final BeanWrapper src = new BeanWrapperImpl(source);  
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();  
   
        Set<String> emptyNames = new HashSet<String>();  
        for (java.beans.PropertyDescriptor pd : pds) {  
            Object srcValue = src.getPropertyValue(pd.getName());  
            if (srcValue == null)  
                emptyNames.add(pd.getName());  
        }  
        String[] result = new String[emptyNames.size()];  
        return emptyNames.toArray(result);  
    }  
   
    /** 
     * @Description <p> 拷贝非空对象属性值 </P> 
     * @param source 源对象 
     * @param target 目标对象 
     */  
    public static void copyPropertiesIgnoreNull(Object source, Object target) {  
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));  
    }  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int j=0;j<10000;j++)
		{
			Set<String> testSet = new HashSet<String>();
			for(int i=0; i<5000; i++)
			{
				testSet.add(UUID.randomUUID().toString());
			}
			if(testSet.size() < 5000)
				System.out.println(testSet.size());
		}
		
	}

}
