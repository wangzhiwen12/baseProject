var _SelectPics = [];
var _PicDatas = [];
var CusPicture = function (webUploader,callBack, param,sysType,apiUrl, w, h) {

    this._webUploader = webUploader;
    this._apiserver = sysType=='crm'?apiUrl+'Home/Upload/PostImage':apiUrl+'Cms/Upload/PostImage';
    this._getpicurl = sysType=='crm'?apiUrl+'Home/CusPicture/GetList':apiUrl+'Cms/CusPicture/GetList';
    this._getFoldersurl = sysType=='crm'?apiUrl+'PicBox/FolderList/GetList':'';
    this._getFolderDetailsurl = sysType=='crm'?apiUrl+'PicBox/ImgList/GetImgsByFolderId':'';
    this._w = w;
    this._h = h;
    this._callBack = callBack;
    this._param = param;
    this.Init();
};

function CusPictureModel(data) {
    var self = this;
    self.Id = data.Id;//系统编号
    self.FileName = data.FileName;//文件名称
    self.FilePath = data.FilePath;//文件路径
    self.FullFilePath = data.FullFilePath;//文件全路径
    self.Thumbnail = data.Thumbnail;//缩略图
    self.FullThumbnail = data.FullThumbnail;//缩略图全路径
    //self.FileSizeM = data.FileSize + "M";//文件大小
    //self.FileSize = data.FileSize;//文件大小
    //self.FileType = data.FileType;//文件类型
    self.Type = 0;//单独的图片还是文件夹的
    var tempdata = "none";
    if (data.IsSelected != null)
        tempdata = data.IsSelected;
    self.IsSelected = tempdata;
}
function FolderListModel(data) {
    var self = this;
    self.Id = data.Id;//系统编号
    self.FolderName = data.FolderName;//文件名称  
}

CusPicture.prototype = {
    Init: function() {
        _SelectPics = [];
        _PicDatas = [];
        if (!this._w) {
            this._w = 800;
        }
        $('#modalGlobal .modal-dialog').css('width', this._w);
        if (!this._h) {
            this._h = 500;
        }
        $('#modalGlobal .modal-dialog').css('height', this._w);
        $('#modalGlobal').off('hidden.bs.modal');
        $('#modalGlobal').on('hidden.bs.modal', function() {
            $(this).removeData("bs.modal");
            $('#modalGlobal .modal-dialog').html('');
            if (this._callBack) {
                //console.log(_SelectPics);
                this._callBack(_SelectPics); //传值
            }
            if ($(".modal:visible").length > 0) {
                $("body").addClass("modal-open");
            }
        });
        var html = '<div class="modal-content" id="commonModelBody" style="width: 800px">';
        var head = '<div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> <span class="modal-title fz-16" id="myModalLabel"><span>选择图片</span></span></div>';
        var body = '<div class="modal-body" style="min-height: 350px; overflow-x: hidden; overflow-y: auto; scrollbar-base-color: #ff0000">\
        <ul class="nav nav-tabs" role="tablist">\
            <li class="active"><a href="#userimgtab" role="tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-user"></i> 我的图片</a></li>\
            <li class="" id="folder-li" style="display:none"><a href="#userfoldertab" role="tab" data-toggle="tab" aria-expanded="true" ><i class="fa fa-user"></i> 我的文件夹</a></li>\
            <li class=""><a href="#uploadimgtab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-cloud-upload"></i> 上传图片</a></li>\
        </ul>\
        <div class="tab-content">\
            <div class="tab-pane profile active" id="userimgtab">\
                <div style="min-height: 350px; overflow-x: hidden; overflow-y: auto; scrollbar-base-color: #ff0000">\
                    <div class="row list-group king-gallery" id="mypic-div">\
                    </div>\
                    <div>\
        <div class="col-md-5" style="line-height:40px">\
            <div class="dataTables_info" id="datatable-column-filter_info" role="status" aria-live="polite">\
                <span id="startrow">1</span>至<span id="endrow">12</span>张，共<span id="totalItemCount">0</span>张\
            </div>\
        </div>\
        <div class="col-md-7">\
            <div class="dataTables_paginate paging_simple_numbers" id="datatable-column-filter_paginate">\
                <ul class="pagination" id="ulpics">\
                </ul>\
            </div>\
        </div>\
                    </div>\
                </div>\
            </div>\
            <div class="tab-pane profile" id="userfoldertab">\
                <div id="folders-div" style="min-height: 350px; overflow-x: hidden; overflow-y: auto; scrollbar-base-color: #ff0000">\
                    <div class="row" id="myfolders-div">\
                    </div>\
                    <br />\
                    <div>\
                     <div class="col-md-5" style="line-height:40px">\
                    <div class="dataTables_info" id="datatable-column-filter_info" role="status" aria-live="polite">\
                        <span id="startrow-folders">1</span>至<span id="endrow-folders">12</span>个，共<span id="totalItemCount-folders">0</span>个\
                    </div>\
                    </div>\
                    <div class="col-md-7">\
                        <div class="dataTables_paginate paging_simple_numbers" id="datatable-column-filter_paginate">\
                            <ul class="pagination" id="ulpics-folders">\
                            </ul>\
                        </div>\
                    </div>\
                    </div>\
                </div>\
                <div  id="foldersdtl-div" style="min-height: 350px; overflow-x: hidden; overflow-y: auto; scrollbar-base-color: #ff0000;display:none">\
                    <div class="row">\
                        <div class="col-sm-2 font15 form-group">\
                            <button class="btn btn-default redbtn">\
                                <span class="glyphicon glyphicon-circle-arrow-left"></span>\
                                <span id="curFolderName"></span>\
                            </button>\
                        </div>\
                    </div>\
                    <div class="row list-group king-gallery" id="cusFolderImgs-div">\
                    </div>\
                    <div>\
                         <div class="col-md-5" style="line-height:40px">\
                    <div class="dataTables_info" id="datatable-column-filter_info" role="status" aria-live="polite">\
                        <span id="startrow-folders-dtl">1</span>至<span id="endrow-folders-dtl">12</span>张，共<span id="totalItemCount-folders-dtl">0</span>张\
                    </div>\
                    </div>\
                    <div class="col-md-7">\
                        <div class="dataTables_paginate paging_simple_numbers" id="datatable-column-filter_paginate">\
                            <ul class="pagination" id="ulpics-folders-dtl">\
                            </ul>\
                        </div>\
                    </div>\
                    </div>\
                </div>\
            </div>\
            <div class="tab-pane profile" id="uploadimgtab">\
        <div  style="min-height: 350px; overflow-x: hidden; overflow-y: auto; scrollbar-base-color: #ff0000">\
            <div id="uploader">\
                        <div class="queueList">\
                            <div id="dndArea" class="placeholder">\
                                <div id="filePicker"></div>\
                                <p>最大支持 1MB 的图片(bmp/png/jpeg/jpg/gif)，不能选中大于 1MB 的图片</p>\
                            </div>\
                        </div>\
                        <div class="statusBar" style="display:none;">\
                            <div class="progress">\
                                <span class="text">0%</span>\
                                <span class="percentage"></span>\
                            </div><div class="info"></div>\
                            <div class="btns">\
                                <div id="filePicker2"></div><div class="uploadBtn">确认上传</div>\
                            </div>\
                        </div>\
                    </div>\
                </div>\
                <div class="modal-footer" style="height: 45px">\
                </div>\
            </div>\
        </div>\
    </div>';
        var footer = ' <div class="modal-footer"><button type="button" class="btn btn-default" id="close" data-dismiss="modal"><i class="fa fa-times-circle"></i> 关闭</button><button type="button" class="btn btn-custom-primary"  id="btnModelUploadSelect"><i class="fa fa-check-circle"></i> 确定</button></div>';
        html += head;
        html += body;
        html += footer;
        html += '</div>';
        $('#modalGlobal .modal-dialog').html(html);
        $('#modalGlobal').modal('show');
        this.initPicDatas();
        this.initWebuploader();
    },
    //初始化原来已经上传的图片
    initPicDatas: function() {
        var getUrl = this._getpicurl;
        var getFoldersUrl = this._getFoldersurl;
        if (getFoldersUrl) {
            $("#folder-li").show();
        }
        var getFolderDetailsurl = this._getFolderDetailsurl;
        
        var tempPageNumber = 1;
        var pageSize = 12;
        var curpageIdx = 1;
        var pageCount = 0;
        
        var tempFoldersPageNumber = 1;
        var pageFoldersCount = 0;
        var curpageFoldersIdx = 1;
        
        var tempFoldersDtlPageNumber = 1;
        var pageFoldersDtlCount = 0;
        var curpageFoldersDtlIdx = 1;

        var selectCount = this._param;
        var callBack = this._callBack;

        var FoldersData = [];
        
        //1：图片处理
        function getDatas() {
            var queryString = "?" + "PageIndex=" + tempPageNumber + "&PageSize=" + pageSize;
            $.ajax(getUrl + queryString, {
                method: 'get',
                dataType: 'json'
            }).done(function (data) {
                var rows = [];
                $.each(data.Data.PagedList, function (i, v) {
                    var model = new CusPictureModel(v);
                    model.Type = 0;
                    rows.push(model);
                });
                _PicDatas = rows;
                var pagerNum = tempPageNumber;
                $("#startrow").text(pagerNum == 1 ? "1" : (pagerNum - 1) * pageSize);
                $("#endrow").text(tempPageNumber * pageSize);
                var totalItemCount = data.Data.TotalRowsCount;
                $("#totalItemCount").text(totalItemCount);
                pageCount = totalItemCount > 0 ? Math.ceil(totalItemCount / pageSize) : 0;
                SetPages();
                setPagePicDatas(_PicDatas, "#mypic-div");
            });
        }
        getDatas();
       
        $("#commonModelBody").on("click", ".pageItem", function() {
            $.each($("#ulpics").children(), function(i, v) {
                $(v).removeClass("active");
            });
            var curpage = $(this);
            var pageIdx = $(curpage).data("id");
            curpageIdx = pageIdx;
            tempPageNumber = pageIdx;
            getDatas();
        });
        function SetPages() {
            var foo = getAllPage(tempPageNumber, pageCount);
            var fdisabled = "";
            var edisabled = "";
            if (curpageIdx == 1) fdisabled = "disabled";
            if (curpageIdx == pageCount) edisabled = "disabled";
            var pageFrist = '<li class="paginate_button previous ' + fdisabled + '"><a href="javascript:void(0)" id="previousPage">上一页</a></li>';
            var pageEnd = '<li class="' + edisabled + '"><a href="javascript:void(0)" id="nextPage">下一页</a></li>';
            var pageHtml = "";
            $.each(foo, function (i, v) {
                var active = "";
                if (v == curpageIdx) active = "active";
                pageHtml += '<li class="paginate_button ' + active + '"><a class="pageItem" data-id="' + v + '" href="javascript:void(0)">' + v + '</a></li>';
            });
            $("#ulpics").html(pageFrist + pageHtml + pageEnd);
            //上一页
            $("#previousPage").click(function() {
                if (curpageIdx != 1) {
                    curpageIdx = curpageIdx - 1;
                    tempPageNumber = curpageIdx;
                    getDatas();
                }
            });
            //下一页
            $("#nextPage").click(function () {
                if (curpageIdx != pageCount) {
                    curpageIdx = curpageIdx + 1;
                    tempPageNumber = curpageIdx;
                    getDatas();
                }
            });
        }
        function getAllPage(ctempPageNumber, cpageCount) {
            var step = 5;
            var x = Math.ceil(ctempPageNumber / step);
            var start = (step * x) - step + 1;
            var length = step + start <= cpageCount ? step + start : cpageCount;

            var foo = [];
            for (var i = start; i <= length ; i++) {
                foo.push(i);
            }
            return foo;
        }
        function setPagePicDatas(cPicDatas,ctx) {
            var pichtml = '';
            $.each(cPicDatas, function (i, v) {
                $.each(_SelectPics, function (ci, cv) {
                    if (cv.Id == v.Id && cv.Type == v.Type)
                        v.IsSelected = "block";
                });
                pichtml += ' <div class="item col-md-2 col-sm-5" style="cursor: pointer" data-id="'+v.Id+'">\
                    <div class="thumbnail">\
                        <div title=' + v.FileName + '>\
                            <img style="width: 100%;height: 95px" src=' + v.FullThumbnail + ' alt="">\
                            <div class="material-modal" style="display:' + v.IsSelected + ';  position: absolute;height: 95px;top: 0;right: 0;bottom: 0;left: 0;z-index: 2;background-color: rgba(61, 55, 55, 0.8);">\
                                <span class="editLayinner">\
                                    <i class="glyphicon glyphicon-ok" style="margin-left:40px; margin-top: 30px; color: white;font-size: 36px;"></i>\
                                </span>\
                            </div>\
                        </div>\
                        <div class="imgTitle">\
                            <span>' + v.FileName + '</span>\
                        </div>\
                    </div>\
                </div>';
            });
            $(ctx).html(pichtml);
            $(".item").click(function() {
                var model = $(this).find(".material-modal");
                var modelDisplay = $(model).css('display');
                var pid = $(this).data("id");
                var pic = getSelectData(cPicDatas,pid);
                if (modelDisplay == "none") {
                    $(model).css('display', 'block');
                    _SelectPics.push(pic);
                } else {
                    $(model).css('display', 'none');
                    var idx = getSelectDataIdx(pid, pic.Type);
                    _SelectPics.splice(idx, 1);
                }
                if (selectCount == 1) {//只选择一张
                    $('#modalGlobal').modal('hide');
                    if (callBack) {
                        callBack(_SelectPics); //传值
                    }
                }
            });
        }
        
        //2：文件夹处理
        function getFoldersDatas() {
            if (getFoldersUrl) {
                var queryString = "?" + "PageIndex=" + tempFoldersPageNumber + "&PageSize=" + pageSize;
                $.ajax(getFoldersUrl + queryString, {
                    method: 'get',
                    dataType: 'json'
                }).done(function (data) {
                    var rows = [];
                    $.each(data.Data.PagedList, function (i, v) {
                        rows.push(new FolderListModel(v));
                    });
                    FoldersData = rows;
                    var pagerNum = tempFoldersPageNumber;
                    $("#startrow-folders").text(pagerNum == 1 ? "1" : (pagerNum - 1) * pageSize);
                    $("#endrow-folders").text(tempFoldersPageNumber * pageSize);
                    var totalItemCount = data.Data.TotalRowsCount;
                    $("#totalItemCount-folders").text(totalItemCount);
                    pageFoldersCount = totalItemCount > 0 ? Math.ceil(totalItemCount / pageSize) : 0;
                    SetFoldersPages();
                    setPageFoldersDatas();
                });
            }
        }
        getFoldersDatas();
        $("#commonModelBody").on("click", ".pageItem-folders", function () {
            $.each($("#ulpics-folders").children(), function (i, v) {
                $(v).removeClass("active");
            });
            var curpage = $(this);
            var pageIdx = $(curpage).data("id");
            curpageFoldersIdx = pageIdx;
            tempFoldersPageNumber = pageIdx;
            getFoldersDatas();
        });
        var curfoldersId = 0;
        function setPageFoldersDatas() {
            var pichtml = '';
            $.each(FoldersData, function (i, v) {
                pichtml += ' <div class="col-md-4 ezr-spacepadding">\
                        <div class="ezr-spacefilelist item-folders" data-id="' + v.Id + '" data-name="' + v.FolderName + '">\
                            <span class="pull-left ">\
                                <span class="ezpuipc-fileimg" ></span>\
                            </span>\
                            <span class="ezr-spaceInfo ezr-spaceInfostlye" style="width: 65%;">\
                                <span class="ezr-spaceInfotext" data-bind="text:FolderName">' + v.FolderName + '</span>\
                            </span>\
                        </div>\
                    </div>';
            });
            $("#myfolders-div").html(pichtml);
            $(".item-folders").click(function () {
                if (id != curfoldersId) {
                    var id = $(this).data("id");
                    var name = $(this).data("name");
                    $("#folders-div").hide();
                    $("#foldersdtl-div").show();
                    $("#curFolderName").text(name);
                    curfoldersId = id;
                    getFoldersDtlDatas();
                }
            });
        }
        function SetFoldersPages() {
            var foo = getAllPage(tempFoldersPageNumber, pageFoldersCount);
            var fdisabled = "";
            var edisabled = "";
            if (curpageFoldersIdx == 1) fdisabled = "disabled";
            if (curpageFoldersIdx == pageFoldersCount) edisabled = "disabled";
            var pageFrist = '<li class="paginate_button previous ' + fdisabled + '"><a href="javascript:void(0)" id="previousPage-folders">上一页</a></li>';
            var pageEnd = '<li class="' + edisabled + '"><a href="javascript:void(0)" id="nextPage-folders">下一页</a></li>';
            var pageHtml = "";
            $.each(foo, function (i, v) {
                var active = "";
                if (v == curpageFoldersIdx) active = "active";
                pageHtml += '<li class="paginate_button ' + active + '"><a class="pageItem-folders" data-id="' + v + '" href="javascript:void(0)">' + v + '</a></li>';
            });
            $("#ulpics-folders").html(pageFrist + pageHtml + pageEnd);
            //上一页
            $("#previousPage-folders").click(function () {
                if (curpageFoldersIdx != 1) {
                    curpageFoldersIdx = curpageFoldersIdx - 1;
                    tempFoldersPageNumber = curpageFoldersIdx;
                    getFoldersDatas();
                }
            });
            //下一页
            $("#nextPage-folders").click(function () {
                if (curpageFoldersIdx != pageFoldersCount) {
                    curpageFoldersIdx = curpageFoldersIdx + 1;
                    tempFoldersPageNumber = curpageFoldersIdx;
                    getFoldersDatas();
                }
            });
        }
        //文件夹图片
        var FoldersDtlData = [];
        function getFoldersDtlDatas() {
            if (getFolderDetailsurl) {
                var queryString = "?id=" +curfoldersId+ "&PageIndex=" + tempFoldersDtlPageNumber + "&PageSize=" + pageSize;
                $.ajax(getFolderDetailsurl + queryString, {
                    method: 'get',
                    dataType: 'json'
                }).done(function (data) {
                    var rows = [];
                    $.each(data.Data.PagedList, function (i, v) {
                        var model = new CusPictureModel(v);
                        model.Type = 1;
                        model.FileName = v.ImgName;
                        model.FilePath = v.ImgPath;
                        rows.push(model);
                    });
                    FoldersDtlData = rows;
                    var pagerNum = tempFoldersDtlPageNumber;
                    $("#startrow-folders-dtl").text(pagerNum == 1 ? "1" : (pagerNum - 1) * pageSize);
                    $("#endrow-folders-dtl").text(tempFoldersDtlPageNumber * pageSize);
                    var totalItemCount = data.Data.TotalRowsCount;
                    $("#totalItemCount-folders-dtl").text(totalItemCount);
                    pageFoldersDtlCount = totalItemCount > 0 ? Math.ceil(totalItemCount / pageSize) : 0;
                    SetFoldersDtlPages();
                    setPagePicDatas(FoldersDtlData, "#cusFolderImgs-div");
                });
            }
        }
        $("#commonModelBody").on("click", ".pageItem-folders-dtl", function () {
            $.each($("#ulpics-folders-dtl").children(), function (i, v) {
                $(v).removeClass("active");
            });
            var curpage = $(this);
            var pageIdx = $(curpage).data("id");
            curpageFoldersDtlIdx = pageIdx;
            tempFoldersDtlPageNumber = pageIdx;
            getFoldersDtlDatas();
        });
        function SetFoldersDtlPages() {
            var foo = getAllPage(tempFoldersDtlPageNumber, pageFoldersDtlCount);
            var fdisabled = "";
            var edisabled = "";
            if (curpageFoldersDtlIdx == 1) fdisabled = "disabled";
            if (curpageFoldersDtlIdx == pageFoldersDtlCount) edisabled = "disabled";
            var pageFrist = '<li class="paginate_button previous ' + fdisabled + '"><a href="javascript:void(0)" id="previousPage-folders-dtl">上一页</a></li>';
            var pageEnd = '<li class="' + edisabled + '"><a href="javascript:void(0)" id="nextPage-folders-dtl">下一页</a></li>';
            var pageHtml = "";
            $.each(foo, function (i, v) {
                var active = "";
                if (v == curpageFoldersDtlIdx) active = "active";
                pageHtml += '<li class="paginate_button ' + active + '"><a class="pageItem-folders-dtl" data-id="' + v + '" href="javascript:void(0)">' + v + '</a></li>';
            });
            $("#ulpics-folders-dtl").html(pageFrist + pageHtml + pageEnd);
            //上一页
            $("#previousPage-folders-dtl").click(function () {
                if (curpageFoldersDtlIdx != 1) {
                    curpageFoldersDtlIdx = curpageFoldersDtlIdx - 1;
                    tempFoldersDtlPageNumber = curpageFoldersDtlIdx;
                    getFoldersDtlDatas();
                }
            });
            //下一页
            $("#nextPage-folders-dtl").click(function () {
                if (curpageFoldersDtlIdx != pageFoldersDtlCount) {
                    curpageFoldersDtlIdx = curpageFoldersDtlIdx + 1;
                    tempFoldersDtlPageNumber = curpageFoldersDtlIdx;
                    getFoldersDtlDatas();
                }
            });
        }

        $(".redbtn").click(function() {
            $("#folders-div").show();
            $("#foldersdtl-div").hide();
        });
        
        $("#close").click(function() {
            $('#modalGlobal').modal('hide');
        });
        $("#btnModelUploadSelect").click(function() {
            $('#modalGlobal').modal('hide');
            if (callBack) {
                callBack(_SelectPics); //传值
            }
        });
        function getSelectData(cPicDatas, id) {
            var pic;
            $.each(cPicDatas, function (i, v) {
                if (id == v.Id) {
                    pic = v;
                    return false;
                }
            });
            return pic;
        }

        function getSelectDataIdx(id,type) {
            var idx;
            $.each(_SelectPics, function (i, v) {
                if (id == v.Id && type == v.Type) {
                    idx = i;
                    return false;
                }
            });
            return idx;
        }
    },
    initWebuploader: function () {
        var WebUploader = this._webUploader;
        var maxCount = 300;
        var acceptextensions = 'gif,jpg,jpeg,bmp,png';
        var apiserver = this._apiserver;
        var callBack = this._callBack;
        var $wrap = $('#uploader'),
            // 图片容器
            $queue = $('<ul class="filelist"></ul>')
                .appendTo($wrap.find('.queueList')),
            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find('.statusBar'),
            // 文件总体选择信息。
            $info = $statusBar.find('.info'),
            // 上传按钮
            $upload = $wrap.find('.uploadBtn'),
            // 没选择文件之前的内容。
            $placeHolder = $wrap.find('.placeholder'),
            $progress = $statusBar.find('.progress').hide(),
            // 添加的文件数量
            fileCount = 0,
            // 添加的文件总大小
            fileSize = 0,
            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,
            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,
            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',
            // 所有文件的进度信息，key为file id
            percentages = {},
            // 判断浏览器是否支持图片的base64
            isSupportBase64 = (function() {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function() {
                    if (this.width != 1 || this.height != 1) {
                        support = false;
                    }
                }
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            })(),
            // 检测是否已经安装flash，检测flash的版本
            flashVersion = (function() {
                var version;

                try {
                    version = navigator.plugins['Shockwave Flash'];
                    version = version.description;
                } catch(ex) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                            .GetVariable('$version');
                    } catch(ex2) {
                        version = '0.0';
                    }
                }
                version = version.match(/\d+/g);
                return parseFloat(version[0] + '.' + version[1], 10);
            })(),
            supportTransition = (function() {
                var s = document.createElement('p').style,
                    r = 'transition' in s ||
                        'WebkitTransition' in s ||
                        'MozTransition' in s ||
                        'msTransition' in s ||
                        'OTransition' in s;
                s = null;
                return r;
            })(),
        
        // WebUploader实例
        uploader;
        if (!WebUploader.Uploader.support('flash') && WebUploader.browser.ie) {

            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function (container) {
                    window['expressinstallcallback'] = function (state) {
                        switch (state) {
                            case 'Download.Cancelled':
                                msg.error('您取消了更新！')
                                break;

                            case 'Download.Failed':
                                msg.error('安装失败')
                                break;

                            default:
                                msg.ok('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };

                    var swf = 'expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                            'x-shockwave-flash" data="' + swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">' +
                        '<param name="movie" value="' + swf + '" />' +
                        '<param name="wmode" value="transparent" />' +
                        '<param name="allowscriptaccess" value="always" />' +
                    '</object>';

                    container.html(html);

                })($wrap);

                // 压根就没有安转。
            } else {
                $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }

            return;
        } else if (!WebUploader.Uploader.support()) {
            msg.error('Web Uploader 不支持您的浏览器！');
            return;
        }
        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker',
                label: '点击选择图片'
            },
            formData: {
                uid: 123
            },
            dnd: '#dndArea',
            paste: '#uploader',
            swf: 'Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: apiserver,
            // runtimeOrder: 'flash',

            accept: {
                title: 'Images',
                extensions: acceptextensions,
                mimeTypes: 'image/bmp,image/png,image/jpeg,image/jpg,image/gif'
            },

            compress: false,


            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,

            fileNumLimit: maxCount,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 1 * 1024 * 1024    // 1 M
        });

        // 拖拽时不接受 js, txt 文件。
        uploader.on('dndAccept', function (items) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for (; i < len; i++) {
                // 如果在列表里面
                if (~unAllowed.indexOf(items[i].type)) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });
        //当文件上传成功时触发
        uploader.on('uploadSuccess', function (file, response) {
            var datajson = response._raw;
            var data = JSON.parse(datajson).Data;
            var cusPicture = { FileName: data.name, FilePath: data.path, FullFilePath: data.pathfull, Thumbnail: data.thumbnail, FullThumbnail: data.thumbnailfull };
            var model = new CusPictureModel(cusPicture);
            _SelectPics.push(model);
        });
        //所有文件上传完之后触发
        uploader.on('uploadFinished', function () {
            var stats = uploader.getStats();
            if (stats.uploadFailNum == 0) {
                $('#modalGlobal').modal('hide');
                if (callBack) {
                    callBack(_SelectPics); //传值
                }
            }
        });
        uploader.on('beforeFileQueued', function (file) {
            var fullName = file.name;
            var fileName = file.name.substr(0, fullName.lastIndexOf('.'));
            var reg = /^[A-Za-z\d_\u4e00-\u9fa5]+$/;
            var r = fileName.match(reg);
            if (r == null) {
                msg.error('不能上传图片名称包括除”_“之外特殊字符的图片');
                return false;
            }
        });
        // uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

        // 添加“添加文件”的按钮，
        if (maxCount > 1) {
            uploader.addButton({
                id: '#filePicker2',
                label: '继续添加'
            });
        }

        uploader.on('ready', function () {
            window.uploader = uploader;
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile(file) {

            var $li = $('<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>' +
                    '<p class="progress"><span></span></p>' +
                    '</li>'),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>').appendTo($li),
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find('p.imgWrap'),
                $info = $('<p class="error"></p>'),

                showError = function (code) {
                    switch (code) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }

                    $info.text(text).appendTo($li);
                };

            if (file.getStatus() === 'invalid') {
                showError(file.statusText);
            } else {
                // @todo lazyload
                $wrap.text('预览中');
                uploader.makeThumb(file, function (error, src) {
                    var img;

                    if (error) {
                        $wrap.text('不能预览');
                        return;
                    }

                    if (isSupportBase64) {
                        img = $('<img src="' + src + '">');
                        $wrap.empty().append(img);
                    } else {
                        $.ajax(apiserver, {
                            method: 'POST',
                            data: src,
                            dataType: 'json'
                        }).done(function (response) {
                            if (response.result) {
                                img = $('<img src="' + response.result + '">');

                                $wrap.empty().append(img);
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight);

                percentages[file.id] = [file.size, 0];
                file.rotation = 0;
            }

            file.on('statuschange', function (cur, prev) {
                if (prev === 'progress') {
                    $prgress.hide().width(0);
                } else if (prev === 'queued') {
                    $li.off('mouseenter mouseleave');
                    $btns.remove();
                }

                // 成功
                if (cur === 'error' || cur === 'invalid') {
                    showError(file.statusText);
                    percentages[file.id][1] = 1;
                } else if (cur === 'interrupt') {
                    showError('interrupt');
                } else if (cur === 'queued') {
                    percentages[file.id][1] = 0;
                } else if (cur === 'progress') {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if (cur === 'complete') {
                    $li.append('<span class="success"></span>');
                }

                $li.removeClass('state-' + prev).addClass('state-' + cur);
            });

            $li.on('mouseenter', function () {
                $btns.stop().animate({ height: 30 });
            });

            $li.on('mouseleave', function () {
                $btns.stop().animate({ height: 0 });
            });

            $btns.on('click', 'span', function () {
                var index = $(this).index(),
                    deg;

                switch (index) {
                    case 0:
                        uploader.removeFile(file);
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if (supportTransition) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');

                }


            });

            $li.appendTo($queue);
        }

        // 负责view的销毁
        function removeFile(file) {
            var $li = $('#' + file.id);

            delete percentages[file.id];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each(percentages, function (k, v) {
                total += v[0];
                loaded += v[0] * v[1];
            });

            percent = total ? loaded / total : 0;


            spans.eq(0).text(Math.round(percent * 100) + '%');
            spans.eq(1).css('width', Math.round(percent * 100) + '%');
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if (state === 'ready') {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize(fileSize) + '。';
            } else if (state === 'confirm') {
                stats = uploader.getStats();
                if (stats.uploadFailNum) {
                    text = '已成功上传' + stats.successNum + '张图片，' +
                        stats.uploadFailNum + '张图片上传失败，<a class="retry" style="color:#0077dd" href="javascript:void(0);">重新上传</a>失败图片或<a class="ignore" style="color:#0077dd" href="javascript:void(0);">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize(fileSize) +
                        '），已上传' + stats.successNum + '张';

                if (stats.uploadFailNum) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html(text);
        }

        function setState(val) {
            var file, stats;

            if (val === state) {
                return;
            }

            $upload.removeClass('state-' + state);
            $upload.addClass('state-' + val);
            state = val;

            switch (state) {
                case 'pedding':
                    $placeHolder.removeClass('element-invisible');
                    $queue.hide();
                    $statusBar.addClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass('element-invisible');
                    $('#filePicker2').removeClass('element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $('#filePicker2').addClass('element-invisible');
                    $progress.show();
                    $upload.text('暂停上传');
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text('继续上传');
                    break;

                case 'confirm':
                    $progress.hide();
                    $('#filePicker2').removeClass('element-invisible');
                    $upload.text('开始上传');

                    stats = uploader.getStats();
                    if (stats.successNum && !stats.uploadFailNum) {
                        setState('finish');
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if (stats.successNum) {
                        //msg.ok('上传成功');
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

        uploader.onUploadProgress = function (file, percentage) {
            var $li = $('#' + file.id),
                $percent = $li.find('.progress span');

            $percent.css('width', percentage * 100 + '%');
            percentages[file.id][1] = percentage;
            updateTotalProgress();
        };

        uploader.onFileQueued = function (file) {
            fileCount++;
            fileSize += file.size;

            if (fileCount === 1) {
                $placeHolder.addClass('element-invisible');
                $statusBar.show();
            }

            addFile(file);
            setState('ready');
            updateTotalProgress();
        };

        uploader.onFileDequeued = function (file) {
            fileCount--;
            fileSize -= file.size;

            if (!fileCount) {
                setState('pedding');
            }

            removeFile(file);
            updateTotalProgress();

        };

        uploader.on('all', function (type) {
            var stats;
            switch (type) {
                case 'uploadFinished':
                    setState('confirm');
                    break;

                case 'startUpload':
                    setState('uploading');
                    break;

                case 'stopUpload':
                    setState('paused');
                    break;

            }
        });

        uploader.onError = function (code) {
            msg.error('错误: ' + code);
        };

        $upload.on('click', function () {
            if ($(this).hasClass('disabled')) {
                return false;
            }

            if (state === 'ready') {
                uploader.upload();
            } else if (state === 'paused') {
                uploader.upload();
            } else if (state === 'uploading') {
                uploader.stop();
            }
        });

        $info.on('click', '.retry', function () {
            uploader.retry();
        });

        $info.on('click', '.ignore', function () {
            var errofiles = uploader.getFiles("error");
            $.each(errofiles, function (k, v) {
                uploader.removeFile(v);
            })
        });

        $upload.addClass('state-' + state);
        updateTotalProgress();
    }
}