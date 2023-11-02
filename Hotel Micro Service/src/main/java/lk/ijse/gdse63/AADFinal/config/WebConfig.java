package lk.ijse.gdse63.AADFinal.config;

import lk.ijse.gdse63.AADFinal.util.StringToPricesListConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToPricesListConverter());
    }
}