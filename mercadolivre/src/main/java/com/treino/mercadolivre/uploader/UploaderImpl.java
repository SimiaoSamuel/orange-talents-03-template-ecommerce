package com.treino.mercadolivre.uploader;

import com.treino.mercadolivre.foto.Foto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UploaderImpl implements Uploader{
    @Override
    public List<String> sendToCloud(List<MultipartFile> files) {
        return files.stream().map(m -> m.getOriginalFilename() + ".com")
                .collect(Collectors.toList());
    }
}
