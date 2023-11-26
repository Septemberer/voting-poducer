package itmo.apartmentService.service.impl;

import itmo.apartmentService.jpa.entity.Customer;
import itmo.apartmentService.jpa.repository.CustomerRepository;
import itmo.apartmentService.service.repo.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Optional<User> findByToken(String token) {
		Optional<Customer> customer = customerRepository.findByToken(token);
		if (customer.isPresent()) {
			Customer customer1 = customer.get();
			User user = new User(customer1.getUserName(), customer1.getPassword(), true, true,
					true, true, AuthorityUtils.createAuthorityList("USER"));
			return Optional.of(user);
		}
		return Optional.empty();
	}
}
