package dev.kertz.inventory.controller;

import dev.kertz.inventory.model.VAT;
import dev.kertz.inventory.repository.VATRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class VATController {


    private VATRepository repository;

    @Autowired
    VATController(VATRepository repository){ this.repository = repository; }

    @GetMapping("/vat")
    public List<VAT> getAllVATs(){ return repository.findAll(); }

    @PostMapping("/vat")
    public VAT newVAT(@RequestBody VAT vat){ return repository.save(vat); }
}
