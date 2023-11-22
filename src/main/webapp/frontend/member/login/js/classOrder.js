let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);

$(document).on("click" , "button.refund" , function(){
	
	
	$.ajax({
	url: `${projectName}/classorder/refundApplication`,
	data: {
		"id" : $(this).data("id"),
//		"action" : ""
	},
	dataType: 'json',
	type: 'get',
	cache: false,
	success: function (data) {
	  console.log(data);
	  
	  if(data.message){
		  window.location.reload();
	  }else{
		  console.log("錯誤")
	  }
	},
	error: function (d) {
	  alert('404. Please wait until the File is Loaded.');
	},
});
	
})

