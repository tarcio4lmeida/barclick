package com.tarcio4lmeida.barclick.resources;

import com.tarcio4lmeida.barclick.dtos.MesaDTO;
import com.tarcio4lmeida.barclick.dtos.PedidoDTO;
import com.tarcio4lmeida.barclick.services.MesaService;
import com.tarcio4lmeida.barclick.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaResource {
    private final MesaService mesaService;
    private final PedidoService pedidoService;


    @GetMapping
    public List<MesaDTO> listarMesas() {
        return mesaService.listarMesas();
    }

    @PostMapping
    public ResponseEntity<MesaDTO> criarMesa(@RequestBody MesaDTO mesaDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(mesaDTO.getId()).toUri();

        mesaDTO = mesaService.criarMesa(mesaDTO);
        return ResponseEntity.created(uri).body(mesaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaDTO> atualizarMesa(@PathVariable Long id, @RequestBody MesaDTO mesaDTO) {
        MesaDTO mesaAtualizada = mesaService.atualizarMesa(id, mesaDTO);
        return ResponseEntity.ok(mesaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerMesa(@PathVariable Long id) {
        mesaService.removerMesa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{mesaId}/pedidos")
    public ResponseEntity<List<PedidoDTO>> getNonFinalizedPedidos(@PathVariable Long mesaId) {
        List<PedidoDTO> pedidos = pedidoService.findNonFinalizedPedidosByMesa(mesaId);
        return ResponseEntity.ok(pedidos);
    }
}
