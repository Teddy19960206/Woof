
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
    html = `<label for="payname">付款人姓名</label>
                    <input type="text" class="form-control text-center mb-3" id="payname" style="width: 200px" required/>
                    <label class="d-block">信用卡卡號</label>
                    <input class="form-control d-inline text-center mb-3 credit" type="text" maxlength="4" onkeypress='validate(event)' pattern="[0-9]{4}" title="請輸入4位數字" required /><span> - </span>
                    <input class="form-control d-inline text-center credit" type="text" maxlength="4" onkeypress='validate(event)' pattern="[0-9]{4}" title="請輸入4位數字" required /><span> - </span>
                    <input class="form-control d-inline text-center credit" type="text" maxlength="4" onkeypress='validate(event)' pattern="[0-9]{4}" title="請輸入4位數字" required /><span> - </span>
                    <input class="form-control d-inline text-center credit" type="text" maxlength="4" onkeypress='validate(event)' pattern="[0-9]{4}" title="請輸入4位數字" required />
                    <label class="d-block mb-3">有效期限</label>
                    <input class="form-control d-inline text-center credit" type="text" maxlength="2" onkeypress='validate(event)' pattern="[0-9]{2}" title="請輸入2位數字" required placeholder="MM"/><span> - </span>
                    <input class="form-control d-inline text-center credit" type="text" maxlength="2" onkeypress='validate(event)' pattern="[0-9]{2}" title="請輸入2位數字" required placeholder="YY"/>
                    <div class="mt-2">
                        <label class="mb-3">末三碼</label>
                        <input class="form-control verification text-center credit" type="text" maxlength="3" onkeypress='validate(event)' pattern="[0-9]{3}" title="請輸入3位數字" required />
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



smmpCount.addEventListener("keyup" , function (){
    if (parseInt(smmpCount.value) > smmp){
        $("#myModal").modal("show");
        smmpCount.value = '';
        actualAmount.value = price.value;
    }else{
    }
})

smmpCount.addEventListener("input" , function (){

    //  禁止輸入負數
    if (this.value < 0){
        this.value = '';
        return;
    }

    actualAmount.value = price.value - smmpCount.value;

})

const inputs = document.querySelectorAll('.credit');
$(document).on('input' , 'input.credit',function(){

    inputs.forEach((input, index) => {
        input.addEventListener('input', () => {
            if (input.value.length == input.getAttribute('maxlength')) {
                console.log("111")
                if (index < inputs.length - 1) {
                    inputs[index + 1].focus();
                }
            }
        });
    });
});


function validate(event) {
    let charCode = event.which ? event.which : event.keyCode;
    if (charCode < 48 || charCode > 57) {
        event.preventDefault();
    }
}

