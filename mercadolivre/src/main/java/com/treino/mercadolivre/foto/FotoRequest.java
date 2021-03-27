package com.treino.mercadolivre.foto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class FotoRequest {
    @Valid
    @Size(min = 1)
    private List<MultipartFile> fotos;

    public List<MultipartFile> getFotos() {
        return fotos;
    }

    @JsonCreator
    public FotoRequest(@Size(min = 1) @JsonProperty("fotos") List<MultipartFile> fotos) {
        this.fotos = fotos;
    }

    public List<Foto> toListOfUri(List<String> uris){
        return uris.stream().map(Foto::new).collect(Collectors.toList());
    }
}
