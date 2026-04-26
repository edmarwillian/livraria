package com.trabalho.livraria.controllers;

import com.trabalho.livraria.entities.Livro;
import com.trabalho.livraria.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    private static final String UPLOAD_DIR = "uploads/capas/";

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        Livro livro = service.buscarPorId(id);
        if (livro == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public ResponseEntity<Livro> salvar(@RequestBody @Valid Livro livro) {
        return ResponseEntity.ok(service.salvar(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody @Valid Livro livro) {
        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        livro.setCodLivro(id);
        return ResponseEntity.ok(service.salvar(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/capa/{id}")
    public ResponseEntity<Livro> uploadCapa(@PathVariable Long id,
                                           @RequestParam MultipartFile arquivo) throws IOException {

        Livro livro = service.buscarPorId(id);
        if (livro == null)
            return ResponseEntity.notFound().build();

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        String nomeDoArquivo = "livro_" + id + "_" + arquivo.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + nomeDoArquivo);

        Files.write(path, arquivo.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

        livro.setCapa(nomeDoArquivo);

        return ResponseEntity.ok(service.salvar(livro));
    }

    @GetMapping("/capa/{id}")
    public ResponseEntity<byte[]> getCapa(@PathVariable Long id) throws IOException {

        Livro livro = service.buscarPorId(id);
        if (livro == null || livro.getCapa() == null)
            return ResponseEntity.notFound().build();

        Path path = Paths.get(UPLOAD_DIR + livro.getCapa());

        if (!Files.exists(path))
            return ResponseEntity.notFound().build();

        byte[] imagem = Files.readAllBytes(path);

        String contentType = Files.probeContentType(path);
        if (contentType == null)
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imagem);
    }
}