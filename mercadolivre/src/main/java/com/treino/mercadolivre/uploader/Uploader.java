package com.treino.mercadolivre.uploader;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Uploader {
    List<String> sendToCloud(List<MultipartFile> files);
}
