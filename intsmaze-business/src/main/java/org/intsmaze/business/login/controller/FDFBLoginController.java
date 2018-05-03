package org.intsmaze.business.login.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.intsmaze.business.Interfaces.IUserApi;
import org.intsmaze.business.login.vo.FDFBChangePasswordVo;
import org.intsmaze.business.springmvc.controller.BusinessUtil;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.FDFBMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FDFBLoginController {
	
	private Logger log = Logger.getLogger(FDFBLoginController.class);
	
	@Autowired
	private IUserApi userApi;
	

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		log.info("login login login");
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("logout logout logout");
		ModelAndView mav = new ModelAndView();
//		request.getSession().setAttribute(Constant.USERINFO_SESSION, null);
//		request.getSession().setAttribute(Constant.SESSION_USERMENU, null);
		request.getSession().invalidate();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping({ "/", "/index.jsp", "index","/index"})
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		log.info("index index index");
		mav.setViewName("index");
		return mav;
	}
	
	private int width = 90;// 定义图片的width
	private int height = 30;// 定义图片的height
	private int codeCount = 4;// 定义图片上显示验证码的个数
	private int xx = 18;
	private int fontHeight = 28;
	private int codeY = 24;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	@RequestMapping("/code")
	public void getCode(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = buffImg.createGraphics();
		// Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);

		// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(36)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			gd.drawString(code, (i + 1) * xx, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		log.debug(randomCode);
		session.setAttribute("verifyCode", randomCode.toString());

		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
	
	@RequestMapping(value="/loginSso", method=RequestMethod.GET)
	public ModelAndView loginSso(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		
		String id = (String)request.getAttribute("UserId");
		Map user = userApi.getUserById(id);
		if(user == null){
			mav.setViewName("error");
			return mav;
		}
		mav.addObject("SUCCESS", true);
		mav.addObject("MESSAGE", "欢迎您，" + user.get(Constant.USER_XM_SESSION));
		
		//属于省公司身份的角色ID列表
		List<String> shengList = new ArrayList<String>(java.util.Arrays.asList("'0af88708-3932-45c5-a5ba-470aeeeb6d9b'"));
		//user的角色ID数组
		String[] roleArr = user.get(Constant.USER_ROLE_SESSION).toString().split(",");
		//计算两个集合的交集
		shengList.retainAll(java.util.Arrays.asList(roleArr));
		
		log.debug((String)user.get(Constant.USER_HOMEPAGE_SESSION));
		Map userInfo = new HashMap();
		userInfo.put(Constant.USER_NAME_SESSION, user.get(Constant.USER_NAME_SESSION));
		userInfo.put(Constant.USER_DEPARTMENT_SESSION, user.get(Constant.USER_DEPARTMENT_SESSION));
		userInfo.put(Constant.USER_ROLE_SESSION, user.get(Constant.USER_ROLE_SESSION));
		userInfo.put(Constant.USER_XB_SESSION, user.get(Constant.USER_XB_SESSION));
		userInfo.put(Constant.USER_XM_SESSION, user.get(Constant.USER_XM_SESSION));
		userInfo.put(Constant.USER_HOMEPAGE_SESSION, user.get(Constant.USER_HOMEPAGE_SESSION));
		userInfo.put(Constant.USER_STYLE_SESSION, user.get(Constant.USER_STYLE_SESSION));
		userInfo.put(Constant.USER_ID_SESSION, user.get(Constant.USER_ID_SESSION));
		userInfo.put(Constant.USER_ROLENAME_SESSION, BusinessUtil.getRoleNameByRoleids((String)user.get(Constant.USER_ROLE_SESSION)));
		userInfo.put(Constant.USER_DEPNAME_SESSION, BusinessUtil.getDepNameByDepid((String)user.get(Constant.USER_DEPARTMENT_SESSION)));
		userInfo.put(Constant.USER_IS_SHENG_SESSION, shengList.size()>0);
		
		userInfo.put(Constant.USER_ORG_INNER_CODING_SESSION, user.get(Constant.USER_ORG_INNER_CODING_SESSION));
		userInfo.put(Constant.USER_ORG_NAME_SESSION, user.get(Constant.USER_ORG_NAME_SESSION));
		userInfo.put(Constant.USER_NEW_ORG_NO_SESSION, user.get(Constant.USER_NEW_ORG_NO_SESSION));
		
		request.getSession().setAttribute(Constant.USERINFO_SESSION, userInfo);
		
		
		mav.setViewName((String)user.get(Constant.USER_HOMEPAGE_SESSION));
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(String username, String password, String verifyCode, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(username==null || "".equals(username.trim()))
		{
			mav.addObject("SUCCESS", false);
            mav.addObject("MESSAGE","用户名不能为空");
            mav.setViewName("login");
		}
		else if(password==null || "".equals(password.trim()))
		{
			mav.addObject("SUCCESS", false);
            mav.addObject("MESSAGE","密码不能为空");
            mav.setViewName("login");
		}
		else
		{
			password = FDFBMD5.stringMD5(password);
			Map user = userApi.checkUserNameAndPassword(username, password);

			if (user == null) {
				mav.addObject("SUCCESS", false);
				mav.addObject("MESSAGE", "用户名或密码不正确");
				mav.setViewName("login");
			}
			else{
				mav.addObject("SUCCESS", true);
				mav.addObject("MESSAGE", "欢迎您，" + user.get(Constant.USER_XM_SESSION));
				
				//属于省公司身份的角色ID列表
				List<String> shengList = new ArrayList<String>(java.util.Arrays.asList("'0af88708-3932-45c5-a5ba-470aeeeb6d9b'"));
				//user的角色ID数组
				String[] roleArr = user.get(Constant.USER_ROLE_SESSION).toString().split(",");
				//计算两个集合的交集
				shengList.retainAll(java.util.Arrays.asList(roleArr));
				
				log.debug((String)user.get(Constant.USER_HOMEPAGE_SESSION));
				Map userInfo = new HashMap();
				userInfo.put(Constant.USER_NAME_SESSION, user.get(Constant.USER_NAME_SESSION));
				userInfo.put(Constant.USER_DEPARTMENT_SESSION, user.get(Constant.USER_DEPARTMENT_SESSION));
				userInfo.put(Constant.USER_ROLE_SESSION, user.get(Constant.USER_ROLE_SESSION));
				userInfo.put(Constant.USER_XB_SESSION, user.get(Constant.USER_XB_SESSION));
				userInfo.put(Constant.USER_XM_SESSION, user.get(Constant.USER_XM_SESSION));
				userInfo.put(Constant.USER_HOMEPAGE_SESSION, user.get(Constant.USER_HOMEPAGE_SESSION));
				userInfo.put(Constant.USER_STYLE_SESSION, user.get(Constant.USER_STYLE_SESSION));
				userInfo.put(Constant.USER_ID_SESSION, user.get(Constant.USER_ID_SESSION));
				userInfo.put(Constant.USER_ROLENAME_SESSION, BusinessUtil.getRoleNameByRoleids((String)user.get(Constant.USER_ROLE_SESSION)));
				userInfo.put(Constant.USER_DEPNAME_SESSION, BusinessUtil.getDepNameByDepid((String)user.get(Constant.USER_DEPARTMENT_SESSION)));
				userInfo.put(Constant.USER_IS_SHENG_SESSION, shengList.size()>0);
				
				userInfo.put(Constant.USER_ORG_INNER_CODING_SESSION, user.get(Constant.USER_ORG_INNER_CODING_SESSION));
				userInfo.put(Constant.USER_ORG_NAME_SESSION, user.get(Constant.USER_ORG_NAME_SESSION));
				userInfo.put(Constant.USER_NEW_ORG_NO_SESSION, user.get(Constant.USER_NEW_ORG_NO_SESSION));
				
				request.getSession().setAttribute(Constant.USERINFO_SESSION, userInfo);
				
				
				if(FDFBMD5.stringMD5(Constant.DEFAULT_PASSWORD).equals(password))
				{
					mav.setViewName("redirect:goChangePassword");
				}
				else
				{
					mav.setViewName((String)user.get(Constant.USER_HOMEPAGE_SESSION));
				}
			}
		}
		return mav;
	}
	
	
	@RequestMapping("/goChangePassword")
	public ModelAndView goChangePassword(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("changePassword");
		return mav;
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView changePassword(@Validated() @ModelAttribute(Constant.COMMON_UI_ERROR_NAME) FDFBChangePasswordVo user, BindingResult result, HttpServletRequest request) {//都可以，但是验证错误信息返回必须使用这个
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {  
			mav.setViewName("changePassword");
			return mav;
        }  
		userApi.ChangePassword(user.getUserid(), FDFBMD5.stringMD5(user.getNewpassword()));
		
		Map userMap = (Map)request.getSession().getAttribute(Constant.USERINFO_SESSION);
		mav.setViewName((String)userMap.get(Constant.USER_HOMEPAGE_SESSION));
		return mav;
	}
}
