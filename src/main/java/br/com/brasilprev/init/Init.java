package br.com.brasilprev.init;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.brasilprev.entity.Cliente;
import br.com.brasilprev.entity.User;
import br.com.brasilprev.entity.UserRole;
import br.com.brasilprev.repository.RegistroGeralRepository;
import br.com.brasilprev.service.ClienteService;
import br.com.brasilprev.service.UserService;

@SuppressWarnings("ALL")
@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	RegistroGeralRepository registroGeralRepository;

	private final UserService userService;
	private final ClienteService clienteService;

	public Init(UserService userService,ClienteService clienteService) {
		this.userService = userService;
		this.clienteService = clienteService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		LocalDateTime localDateTime = LocalDateTime.now();
		int dia = 366;

		User root = new User("Administrador", "000.111.222-33", "root@email.com", "root", UserRole.ADMIN.getRole(),
				localDateTime.toLocalDate().minusDays(dia), true);
		userService.save(root);

		User user = new User("User", "111.555.333-22", "user@email.com", "user", UserRole.USER.getRole(),
				localDateTime.toLocalDate().minusDays(dia), true);
		userService.save(user);
		
		Cliente cliente1 = new Cliente();
		cliente1.setCpf("99999999999");
		cliente1.setNome("cliente 1");
		cliente1.setEndereco("Rua teste 1");
		clienteService.save(cliente1);
		
		Cliente cliente2 = new Cliente();
		cliente2.setCpf("88888888888");
		cliente2.setNome("cliente 2");
		cliente1.setEndereco("Rua teste 2");		
		clienteService.save(cliente2);
		
		Cliente cliente3 = new Cliente();
		cliente3.setCpf("77777777777");
		cliente3.setNome("cliente 3");
		cliente1.setEndereco("Rua teste 3");
		clienteService.save(cliente3);

	}
}
