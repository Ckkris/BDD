package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class DataBddApplication {

    public static void main(String[] args) {
    	ConfigurableApplicationContext context = SpringApplication.run(Config.class);
        ClientRepository repository = context.getBean(ClientRepository.class);
        //SpringApplication.run(DataApplication.class, args);
  
        Iterable<Client> Clients = repository.findAll();
        System.out.println("Clients trouvés avec la méthode findAll():");
        System.out.println("-------------------------------");
        for (Client Client : Clients) {
            System.out.println(Client);
        }
}
    
}
