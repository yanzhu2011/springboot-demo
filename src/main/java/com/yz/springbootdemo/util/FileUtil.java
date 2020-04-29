package com.yz.springbootdemo.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class FileUtil {

    public static String readFileContent(String filePath) {
        BufferedReader br = null;
        String res = "";
        try {
            String line = null;
            StringBuffer bufAll = new StringBuffer(); //保存修改过后的所有内容，不断增加
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                //修改内容核心代码
                String output = unicode2String(line);
                bufAll.append(output);
            }
            res = bufAll.toString();
            // 特殊处理：最后一个字符为";"，需要剔除
            String last = res.substring(res.length()-1, res.length());
            if (StringUtils.isNoneBlank(res) && ";".equals(res.substring(res.length()-1, res.length()))) {
                res = res.substring(0,res.length()-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }
        return res;
    }

    //写回文件
    public static void writeFile(String filePath, String content) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }

    public static String unicode2String(String str) {
        String[] unicodeArr = str.split(";");
        StringBuffer string = new StringBuffer();
        for (String unicode : unicodeArr) {
            if (unicode.startsWith("&#x")) {
                String[] hex = unicode.replace("&#x", "").split(";");
                for (int i = 0; i < hex.length; i++) {
                    int data = Integer.parseInt(hex[i], 16);
                    string.append((char) data);
                }
            } else if (unicode.startsWith("&#")) {
                String[] hex = unicode.replace("&#", "").split(";");
                for (int i = 0; i < hex.length; i++) {
                    int data = Integer.parseInt(hex[i], 10);
                    string.append((char) data);
                }
            } else {
                // 原内容不变
                string.append(unicode);
                string.append(";");
            }
        }
        return string.toString();
    }


    public static void main(String[] args) {
        String name = "一年级下册数学试题-周周练4（无答案）人教版.doc";
        String content = FileUtil.readFileContent(String.format("/Users/yanzhu/Downloads/其他/转换/%s.html", name));
        FileUtil.writeFile(String.format("/Users/yanzhu/Downloads/其他/转换/%s-2.html", name), content);
    }
}

