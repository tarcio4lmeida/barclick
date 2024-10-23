package com.tarcio4lmeida.barclick.resources;

import com.tarcio4lmeida.barclick.dtos.CriarAtualizarPedidoDTO;
import com.tarcio4lmeida.barclick.dtos.PedidoDTO;
import com.tarcio4lmeida.barclick.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody CriarAtualizarPedidoDTO pedidoDto) {
        PedidoDTO novoPedido = pedidoService.criarPedido(pedidoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody CriarAtualizarPedidoDTO dto) {
        PedidoDTO pedidoAtualizado = pedidoService.update(id, dto);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/entregar")
    public ResponseEntity<PedidoDTO> marcarComoEntregue(@PathVariable Long id) {
        PedidoDTO pedidoAtualizado = pedidoService.marcarComoEntregue(id);
        return ResponseEntity.ok(pedidoAtualizado);
    }

}
