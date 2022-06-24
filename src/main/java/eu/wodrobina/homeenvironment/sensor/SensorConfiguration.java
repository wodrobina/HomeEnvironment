package eu.wodrobina.homeenvironment.sensor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SensorConfiguration {

    @Bean
    SensorService sensorService(SensorRepository sensorRepository){
        return new SensorService(sensorRepository);
    }


}
