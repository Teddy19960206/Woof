let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);

const memNo = document.getElementById("memNo");
const memName = document.getElementById("memName");
const groupScheduleName = document.getElementById("groupScheduleName");
const gcoDate = document.getElementById("gcoDate");
const method = document.getElementById("method");
const smmp = document.getElementById("smmp");
const actualAmount = document.getElementById("actualAmount");
const status = document.getElementById("status");
const orderEL = document.getElementById("orderNoOriginal");
const show = document.getElementById("show");
const payText = document.getElementById("payText");
const total = document.getElementById("total");

$(function () {
    const orderNo = document.getElementById("orderNo").value;
    if (orderNo.length !== 0){
        fetchData(orderNo);
        orderEL.value = formatOrderId(orderNo);
    }else {
        window.location.href=`${projectName}/index.jsp`;
    }
})

function formatOrderId(orderId) {
    return 'woofGroup' + orderId.toString().padStart(8, '0');
}



async function fetchData(orderNo){
    try{
        let orderNo = $("#orderNo").val();
        let url = `${projectName}/groupOrder/getOneOrder`;
        const response = await fetch(url , {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body : `orderNo=`+ orderNo

        })
        if (!response.ok){
            throw new Error("錯誤")
        }

        const data = await response.json();

        memNo.value = data.gcoNo;
        memName.value = data.member.memName;
        groupScheduleName.value = data.groupCourseSchedule.groupCourse.classType.ctName +"\t" + data.groupCourseSchedule.groupCourse.skill.skillName;
        gcoDate.value = data.gcoDate;

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
        total.value = parseInt(data.actualAmount) + parseInt(data.gcoSmmp);
        payText.innerText = data.actualAmount;

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