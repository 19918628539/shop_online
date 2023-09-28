package com.example.controller;/*
 *ClassName:
 *UserName:86189
 *Time:2021/11/18/15:38
 */

import com.example.R;
import com.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file/")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping
    public R<?> upload(MultipartFile file) throws IOException {
        String url = fileService.save(file.getInputStream(),file.getOriginalFilename(),"imgs");
        return R.ok().setData("//"+url);
    }
}
