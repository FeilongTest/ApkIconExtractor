package com.fl;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.IconFace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        String apkPath = "apk/test.apk";
        try (ApkFile apkFile = new ApkFile(apkPath)) {
            List<IconFace> list = apkFile.getAllIcons();
            for (int i = 0; i < list.size(); i++) {
                IconFace tempIcon = list.get(i);
                String fileName = tempIcon.getPath().substring(tempIcon.getPath().lastIndexOf("/") + 1);
                File f = new File("icons/"+ String.format("%.2f",(float)tempIcon.getData().length/1024) + "kb_" +fileName);
                FileOutputStream os = new FileOutputStream(f);
                os.write(tempIcon.getData());
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
