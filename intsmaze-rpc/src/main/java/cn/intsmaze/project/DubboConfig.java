package org.intsmaze.project;
import org.springframework.context.annotation.Configuration;  
import org.springframework.context.annotation.ImportResource;  
import org.springframework.context.annotation.PropertySource;  
/** 
 * @author:YangLiu
 * @date:2018年4月17日 下午5:11:19 
 * @describe: 
 */
@Configuration  
@PropertySource("classpath:dubbo/dubbo.properties")  
@ImportResource({ "classpath:dubbo/*.xml" })  
public class DubboConfig {

}
