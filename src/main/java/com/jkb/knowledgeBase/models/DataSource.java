package com.jkb.knowledgeBase.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DataSource {
    private String type;
    private String id;
    private String name;
    private String desc;
    private Type dataSourceType;

    public enum Type {
            SOURCE, TARGET;
    }
}