package com.tarcio4lmeida.barclick.resources;

import com.tarcio4lmeida.barclick.dtos.CriarPedidoDTO;
import com.tarcio4lmeida.barclick.dtos.PedidoDTO;
import com.tarcio4lmeida.barclick.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody CriarPedidoDTO pedidoDto) {
        PedidoDTO novoPedido = pedidoService.criarPedido(pedidoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

}
