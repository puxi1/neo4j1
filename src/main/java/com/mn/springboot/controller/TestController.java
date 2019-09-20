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
        //cql语句
        //String cql = "match (n:Person{name:\"Anthony Edwards\"}) return n.name as name,n.born as born";
        String cql = "match (n:Person) return count(n) as cou";
        retMap.put("fieldList",neo4jUtil.getFields(cql));
        return retMap;
    }

    @GetMapping("addrsp")
    public void addrsp(@RequestBody Person person1,@RequestBody Person person2){
        //创建单个节点
        //String cql = "create (:Person{name:\"康康\"})";
        //创建多个节点
        //String cql = "create (:Person{name:\"李雷\"}) create (:Person{name:\"小明\"})";
        //根据已有节点创建关系
        //String cql = "match (n:Person{name:\"李雷\"}),(m:Person{name:\"小明\"}) create (n)-[r:friendRelation]->(m)";
        //同时创建节点和关系
        String cql = "create (:Person{name:\""+person1.getName()+"\"})-[r:"+person1.getRelation()+"]->(:Person{name:\""+person2+"\"})";
        neo4jUtil.add(cql);
    }

    @PostMapping("addsin")
    public boolean addsin(@RequestBody Person person){
//    public boolean addsin(){
        //创建单个节点
//        String cql = "create (:Person{name:\"张三\"})";
        String cql = "create (:Person{name:\""+person.getName()+"\",workid: \""+person.getWorkid()+"\",part: \""+person.getPart()+"\",leader: \""+person.getLeader()+"\"})";
        //创建多个节点
        //String cql = "create (:Person{name:\"李雷\"}) create (:Person{name:\"小明\"})";
        //根据已有节点创建关系
        //String cql = "match (n:Person{name:\"李雷\"}),(m:Person{name:\"小明\"}) create (n)-[r:friendRelation]->(m)";
        //同时创建节点和关系
//        String cql = "create (:Person{name:\"张三\"})-[r:friendRelation]->(:Person{name:\"王五\"})";
        try{
            neo4jUtil.add(cql);
            return true;
        }catch (Exception e) {
            return false;
        }
    }


    @GetMapping("delete")
    public boolean delete(@RequestBody Person person){
//    public void addsig(){
        //创建单个节点
//        String cql = "create (:Person{name:\"张三\"})";
        String cql = "match (n:Person{workid: \""+person.getWorkid()+"\"})-[r]-() delete r";
        //创建多个节点
        //String cql = "create (:Person{name:\"李雷\"}) create (:Person{name:\"小明\"})";
        //根据已有节点创建关系
        //String cql = "match (n:Person{name:\"李雷\"}),(m:Person{name:\"小明\"}) create (n)-[r:friendRelation]->(m)";
        //同时创建节点和关系
//        String cql = "create (:Person{name:\"张三\"})-[r:friendRelation]->(:Person{name:\"王五\"})";
        try{
            neo4jUtil.add(cql);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}

