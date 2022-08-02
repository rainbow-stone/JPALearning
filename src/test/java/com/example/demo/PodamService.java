package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import com.example.demo.entity.Customer;
import org.springframework.boot.test.context.TestComponent;

import uk.co.jemos.podam.api.*;
import uk.co.jemos.podam.typeManufacturers.IntTypeManufacturerImpl;
import uk.co.jemos.podam.typeManufacturers.LongTypeManufacturerImpl;
import uk.co.jemos.podam.typeManufacturers.StringTypeManufacturerImpl;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturer;

@TestComponent
public class PodamService {

    public PodamFactory createFactory() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        final IntTypeManufacturerImpl intManufacturer = new IntTypeManufacturerImpl() {
            @Override
            public Integer getInteger(AttributeMetadata attributeMetadata) {
                if (attributeMetadata.getPojoClass() == Timestamp.class) {
                    return PodamUtils.getIntegerInRange(0, 999999999);
                } else {
                    return super.getInteger(attributeMetadata);
                }
            }
        };

        final LongTypeManufacturerImpl longManufacturer = new LongTypeManufacturerImpl() {
            @Override
            public Long getLong(AttributeMetadata attributeMetadata) {
                if (attributeMetadata.getPojoClass() == Timestamp.class) {
                    return PodamUtils.getLongInRange(0, 999999999);
                } else {
                    return super.getLong(attributeMetadata);
                }
            }
        };

        final StringTypeManufacturerImpl stringManufacturer = new StringTypeManufacturerImpl() {
            @Override
            public String getStringValue(AttributeMetadata attributeMetadata) {
                return String.valueOf(PodamUtils.getIntegerInRange(1, 9));
            }

            @Override
            public String getStringOfLength(int length, AttributeMetadata attributeMetadata) {
                return String.valueOf(PodamUtils.getIntegerInRange(1, 9));
            }

        };

        final TypeManufacturer<BigDecimal> doubleTypeManufacturer = (strategy, attributeMetadata, genericTypesArgumentsMap) ->
                BigDecimal.valueOf(PodamUtils.getDoubleInRange(0, 10000000D)).setScale(2,
                  RoundingMode.HALF_DOWN);
        podamFactory.getStrategy().addOrReplaceTypeManufacturer(int.class, intManufacturer);
        podamFactory.getStrategy().addOrReplaceTypeManufacturer(Integer.class, intManufacturer);
        podamFactory.getStrategy().addOrReplaceTypeManufacturer(Long.class, longManufacturer);
        podamFactory.getStrategy().addOrReplaceTypeManufacturer(BigDecimal.class, doubleTypeManufacturer);
        podamFactory.getStrategy().addOrReplaceTypeManufacturer(String.class, stringManufacturer);


        DefaultClassInfoStrategy classInfoStrategy = DefaultClassInfoStrategy.getInstance();

        classInfoStrategy.addExcludedField(Customer.class, "needAuthCheck");
        podamFactory.setClassStrategy(classInfoStrategy);
        return podamFactory;
    }
}
