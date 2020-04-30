package com.crland.jdbc.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MailMonitorUtils {

    public static void sendMail(List<String> mailList, String content) {
        try {
            String mailContent = content;

            String from = "crldBigData@crland.com.cn";

            String host = "10.0.76.11";

            Properties properties = System.getProperties();

            properties.setProperty("mail.smtp.host", host);

            Session session = Session.getDefaultInstance(properties);

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setSubject("新营销数据库同步 告警邮件");

            message.setText(mailContent);

            Transport transport = session.getTransport("smtp");

            transport.connect("crldBigData@crland.com.cn", "HelloCrld@");

            transport.sendMessage(message, address(mailList));
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }


    public static InternetAddress[] address(List<String> mailList) {
        InternetAddress[] address = null;
        try {
            List<InternetAddress> list = new ArrayList();
            for (int i = 0; i < mailList.size(); i++) {
                list.add(new InternetAddress((String) mailList.get(i)));
            }
            address = (InternetAddress[]) list.toArray(new InternetAddress[list.size()]);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return address;
    }

    private static List<String> getContentFile(String configFile)
            throws IOException {
        List<String> list = new ArrayList<>();
        File f = new File(configFile);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String s = "";
        while ((s = br.readLine()) != null) {
            list.add(s);
        }
        br.close();
        return list;
    }
}