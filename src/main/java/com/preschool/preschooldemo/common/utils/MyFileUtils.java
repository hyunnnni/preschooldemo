package com.preschool.preschooldemo.common.utils;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@Component
@Getter
public class MyFileUtils {

    private final String uploadPrefixPath;

    public MyFileUtils(@Value("${file.dir}") String uploadPrefixPath) {
        this.uploadPrefixPath = uploadPrefixPath;
    }

    //폴더 만들기
    public String makeFolders(String path) {
        File folder = new File(uploadPrefixPath, path);
        folder.mkdirs();

        return folder.getAbsolutePath();

    }

    //랜덤 파일명 만들기
    public String getRandomFileNm() {
        return UUID.randomUUID().toString();
    }

    //확장자 얻어오기
    public String getExt(String fileNm) {
        return fileNm.substring(fileNm.lastIndexOf("."));
    }
    //이름 얻어오기
    public String getName(String fileNm) {
        return fileNm.substring(0, fileNm.lastIndexOf("."));
    }
    //랜덤 파일명 만들기 with 확장자
    public String getRandomFileNm(String originFileNm) {

        return getName(originFileNm)+ getRandomFileNm() + getExt(originFileNm);
    }

    //랜덤 파일명 만들기 with 확장자 from MultipartFile
    public String getRandomFileNm(MultipartFile mf) {
        String fileNm = mf.getOriginalFilename();
        return getRandomFileNm(fileNm);
    }

    //메모리에 있는 내용을 파일로 옮기는 메소드
    public String transferTo(MultipartFile mf, String target) {
        String fileNm = getRandomFileNm(mf);
        String folderPath = makeFolders(target);
        File saveFile = new File(folderPath, fileNm);
        saveFile.exists();
        try {
            mf.transferTo(saveFile);
            return fileNm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delFolderTrigger(String relativePath) {
        delFolder(uploadPrefixPath + relativePath);
    }

    public void delFolder(String folderPath) { //폴더와 그 아래에 폴더 및 파일 삭제
        File folder = new File(folderPath);
        if (folder.exists()) {
            File[] files = folder.listFiles();

            for (File file : files) {
                if (file.isDirectory()) {
                    delFolder(file.getAbsolutePath());
                } else {
                    file.delete();
                }
            }
            folder.delete();
        }
    }
}
