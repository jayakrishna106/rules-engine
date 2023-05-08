package com.jkb.knowledgeBase.models;

import java.util.List;

public class Field {
    private FieldType inputType;
    private List<Action> actions;
    private String path;
    private String fieldType;
    private String name;
    private String id;


    public enum FieldType{
        XML, JSON
    }
    public class Action{
        private String string;
        private ActionType type;
    }
    private enum ActionType{
        Append, Upper, Lower
    }
}
