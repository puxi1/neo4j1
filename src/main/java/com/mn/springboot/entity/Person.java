package com.mn.springboot.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
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
    private String leader;

    public Set<Person> getFriends() {
        return friends;
    }

    public void setFriends(Set<Person> friends) {
        this.friends = friends;
    }

    @Relationship(type = "FRIENDSHIP",direction = Relationship.UNDIRECTED)
    private Set<Person> friends;

    public Person(){}

    public Person(String name, Long workid, String part, String leader) {
        this.name = name;
        this.workid = workid;
        this.part = part;
        this.leader = leader;
    }

    public void makeNewFriend(Person person){
        if (friends==null){
            friends = new HashSet<>();
        }
        if (friends.contains(person)){
            return;
        }
        friends.add(person);
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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Override
    public String toString(){
        return String.format("%s@id%s",getName(),getId().toString());
    }

}
