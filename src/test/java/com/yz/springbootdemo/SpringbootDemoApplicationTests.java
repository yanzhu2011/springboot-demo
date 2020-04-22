package com.yz.springbootdemo;

import org.jodconverter.DocumentConverter;
import org.jodconverter.office.OfficeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class SpringbootDemoApplicationTests {
    @Autowired
    private DocumentConverter documentConverter;

    @Test
    void contextLoads() {
    }

    /**
     * OpenOffice文件转化
     * 1、pom依赖配置：3个包
     * 2、yml文件jodconverter相关配置，注意"office-home"指定安装等openOffice目录不要错了
     * 3、中文乱码问题：参考https://blog.csdn.net/laoyang360/article/details/73555598/
     * @throws OfficeException
     */
    @Test
    void testOpenOffice() throws OfficeException {
        // 源文件 （office）
        String name = "江苏省江都中学2019-2020学年下学期高一数学周练试卷2020.3.22（无答案）.docx";
        File source = new File(String.format("/Users/yanzhu/Downloads/其他/%s", name));
        // 源文件 （pdf）
        File target = new File(String.format("/Users/yanzhu/Downloads/其他/%s.html", name));
        // 转换文件
        if (!target.exists()) {
            documentConverter.convert(source).to(target).execute();
        }
    }

}
