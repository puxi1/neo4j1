


window.onload = function() {
    var aBtn = document.getElementsByTagName('button')[0];
    var aBtn1 = document.getElementsByTagName('button')[1];
    var aBtn2 = document.getElementsByTagName('button')[2];
    var aBtn3 = document.getElementsByTagName('button')[3];
    var oDiv1 = document.getElementById('overlay');
    var oDiv2 = document.getElementById('win');
    var oDiv3 = document.getElementById('searchoverlay');
    var oDiv4 = document.getElementById('searchwin');
    var oDiv5 = document.getElementById('infooverlay');
    var oDiv6 = document.getElementById('infowin');
    var oDiv7 = document.getElementById('reloverlay');
    var oDiv8 = document.getElementById('relwin');
    var oDiv9 = document.getElementById('depoverlay');
    var oDiv10 = document.getElementById('depwin');
    var oDiv11 = document.getElementById('relation');
    var oDiv12 = document.getElementById('relawin');
    var oSpan = oDiv2.getElementsByTagName('span')[0];
    var oSpan1 = document.getElementById('searchclose');
    var oSpan2 = document.getElementById('infoclose');
    var oSpan3 = document.getElementById('relclose');
    var oSpan4 = document.getElementById('depclose');
    var oSpan5 = document.getElementById('relaclose');



    aBtn.onclick = function(){
        oDiv1.style.display = 'block';
        oDiv2.style.display = 'block';
    };
    aBtn1.onclick = function(){
        oDiv3.style.display = 'block';
        oDiv4.style.display = 'block';
    };
    aBtn2.onclick = function(){
        oDiv9.style.display = 'block';
        oDiv10.style.display = 'block';
    };
    aBtn3.onclick = function(){
        oDiv11.style.display = 'block';
        oDiv12.style.display = 'block';
    };
    oSpan.onclick = function(){
        oDiv1.style.display = 'none';
        oDiv2.style.display = 'none';
    };
    oSpan1.onclick = function(){
        oDiv3.style.display = 'none';
        oDiv4.style.display = 'none';
    };
    oSpan2.onclick = function(){
        oDiv3.style.display = 'none';
        oDiv4.style.display = 'none';
        oDiv5.style.display = 'none';
        oDiv6.style.display = 'none';
    };
    oSpan3.onclick = function(){
        oDiv3.style.display = 'none';
        oDiv4.style.display = 'none';
        oDiv7.style.display = 'none';
        oDiv8.style.display = 'none';
    };
    oSpan4.onclick = function(){
        oDiv9.style.display = 'none';
        oDiv10.style.display = 'none';
    };
    oSpan5.onclick = function(){
        oDiv11.style.display = 'none';
        oDiv12.style.display = 'none';
    };


    $("#closed").click(function(){
        $("#overlay").hide();
    });
    $("#closed").click(function(){
        $("#win").hide();
    });
    $("#sub").click(function() {
        $("#overlay").submit();
    });
    $("#searchclosed").click(function(){
        $("#searchoverlay").hide();
    });
    $("#searchclosed").click(function() {
        $("#searchwin").hide();
    });
    $("#submit").click(function() {
        $("#searchoverlay").submit();
    });

    $("#infoclosed").click(function(){
        $("#infooverlay").hide();
        $("#searchoverlay").hide();
    });
    $("#infoclosed").click(function(){
        $("#infowin").hide();
        $("#searchwin").hide();
    });
    $("#relclosed").click(function(){
        $("#reloverlay").hide();
        $("#searchoverlay").hide();
    });
    $("#relclosed").click(function(){
        $("#relwin").hide();
        $("#searchwin").hide();
    });
    $("#depclosed").click(function(){
        $("#depoverlay").hide();
    });
    $("#depclosed").click(function(){
        $("#depwin").hide();
    });
    $("#relaclosed").click(function(){
        $("#relation").hide();
    });
    $("#relaclosed").click(function(){
        $("#relawin").hide();
    });


    $("#contentSelect").change(function(data){
        var value = $("#contentSelect option:selected").attr("value");
        if(value == 1){
            $("#infowin").show();
            $("#infooverlay").show();
        } else if(value == 2){
            $("#relwin").show();
            $("#reloverlay").show();
        }
    });

};



    function con(over,window,msg) {
        var m1 = document.getElementById(over);
        var m2 = document.getElementById(window);
        // var msg = '';
        if(!confirm(msg))
        {
            m2.style.display = 'none';//关闭窗口
            m1.style.display = 'none';
            return false;
        }
        parent.location.reload();
    }

    function delcon() {
        var msg="是否删除?";
        if(!confirm(msg))
        {
            return false;
        }
        parent.location.reload();
    }

    function dragEnd() {
        network.on("dragEnd", function (params) {
            if (params.nodes && params.nodes.length > 0) {
                network.clustering.updateClusteredNode(params.nodes[0], {physics: false});
            }
        });
    }

    function login() {
        var overlay = 'overlay';
        var win = 'win';
        var msg = "是否确认添加员工?";
        con(overlay,win,msg);


        var fields = $('#form1').serializeArray();
        var obj = {}; //声明一个对象
        $.each(fields, function (index, result) {
            obj[result.name] = result.value;//通过变量，将属性值，属性一起放到对象中
        });
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/addsin",//url
            data: JSON.stringify(obj),
            // data:$('#form1').serializeArray(),
            async: false,
            contentType: "application/json",
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result) {
                    parent.location.reload();
                    // alert("SUCCESS");
                }
            },
            error: function () {
                alert("异常！");
                win.style.display = 'none';//关闭窗口
                overlay.style.display = 'none';
            }
        });
    }

    function deplogin() {
        var overlay = 'depoverlay';
        var win = 'depwin';
        var msg = "是否确认添加部门?";
        con(overlay,win,msg);

        var fields = $('#form5').serializeArray();
        var obj = {}; //声明一个对象
        $.each(fields, function(index, result) {
            obj[result.name] = result.value;//通过变量，将属性值，属性一起放到对象中
        });
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/addPart" ,//url
            data: JSON.stringify(obj),
            async:false,
            contentType:"application/json",
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result) {
                    parent.location.reload();
                    // alert("SUCCESS");
                }
            },
            error : function() {
                alert("异常！");
                win.style.display='none';//关闭窗口
                overlay.style.display='none';
            }
        });
    }

    function relalogin() {
        var overlay = 'relaoverlay';
        var win = 'relawin';
        var msg = "是否确认添加关系?";
        con(overlay,win,msg);


        var fields = $('#form6').serializeArray();
        var obj = {}; //声明一个对象
        $.each(fields, function(index, result) {
            obj[result.name] = result.value;//通过变量，将属性值，属性一起放到对象中
        });
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/addrsp" ,//url
            data: JSON.stringify(obj),
            async:false,
            contentType:"application/json",
            success: function (result) {
                if (result) {
                    alert("添加成功");
                    parent.location.reload();
                }
            },
            error : function() {
                alert("异常！");
                win.style.display='none';//关闭窗口
                overlay.style.display='none';
            }
        });
    }




    function infologin() {
        network.moveTo({scale: 0.8});
        var tipMsg = layer.msg('数据加载中，请稍等...', {icon: 16,shade:[0.1,'#000'],time:0,offset:'250px'});
        //该节点已扩展
        // nodeExtendArr.push(id);
        var infooverlay = document.getElementById('infooverlay');
        var infowin = document.getElementById('infowin');
        infowin.style.display='none';//关闭窗口
        infooverlay.style.display='none';
        var searchoverlay = document.getElementById('searchoverlay');
        var searchwin = document.getElementById('searchwin');
        searchwin.style.display='none';//关闭窗口
        searchoverlay.style.display='none';

        var fields = $('#form3').serializeArray();
        var obj = {}; //声明一个对象
        $.each(fields, function(index, result) {
            obj[result.name] = result.value;//通过变量，将属性值，属性一起放到对象中

        });
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/search2" ,//url
            data: JSON.stringify(obj),
            async:false,
            contentType:"application/json",
            success: function (result) {
                layer.close(tipMsg);
                if (result) {
                    init();
                    createNetwork({nodes:result.nodeList,edges:result.edgeList});
                    console.log(result);

                }
            },
            error : function() {
                alert("异常！");
                win.style.display='none';//关闭窗口
                overlay.style.display='none';
            }
        });
        dragEnd();
        network.on("doubleClick", function (params) {
            var nodeId = params.nodes[0];
            del(nodeId);
            delcon();
            infologin();
        });
    }




    function rellogin() {
        var reloverlay = document.getElementById('reloverlay');
        var relwin = document.getElementById('relwin');
        relwin.style.display='none';//关闭窗口
        reloverlay.style.display='none';
        searchwin.style.display='none';//关闭窗口
        searchoverlay.style.display='none';

        var fields = $('#form4').serializeArray();
        var obj = {}; //声明一个对象
        $.each(fields, function(index, result) {
            obj[result.name] = result.value;//通过变量，将属性值，属性一起放到对象中

        });
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/getShortPath" ,//url
            data: JSON.stringify(obj),
            // data:$('#form1').serializeArray(),
            async:false,
            contentType:"application/json",
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result) {
                    init();
                    createNetwork({nodes:result.nodeList,edges:result.edgeList});
                }
            },
            error : function() {
                alert("异常！");
                win.style.display='none';//关闭窗口
                overlay.style.display='none';
            }
        });
        dragEnd();
        network.on("doubleClick", function (params) {
            var nodeId = params.nodes[0];
            del(nodeId);
            delcon();
            rellogin();
        });

    }





//拓扑
    var network;
// 创建节点对象
    var nodes;
// 创建连线对象
    var edges;
// 已扩展的节点
    var nodeExtendArr = new Array();

    $(function () {
        init();
        //修改初始缩放
        getData();
        network.moveTo({scale: 0.8});


        //先初始化一个节点
        $.ajax({
            url: '/get',
            async: false,
            success: function (ret) {
                if (ret) {
                    createNetwork({nodes: ret.nodeList});
                } else {
                    layer.msg("查询失败");
                }
            }
        });
        dragEnd();
        network.on("doubleClick", function (params) {
            var nodeId = params.nodes[0];
            del(nodeId);
            delcon();
        });
    });



    function init(){

        // 创建节点对象
        nodes = new vis.DataSet([]);
        // 创建连线对象
        edges = new vis.DataSet([]);
        // 创建一个网络拓扑图  不要使用jquery获取元素
        var container = document.getElementById('network_id');
        var data = {nodes: nodes, edges: edges};
        //全局设置，每个节点和关系的属性会覆盖全局设置
        var options = {
            //设置节点形状
            nodes:{
                shape: 'dot',//采用远点的形式
                size: 30,
                font:{
                    face:'Microsoft YaHei'
                }
            },
            // 设置关系连线
            edges:{
                font:{
                    face:'Microsoft YaHei'
                }
            },
            //设置节点的相互作用
            interaction: {
                //鼠标经过改变样式
                hover: true
                //设置禁止缩放
                //zoomView:false
            },
            //力导向图效果
            physics: {
                enabled: true,
                barnesHut: {
                    gravitationalConstant: -4000,
                    centralGravity: 0.3,
                    springLength: 120,
                    springConstant: 0.04,
                    damping: 0.09,
                    avoidOverlap: 0
                }
            }
        };
        network = new vis.Network(container, data, options);
        console.log(network);
    }



//获取id扩展后的数据
    function getData(){
        var tipMsg = layer.msg('数据加载中，请稍等...', {icon: 16,shade:[0.1,'#000'],time:0,offset:'250px'});
        //该节点已扩展
        // nodeExtendArr.push(id);
        $.ajax({
            url:'/getPath',
            // data:{
            //     id:id //当前节点id
            // },
            success: function(ret) {
                layer.close(tipMsg);
                if(ret){
                    createNetwork({nodes:ret.nodeList,edges:ret.edgeList});
                }else{
                    layer.msg("查询失败");
                }
            }
        });
    }

    function del(id) {
        nodeExtendArr.push(id);
        $.ajax({
            type: "POST",
            url:'/delete',
            data:{
                id:id //当前节点id
            },

            success: function(ret) {
                layer.close(tipMsg);
                if(ret){
                    alert("删除成功");
                }else{
                    layer.msg("删除失败");
                }
            }
        });
    }
//扩展节点 param nodes和relation集合
    function createNetwork(param) {
        //可以试试注释掉去重的方法看看效果
        if(param.nodes && param.nodes.length>0){
            //去除已存在的节点  以“id”属性为例删除重复节点，根据具体的属性自行修改
            for(var i in network.body.data.nodes._data){
                var nodeTemp = network.body.data.nodes._data[i];
                param.nodes = deleteValueFromArr(param.nodes,"nodeId",nodeTemp.id);
            }
            if(param.nodes && param.nodes.length>0){
                //添加节点
                for(var i=0;i<param.nodes.length;i++){
                    var node = param.nodes[i];
                    //控制背景色 不同类型的节点颜色不同
                    var background = "#97C2FC";
                    //人
                    if(node.name && node.name!=""){
                        background = "#FFD86E";
                    }
                    //电影
                    if(node.partname && node.partname!=""){
                        background = "#6DCE9E";
                    }
                    //拼接返回的结果显示在图上
                    var label="";
                    var title="";
                    if(node.partname == undefined){
                        label= node.name + "\n";
                    } else {
                        label= node.partname + "\n";
                    }
                    for(var n in node){
                        if (n !="nodeId"){
                            title += n + ":" + node[n] +"<br/>";
                        }
                    }
                    nodes.add({
                        id: node.nodeId,
                        label: label,
                        title:title,
                        color:{
                            background:background
                        }
                    })
                }
            }
        }
        if(param.edges && param.edges.length>0){
            //去除已存在的关系
            for(var i in network.body.data.edges._data){
                var edgeTemp = network.body.data.edges._data[i];
                param.edges = deleteValueFromArr(param.edges,"edgeId",edgeTemp.id);
            }
            if(param.edges && param.edges.length>0) {
                //添加关系
                for (var i = 0; i < param.edges.length; i++) {
                    var edge = param.edges[i];
                    //拼接返回的结果显示在图上  根据数据库属性存储的格式进行调整
                    var label = "";
                    for(var n in edge.roles){
                        label += edge.roles[n] + " ";
                    }
                    edges.add({
                        id: edge.edgeId,
                        arrows: 'to',
                        from: edge.edgeFrom,
                        to: edge.edgeTo,
                        label: label,
                        font: {align: "middle"},
                        length: 150
                    });
                }
            }
        }
    }

    $('input[type=checkbox][name=checkbox]').change(function(e) {
        for(var i in network.body.data.nodes._data){
            if(network.body.data.nodes._data[i].label.indexOf("title")!=-1 && e.target.value == "Movie" && !e.currentTarget.checked){
                network.clustering.updateClusteredNode(i, {hidden : true});
            }else if(network.body.data.nodes._data[i].label.indexOf("name")!=-1 && e.target.value == "Person" && !e.currentTarget.checked){
                network.clustering.updateClusteredNode(i, {hidden : true});
            }else{
                network.clustering.updateClusteredNode(i, {hidden : false});
            }
        }
    });

//根据对象组数中的某个属性值进行过滤删除
//arrName数组名  field过滤的字段   keyValue字段值
    function deleteValueFromArr(arrName,field,keyValue){
        if(arrName==null || arrName.length==0){
            return null;
        }
        for (var i =0;i< arrName.length;i++){
            if(arrName[i][field]==keyValue){
                arrName.splice(i,1);
            }
        }
        return arrName;
    }
//根据对象数组中的某个属性值获取过滤后的数组
//arrName数组名  field过滤的字段   keyValue字段值
    function getArrFromArr(arrName,field,keyValue){
        var arrReturn=[];
        if(arrName==null || arrName.length==0){
            return arrReturn;
        }
        var obj;
        for (var item=0; item< arrName.length;item++){
            obj=arrName[item];
            if(obj[field]==keyValue){
                arrReturn.push(obj);
            }
        }
        return arrReturn;
    }


