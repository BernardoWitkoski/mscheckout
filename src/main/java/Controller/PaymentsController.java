package Controller;

import Dto.PaymentsDTO;
import Dto.PaymentsFormDTO;
import Service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/payments")
public class PaymentsController {

    @Autowired
    private PaymentsService service;

    @PostMapping
    public ResponseEntity<PaymentsDTO> save(@RequestBody @Valid PaymentsFormDTO body) {
        PaymentsDTO payments = this.service.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(payments);
    }

    @GetMapping
    public ResponseEntity<Page<PaymentsDTO>> findAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page
    ) {
        Page<PaymentsDTO> payments = this.service.findAll(page);
        return ResponseEntity.ok(payments);
    }

    @GetMapping(path = "/:{id}")
    public ResponseEntity<PaymentsDTO> search(@PathVariable Long id, @RequestBody @Valid PaymentsFormDTO body) {
        PaymentsDTO payments = this.service.search(id);
        return ResponseEntity.ok(payments);
    }

    @PutMapping(path = "/:{id}")
    public ResponseEntity<PaymentsDTO> update(@PathVariable Long id, @RequestBody @Valid PaymentsFormDTO body) {
        PaymentsDTO payments = this.service.update(id, body);
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping(path = "/:{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
