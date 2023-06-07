package wearecarnival.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wearecarnival.com.models.Imagem;
import wearecarnival.com.repositories.ImagemRepository;
import wearecarnival.com.utils.ImageUtils;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImagemService {
    @Autowired
    private ImagemRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

        Imagem imagem = repository.save(Imagem.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imagem(ImageUtils.compressImage(file.getBytes())).build());
        if (imagem != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<Imagem> dbImageData = repository.findByName(fileName);
        byte[] imagens=ImageUtils.decompressImage(dbImageData.get().getImagem());
        return imagens;
    }
}
