let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);

const memNo = document.getElementById("memNo");
const memName = document.getElementById("memName");
const classAcount = document.getElementById("classAcount");
const coTime = document.getElementById("coTime");
const method = document.getElementById("method");
const smmp = document.getElementById("smmp");
const actualAmount = document.getElementById("actualAmount");
const status = document.getElementById("status");

const show = document.getElementById("show");

$(function () {
    const coNo = document.getElementById("coNo").value;
    if (coNo.length !== 0){
        fetchData(coNo);
    }else {
        window.location.href=`${projectName}/index.html`;
    }
})


async function fetchData(coNo){
    try{
        let coNo = $("#coNo").val();
        let url = `${projectName}/groupOrder/getOneOrder`;
        const response = await fetch(url , {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body : `coNo=`+ coNo

        })
        if (!response.ok){
            throw new Error("錯誤")
        }

        const data = await response.json();

        coNo.value = data.coNo;
        memNo.value = data.memNo;
        memName.value = data.member.memName;
        classAcount.value = data.classAcount;
        coTime.value = data.coTime;

        let payMethod;
        switch (data.gcoPaymentMethod){
            case 0:
                payMethod = '信用卡';
                break;
            case 1:
                payMethod = '匯款';
                showTransfer();
                break;
            case 2:
                payMethod = '綠界';
                break;
        }

        method.value = payMethod;
        smmp.value = data.gcoSmmp;
        actualAmount.value = data.actualAmount;

        let mode;

        switch (data.gcoStatus){
            case 0:
                mode = '未付款';
                break;
            case 1:
                mode = '已付款';
                break;
            case 2:
                mode = '已退款';
                break;
            case 3:
                mode = '已取消';
                break;
            case 4:
                mode = '已完成';
                break;
        }

        status.value = mode;

    }catch (error){
        console.log(error);
    }
}

function showTransfer(){
    show.style.display = 'block';
}