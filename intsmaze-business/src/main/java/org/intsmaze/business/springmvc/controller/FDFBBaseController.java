
package org.intsmaze.business.springmvc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.intsmaze.business.Interfaces.IUserApi;
import org.intsmaze.business.service.IAuditLogService;
import org.intsmaze.business.service.IButtonCustomConfigService;
import org.intsmaze.business.springmvc.util.JsonUtil;
import org.intsmaze.business.springmvc.util.UploadFileTo;
import org.intsmaze.business.vo.FDFBButtonCustomConfigVo;
import org.intsmaze.core.exception.FDFBexception;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
public class FDFBBaseController {

	private static final Logger log = Logger.getLogger(FDFBBaseController.class);
	
	@Autowired
	IUserApi userApi;
	
	@Autowired
	SessionLocaleResolver resolver;
	
	@Autowired
	IAuditLogService auditLogService;
	
	@Autowired
	IButtonCustomConfigService configService;
	
	@RequestMapping("language")
	public ModelAndView language(HttpServletRequest request,
			HttpServletResponse response, String langType) {

		langType = langType.toLowerCase();
		if (langType == null || langType.equals("")) {
			return new ModelAndView(Constant.REDIRECT_SEP_STR);
		} else {
			if (langType.equals("zh_cn")) {
				resolver.setLocale(request, response, Locale.CHINA);
			} else if (langType.equals("en")) {
				resolver.setLocale(request, response, Locale.ENGLISH);
			} else {
				resolver.setLocale(request, response, Locale.CHINA);
			}
		}
		
		request.getSession().setAttribute(Constant.SESSION_USERMENU,null);//由于国际化原因，seseion中的munu必须重新获取
		
		String retUrl = request.getHeader("Referer");  
		String contextPath = request.getContextPath();
		retUrl = retUrl.substring(retUrl.indexOf(contextPath) + contextPath.length());
		return new ModelAndView(Constant.REDIRECT_STR + retUrl);//返回上一级请求
	}
	
	@RequestMapping(value="initMenu", method=RequestMethod.POST)
	@ResponseBody
	public String initMenu(HttpServletRequest request,
			HttpServletResponse response) {
		String menuInThread = (String)request.getSession().getAttribute(Constant.SESSION_USERMENU);
		
		
		if(menuInThread==null || "".equals(menuInThread))
		{
			Map user = (Map) request.getSession().getAttribute(Constant.USERINFO_SESSION);
			menuInThread = userApi.getPermitByPermitTypeAndRole(Constant.PERMITTYPE_MENU, String.valueOf(user.get(Constant.USER_ROLE_SESSION)), Constant.PERMIT_ORDER_COLUMN, Constant.INIT_TYPE_BY_ROLE, request);
			request.getSession().setAttribute(Constant.SESSION_USERMENU,menuInThread);
		}
		
		return menuInThread;
		
	}
	
	
//	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
//	@ResponseBody
//	public String uploadFile( @RequestParam("files") MultipartFile file,HttpServletRequest request){
//		
//		
//		String path = request.getSession().getServletContext().getRealPath("upload") + "/" + request.getParameter("path");
//		
//		String fileName = file.getOriginalFilename();
//		
//		File targetFile = new File(path, fileName);
//		
//		if (!targetFile.exists()) {
//            targetFile.mkdirs();
//        }
//		
//        try {
//            file.transferTo(targetFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        if( FileUtil.getFileSize(new File(path)) > 5*1024*1024 ){
//        	
//        	targetFile.delete();
//        	
//        	return "full";
//        }else{
//        	return "success";
//        }
//	}
	
	@RequestMapping(value="/getFileList",method=RequestMethod.GET)
	@ResponseBody
	public String getFileList(HttpServletRequest request){	
		
		String halfPath = request.getParameter("path");
		
		String path = request.getSession().getServletContext().getRealPath("upload") + "/" + halfPath;
		
		File file = new File(path);
		
		JSONArray jsonArr = new JSONArray();
		
		for(String fileName:file.list()){
			
			JSONObject json = new JSONObject();
			
			json.put("fileName", fileName);
			
			jsonArr.add(json);
		}
		
		return jsonArr.toString();
	}
	
	@RequestMapping(value="/downloadFile")
    public ResponseEntity<byte[]> download(HttpServletRequest request)throws Exception {
    	//下载文件路径
    	
    	String halfPath = request.getParameter("path");
    	
    	System.out.println(halfPath);
    	
    	String path = request.getSession().getServletContext().getRealPath("upload") + "/" + halfPath;
    	
    	String fileName = halfPath.substring(halfPath.lastIndexOf("\\")+1);
    	
    	File file = new File(path);
    	HttpHeaders headers = new HttpHeaders();  
    	//下载显示的文件名，解决中文名称乱码问题  
    	String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
    	//通知浏览器以attachment（下载方式）打开图片
    	headers.setContentDispositionFormData("attachment", downloadFielName); 
    	//application/octet-stream ： 二进制流数据（最常见的文件下载）。
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);  
    }
    
    
	
	@RequestMapping(value="/deleteFile",method=RequestMethod.GET)
	@ResponseBody
	public String deleteFile(HttpServletRequest request){
		
		String path = request.getSession().getServletContext().getRealPath("upload") + "/" + request.getParameter("path");
		
		File file = new File(path);
		
		if(file.delete())
			return "success";
		else
			return "fail";
	}
	
	
	
	
	@RequestMapping(value="/uploadFiles", method=RequestMethod.POST)
	@ResponseBody
	public String uploadFiles(@RequestParam(value = "files", required = false) MultipartFile file, HttpServletRequest request)
	{
		
//		name = fileName,
//        size = file.ContentLength,
//        type = file.ContentType,
//        url = StorageRoot + fileName,
//        delete_url = "/" + this.OrgCode + "/" + this.SysCode + "/Attachment/" + fileName,
//        thumbnail_url = @"data:image/png;base64," + EncodeFile(fullName),
//        delete_type = "DELETE",
		String a = request.getParameter("docType");
		Random rand = new Random();
		int randNum = rand.nextInt(9999)+10000;
		String contextPath = request.getContextPath();        
		String orgFileName = file.getOriginalFilename();// getOriginalFilename和getName是不一样的哦
		String fileNameResult = "";
		UploadFileTo uft = new UploadFileTo();
		if(file == null)
		{
			//do Nothing
		}
		else
		{
			try {
				String path = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_DIR);// 文件保存目录，也可自定为绝对路径
				String[] fileNameArray = orgFileName.split("\\.");
				String type = fileNameArray.length>1?fileNameArray[1]:"";
				if(!"".equals(type))
				{
					type = "." + type;
				}
				String fileName = randNum + DateUtil.formatDateTimeForFile(new Date()) + type;
				log.debug(path);
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				file.transferTo(targetFile);
				fileNameResult = path + File.separatorChar + targetFile.getName();
				
				uft.setName(fileName);
				uft.setDeleteType("DELETE");
				uft.setDeleteUrl(contextPath + "/deleteFile?fileName=" + fileName);
				uft.setSize(file.getSize());
				uft.setThumbnailUrl(contextPath + Constant.UPLOAD_DIR + "/" + fileName);
				uft.setType(file.getContentType());
				uft.setUrl(contextPath + Constant.UPLOAD_DIR + "/" + fileName);
				uft.setLogicUrl(Constant.UPLOAD_DIR + "/" + fileName);
			} catch (Exception e) {
				e.printStackTrace();
				//do Nothing
			}
		}
		List uftList  = new ArrayList(1);
		uftList.add(uft);
		Map resultMap = new HashMap(1);
		resultMap.put("files", uftList);
		fileNameResult = JsonUtil.toJson(resultMap);
		return fileNameResult;
	}

	@RequestMapping(value="/deleteFile/{fileName}", method=RequestMethod.DELETE)
	public @ResponseBody List deleteFile(@PathVariable String fileName, HttpServletRequest request)
	{
		
//		name = fileName,
//        size = file.ContentLength,
//        type = file.ContentType,
//        url = StorageRoot + fileName,
//        delete_url = "/" + this.OrgCode + "/" + this.SysCode + "/Attachment/" + fileName,
//        thumbnail_url = @"data:image/png;base64," + EncodeFile(fullName),
//        delete_type = "DELETE",

		List<Map<String, Object>> results = new ArrayList(1);
		Map<String, Object> success = new HashMap(1);
		String contextPath = request.getContextPath();        
		String fileNameResult = "";
		UploadFileTo uft = new UploadFileTo();
		try {
			String path = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_DIR);// 文件保存目录，也可自定为绝对路径
			fileName = path + File.separatorChar + fileName;
			log.debug(fileName);
			File targetFile = new File(fileName);
			if (!targetFile.isFile()) {
				//do nothing
				success.put("success", false);
		        results.add(success);
			}
			else
			{
				targetFile.delete();
		        success.put("success", true);
		        results.add(success);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//do Nothing
			success.put("success", false);
	        results.add(success);
		}

//		fileNameResult = JsonUtil.toJson(resultMap);
		return results;
	}
	
	
	@RequestMapping(value="/uploadFiles", method=RequestMethod.GET)
	@ResponseBody
	public String uploadFileTest(HttpServletRequest request)
	{
		String a = request.getParameter("docType");
		/*test code*/
		String path = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_DIR);// 文件保存目录，也可自定为绝对路径
		String fileName = "1384920160708182851913.jpg";
		String contextPath = request.getContextPath();       
		
		UploadFileTo uft = new UploadFileTo();
		uft.setName(fileName);
		uft.setDeleteType("DELETE");
		uft.setDeleteUrl(contextPath + "/deleteFile?fileName=" + fileName);
		uft.setSize(123);
		uft.setThumbnailUrl(contextPath + Constant.UPLOAD_DIR + "/" + fileName);
		uft.setType("jpg");
		uft.setUrl(contextPath + Constant.UPLOAD_DIR + "/" + fileName);
		uft.setLogicUrl(Constant.UPLOAD_DIR + "/" + fileName);
		
		String fileName2 = "1570320160708181335232.jpg";
		
		UploadFileTo uft2 = new UploadFileTo();
		uft2.setName(fileName2);
		uft2.setDeleteType("DELETE");
		uft2.setDeleteUrl(contextPath + "/deleteFile?fileName=" + fileName2);
		uft2.setSize(234);
		uft2.setThumbnailUrl(contextPath + Constant.UPLOAD_DIR + "/" + fileName2);
		uft2.setType("jpg");
		uft2.setUrl(contextPath + Constant.UPLOAD_DIR + "/" + fileName2);
		uft2.setLogicUrl(Constant.UPLOAD_DIR + "/" + fileName2);
		
		List list = new ArrayList(2);
		list.add(uft);
		list.add(uft2);
		/*test code end*/
		Map mav = new HashMap(1);
		mav.put("files", list);
		
		String str = JsonUtil.toJson(mav);
		return str;
	}
	
	@RequestMapping(value="/auditLog", method=RequestMethod.GET)
	public ModelAndView listAuditlog(HttpServletRequest request)
	{
		String seqno = request.getParameter("seqno");
		List list = auditLogService.getAuditLogListByRefSeqno(seqno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("auditLogList", list);
		mav.setViewName("auditLog");
		return mav;
	}
	
	@RequestMapping(value="/toShowUpload",method=RequestMethod.GET)
	public ModelAndView toShowUpload(HttpServletRequest request){
		
		String seqno = request.getParameter("seqno"); //底层路径
		String folderPath =  request.getParameter("folderPath"); //upload下的路径
		String canUpload = request.getParameter("canUpload");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("seqno",seqno);
		mav.addObject("folderPath", folderPath);
		mav.addObject("canUpload", canUpload);
		
		mav.setViewName("upload");
		
		return mav;
		
	}
	
	
	@RequestMapping(value="/saveColumnCustomConfig", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Map saveColumnCustomConfig(@RequestBody String columnNames, String pageUrl, HttpServletRequest request) {
		Map map = new HashMap(2);
		try{
			configService.delConfigByUsernameAndPageUrl(BusinessUtil.getCurrentUserName(request), pageUrl);
			String columnNamesTmp = (String)request.getParameter("columnNames");
			String[] columnNameArray = columnNamesTmp.split(",");
			for(int i=0,n=columnNameArray.length; i<n; i++)
			{
				String columnName = columnNameArray[i];
				String username = BusinessUtil.getCurrentUserName(request);
				FDFBButtonCustomConfigVo configVo = new FDFBButtonCustomConfigVo();
				configVo.setColumnname(columnName);
				configVo.setCreateby(username);
				configVo.setModifyby(username);
				configVo.setDelstatus(Constant.DEL_STATUS_NO);
				configVo.setPageurl(pageUrl);
				configVo.setProcessstatus(Constant.BOOTH_STATUS_APP);
				configVo.setUsername(username);
				configService.insert(configVo);
			}
			map.put("result", "success");
			return map;
		}catch (Exception e) {
			// TODO: handle exception
			try {
				throw new FDFBexception("失败");
			} catch (FDFBexception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
		}
	}
}
