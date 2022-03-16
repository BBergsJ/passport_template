package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Passport;
import ru.job4j.repository.PassportStore;

import java.util.List;
import java.util.Optional;

@Service
public class PassportService {

    private final PassportStore passportStore;

    public PassportService(PassportStore passportStore) {
        this.passportStore = passportStore;
    }

    public Passport save(Passport passport) {
        return passportStore.save(passport);
    }

    public Passport update(int id, Passport passport) {
        return passportStore.update(id, passport);
    }

    public boolean delete(int id) {
        return passportStore.delete(id);
    }

    public List<Passport> findAll() {
        return passportStore.findAll();
    }

    public List<Passport> findBySerial(int serial) {
        return passportStore.findBySerial(serial);
    }

    public List<Passport> findUnavailable() {
        return passportStore.findUnavailable();
    }

    public List<Passport> findForReplace() {
        return passportStore.findForReplace();
    }
}
