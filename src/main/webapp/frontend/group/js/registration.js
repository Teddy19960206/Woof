
const useSmmpRadio = document.getElementById("UseSmmp");
const notUseSmmpRadio = document.getElementById("notUseSmmp");
const inputSmmp = document.getElementById("inputSmmp");
const credit = document.getElementById("credit");
const transfer = document.getElementById("transfer");
const ecPay = document.getElementById("ecPay");
const showPayment = document.getElementById("showPayment");
const actualAmount = document.getElementById("actualAmount");
const price = document.getElementById("price");
// 會員擁有的毛毛幣
const smmp = parseInt(document.getElementById("smmp").value);


// 會員使用的毛毛幣
const smmpCount = document.getElementById("smmpCount");


window.addEventListener("load" , ()=>{
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
    html = `<input class="form-control d-inline text-center" type="text" maxlength="4" onkeypress='validate(event)' value="4311" pattern="[0-9]{4}" title="請輸入4位數字" required><span> - </span>
                    <input class="form-control d-inline text-center" type="text" maxlength="4" onkeypress='validate(event)' value="9522" pattern="[0-9]{4}" title="請輸入4位數字" required><span> - </span>
                    <input class="form-control d-inline text-center" type="text" maxlength="4" onkeypress='validate(event)' value="2222" pattern="[0-9]{4}" title="請輸入4位數字" required><span> - </span>
                    <input class="form-control d-inline text-center  " type="text" maxlength="4" onkeypress='validate(event)' value="2222" pattern="[0-9]{4}" title="請輸入4位數字" required>

                    <div class="mt-5">
                        <label class="mb-3">驗證碼</label>
                        <input class="form-control verification text-center" type="text" maxlength="3" onkeypress='validate(event)' value="222">
                    </div>
                    <div class="d-flex justify-content-center mt-5 myBtn">
                        <button type="submit" class="button-62">確認付款</button>
                    </div>`;
    showPayment.innerHTML = html;
});

transfer.addEventListener("change" , function (){
    html = `<h5>匯款帳號</h5>
                    <p>銀行：合作金庫 (006)</p>
                    <p>匯款帳號：3333-333-333333</p>
                    <div class="d-flex justify-content-center mt-5 myBtn">
                        <button type="submit" class="button-62">確認付款</button>
                    </div>`;
    showPayment.innerHTML = html;
});

ecPay.addEventListener("change" , function (){

    html = `<div class="d-flex justify-content-center mt-5 myBtn">
                        <button type="submit" class="button-62">使用綠界</button>
                    </div>`;
    showPayment.innerHTML = html;
});



smmpCount.addEventListener("keypress" , function (evt){

})

smmpCount.addEventListener("keyup" , function (){
    if (parseInt(smmpCount.value) > smmp){
        $("#myModal").modal("show");
        smmpCount.value = '';
        actualAmount.value = price.value;
    }else{
    }
})

smmpCount.addEventListener("input" , function (){
    actualAmount.value = price.value - smmpCount.value;
})

