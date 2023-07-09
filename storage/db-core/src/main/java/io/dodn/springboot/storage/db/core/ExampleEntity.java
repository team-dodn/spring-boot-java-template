package io.dodn.springboot.storage.db.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class ExampleEntity extends BaseEntity {

    @Column
    private String exampleColumn;

    public ExampleEntity() {
    }

    public ExampleEntity(String exampleColumn) {
        this.exampleColumn = exampleColumn;
    }

    public String getExampleColumn() {
        return exampleColumn;
    }

}
