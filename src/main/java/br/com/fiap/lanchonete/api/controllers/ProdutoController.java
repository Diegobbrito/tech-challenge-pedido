package br.com.fiap.lanchonete.api.controllers;

import br.com.fiap.lanchonete.api.dto.response.CategoriaResponse;
import br.com.fiap.lanchonete.api.dto.response.ProdutoResponse;
import br.com.fiap.lanchonete.core.usecase.categoria.IBuscarCategoria;
import br.com.fiap.lanchonete.core.usecase.produto.IBuscarProduto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos", description = "Controle de produtos")
@RestController
public class ProdutoController {

    private final IBuscarProduto buscarProdutoUseCase;
    private final IBuscarCategoria buscarCategoriaUseCase;
    public ProdutoController(IBuscarProduto buscarProdutoUseCase, IBuscarCategoria buscarCategoriaUseCase) {
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.buscarCategoriaUseCase = buscarCategoriaUseCase;
    }
    @Operation(summary = "Lista todas as categorias com suas descrições")
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        return ResponseEntity.ok(buscarCategoriaUseCase.buscarTodas());
    }
    @Operation(summary = "Listagem de todos os produtos")
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponse>> listarTodosOsProdutos(){
        return ResponseEntity.ok(buscarProdutoUseCase.buscarTodos());
    }
    @Operation(summary = "Listagem de produtos por categoria")
    @GetMapping("/produtos/{categoriaId}")
    public ResponseEntity<List<ProdutoResponse>> listarProdutosPorCategoria(@Parameter(example = "1") @PathVariable Integer categoriaId){
        return ResponseEntity.ok(buscarProdutoUseCase.buscarPorCategoria(categoriaId));
    }
}
