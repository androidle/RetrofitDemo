package com.example.utils.beanconvertor;



public interface Transformer {
     Class<?> getDeserializedType();

     Class<?> getSerializedType();

     Object serialize(Object data);

     Object deserialize(Object data);

}
