function ajaxFunction() {
    var xmlHttp;
    try {  // Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        try {  // IE
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
        }
    }
    return xmlHttp;
}

window.onload = function() {
    document.getElementById("ok").onclick = function() {
        // 1 创建XmlHttpRequest对象
        var xmlHttp = ajaxFunction();
        /*
         *
         * 2 接收服务器返回的数据[注册监听]
         *
         * 怎么接收?
         * 什么时候接收?
         *
         * onreadystatechange:
         *
         * 该事件处理函数由服务器触发，而不是用户
         * 在Ajax执行过程中，服务器会通知客户端当前的通信状态。这依靠更新XMLHttpRequest对象的readyState来实现
         * 改变readyState属性是服务器对客户端连接操作的一种方式。
         * 每次 readyState 属性的改变都会触发 readystatechange事件
         * 在 Ajax 执行过程中，服务器会通知客户端当前的通信状态。通信状态的改变要激发函数的执行
         */
        xmlHttp.onreadystatechange = function() {
            alert(xmlHttp.readyState);
            // alert(xmlHttp.status);
            if (xmlHttp.readyState == 4) {

            }
        }
    }
}
