package com.pilgrimm.wm.admin.base.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pilgrimm.core.common.AbstractController;
import com.pilgrimm.core.util.MD5Util;
import com.pilgrimm.core.util.PBKDF2Util;
import com.pilgrimm.core.util.RsaUtil;

@Controller
@RequestMapping("/home")
public class HomeController extends AbstractController {
	
	/**
	 * 主页
	 */
	@RequestMapping("/index")
	public String index() {
		return "/home/index";
	}
	
	/**
	 * 首页
	 */
	@RequestMapping("/home")
	public String home() {
		return "/home/home";
	}

	/**
	 * 列表页 
	 */
	@RequestMapping("/adminlte/workplate")
	public String workplate() {
		return "/home/adminlte/workplate";
	}
	
	/**
	 * 列表页 
	 */
	@RequestMapping("/metronic/index")
	public String index2() {
		return "/home/metronic/index";
	}
	
	/**
	 * 列表页 
	 */
	@RequestMapping("/hplus/index")
	public String index3() {
		return "/home/hplus/index";
	}
	
	/**
	 * 登录页
	 */
	@RequestMapping("/login")
	public String login() {
		return "/home/login";
	}
	
	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request, String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", "0");
		
		String code = request.getParameter("code");
		String vCode = (String) request.getSession().getAttribute("code");
		SecurityUtils.getSubject().getSession().setAttribute("code", "");
		if (vCode == null) {
			map.put("errcode", "1");
			map.put("errmsg", "验证码超时了！");
			return map;
		} else if (!code.toUpperCase().equals(vCode)) {
			map.put("errcode", "1");
			map.put("errmsg", "验证码不正确！");
			return map;
		}
		
		RSAPrivateKey privateKey = RsaUtil.getPrivateKey();
		try {
			password = RsaUtil.decryptByPrivateKey(password, privateKey);
			
			password = new Md5Hash(password, username).toHex();
			// 此处指定编码格式，防止环境不同默认编码格式不同导致密文不一致
			String md5Username = MD5Util.bit32(username, "utf-8");
			password = PBKDF2Util.getPBKDF2(password, md5Username);
		} catch (Exception e) {
			map.put("errcode", "1");
			map.put("errmsg", "错误！");
			return map;
		}
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到CustomRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			subject.login(token);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			map.put("errcode", "1");
			map.put("errmsg", "用户名错误！");
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			map.put("errcode", "1");
			map.put("errmsg", "密码错误！");
		}
		return map;
	}
	
	/**
	 * 登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return "redirect:/home/login";
	}
	
	/**
	 * 获取验证码
	 */
	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		
		int width = 110;// 定义图片的width
		int height = 34;// 定义图片的height
		int codeCount = 4;// 定义图片上显示验证码的个数
		int codeX = 18;
		int fontHeight = 32;
		int codeY = 32;
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
				'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z',  '2', '3', '4', '5', '6', '7', '8', '9' };

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = buffImg.createGraphics();
		// Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		SecureRandom random = new SecureRandom(); 
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
			String code = String.valueOf(codeSequence[random.nextInt(32)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			gd.drawString(code, (i + 1) * codeX, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		// 将四位数字的验证码保存到Session中。
		SecurityUtils.getSubject().getSession().setAttribute("code", randomCode.toString());

		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos;
		try {
			sos = response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
