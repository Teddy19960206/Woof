const p_file = document.getElementById("p_file");
const preview = document.getElementById("preview");

function clearPhoto() {
    preview.innerHTML = `<span class="text">預覽圖</span>`;
}


function readImg(result) {
    let reader = new FileReader();
    reader.readAsDataURL(result);
    reader.addEventListener("load", function () {
        let img_tag = `<img src="${this.result}" class="preview_img">`;
        preview.innerHTML = img_tag;
    });
}

p_file.addEventListener("change", function (e) {
    if (this.files.length > 0) {
        readImg(this.files[0]);
    } else {
        p_file.value = "";
        preview.innerHTML = `<span class="text">預覽圖</span>`;
    }
});