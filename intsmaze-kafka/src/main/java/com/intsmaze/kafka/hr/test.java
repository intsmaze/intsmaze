package com.intsmaze.kafka.hr;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/4/24 22:01
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class test {
    public static void main(String[] args) {

        Properties props = new Properties();

        props.put("bootstrap.servers", "zdhdpppcd14.crhd0a.crc.hk:6667,zdhdpppcd16.crhd0a.crc.hk:6667,zdhdpppcd17.crhd0a.crc.hk:6667,zdhdpppcd18.crhd0a.crc.hk:6667,zdhdpppcd19.crhd0a.crc.hk:6667,zdhdpppcd20.crhd0a.crc.hk:6667");
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        String paths = "/data1/bdp-config/appuser.keytab";
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.jaas.config", "com.sun.security.auth.module.Krb5LoginModule required "
                + "useTicketCache=false "
                + "renewTicket=true "
                + "serviceName=\"kafka\" "
                + "useKeyTab=true "
                + "debug=true "
                + "keyTab=\"" + paths + "\" "
//                + "keyTab=\"/app/bdp-config/appuser.keytab\" "
//                + "principal=\"appuser@CRLAND.COM\";");
                + "principal=\"appuser@CRLAND.COM\";");


        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("MixcRealTimeKl"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("paririon = %d,offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }

    public void dealJson(String msg)
    {
        js = JSONObject.fromObject(msg);
        String projcode = js.getString("projcode");
        String projname = js.getString("projname");
        String sitetype = js.getString("sitetype");
        String sitetypename = js.getString("sitetypename");
        String area_id = js.getString("area_id");
        String areaname = js.getString("areaname");
        String counttime = js.containsKey("counttime") ? js.getString("counttime") : "1900-01-01 00:00:00";
        String innum = js.getString("innum");
        String outnum = js.getString("outnum");
        String bdp_create_date = js.containsKey("update_time") ? js.getString("update_time") : "1900-01-01 00:00:00";
        if(js.containsKey("bdp_create_date")){
            bdp_create_date = js.getString("bdp_create_date");
        }else if(js.containsKey("update_time")){
            bdp_create_date = js.getString("update_time");
        }
        String bdp_id = "";
        if(!js.containsKey("id") && js.containsKey("bdp_id")){
            bdp_id = js.getString("bdp_id");
        }else if(js.containsKey("id") && !js.containsKey("bdp_id")){
            bdp_id = js.getString("id");
        }else if(js.containsKey("id") && js.containsKey("bdp_id") && !js.getString("bdp_id").equals("") && js.getString("bdp_id").length()>0){
            bdp_id = js.getString("bdp_id");
        }
        String bdp_part_day = js.getString("counttime").split(" ")[0];
        String projnum = js.getString("projcode");
        String load_gp_time = sdf.format(new Date());
        String datasource_num_id = "98";
        String provider = js.containsKey("provider") ? js.getString("provider"): providerMap.get(js.getString("projcode")) ;

        if()

    }
}
