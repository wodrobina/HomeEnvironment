package eu.wodrobina.homeenvironment.sensor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SensorConfiguration {

    private final SensorRepository sensorRepository;

    public SensorConfiguration(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Bean
    SensorService sensorService(){
        return new SensorService(sensorRepository);
    }


}
