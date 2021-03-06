package com.review.gradle_j11_sb255.utils.functions;

import com.review.gradle_j11_sb255.entities.ParameterEntity;
import com.review.gradle_j11_sb255.utils.Constant;

public class ParameterFunction {

    ParameterFunction() {}

    public static String clientNumber(ParameterEntity parameterEntity){

        String value = String.valueOf(parameterEntity.getValue());
        Integer length = parameterEntity.getLength();

        return parameterEntity.getPrefix()
            .concat(String.format(Constant.PERCENTAGE
                    .concat(Constant.ZERO)
                    .concat(String.valueOf(length-value.length()))
                    .concat(Constant.DMIN), parameterEntity.getValue() + 1))
            .concat(parameterEntity.getSuffix());
    }

}
