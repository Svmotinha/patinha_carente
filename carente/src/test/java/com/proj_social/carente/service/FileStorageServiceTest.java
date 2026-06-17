package com.proj_social.carente.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileStorageServiceTest {

    private FileStorageService fileStorageService;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        fileStorageService = new FileStorageService();
        // Injeta o diretório temporário para os testes não afetarem o sistema de arquivos real
        ReflectionTestUtils.setField(fileStorageService, "uploadDir", tempDir.toString());
    }

    @Test
    void storeFile_DeveGerarNomesDiferentes_ParaArquivosComMesmoNomeOriginal() {
        MockMultipartFile file1 = new MockMultipartFile("file", "pet.jpg", "image/jpeg", "conteudo1".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("file", "pet.jpg", "image/jpeg", "conteudo2".getBytes());

        String nomeGerado1 = fileStorageService.storeFile(file1);
        String nomeGerado2 = fileStorageService.storeFile(file2);

        assertNotEquals(nomeGerado1, nomeGerado2, "Os nomes gerados devem ser únicos mesmo para arquivos com mesmo nome original");
        assertTrue(nomeGerado1.endsWith("_pet.jpg"));
        assertTrue(nomeGerado2.endsWith("_pet.jpg"));
        
        // Verifica se ambos os arquivos foram realmente criados no diretório temporário
        assertTrue(tempDir.resolve(nomeGerado1).toFile().exists());
        assertTrue(tempDir.resolve(nomeGerado2).toFile().exists());
    }
}
