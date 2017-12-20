package com.itechart.trucking.service;

import com.itechart.trucking.domain.Letter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ImageService {


    void updateImage(MultipartFile  file);

}
