package com.jkb.knowledgeBase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Mapper {
    private String type;
    private DataSource input;
    private DataSource output;
    private Properties properties;
    private Properties constants;
}
