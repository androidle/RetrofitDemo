package com.example.utils.beanconvertor;





import java.util.List;

public abstract class Convertor {

    public Convertor() {
    }

    private Transformer[] transformers;

    private List supportClasses;

    protected Transformer[] getTransformerList() {
        return transformers;
    }

    public void setTransformerList(Transformer... transformers) {
        this.transformers = transformers;
        for (Transformer transformer : transformers) {
            supportClasses.add(transformer.getSerializedType());
        }
    }
}
