package com.mn.springboot.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by Alex on 2017/6/9.
 */
@NodeEntity
public class Person {

    @GraphId
    private Long id;

    private String name;
    private Long workid;
    private String part;
    private String position;


    @Relationship(type = "FRIENDSHIP",direction = Relationship.UNDIRECTED)
    private Set<Person> relation;

    public Set<Person> getRelation() {
        return relation;
    }

    public void setRelation(Set<Person> relation) {
        this.relation = relation;
    }

    public Person(){}

    public Person(String name, Long workid, String part, String position) {
        this.name = name;
        this.workid = workid;
        this.part = part;
        this.position = position;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWorkid() {
        return workid;
    }

    public void setWorkid(Long workid) {
        this.workid = workid;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return String.format("%s@id%s",getName(),getId().toString());
    }

}
