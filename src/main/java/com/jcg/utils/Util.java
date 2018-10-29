package com.jcg.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.util.FileUtil;

import com.alibaba.fastjson.JSONObject;



/*import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

/*import com.opensymphony.xwork2.ActionContext;
import com.yt.bean.Equipment;
import com.yt.bean.EquipmentBean;
import com.yt.bean.ExcelBean;*/

/**
 * 公共类
 * @author achenshengping
 *
 */
public class Util {
	public static JSONObject readJson(Integer type) {
		String url = Util.class.getResource("").getPath()
				.replaceAll("%20", " ");
		String filed="";
		if (type==1) {
			filed="sidebar.json";
		}else{
			filed="systemMenu.json";
		}
		String path = url.substring(0, url.indexOf("WEB-INF"))
				+ "WEB-INF/classes/com/yt/file/"+filed;
		File file = new File(path);
		
		JSONObject jsons = null;
		try {
//			String json = FileUtil.readAsString(file);
			byte[] bt=FileUtil.readAsByteArray(file);
			String json=new String(bt,"UTF-8");
			jsons = JSONObject.parseObject(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return jsons;
		}

	}
	//获取ip地址
	public static String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}
	//字符串转换为时间戳
	public static long timeStemp(String time){
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     Date date=new Date();
		try {
			date = simpleDateFormat .parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     long timeStemp = date.getTime();
	     return timeStemp/1000;
	} 
	//复制上传的文件并保存
    public static void copy(File src, File dst) {  
        InputStream in = null;  
        OutputStream out = null;  
        try {  
            in = new BufferedInputStream(new FileInputStream(src), (16 * 1024));  
            out = new BufferedOutputStream(new FileOutputStream(dst),  
                    (16*1024));  
            byte[] buffer = new byte[(16*1024)];  
            int len = 0;  
            while ((len = in.read(buffer)) > 0) {  
                out.write(buffer, 0, len);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (null != in) {  
                try {  
                    in.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (null != out) {  
                try {  
                    out.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    } 
    
	public  void setPro(Properties config,String pro){  
      try{  
             String url = this.getClass().getResource("").getPath().replaceAll("%20", " ");  
             String path = url.substring(0, url.indexOf("WEB-INF")) + "WEB-INF/classes/com/yt/"+pro;  
//	             config = new Properties(); 
             FileOutputStream fos = new FileOutputStream(path);
             config.store(fos,"update"); 
             fos.close();
//	             return config.getProperty("rcerp.url");  
         }
      	catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
      
     }
	public static JSONObject readJson(String paths) {
		String url = Util.class.getResource("").getPath()
				.replaceAll("%20", " ");
		String path = url.substring(0, url.indexOf("WEB-INF"))
				+ "WEB-INF/classes/com/yt/" + paths;
		File file = new File(path);
		JSONObject jsons = null;
		try {
			byte[] bt=FileUtil.readAsByteArray(file);
			String json=new String(bt,"UTF-8");
			jsons = JSONObject.parseObject(json);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return jsons;
		}
	}

	/**
	 * 获取配置文件中的信息
	 * @param pro
	 * @return
	 */
	public  Properties readRcErpURL(String pro){  
		Properties config=new Properties();
      try{  
             String url = this.getClass().getResource("").getPath().replaceAll("%20", " ");  
             String path = url.substring(0, url.indexOf("WEB-INF")) + "WEB-INF/classes/"+pro;
             if(path.startsWith("file:/")){
            	 path = path.split("file:/")[1];
             }
//	             config = new Properties(); 
             FileInputStream fis= new FileInputStream(path);
             config.load(fis); 
             fis.close();
         }
      	catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			return config;
		}
     }
	
	/**
	 * 获取配置文件中的信息
	 * @param pro
	 * @return
	 */
	public String getDBPropertiesByKey(String pro){  
		Properties config = this.readRcErpURL("db.properties");
		return config.getProperty(pro);
	}
	
	/*public static String getToken() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		// HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get();
		
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equals("token")) {
					
					return c.getValue();
				}
			}
		}
		return null;
	}*/
	
	public static String getToken(Cookie[] cookies) {
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equals("token")) {
					
					return c.getValue();
				}
			}
		}
		return null;
	}

	public static String getRoleName(Integer roleId) {
		switch (roleId) {
		case 1:
			return "总部超级管理员";

		case 2:

			return "管理员";
		case 3:

			return "库管员";
		case 4:

			return "观察员";
		case 5:

			return "代理超级管理员";
		case 6:

			return "操作员";

		default:
			return "系统管理员";
		}
	}
	
	/** 
     * 二进制--》十六进制转化 
     * @param buf 
     * @return 
     */  
	public static String parseByte2HexStr(byte buf[]) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
            String hex = Integer.toHexString(buf[i] & 0xFF);  
            if (hex.length() == 1) {  
                hex = '0' + hex;  
            }  
            sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 十六进制--》二进制转化 
     * @param hexStr 
     * @return 
     */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
        if (hexStr.length() < 1) {  
            return null;  
        }  
        byte[] result = new byte[hexStr.length() / 2];  
        for (int i = 0; i < hexStr.length() / 2; i++) {  
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);  
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),  
                    16);  
            result[i] = (byte) (high * 16 + low);  
        }  
        return result;  
    }  
	
	/**
	 * 获取basepath
	 * @param req
	 * @return 返回当前页面使用的协议，http 或是 https
	 */
	public static String getBashPath(HttpServletRequest req){
		return req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();
	}
	
	/**
	 * 获取basepath域名路径
	 * @param req
	 * @return 返回当前页面域名和使用的协议，http 或是 https
	 */
	public static String getDomainPath(HttpServletRequest req){
		return req.getScheme()+"://"+req.getServerName()+req.getContextPath();
	}
	
	/**
	 * 字符串截取
	 * @param beginIndex
	 * @param endIndex
	 * @param param
	 * @return
	 */
    public static String substringUtil(int beginIndex, int endIndex, String param) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > param.length()) {
        	endIndex = param.length();
        }
        return param.substring(beginIndex, endIndex);
    }
    
    /**
     * URL中参数替换
     * @param param
     * @return
     */
    public static String subURL(String param) {
    	param = param.replaceAll("%2F", "--2F--");
    	param = param.replaceAll("%5C", "--5C--");
    	return param;
    }
    
    /**
     * URL中参数替换
     * @param param
     * @return
     */
    public static String unSubURL(String param) {
    	/*param = param.replaceAll("--2F--", "%2F");
    	param = param.replaceAll("--5C--", "%5C");*/
    	param = param.replaceAll("--2F--", "/");
    	param = param.replaceAll("--5C--", "\\\\");
    	return param;
    }
    
    /** 
     * 获取16进制随机数 UUID
     * @param len 返回值的长度
     * @return 
     * @throws CoderException 
     */  
    public static String randomHexString(int len)  {  
        try {  
            StringBuffer result = new StringBuffer();  
            for(int i=0;i<len;i++) {  
                result.append(Integer.toHexString(new Random().nextInt(16)));  
            }  
            return result.toString().toUpperCase();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    /** 
     * Mac添加冒号：无冒号的Mac加冒号
     * @param Mac
     * @return 
     */  
    public static String macAddColon(String mac)  {  
		if(mac.length()<=12){
			// 组装Mac数据
			StringBuilder sb=new StringBuilder(mac.toLowerCase());
			sb.insert(10,":");
			sb.insert(8,":");
			sb.insert(6,":");
			sb.insert(4,":");
			sb.insert(2,":");
			mac = sb.toString();
		}
    	return mac.toLowerCase();  
    }
    /** 
     * Mac删除冒号
     * @param Mac
     * @return 
     */  
    public static String macDelColon(String mac)  {  
    	return mac.replaceAll(":", "").toLowerCase();  
    }  
    
    /**
     * 将JSONObject对象转换成HTTP参数字符串
     * @param param JSONObject对象
     * @return
     */
    public static String analysisJSON(com.alibaba.fastjson.JSONObject param){
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = param.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = param.getString(key);
			sb.append(key);
			sb.append("=");
			sb.append(value);
			sb.append("&");
		}
		String result = sb.toString();
		return result.substring(0,result.length() - 1);
	}
    
    /**
     * 将字符串格式的json拼接成HTTP参数字符串
     * @param param json格式字符串
     * @return
     */
    public static String analysisJSON(String params){
    	com.alibaba.fastjson.JSONObject param = com.alibaba.fastjson.JSONObject.parseObject(params);
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = param.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = param.getString(key);
			sb.append(key);
			sb.append("=");
			sb.append(value);
			sb.append("&");
		}
		String result = sb.toString();
		return result.substring(0,result.length() - 1);
	}
    
    /**
     * 将图片转换成字节数组
     * @param path 图片路径
     * @return
     */
    public static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 字节数组转成图片
	 * @param data 字节数组
	 * @param path 图片存储路径
	 * @throws IOException 
	 */
	public static boolean byte2image(byte[] data, String path) throws IOException {
		if (data.length < 3 || path.equals(""))return false;
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
		imageOutput.write(data, 0, data.length);
		imageOutput.close();
		//System.out.println("Make Picture success,Please find image in " + path);
		return true;
	}
    
    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                //System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                //System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            //System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
    
    /**
     * 计算当天剩余时间多长(单位秒)
     * @return
     */
    public static long remainingSeconds(){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		long time1 = cal.getTime().getTime();
		long time2 = new Date().getTime();
		long count = ((time1 - time2)/1000);
		//System.out.println(count);
		
		if(count < 0){
			count = 0;
		}
		return count;
    }
    
    /**
     * 读取json文件
     */
    public static String readJsonFile(String path){
        String laststrJson = "";
        BufferedReader reader;
        try {  
            reader = new BufferedReader(new FileReader(new File(path)));
            String tempString = null;
            //int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                laststrJson = laststrJson + tempString;
                //line++;
            }
            reader.close();  
        } catch (IOException e1) {
            e1.printStackTrace();  
        }
        return laststrJson;
    }

    /**
     * 写出json文件
     */
    public static void writeJsonFile(String newJsonString, String path){
        try {
            FileWriter fw = new FileWriter(path);
            PrintWriter out = new PrintWriter(fw);  
            out.write(newJsonString);  
            out.println();
            fw.close();  
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 格式化json字符串
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
            case '"':
                                if (last != '\\'){
                    isInQuotationMarks = !isInQuotationMarks;
                                }
                sb.append(current);
                break;
            case '{':
            case '[':
                sb.append(current);
                if (!isInQuotationMarks) {
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                }
                break;
            case '}':
            case ']':
                if (!isInQuotationMarks) {
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                }
                sb.append(current);
                break;
            case ',':
                sb.append(current);
                if (last != '\\' && !isInQuotationMarks) {
                    sb.append('\n');
                    addIndentBlank(sb, indent);
                }
                break;
            default:
                sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     * 
     * @param sb
     * @param indent
     * @author lizhgb
     * @Date 2015-10-14 上午10:38:04
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
    
    /**
     * 绘制字体头像
     * 如果是英文名，只显示首字母大写
     * 如果是中文名，只显示最后两个字
     * @param name
     * @param outputPath
     * @param outputName
     * @throws IOException
     */
    public static void generateImg(String name, String outputPath, String outputName)
            throws IOException {
        int width = 100;
        int height = 100;
        int nameLen = name.length();
        String nameWritten;
        //如果用户输入的姓名少于等于2个字符，不用截取
        if (nameLen <= 2) {
            nameWritten = name;
        } else {
            //如果用户输入的姓名大于等于3个字符，截取后面两位
            String first = name.substring(0, 1);
            if (isChinese(first)) {
                //截取倒数两位汉字
                nameWritten = name.substring(nameLen - 2);
            } else {
                //截取前面的两个英文字母
                nameWritten = name.substring(0, 2);
            }
        }
        String filename = outputPath + File.separator + outputName + ".jpg";
        File file = new File(filename);
        //Font font = new Font("微软雅黑", Font.PLAIN, 30);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setBackground(getRandomColor());
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.WHITE);
        Font font = null;
        //两个字及以上
        if(nameWritten.length() >= 2) {
            font = new Font("微软雅黑", Font.PLAIN, 30);
            g2.setFont(font);
            String firstWritten = nameWritten.substring(0, 1);
            String secondWritten = nameWritten.substring(1, 2);
            //两个中文 如 言曌
            if (isChinese(firstWritten) && isChinese(secondWritten)) {
                g2.drawString(nameWritten, 20, 60);
            }
            //首中次英 如 罗Q
            else if (isChinese(firstWritten) && !isChinese(secondWritten)) {
                g2.drawString(nameWritten, 27, 60);
                //首英,如 AB
            } else {
                nameWritten = nameWritten.substring(0,1);
            }
        }
        //一个字
        if(nameWritten.length() ==1) {
            //中文
            if(isChinese(nameWritten)) {
                font = new Font("微软雅黑", Font.PLAIN, 50);
                g2.setFont(font);
                g2.drawString(nameWritten, 25, 70);
            }
            //英文
            else {
                font = new Font("微软雅黑", Font.PLAIN, 55);
                g2.setFont(font);
                g2.drawString(nameWritten.toUpperCase(), 33, 67);
            }
        }
        BufferedImage rounded = makeRoundedCorner(bi, 99);
        //将图片资源输出在某个位置
        ImageIO.write(rounded, "png", file);
    }
    
    /**
     * 绘制头像，将图片资源转为字节数组
     * @param name
     * @return
     * @throws IOException
     */
    public static byte[] generateImg(String name) throws IOException {
    	int width = 100;
    	int height = 100;
    	int nameLen = name.length();
    	String nameWritten;
    	//如果用户输入的姓名少于等于2个字符，不用截取
    	if (nameLen <= 2) {
    		nameWritten = name;
    	} else {
    		//如果用户输入的姓名大于等于3个字符，截取后面两位
    		String first = name.substring(0, 1);
    		if (isChinese(first)) {
    			//截取倒数两位汉字
    			nameWritten = name.substring(nameLen - 2);
    		} else {
    			//截取前面的两个英文字母
    			nameWritten = name.substring(0, 2);
    		}
    	}
    	BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g2 = (Graphics2D) bi.getGraphics();
    	g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
    			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    	g2.setBackground(getRandomColor());
    	g2.clearRect(0, 0, width, height);
    	g2.setPaint(Color.WHITE);
    	Font font = null;
    	//两个字及以上
    	if(nameWritten.length() >= 2) {
    		font = new Font("微软雅黑", Font.PLAIN, 30);
    		g2.setFont(font);
    		String firstWritten = nameWritten.substring(0, 1);
    		String secondWritten = nameWritten.substring(1, 2);
    		//两个中文 如 言曌
    		if (isChinese(firstWritten) && isChinese(secondWritten)) {
    			g2.drawString(nameWritten, 20, 60);
    		}
    		//首中次英 如 罗Q
    		else if (isChinese(firstWritten) && !isChinese(secondWritten)) {
    			g2.drawString(nameWritten, 27, 60);
    			//首英,如 AB
    		} else {
    			nameWritten = nameWritten.substring(0,1);
    		}
    	}
    	//一个字
    	if(nameWritten.length() ==1) {
    		//中文
    		if(isChinese(nameWritten)) {
    			font = new Font("微软雅黑", Font.PLAIN, 50);
    			g2.setFont(font);
    			g2.drawString(nameWritten, 25, 70);
    		}
    		//英文
    		else {
    			font = new Font("微软雅黑", Font.PLAIN, 55);
    			g2.setFont(font);
    			g2.drawString(nameWritten.toUpperCase(), 33, 67);
    		}
    	}
    	BufferedImage rounded = makeRoundedCorner(bi, 99);
    	//将图片资源转换为字节数组
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	ImageIO.write(rounded, "png", baos );
    	baos.flush();
    	return baos.toByteArray();
    }
    
    /**
     * 判断字符串是否为中文
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find())
            return true;
        else
            return false;
    }
    /**
     * 获得随机颜色
     * @return
     */
    private static Color getRandomColor() {
        String[] beautifulColors =
                new String[]{"232,221,203", "205,179,128", "3,101,100", "3,54,73", "3,22,52",
                        "237,222,139", "251,178,23", "96,143,159", "1,77,103", "254,67,101", "252,157,154",
                        "249,205,173", "200,200,169", "131,175,155", "229,187,129", "161,23,21", "34,8,7",
                        "118,77,57", "17,63,61", "60,79,57", "95,92,51", "179,214,110", "248,147,29",
                        "227,160,93", "178,190,126", "114,111,238", "56,13,49", "89,61,67", "250,218,141",
                        "3,38,58", "179,168,150", "222,125,44", "20,68,106", "130,57,53", "137,190,178",
                        "201,186,131", "222,211,140", "222,156,83", "23,44,60", "39,72,98", "153,80,84",
                        "217,104,49", "230,179,61", "174,221,129", "107,194,53", "6,128,67", "38,157,128",
                        "178,200,187", "69,137,148", "117,121,71", "114,83,52", "87,105,60", "82,75,46",
                        "171,92,37", "100,107,48", "98,65,24", "54,37,17", "137,157,192", "250,227,113",
                        "29,131,8", "220,87,18", "29,191,151", "35,235,185", "213,26,33", "160,191,124",
                        "101,147,74", "64,116,52", "255,150,128", "255,94,72", "38,188,213", "167,220,224",
                        "1,165,175", "179,214,110", "248,147,29", "230,155,3", "209,73,78", "62,188,202",
                        "224,160,158", "161,47,47", "0,90,171", "107,194,53", "174,221,129", "6,128,67",
                        "38,157,128", "201,138,131", "220,162,151", "137,157,192", "175,215,237", "92,167,186",
                        "255,66,93", "147,224,255", "247,68,97", "185,227,217"};
        int len = beautifulColors.length;
        Random random = new Random();
        String[] color = beautifulColors[random.nextInt(len)].split(",");
        return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]),
                Integer.parseInt(color[2]));
    }
    /**
     * 图片做圆角处理
     * @param image
     * @param cornerRadius
     * @return
     */
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }
    
}
