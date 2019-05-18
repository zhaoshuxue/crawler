<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>图片列表</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/js/bootstrap-3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/js/bootstrap-table/bootstrap-table.min.css"/>
    <style type="text/css">
        .dn {
            display: none;
        }

        .fixed-table-body {
            height: auto !important;
        }
    </style>
</head>
<body>
<div class="container">
    <div>
        <button type="button" class="btn btn-default" id="search">查询</button>
        <button type="button" class="btn btn-primary" id="add">新增</button>
    </div>
    <table id="table"></table>
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel2" aria-hidden="true" style="top: 10%">
    <div class="modal-dialog" style="">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">添加</h4>
            </div>
            <div id="detailDiv" class="modal-body" style="min-height:100px;">
                <form role="form">
                    <div class="input-group">
                        <span class="" style="">标题</span>
                        <input class="form-control" id="title" placeholder="输入标题" />
                    </div>
                    <div class="input-group">
                        <input name="tupian" id="tupian" type="file" class="form-control"
                               style="width:250px; display: none;"/>
                        <span class="btn btn-primary" id="selectImg">请选择一张图片</span>
                        <span class="btn btn-primary dn" id="startUpload">请选择一张图片</span>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<script type="text/javascript" src="${basePath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${basePath}/static/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>

<script type="text/javascript">

    var basePath = "${basePath}";
    $(function () {
        loadTable();
//
        $("#add").click(function () {
            $('#addModal').modal();
        });

    });

    function loadTable() {
        $('#table').bootstrapTable({
            method: "post",
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            url: basePath + "/imageList",
            cache: false,
            striped: true,
            sidePagination: 'server',//设置为服务器端分页
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType: "",
            queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
                return {
                    pid: "${pid}"
                }
            },
            uniqueId: 'id',
            columns: [
                {
                     checkbox: true, // 显示一个勾选框
                     align: 'center' // 居中显示
                }, {
                    field: 'id', // 返回json数据中的name
                    title: 'ID', // 表格表头显示文字
                    align: 'center', // 左右居中
                    valign: 'middle' // 上下居中
                }, {
                    field: 'title', // 返回json数据中的name
                    title: '标题', // 图片类型，0:gif，1:jpg，2:jpeg，3:png，4:mp4,5:
                    align: 'center', // 左右居中
                    valign: 'middle'
                }, {
                    field: 'more', // 返回json数据中的name
                    title: 'more', // 图片类型，0:gif，1:jpg，2:jpeg，3:png，4:mp4,5:
                    align: 'center', // 左右居中
                    valign: 'middle'
                }, {
                    field: 'src',
                    title: '图片地址',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) { // 单元格格式化函数
                        if (value == '') {
                            return "没有资源";
                        }
                        if (row.more > 1) {
                            var arr = value.split('!@#');
                            var html = '';
                            for(var i=0, len=arr.length; i<len; i++){
                                html += '<img src="' + arr[i] + '" style="width:100%;" />';
                            }
                            return html;
                        }
                        return '<img src="' + value + '" style="width:100%;" />';
                    }
                }, {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle'
//                }, {
//                    title: "操作",
//                    align: 'center',
//                    valign: 'middle',
//                    formatter: function (value, row, index) {
//                        var cls = 'btn btn-primary btn-sm';
//                        var btn_update = '<button class="' + cls + '" onclick="update(\'' + row.id + '\')">编辑</button>';
//                        var btn_img = '<button class="' + cls + '" onclick="albumDetail(\'' + row.id + '\')">详情</button>';
//                        var btn = btn_update + btn_img;
//                        return btn;
//                    }
                }
            ],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        });

    }


</script>
</body>

</html>