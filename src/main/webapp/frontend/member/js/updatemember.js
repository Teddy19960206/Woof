const p_file = document.getElementById("p_file");
const preview = document.getElementById("preview");
let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);

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
p_file.addEventListener("change", function () {
    if (this.files.length > 0) {
        readImg(this.files[0]);
    } else {
        p_file.value = "";
        preview.innerHTML = `<span class="text">預覽圖</span>`;
    }
});
