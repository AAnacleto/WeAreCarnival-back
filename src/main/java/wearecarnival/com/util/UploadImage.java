package wearecarnival.com.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadImage {
    public static boolean fazerUploadImagem(MultipartFile imagem) throws Exception {
        boolean sucessoUpload = true;

        if(!imagem.isEmpty()) {
            String nomeArquivo = imagem.getOriginalFilename();

            try{

                //Criando diretório para armazenar o arquivo
                String pastaUploadImagem = "src/main/resources/static/images/img-evento";
                File dir = new File(pastaUploadImagem);

                if(!dir.exists()) {
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(imagem.getBytes());
                stream.close();

                System.out.println("Armazenado em: " + serverFile.getAbsolutePath());
                System.out.println("Você fez o upload do arquivo: " + nomeArquivo + " com sucesso!");

            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            throw new Exception("Imagem não enviada!");
        }

        return sucessoUpload;
    }

}
