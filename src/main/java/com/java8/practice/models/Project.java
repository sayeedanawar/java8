package com.java8.practice.models;

public class Project {
    private String code;
    private String name;
    private String client;
    private String manager;

    public Project() {}

    public Project(String code, String name, String client, String manager) {
        this.code = code;
        this.name = name;
        this.client = client;
        this.manager = manager;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    @Override
    public String toString() {
        return "Project [code=" + code + ", name=" + name + ", client=" + client + ", manager=" + manager + "]";
    }
}
