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

$(function () {
    const orderNo = document.getElementById("orderNo").value;
    if (orderNo.length !== 0){
        fetchData(orderNo);
    }else {
        window.location.href=`${projectName}/index.html`;
    }
})


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
        method.value = data.gcoPaymentMethod;
        smmp.value = data.gcoSmmp;
        actualAmount.value = data.actualAmount;
        status.value = data.gcoStatus;

    }catch (error){
        console.log(error);
    }



}