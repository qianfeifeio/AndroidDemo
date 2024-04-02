package com.common.base.util;

import java.io.File;

/**
 * Description:File文件操作
 *  
 * @Author qianfei
 * @Create 2024/4/2
 * @Version 1.0
 */
public class FileUtils {


    /**
     * 删除指定的目录（含子目录及文件）
     * 1、直接删除含子文件及目录的文件夹不成功（false）
     * @param file
     */
    public static void deleteDirectory(File file) {
        // 如果 file 是文件，直接 delete
        // 如果 file 是目录，先把它的下一级干掉，然后删除自己
        if (file.isDirectory()) {
            File[] all = file.listFiles();
            // 循环删除的是 file 的下一级
            for (File f : all) {// f 代表 file 的每一个下级
                deleteDirectory(f);
            }
        }
        // 删除自己
        file.delete();
    }
}
