package com.ex.befinal.file.controller;

import com.ex.befinal.file.dto.AttachmentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class AttachmentController {

    @Value("${com.example.upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<AttachmentDTO>> uploadFile(MultipartFile[] uploadFiles) {
        List<AttachmentDTO> attachmentDTOList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {

            //이미지 파일만 업로드 가능
            if (uploadFile.getContentType().startsWith("image") == false) {
                // 이미지가 아닌 경우 403 Forbidden 으로 반환
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            // 실제 파이 이름 IE나 Edge 전체 경로가 들어오므로
            String Name = uploadFile.getOriginalFilename();

            // UUID
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 "_" 를 이용하여 구분
            String saveName = uploadPath + File.separator + File.separator + uuid + "_" + Name;

            // 저장 경로에 대한 Path 객체 생성
            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath); // 실제 이미지 저장 메서드
                attachmentDTOList.add(new AttachmentDTO(saveName, uuid));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(attachmentDTOList, HttpStatus.OK);
    }

    private String makeFolder() {

        // 현재 날짜를 기반으로 폴더를 생성하는 메서드
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd"));

        // 슬래시를 플랫폼에 맞는 파일 분리자로 대체
        String folderPath = str.replace("/", File.separator);

        // 폴더가 생성 되지 않으면 대신 생성
//        File uploadPathFolder = new File(uploadPath,folderPath);
//
//        if(!uploadPatheFolder.exists){
//            uploadPatheFolder.mkdirs();
//        }
//        return uploadfolderPath;
        return makeFolder();
    }
}
