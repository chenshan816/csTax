/**
 * 博客主页的自己完成的主要代码
 */

	var isChange = false;
	$(document).ready(function(){
    	$("nav").sticky({topSpacing:0});
  	});
	//图片展示，给modal传值
	function showImg(img){
		isChange = false;
		var show_src = $(img).attr("src");
		var show_img = $("#show_img").attr("src",show_src);
		var show_id  = $(img).attr("id");
		var show_imgId = $("#show_imgId").val(show_id);
		//异步查询该图的信息？
		$.ajax({
			url:"${ctx}/blog/blog_getImgDetail.action",
			type:"post",
			data:{"imgId":show_id},
			dataType:"json",
			async:"true",
			success:function(data){
				if(data != null && data !="" && data != undefined){
					if("success" == data.msg){
						$("#imgTitle").html(data.img[0].title);
						$("#imgContext").html(data.img[0].context);
					}
				}
			}
		});
		$("#img").modal("show");
	}
	//进行添加图片的详细信息
	function addImgDetail(){
		if(!isChange){
			var title = $("#imgTitle").text();
			var inputTitle = $("<input id='title' type='text'value='" + title + "'/>");
			$("#imgTitle").html(inputTitle); 
			inputTitle.trigger("focus");
			var context = $("#imgContext").text();
			var inputContext = $("<textarea id='context' rows='20' class='form-control'>"+context+"</textarea>");
			$("#imgContext").html(inputContext);
			isChange = true;
		}else{
			var imgId = $("#show_imgId").val();
			var title = $("#title").val();
			var context = $("#context").val();
			$.ajax({
				url:"${ctx}/blog/blog_addImgDetail.action",
				type:"post",
				data:{"imgId":imgId,"title":title,"context":context},
				dataType:"json",
				success:function(data){
					if(data != null && data !="" && data != undefined){
						if("success" == data.msg){
							$("#imgTitle").empty();
							$("#imgTitle").html(title);
							$("#imgContext").empty();
							$("#imgContext").html(context);
							var images = $("#mainImg >img");
							$.each(images,function(index,img){
								if($(img).attr("id") == imgId){
									var change = title;
									$(img).next().next().html(change);
								}
							});
							isChange = false;
						}
					}
				}
			});
		}
	}
	//文件选择
	function selectFile(){
		$("#fileToUpload").trigger("click");
	}
	//获取路径
	//保存路径
	var imgs={};
	function changImg(e){
		for (var i = 0; i < e.target.files.length; i++) {  
            var file = e.target.files.item(i);  
            if (!(/^image\/.*$/i.test(file.type))) {  
                continue; //不是图片 就跳出这一次循环  
            }  
            //实例化FileReader API  
            var freader = new FileReader();  
            freader.readAsDataURL(file);
            imgs[file.name] = file;
            freader.onload = function(e) {
            	var cImg = "<img class='imgShow' id="+file.name+" onclick='deleteImg(this)' src='"+e.target.result+"'  width='150px' height='150px'>";
            	$("#mainImg").prepend(cImg);
            };
        }
      }
	  //上传图片
	  function uploadFile() {
	     if (window.File && window.FileList) {
	     	 //var xhr = new XMLHttpRequest();
	     	 //xhr.open("POST", $("#uploadForm").attr("action"));
	     	 var fd = new FormData();
	     	 for(var key in imgs){
	     		fd.append("files",imgs[key]); 
	     	 }
	      	 //xhr.send(fd);
	     	 $.ajax({
	     		 url:"${ctx}/blog/blog_addImg.action",
	     		 data:fd,
	     		 type:"post",
	     		 cache : false,
	             processData : false, // 不处理发送的数据，因为data值是Formdata对象，不需要对数据做处理
	             contentType : false, // 不设置Content-type请求头
	     		 success:function(data){
	     			 data = eval("("+data+")");
	     			 if("success" == data.msg){
	     		      	 location.reload();
	     			 }
	     		 }
	     	 });
	     } else {
	      	 $("#uploadForm").submit();   //no html5
	     }
	     return false;
	  }
	  //删除图片
	  function deleteImg(img){
		  var imgName = $(img).attr("id");
		  delete imgs[imgName];
		  $(img).remove();
	  }
	  //查看文章
	  function articleListUI(typeId){
		  	var userId = $("#user").val();
			document.ArticleForm.action = "${ctx}/blog/article_articleListUI.action?typeId="+typeId + "&user.id="+userId;
			document.ArticleForm.submit();
		}
      