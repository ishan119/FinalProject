package lk.ijse.gdse63.AADFinal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TravelPackageMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelPackageMicroServiceApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
