package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.LetterDao;
import com.itechart.trucking.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    LetterDao letterDao;

    @Override
    public void updateImage(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            letterDao.readLetter(1).ifPresent(letter1 -> {
                letter1.setImage(bytes);
            });


        } catch (IOException e) {
            log.debug("Fatal error in image sservice class", e );
        }
    }
}
