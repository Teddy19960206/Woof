let pathName = window.document.location.pathname;
let projectName = pathName.substring(0, pathName.substring(1).indexOf("/") + 1);

const useSmmpRadio = document.getElementById("UseSmmp");
const notUseSmmpRadio = document.getElementById("notusemocoin");
const inputSmmp = document.getElementById("inputSmmp");
//const credit = document.getElementById("credit");
//const transfer = document.getElementById("transfer");
//const ecPay = document.getElementById("ecPay");
//const showPayment = document.getElementById("showPayment");
//const actualAmount = document.getElementById("actualAmount");
//const price = document.getElementById("price");
//const address = document.getElementById("address");
// 會員擁有的毛毛幣
//const smmp = parseInt(document.getElementById("smmp").value);


// 會員使用的毛毛幣
const smmpCount = document.getElementById("smmpCount");
//
//
window.addEventListener("load" , ()=>{
	address.value = "大便";
    notUseSmmpRadio.checked = true;
    inputSmmp.disabled = true;
    smmpCount.value = "";
    actualAmount.value = price.value;
})

// 監聽單選按鈕的變更事件
useSmmpRadio.addEventListener( "change", function () {
    // 如果選擇使用毛毛幣，移除disabled屬性
    inputSmmp.removeAttribute("disabled");
});

notUseSmmpRadio.addEventListener("change", function () {
    // 如果選擇不使用毛毛幣，增加disabled屬性
    inputSmmp.setAttribute("disabled", "disabled");
    smmpCount.value = "";

});


credit.addEventListener("change" , function (){
    html = ` <div class="d-flex mt-3">
					       <input class="form-control text-center" style=" width: 70px" type="text" maxlength="4" onkeypress='validate(event)' value="1111" pattern="[0-9]{4}" title="請輸入4位數字" required />
					       <span class="mx-2 mt-1">-</span>
					       <input class="form-control text-center" style=" width: 70px" type="text" maxlength="4" onkeypress='validate(event)' value="2222" pattern="[0-9]{4}" title="請輸入4位數字" required />
					       <span class="mx-2 mt-1">-</span>
					       <input class="form-control text-center" style=" width: 70px" type="text" maxlength="4" onkeypress='validate(event)' value="3333" pattern="[0-9]{4}" title="請輸入4位數字" required />
					       <span class="mx-2 mt-1">-</span>
					       <input class="form-control text-center" style=" width: 70px" type="text" maxlength="4" onkeypress='validate(event)' value="4444" pattern="[0-9]{4}" title="請輸入4位數字" required />
					   </div>

					<div class="mt-1">
						<label class="mb-1">驗證碼</label> <input
							class="form-control verification text-center" style=" width: 70px" type="text"
							maxlength="3" onkeypress='validate(event)' value="555">
					</div>
					<div class="d-flex justify-content-start mt-3 myBtn">
						<button type="submit" class="btn btn-primary">確認付款</button>
					</div>
				</div>`;
    showPayment.innerHTML = html;
});

transfer.addEventListener("change" , function (){
    html = `<h5 class="mt-3">匯款帳號</h5>
                  <p class="m-0">銀行：合作金庫 (006)</p>
                  <p class="m-0">匯款帳號：3333-333-333333</p>
                      <div class="d-flex justify-content-start myBtn">
							<button type="submit" class="btn btn-primary mt-3">確認付款</button>
						</div>`;
    showPayment.innerHTML = html;
});

ecPay.addEventListener("change" , function (){

    html = `<div class="d-flex justify-content-start myBtn">
				<button type="submit" class="btn btn-primary mt-3">使用綠界</button>
			</div>`;
    showPayment.innerHTML = html;
});


//
//smmpCount.addEventListener("keypress" , function (evt){
//
//})
//
//smmpCount.addEventListener("keyup" , function (){
//    if (parseInt(smmpCount.value) > smmp){
//        $("#myModal").modal("show");
//        smmpCount.value = '';
//        actualAmount.value = price.value;
//    }else{
//    }
//})
//
//smmpCount.addEventListener("input" , function (){
//    actualAmount.value = price.value - smmpCount.value;
//})