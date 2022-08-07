package com.gregperlinli.springcloud.alibaba.confiug;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author gregPerlinLi
 * @since 2022-05-25
 */
@Configuration
@MapperScan({"com.gregperlinli.springcloud.alibaba.dao"})
public class MyBatisConfig {

}
