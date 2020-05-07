package com.intsmaze.jetty.handle.hand;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.intsmaze.kafka.service.PoolKafkaProducer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.intsmaze.common.util.ResultGenerator;
import org.nustaq.serialization.FSTConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;
/**
 * @Description 将接收的数据发往kafka,同时适用客服进线报文demo
 * @Author 刘洋
 * @Date 2018/12/23 11:07
 * @Version 1.0
 **/
public class HandlerSurveyServer extends HttpServlet implements Cloneable {

    final static Logger logger = LoggerFactory.getLogger(HandlerSurveyServer.class);

    private final static String MESSAGE_KEY = "messageKey";

    private final static Joiner JOINER = Joiner.on("-");

    private final Random random = new Random();

    private FSTConfiguration fstConf = FSTConfiguration
            .createDefaultConfiguration();

    private PoolKafkaProducer poolKafkaProducer;

    public PoolKafkaProducer getPoolKafkaProducer() {
        return poolKafkaProducer;
    }

    public void setPoolKafkaProducer(PoolKafkaProducer poolKafkaProducer) {
        this.poolKafkaProducer = poolKafkaProducer;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        try {
            String msg = IOUtils.toString(request.getInputStream(), Charset.forName("utf-8"));
            logger.info("接收到消息>>>>>>>>>>>>>>>>>>" + msg);
            Map<String, Object> dataMap = new HashMap<String, Object>(10);
            Pair<Boolean, String> parseRst =parseXmlMsg(msg, dataMap);

            String topicKey = (String) dataMap.get(MESSAGE_KEY);
            byte[] byteValue = fstConf.asByteArray(dataMap);
            ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(
                    "survey", topicKey.getBytes(Charsets.UTF_8), byteValue);
            poolKafkaProducer.sendSync(record);
            out.write(ResultGenerator.genSuccessResult().toString());
        } catch (Exception e) {
            logger.error("", e);
            out.write("error");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    Pair<Boolean, String> parseXmlMsg(String input,
                                      Map<String, Object> dataMap) {
        try {
            Document documnet = DocumentHelper.parseText(input);
            Element root = documnet.getRootElement();

            Element headElement = root.element("head");
            Preconditions.checkArgument(headElement != null, "XML中无head元素");
            setData(dataMap, headElement);

            Element bodyElement = root.element("body");
            Preconditions.checkArgument(bodyElement != null, "XML中无body元素");
            setData(dataMap, bodyElement);

            return processData(dataMap);
        } catch (Exception e) {
            logger.error("解析xml出错：" + input, e);
            return Pair.of(false, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void setData(Map<String, Object> dataMap, Element parentElement) {
        List<Element> fieldElements = parentElement.elements("field");
        Preconditions.checkArgument(
                (fieldElements != null && !fieldElements.isEmpty()),
                "XML缺少field元素");
        for (Element fieldElement : fieldElements) {
            String name = fieldElement.attributeValue("name");
            String value = StringUtils.trimToNull(fieldElement.getTextTrim());
            dataMap.put(name, value);
        }
    }

    private Pair<Boolean, String> processData(Map<String, Object> dataMap) {
        String callFlow = (String) dataMap.remove("callFlow");
        if (StringUtils.isEmpty(callFlow)) {
            return Pair.of(false, "呼叫流水ID为空！");
        }
        String telephoneStatus = (String) dataMap.remove("telephoneStatus");
        if (StringUtils.isEmpty(telephoneStatus)) {
            return Pair.of(false, "电话状态为空！");
        }
        String telephone = (String) dataMap.remove("telephone");
        if (StringUtils.isEmpty(telephone)) {
            return Pair.of(false, "来电号码为空！");
        }
        String seatNo = (String) dataMap.remove("seatNo");
        if (StringUtils.isEmpty(seatNo)) {
            return Pair.of(false, "座席工号为空！");
        }
        // 报文类型
        dataMap.put("SV_TranCode", (String) dataMap.remove("TranCode"));
        // 渠道代码
        dataMap.put("SV_ChanlSubNo", (String) dataMap.remove("ChanlSubNo"));
        // 应用代码
        dataMap.put("SV_ChanlAppNo", (String) dataMap.remove("ChanlAppNo"));
        // 呼叫流水ID
        dataMap.put("SV_callFlow", callFlow);
        // 进线技能
        dataMap.put("SV_incomingShill",
                (String) dataMap.remove("incomingShill"));
        // 电话状态
        dataMap.put("SV_telephoneStatus", telephoneStatus);
        // 客户姓名
        dataMap.put("SV_name", (String) dataMap.remove("name"));
        // 来电号码
        dataMap.put("SV_telephone", telephone);
        // 卡号
        dataMap.put("SV_cardNo", (String) dataMap.remove("cardNo"));
        // 证件号
        dataMap.put("SV_certNo", (String) dataMap.remove("certNo"));
        // 证件类型
        dataMap.put("SV_certType", (String) dataMap.remove("certType"));
        // 预留手机号
        dataMap.put("SV_obligateTelephone",
                (String) dataMap.remove("obligateTelephone"));
        // 座席工号
        dataMap.put("SV_seatNo", seatNo);
        // 密码验证标识
        dataMap.put("SV_isPassword", (String) dataMap.remove("isPassword"));
        // 来电号码是否是手机号
        putIsPhoneNo(dataMap, telephone);
        // 随机号
        dataMap.put("SV_random", random.nextInt(100) + 1);
        // 交易时间
        dataMap.put("SV_time", System.currentTimeMillis());

        String isPhoneNo = (String) dataMap.get("SV_isPhoneNo");
        String obligateTelephone = (String) dataMap.get("SV_obligateTelephone");
        // messageKey
        String messageKey = System.currentTimeMillis()+ UUID.randomUUID().toString().replace("-", "").toLowerCase();
        dataMap.put(MESSAGE_KEY, messageKey);
        return Pair.of(true, "");
    }


    private void putIsPhoneNo(Map<String, Object> dataMap, String phoneNo) {
        dataMap.put("SV_isPhoneNo", isPhoneNumber(phoneNo));
    }

    public String isPhoneNumber(String input) {
        if (input.startsWith("1") && input.length() == 11) {
            return "1";
        }
        return "0";
    }

}
