package itmo.apartmentService.service;

import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface CustomerService {

	Optional<User> findByToken(String token);
}
