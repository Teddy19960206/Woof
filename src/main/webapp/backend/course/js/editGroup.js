const p_file = document.getElementById("p_file");
const preview = document.getElementById("preview");
let pathurl = window.location.pathname;
let pathname = pathurl.split('/')[1];

// 移除圖片
$("button#delete").on("click" , function (){
    preview.innerHTML = `<span class="text">預覽圖</span>`;
    let id = $(this).data('id')
    console.log(id);
    fetchDelete(id);
})

// 到資料庫把圖片刪除
async function fetchDelete(id) {
    let url = `/${pathname}/groupcourse/delete/${id}`;

    try{
        const response = await fetch(url ,{
            method : "POST"
        });
        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        const data = await response.json();

        console.log(data)

    }catch (error){
        console.error('Error', error);
    }
}


// 讀取圖片
function readImg(result) {
    let reader = new FileReader();
    reader.readAsDataURL(result);
    reader.addEventListener("load", function () {
        let img_tag = `<img src="${this.result}" class="preview_img">`;
        preview.innerHTML = img_tag;
    });
}

// 當選擇新圖片時，讀取圖片並顯示在頁面上，若沒有選擇圖片，則刪除圖片
p_file.addEventListener("change", function (e) {
    if (this.files.length > 0) {
        readImg(this.files[0]);
    } else {
        p_file.value = "";
        preview.innerHTML = `<span class="text">預覽圖</span>`;
    }
});
