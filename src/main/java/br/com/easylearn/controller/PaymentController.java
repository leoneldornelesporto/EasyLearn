package br.com.easylearn.controller;

import br.com.easylearn.domain.Payment;
import br.com.easylearn.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/protectedA/payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/{uuidCurso}/{idAluno}")
    public ResponseEntity<? extends Payment> findPaymentByUuidCursoAndIdAluno(@PathVariable String uuidCurso, @PathVariable Long idAluno){
        Payment payment = paymentRepository.findByUuidCursoAndIdAlunoAndStatusContaining(uuidCurso,idAluno,"PAID");

        if (payment == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<? extends Payment> savePayment(@RequestBody Payment payment){
        Payment save = paymentRepository.save(payment);
        if (save == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(save);
    }

    @DeleteMapping("/todos")
    public Boolean b(){
        paymentRepository.deleteAll();
        return Boolean.TRUE;
    }
}
