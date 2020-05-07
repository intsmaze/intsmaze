package com.intsmaze.jetty.handle.zjrc;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * @Description 将接收的数据存入mysql
 * @Author 刘洋
 * @Date 2018/12/23 11:07
 * @Version 1.0
 **/
public class HandlerLbsServer extends HttpServlet implements Cloneable {

    final static Logger logger = LoggerFactory.getLogger(HandlerLbsServer.class);

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        try {
            String msg = IOUtils.toString(request.getInputStream(), Charset.forName("utf-8"));
            logger.info("接收到消息>>>>>>>>>>>>>>>>>>" + msg);
            UserBean userBean =userDao.findById(Integer.valueOf(msg));
            Thread.sleep(500);
            out.write("sucess------------------------" + userBean.toString());
        } catch (Exception e) {
            logger.error("", e);
            out.write("error");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}
