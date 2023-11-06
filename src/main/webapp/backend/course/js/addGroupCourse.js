
// 讀取圖片
function readImg(result) {
    let reader = new FileReader();
    reader.readAsDataURL(result);
    reader.addEventListener("load", function () {
        let img_tag = `<img src="${this.result}" class="preview_img">`;
        preview.innerHTML = img_tag;
    });
}

// 當選擇新圖片時，讀取圖片並顯示在頁面上
p_file.addEventListener("change", function (e) {
    if (this.files.length > 0) {
        readImg(this.files[0]);
    }
});

function deletePhoto(){
    p_file.value = "";
    preview.innerHTML = `<span class="text">預覽圖</span>`;
}