//let pathName = window.document.location.pathname;
//let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);

$(document).on("click" , "button.cancel" , function(){
	
	
	$.ajax({
	url: `${projectName}/appointmentdetail/get`,
	data: {
		"adNo" : $(this).data("id"),
		"action" : "cancelAppointment"
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

