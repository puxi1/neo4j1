package com.mn.springboot.controller;

import com.mn.springboot.entity.Person;
import com.mn.springboot.utils.Neo4jUtil;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class TestController {
    @Autowired
    private Neo4jUtil neo4jUtil;

    @GetMapping("get")
    public Map<String, Object> get(){
        Map<String, Object> retMap = new HashMap<>();
        //cql语句
        String cql = "match (m) return m";
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        neo4jUtil.getList(cql,nodeList);
        retMap.put("nodeList",nodeList);
        return retMap;
    }

    @GetMapping("search1")
    public Map<String, Object> search1(@RequestBody Person person){
        Map<String, Object> retMap = new HashMap<>();
        //cql语句
        String cql = "match q=(m{workid: "+person.getWorkid()+"})-[]-() return q";
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        neo4jUtil.getList(cql,nodeList);
        retMap.put("nodeList",nodeList);
        return retMap;
    }

    @GetMapping("search2")
    public Map<String, Object> search2(@RequestBody Person person){
        Map<String, Object> retMap = new HashMap<>();
        //cql语句
        String cql = "match q=(m{workid: \""+person.getWorkid()+"\"})-[]-(),p=(n:A{workid: "+person.getWorkid()+"})-[]-()-[]-() return q";
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        neo4jUtil.getList(cql,nodeList);
        retMap.put("nodeList",nodeList);
        return retMap;
    }

    @GetMapping("getPath")
    public Map<String, Object> getPath(String id){
        Map<String, Object> retMap = new HashMap<>();
        //cql语句  ID()可以获取节点自动生成的id
        String cql = "match l=(m)-[]-(n) where ID(m)="+id+" return l";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql,nodeList,edgeList);
        retMap.put("nodeList",nodeList);
        retMap.put("edgeList",edgeList);
        return retMap;
    }

    @GetMapping("getShortPath")
    public Map<String, Object> getShortPath(
            @RequestBody Person person1,
            @RequestBody Person person2){
        Map<String, Object> retMap = new HashMap<>();
        //cql语句
        String cql = "MATCH (p1:Person {workid: \""+person1.getWorkid()+"\"}),(p2:Person{workid: \""+person2.getWorkid()+"\"}),\n" +
                "p=shortestpath((p1)-[*..10]-(p2)) RETURN p";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql,nodeList,edgeList);
        retMap.put("nodeList",nodeList);
        retMap.put("edgeList",edgeList);
        return retMap;
    }

    @GetMapping("getFields")
    public Map<String, Object> getFields(){
        Map<String, Object> retMap = new HashMap<>();
        String cql = "match (n:Person) return count(n) as cou";
        retMap.put("fieldList",neo4jUtil.getFields(cql));
        return retMap;
    }

    //创建非默认关系
    @GetMapping("addrsp")
    public void addrsp(@RequestBody Person person1,@RequestBody Person person2){
        String cql = "create (:Person{workid:\""+person1.getWorkid()+"\"})-[r:"+person1.getRelation()+"]->(:Person{workid:\""+person2.getWorkid()+"\"})";
        neo4jUtil.add(cql);
    }

    //添加新用户节点
    @PostMapping("addsin")
    public boolean addsin(@RequestBody Person person){
        String cql = "match (m:Department{name:\""+person.getPart()+"\"})," +
                "(n:Person{name:\""+person.getName()+"\",workid: \""+person.getWorkid()+"\",part: \""+person.getPart()+"\",leader: \""+person.getLeader()+"\"})" +
                " create (n)-[r:belong]->(m)";
        try{
            neo4jUtil.add(cql);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    //创建新部门节点
    @PostMapping("addPart")
    public boolean addPart(@RequestParam(value = "name") String name){
        String cql = "create (:Department{name:\""+name+"\"})";
        try{
            neo4jUtil.add(cql);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @GetMapping("delete")
    public boolean delete(@RequestBody Person person){
        String cql = "match (n:Person{workid: \""+person.getWorkid()+"\"})-[r]-() delete r";
        try{
            neo4jUtil.add(cql);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}

