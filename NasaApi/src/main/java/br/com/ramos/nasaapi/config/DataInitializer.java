package br.com.ramos.nasaapi.config;

import br.com.ramos.nasaapi.model.User;
import br.com.ramos.nasaapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Popula o banco com usuários de teste na inicialização.
 * - user / password123  → ROLE_USER
 * - admin / admin123    → ROLE_ADMIN
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public DataInitializer(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepo.existsByUsername("user")) {
            userRepo.save(new User("user", encoder.encode("password123"), Set.of("ROLE_USER")));
            log.info("Usuário 'user' criado.");
        }
        if (!userRepo.existsByUsername("admin")) {
            userRepo.save(new User("admin", encoder.encode("admin123"), Set.of("ROLE_USER", "ROLE_ADMIN")));
            log.info("Usuário 'admin' criado.");
        }
    }
}