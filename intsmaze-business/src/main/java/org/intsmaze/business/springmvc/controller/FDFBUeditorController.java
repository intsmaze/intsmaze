package org.intsmaze.business.springmvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.intsmaze.business.springmvc.util.JsonUtil;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/ueditor")
public class FDFBUeditorController {
	
	private static Logger log = Logger.getLogger(FDFBUeditorController.class);

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	@ResponseBody
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String action = request.getParameter("action");
		if ("config".equals(action)) {
			OutputStream os = response.getOutputStream();
			InputStream is = FDFBUeditorController.class.getClassLoader().getResourceAsStream("config.json");
			log.debug(is.toString());
			IOUtils.copy(is, os);
		}
	}

//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	@ResponseBody
//	public String upload(HttpServletRequest request,
//			@RequestParam CommonsMultipartFile upfile) throws IOException {
//		Map<String, String> result = new HashMap<String, String>();
//		log.debug(upfile.getFileItem().getFieldName());
//		String path = getFilePath(upfile, request);
//		File file = new File(path);
//		log.debug(path);
//		String state = "SUCCESS";
//		// 返回类型
//		String rootPath = request.getContextPath();
//		result.put("url", rootPath + "/ueditor/show?filePath=" + path);
//		result.put("size", String.valueOf(file.length()));
//		result.put("type",
//				file.getName().substring(file.getName().lastIndexOf(".")));
//		result.put("state", state);
//		return JsonUtil.toJson(result);
//	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public void show(String filePath, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String absolutePath = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_DIR);
		File file = getFile(absolutePath + File.separator + filePath);

		response.setDateHeader("Expires", System.currentTimeMillis() + 1000
				* 60 * 60 * 24);
		response.setHeader("Cache-Control", "max-age=60");
		response.setContentType("text/html;charset=UTF-8");
		OutputStream os = response.getOutputStream();

		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			IOUtils.copy(is, os);
		} catch (FileNotFoundException e) {
			response.setStatus(404);
			return;
		} finally {
			if (null != is) {
				is.close();
			}
			if (null != os) {
				os.flush();
				os.close();
			}
		}
	}

//	protected String getFilePath(CommonsMultipartFile uploadFile, HttpServletRequest request) {
//		
//		String contextPath = request.getContextPath();       
//		
//		String absolutePath = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_DIR);
//		File folder = new File(absolutePath);
//		if (!folder.exists()) {
//			folder.mkdirs();
//		}
//		String rawName = uploadFile.getFileItem().getName();
//		String fileExt = rawName.substring(rawName.lastIndexOf("."));
//		String tempFileName = System.currentTimeMillis()
//				+ UUID.randomUUID().toString() + fileExt;
//		String newName = System.currentTimeMillis()
//				+ UUID.randomUUID().toString() + fileExt;
//		File saveFile = new File(absolutePath + File.separator + newName);
//		File tempFile = new File(absolutePath + File.separator + tempFileName);
//		try {
//			uploadFile.getFileItem().write(tempFile);
//			Util.reduceImg(tempFile.getAbsolutePath(), saveFile.getAbsolutePath(), 300, 300);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//		return newName;
//	}

	protected File getFile(String path) {
		File file = new File(path);
		return file;

	}

}
