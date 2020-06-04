package com.intsmaze.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YangLiu
 * @date 2018年5月4日
 * @version 1.0
 *  SpringBoot注解扫描范围约定
 *          　　SpringBoot项目的注解扫描默认规则是根据Application类所在的包位置从上往下扫描！
 *          　　“Application类”是指SpringBoot项目入口类
 *          。这个类的位置很关键。如果Application类所在的包为：com.iteye
 *          .wallimn，则只会扫描com.iteye.wallimn包及其所有子包
 *          ，如果service或dao所在包不在com.iteye.wallimn及其子包下，则不会被扫描！
 *          　　如果Application类放在com.iteye.wallimn.app包中，那么与app的同级包、叔叔包是不会被扫描的。
 */
@SpringBootApplication
// (exclude = {
// DataSourceAutoConfiguration.class
// })
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
