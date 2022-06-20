package com.example.homeenvironment.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class SensorData {

    @Enumerated(EnumType.STRING)
    DataType dataType;
    
}
