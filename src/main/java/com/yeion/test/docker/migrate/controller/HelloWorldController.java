package com.yeion.test.docker.migrate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author zhjk on 2017/4/10.
 */
@Slf4j
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(){
//        log.info("测试绝对路径文件读取");
//        File staticPath = new File("D:\\git-repo\\docker-migrate\\src\\main\\resources\\test-file.txt");
//        log.info("绝对路径文件：{}", staticPath);

        log.info("===============");

        log.info("类根路径：{}", getClass().getClassLoader().getResource("").getPath());

        log.info("===============");

        System.out.println("测试控制台输出");
        try {
            log.info("测试文件读取");
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "test-file.txt");
            log.info("文件名：{}", file.getName());
            log.info("绝对路径：{}", file.getAbsolutePath());
            try(FileInputStream fin = new FileInputStream(file)){
                return StreamUtils.copyToString(fin, StandardCharsets.UTF_8);
            }
        } catch (FileNotFoundException e) {
            log.error("找不到文件，错误信息：{}",e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            log.error("文件读取错误：{}", e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
