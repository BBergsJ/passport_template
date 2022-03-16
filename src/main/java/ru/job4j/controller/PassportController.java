package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Passport;
import ru.job4j.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/api/passport")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> create(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                passportService.save(passport),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Passport> update(@RequestParam int id, @RequestBody Passport passport) {
        return new ResponseEntity<>(
                passportService.update(id, passport),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        return new ResponseEntity<>(
                passportService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/find")
    public ResponseEntity<List<Passport>> find(@RequestParam(required = false) String serial) {
        if (serial == null) {
            return new ResponseEntity<>(
                    passportService.findAll(),
                    HttpStatus.OK
            );
        } else {
            var rsl = passportService.findBySerial(Integer.parseInt(serial));
            return new ResponseEntity<>(
                    rsl,
                    HttpStatus.OK
            );
        }
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Passport>> findUnavailable() {
        return new ResponseEntity<>(
                passportService.findUnavailable(),
                HttpStatus.OK
        );
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> findForReplace() {
        return new ResponseEntity<>(
                passportService.findForReplace(),
                HttpStatus.OK
        );
    }
}
