package com.mn.springboot.controller;

import com.mn.springboot.entity.Part;
import com.mn.springboot.entity.Person;
import com.mn.springboot.entity.Relation;
import com.mn.springboot.utils.Neo4jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.List;
import java.util.*;

@RestController
public class ImplController {
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


    @PostMapping("search2")
    public Map<String, Object> search2(@RequestBody Person person){
        Map<String, Object> retMap = new HashMap<>();
        String cql1 = "match l=(m:Person{workid:\""+person.getWorkid()+"\"})--(n)--(:Person) return l";
        String cql2 = "match l=(m:Person{workid:\""+person.getWorkid()+"\"})--(n:Person) return l";
        String cql3 = "match l=(m:Person{workid:\""+person.getWorkid()+"\"})--(n) return l";
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql1,nodeList,edgeList);
        neo4jUtil.getPathList(cql2,nodeList,edgeList);
        neo4jUtil.getPathList(cql3,nodeList,edgeList);
        retMap.put("nodeList",nodeList);
        retMap.put("edgeList",edgeList);
        return retMap;
    }

    @GetMapping("getPath")
    public Map<String, Object> getPath(){
        Map<String, Object> retMap = new HashMap<>();
        //cql语句  ID()可以获取节点自动生成的id
        String cql = "match l=(m)-[r]-(n) return l";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql,nodeList,edgeList);
        retMap.put("nodeList",nodeList);
        retMap.put("edgeList",edgeList);
        return retMap;
    }

    //获取最短路径
    @PostMapping("getShortPath")
    public Map<String, Object> getShortPath(@RequestBody Relation relation) {
        Map<String, Object> retMap = new HashMap<>();
        //cql语句
        String cql = "MATCH (p1:Person {workid: \""+relation.getWorkid1()+"\"}),(p2:Person{workid: \""+relation.getWorkid2()+"\"}),\n" +
                "p=shortestpath((p1)-[*..10]-(p2)) RETURN p";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql,nodeList,edgeList);
        retMap.put("nodeList",nodeList);
        retMap.put("edgeList",edgeList);
        return retMap;
    }


    //创建非默认关系
    @PostMapping("addrsp")
    public boolean addrsp(@RequestBody Relation relation){
        String cql = "match (m: Person {workid: \""+relation.getWorkid1()+"\"}),(n:Person{workid:\""+relation.getWorkid2()+"\"}) merge (m)-[r:"+relation.getRsp()+"]-(n)";
        try{
            neo4jUtil.add(cql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //添加新用户节点
    @PostMapping("addsin")
    public boolean addsin(@RequestBody Person person){
        String cql = "match (m:Department{partname:\""+person.getPart()+"\"}) " +
                " merge(n:Person{name:\""+person.getName()+"\",workid: \""+person.getWorkid()+"\",part: \""+person.getPart()+"\",position: \""+person.getPosition()+"\"})" +
                "-[:"+person.getPosition()+"]->(m)";
        try{
            neo4jUtil.add(cql);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    //创建新部门节点
    @PostMapping("addPart")
    public boolean addPart(@RequestBody Part part){
        String cql1 = "merge (:Department{partname:\""+part.getPartname()+"\"})";
        String cql2 = "match (m:Department{partname:\""+part.getSuperior()+"\"}),(n:Department{partname:\""+part.getPartname()+"\"}) " +
                "merge (n)-[:belong]->(m)";
        try{
            neo4jUtil.add(cql1);
            neo4jUtil.add(cql2);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @PostMapping("delete")
    public boolean delete(String id){
        String cql1 = "match (n)-[r]-() where ID(n)="+id+" delete r";
        String cql2 = "match (n) where ID(n)="+id+" delete n";
        try{
            neo4jUtil.add(cql1);
            neo4jUtil.add(cql2);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @GetMapping("getPart")
    public String getPart() {
        //cql语句
        String cql = "match (m:Department) return m";
        Set<Map<String, Object>> nodeList = new HashSet<>();
        neo4jUtil.getList(cql, nodeList);
        Iterator it = nodeList.iterator();
        String result = "";
        int i = 1;
        while (it.hasNext()) {
            HashMap<String, String> map = (HashMap<String, String>) it.next();
            result += "<option value=\"" + i + "\">" + map.get("partname") + "</option>\n";
            i++;
        }
        return result;
    }
}