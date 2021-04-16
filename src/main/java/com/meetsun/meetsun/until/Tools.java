package com.meetsun.meetsun.until;
 
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
 
public class Tools {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
	}
	
	public static String get14Times() {
		String systemTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		return systemTime;
	}
	
	public static String getMMddHHmmss() {
		String systemTime = new SimpleDateFormat("MMddHHmmss").format(new Date(System.currentTimeMillis()));
		return systemTime;
	}
	
	public static String getMMddHHmmssSSS() {
		String systemTime = new SimpleDateFormat("MMddHHmmssSSS").format(new Date());
		return systemTime;
	}
	
	public static String get19DateTimes() {
		String systemTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
		return systemTime;
	}
	//获取yyyy-MM-dd格式
	public static String get10DateTimes() {
		String systemTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
		return systemTime;
	}
	//获取年份
	public static String getYear() {
		String systemTime = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
		return systemTime;
	}
	//获取时间戳
	public static int getCalendar(String time) {
		int times = 0;
        try {  
            times = (int) ((Timestamp.valueOf(time).getTime())/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        if(times==0){
        	System.out.println("String转10位时间戳失败");
        }
		return times; 
	}
	//计算日期相差天数
	public static int differentDaysByMillisecond(Date date1,Date date2){
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
	//讲yyyy-MM-dd HH:mm:ss日期转换为date类型
	public static Date getDate(String str) throws ParseException {
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		Date sqlDate = new Date(date.getTime());
		return sqlDate;
	}
	
	
	@Transactional
	public static Result<Object> deletePhotoAdrr(String adrr) {
		String filePath = Common.realPath+adrr;
		File file = new File(filePath);
		try {
		    boolean f = file.delete(); // 删除照片
		    if (f) {
		    	return Result.success("success");
		    } else {
		    	Result.error("error");
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    return Result.error("图片不存在或其他异常！");
		}
		return Result.success("error");
	}
	
	public static String savePhoto(MultipartFile file) {
		String path = null;// 文件路径
        String type = null;// 文件类型
        String photoAdrr = null;
        String fileName = file.getOriginalFilename();// 文件原名称
        type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
        if (type != null) {// 判断文件类型是否为空
            if (isType(type.toUpperCase())) {
                // 项目在容器中实际发布运行的根路径
                //String realPath = vo.getRealPath();
                // 自定义的文件名称
                String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                // 设置存放图片文件的路径
                path = Common.realPath+trueFileName;
                // 转存文件到指定的路径
                File f = new File(path);
                if (!f.getParentFile().exists()) {//判断父目录路径是否存在，即test.txt前的I:\a\b\
                    try {
                        f.getParentFile().mkdirs();//不存在则创建父目录
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
					file.transferTo(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
                photoAdrr = trueFileName;
            }else {
            	return "false,"+type;
            }
        }
        return photoAdrr;
	}
	
	public static String savePayTypePhoto(MultipartFile file,String str) {
		String path = null;// 文件路径
        String type = null;// 文件类型
        String photoAdrr = null;
        String fileName = file.getOriginalFilename();// 文件原名称
        type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
        if (type != null) {// 判断文件类型是否为空
            if (isType(type.toUpperCase())) {
                // 项目在容器中实际发布运行的根路径
                //String realPath = vo.getRealPath();
                // 自定义的文件名称
                String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                // 设置存放图片文件的路径
                path = Common.realPath+str+trueFileName;
                // 转存文件到指定的路径
                File f = new File(path);
                if (!f.getParentFile().exists()) {//判断父目录路径是否存在，即test.txt前的I:\a\b\
                    try {
                        f.getParentFile().mkdirs();//不存在则创建父目录
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
					file.transferTo(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
                photoAdrr = trueFileName;
            }else {
            	return "false,"+type;
            }
        }
        return str+photoAdrr;
	}

	public static String base64ToFile(String base64, String savePath) {
	    File file = null;
	    //创建文件目录
	    File dir = new File(Common.realPath+savePath);
	    if (!dir.exists() && !dir.isDirectory()) {
	    	dir.mkdirs();
	    }
	    String type=base64.split(";")[0].split("/")[1];
	    if(!type.equals("jpg")) {
	    	if(!type.equals("gif")) {
	    		if(!type.equals("png")) {
	    			if(!type.equals("jpeg")) {
	    				type = "mp4";
	    	    	}
		    	}
	    	}
	    }
	    String fileName = String.valueOf(System.currentTimeMillis()) + "." + type;
	    String path = Common.realPath+savePath+fileName;
	    BufferedOutputStream bos = null;
	    java.io.FileOutputStream fos = null;
	    try {
	        byte[] bytes = Base64.getDecoder().decode(base64.split(",")[1]);
	        file=new File(path);
	        fos = new java.io.FileOutputStream(file);
	        bos = new BufferedOutputStream(fos);
	        bos.write(bytes);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (bos != null) {
	            try {
	                bos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        if (fos != null) {
	            try {
	                fos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return savePath+fileName;
	}
	
	public static boolean isType(String type) {
		String[] str = Common.type.split("、");
		int flag = 0;
		for(String st:str) {
			if(st.equals(type)) {
				flag = 1; 
				break;
			}
		}
		if(flag == 1) {
			return true;
		}else {
			return false;
		}
	}
}
