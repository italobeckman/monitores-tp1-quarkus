package br.unitins.tp1.monitores.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitorFileServiceImpl implements FileService{
    // verificar tipo de arquivo
    // verificar tamanho do arquivo
    // verificarTamanhoArquivo(arquivo)
    // verificarTipoArquivo(nomeArquivo)
    private final String PATH_CLIENTE = System.getProperty("user.home") + 
    File.separator + "quarkus" +
    File.separator + "images" +
    File.separator + "monitor" +
    File.separator;


    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/png", "image/jpeg", "image/jpg", "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; /// 10MB
    @Override
    public String save(String nomeArquivo, byte[] arquivo) throws IOException {
        
        if (!SUPPORTED_MIME_TYPES.contains(Files.probeContentType(Paths.get(nomeArquivo)))) {
            throw new IOException("Unsupported file type: " + nomeArquivo);
        }

        if (arquivo.length > MAX_FILE_SIZE) {
            throw new IOException("File size exceeds the maximum limit of 10MB: " + nomeArquivo);
        }

        Path diretorio  = Paths.get(PATH_CLIENTE);
        Files.createDirectories(diretorio);

        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        String extensao = mimeType.substring(mimeType.lastIndexOf("/") + 1);
        String novoNomeArquivo = UUID.randomUUID() + "." + extensao;

        Path filePath = diretorio.resolve(novoNomeArquivo);

        //tratar quando o arquivo já existe
        if(filePath.toFile().exists()){
            // gerar novo nome para o arquivo - tratar
            
            throw new IOException("Nome de arquivo já gerado" + novoNomeArquivo);
        }
        try(FileOutputStream fos = new FileOutputStream(filePath.toFile())){
            fos.write(arquivo);
        }

        // salvando a imagem 

        return filePath.toString();
    }   

    @Override
    public File find(String nomeArquivo) {
        // eh ideal verificar se existe para retornar um arquivo vazio




        return new File(PATH_CLIENTE + nomeArquivo);

    }
    
}
