package com.trabalho.livraria.controllers;

import com.trabalho.livraria.entities.Livro;
import com.trabalho.livraria.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Livro> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) {
        Livro livro = service.buscarPorId(id);
        if (livro == null)
            throw new RuntimeException("Livro não encontrado");
        return livro;
    }

    @PostMapping
    public Livro salvar(@RequestBody @Valid Livro livro) {
        return service.salvar(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody @Valid Livro livro) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Livro não encontrado");

        livro.setCodLivro(id);
        return service.salvar(livro);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Livro não encontrado");

        service.deletar(id);
    }

    @PostMapping("/upload/{id}")
    public Livro uploadCapa(@PathVariable Long id, @RequestParam MultipartFile arquivo) throws IOException {

        Livro livro = service.buscarPorId(id);
        if (livro == null)
            throw new RuntimeException("Livro não encontrado");

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        String nomeDoArquivo = "livro_" + id + "_" + arquivo.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + nomeDoArquivo);

        Files.write(path, arquivo.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

        livro.setCapa(nomeDoArquivo);

        return service.salvar(livro);
    }
}