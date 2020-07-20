package com.huiyou.msfw.controller;


import com.huiyou.msfw.model.FileAddress;
import com.huiyou.msfw.model.FileEntity;
import com.huiyou.msfw.utils.Base64Util;
import com.huiyou.msfw.utils.FileUploadTool;
import me.fishlord.common.result.BaseResultEntity;
import me.fishlord.common.result.ResultEntity;
import me.fishlord.common.utils.HttpUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件上传
 * 
 * @author fishlord
 * 
 */
@Controller
public class FileController {
	@Autowired

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Value("${file.basePath}")
	private String basePath;
	@Value("${file.baseUrl}")
	private String baseUrl;
	@Value("${project.domain}")
	private String domain;
	/**
     * 上传
     *
     * @return
     */
	@RequestMapping("/file/upload")
	@ResponseBody
	public BaseResultEntity upload(
			@RequestParam(value = "file", required = true) CommonsMultipartFile file) {
		try {
			String savePath = HttpUtils.getSavePath(basePath,file);
			System.out.println(savePath);
			System.out.println(123);
			file.transferTo(new File(savePath));
			System.out.println("shangchuan"+domain + baseUrl + savePath.replace(basePath,""));
			return ResultEntity.successd(domain + baseUrl + savePath.replace(basePath,""));
		} catch (IOException e) {
			logger.error("文件保存失败", e);
			return ResultEntity.fail("文件保存失败");
		}
	}

	/* 文件导入 */
	@SuppressWarnings({ "deprecation", "resource" })
/*	@RequestMapping("/filepath/upload")
	@ResponseBody
	public ResultEntity importUsers(@RequestParam MultipartFile file,
                                    HttpServletRequest request)throws IOException, EncryptedDocumentException, InvalidFormatException{
		ResultEntity resultEntity = new ResultEntity();
		Workbook wb = WorkbookFactory.create(file.getInputStream());
	     
   		  Sheet sheet = wb.getSheetAt(0);
   		  System.out.println(sheet.getRow(0));
   	      //获取行数
   	      int rows = sheet.getLastRowNum();
   	      System.out.println(rows);
   	      //获取列数
   	      int clos = sheet.getRow(0).getPhysicalNumberOfCells();
   	      System.out.println(clos);
   	      int cunt = 0;
   	      for(int i=1;i<=rows;i++) {
   	    	  for(int j=0;j<clos;j++) {
   	    		  SysDiscuss sysDiscuss = new SysDiscuss();
   	    		  String discussTitle = sheet.getRow(i).getCell(j++).getStringCellValue();
   	    		  String discussContent = sheet.getRow(i).getCell(j++).getStringCellValue();
   	    		  int discussType = Integer.parseInt(NumberToTextConverter.toText(sheet.getRow(i).getCell(j++).getNumericCellValue()));
   	    		  String productname = sheet.getRow(i).getCell(j++).getStringCellValue();
   	    		  String remarks = sheet.getRow(i).getCell(j++).getStringCellValue();
   	    		  int discussStar = Integer.parseInt(NumberToTextConverter.toText(sheet.getRow(i).getCell(j++).getNumericCellValue()));
   	    		  System.out.println(discussTitle+discussContent+discussType+productname+remarks+discussStar);
   	    		  sysDiscuss.setDiscussTitle(discussTitle);
   	    		  sysDiscuss.setDiscussContent(discussContent);
   	    		  sysDiscuss.setDiscussType(discussType);
   	    		  if(discussType==1){
   	    			  SysServiceExample example = new SysServiceExample();
   	    			  example.or().andIsActiveEqualTo(0).andTitleEqualTo(productname);
   	    			  List<SysService> list = sysServiceService.queryIdbyTitle(example);
   	    			  sysDiscuss.setPid(list.get(0).getServiceId());
   	    		  }else {
   	    			  ProductExample productExample = new ProductExample();
   	    			  productExample.or().andIsDelEqualTo(0).andProductNameEqualTo(productname);
   	    			  List<Product> list1 = productService.queryIdByname(productExample);
   	    			  sysDiscuss.setPid(list1.get(0).getProductid());
   	    		  }
   	    		  sysDiscuss.setRemarks(remarks);
   	    		  sysDiscuss.setDiscussStar(discussStar);
   	    		  sysDiscuss.setIsDelete(0);
   	    		  sysDiscuss.setDiscussTime(new Date());
   	    		  int count = sysDiscussService.insetSysDiscuss(sysDiscuss);
   	    		  cunt+=count;
   	    	  }
   	      }
   	      if(cunt == rows) {
   	    	  resultEntity.setCode(0);
   	    	  resultEntity.setMsg("文件导入成功");
   	      }
		
		return resultEntity;
		
	}*/

/*
	*/
/**
	 * @By wxq
	 * @name 视频上传并获取第一帧上传
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */


	@RequestMapping(value = "/file/uploadImg",method = RequestMethod.POST)
	@ResponseBody
	public ResultEntity userUpload(@RequestParam(value = "file", required = false) CommonsMultipartFile file,
                                   HttpServletRequest request) throws Exception {
		String savePath = HttpUtils.getSavePath(basePath,file);
		File f = new File(domain + baseUrl);
		//如果没有这个目录，创建
		if (!f.exists()) {
			f.mkdirs();
		}
		//判断上传的视频文件是否过大
		if (file.getSize() > 100 * 1024 * 1024) {
			return new ResultEntity(-1, "上传视频大于500M，失败", null);
		}
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String dateString = formatter.format(currentTime);
		//
		String imgPath = domain + baseUrl+dateString;
		file.transferTo(new File(savePath));
		File img = new File(imgPath);
		//如果没有这个目录，创建
		if (!img.exists()) {
			img.mkdirs();
		}
		String imgpath=savePath.substring(0,savePath.length()-3);
		//使用ffmpeg截取第一秒
		imgPath = imgpath + "jpg";
		String startTime="00:00:01";
		String command = "ffmpeg    -i " + savePath  + " -f image2 -r 1 -ss "+startTime + " " + imgPath;
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null){
				System.out.println(line);}
		} catch (Throwable t) {
			t.printStackTrace();
			return ResultEntity.success("失败",null);
		}
		FileAddress fileAddress = new FileAddress();
		fileAddress.setAdrImg(domain + imgPath.substring(5));
		fileAddress.setAdrVid(domain + savePath.substring(5));
		return ResultEntity.success("截取成功",fileAddress);
	}


	/**
	 * 上传
	 *
	 * @return
	 */
	@RequestMapping("/file/uploadMultiple")
	@ResponseBody
	public BaseResultEntity uploadMultiple(
			@RequestParam(value = "files", required = true) CommonsMultipartFile[] files) {
		try {
			List <String> paths=new ArrayList<>();
			for (int i = 0; i < files.length; i++) {
				CommonsMultipartFile file =files[i];
				String savePath = HttpUtils.getSavePath(basePath, file);
				file.transferTo(new File(savePath));
				String path=domain + baseUrl + savePath.replace(basePath,"");
				paths.add(path);
			}
			return ResultEntity.successd(paths);
		} catch (IOException e) {
			logger.error("文件保存失败", e);
			return ResultEntity.fail("文件保存失败");
		}
	}
	/*
	 * 上传
	 *
	 * @return
	 */
	@RequestMapping("/file/uploadForLayui")
	@ResponseBody
	public BaseResultEntity uploadForLayui(
			@RequestParam(value = "file", required = true) CommonsMultipartFile file) {
		try {
			String savePath = HttpUtils.getSavePath(basePath, file);
			file.transferTo(new File(savePath));
			Map<String,Object> map=new HashMap<>();
			map.put("src",domain + baseUrl + savePath.replace(basePath, ""));
			map.put("title",file.getFileItem().getName());
			return ResultEntity.successd(map);
		} catch (IOException e) {
			logger.error("文件保存失败", e);
			return ResultEntity.fail("文件保存失败");
		}
	}
	/**
	 * 上传base64
	 * 
	 * @return
	 */
	@RequestMapping("/file/base64upload")
	@ResponseBody
	public BaseResultEntity base64upload(
			@RequestParam(value = "file", required = true) String file) {
		
		MultipartFile file2 = Base64Util.Base64ToMultipartFile(file);
		String fileName = file2.getOriginalFilename();
        String ext = fileName.substring(fileName.indexOf("."), fileName.length());
        Random random = new Random();
        fileName = "" + random.nextInt(10000) + System.currentTimeMillis() + ext;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Integer year = cal.get(Calendar.YEAR);//获取年份
        Integer month=cal.get(Calendar.MONTH)+1;//获取月份
        String monthS ="";
        if(month<10) {
        	monthS = "0"+month.toString();
        }else {
        	monthS = month.toString();
        }
        Integer day=cal.get(Calendar.DATE);//获取日
        String dayS ="";
        if(day<10) {
        	dayS = "0"+month.toString();
        }else {
        	dayS = month.toString();
        }
        String filename1 = year.toString()+"/"+monthS+"/"+dayS+"/" ;
        File file12 = new File(basePath +filename1);
        if(!file12 .exists()  && !file12 .isDirectory()){       
        	file12 .mkdirs(); 
        	
        }
		try {
			String savePath = basePath+filename1 + fileName;
			file2.transferTo(new File(savePath));
			return ResultEntity.successd(domain + baseUrl + savePath.replace(basePath, ""));
		} catch (IOException e) {
			logger.error("文件保存失败", e);
			return ResultEntity.fail("文件保存失败");
		}
	}
	/**
	 * 多图上传
	 * @param files
	 * @return
	 */
	@RequestMapping("/file/multiUpload")
	@ResponseBody
	public BaseResultEntity multiUpload(
			@RequestParam(value = "files", required = true) CommonsMultipartFile[] files) {

		try {
			List<String> list = new ArrayList<>();
			for (CommonsMultipartFile file : files) {
				String savePath = HttpUtils.getSavePath(basePath, file);
				file.transferTo(new File(savePath));
				list.add(domain + baseUrl + savePath.replace(basePath, ""));
			}
			return ResultEntity.successd(list);
		} catch (IOException e) {
			logger.error("文件保存失败", e);
			return ResultEntity.fail("文件保存失败");
		}
	}

	@RequestMapping(value = "/file/uploadFile", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
							   HttpServletRequest request, ModelMap map) {
		String message = "";
		FileEntity entity = new FileEntity();
		FileUploadTool fileUploadTool = new FileUploadTool();
		try {
            entity = fileUploadTool.createFile(multipartFile, request);
			if (entity != null) {
//                service.saveFile(entity);
				message = "上传成功";
				map.put("entity", entity);
				map.put("result", message);
			} else {
				message = "上传失败";
				map.put("result", message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("result", map);
	}
}
