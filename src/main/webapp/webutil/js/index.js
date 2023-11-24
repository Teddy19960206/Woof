const carousel = document.getElementById("carousel-inner");
let pname = window.document.location.pathname;
let proName = pname.substring( 0 , pname.substring(1).indexOf("/")+1);

$(async function (){
    let data = await fetchCarousel();
    carouselHTML(data);
})

async function fetchCarousel(){
    let url = `${proName}/CarouselActive`;
    try{
        const response = await fetch(url ,{
            method : "GET"
        })
        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json();

        return data;

    }catch (error){
        console.error("Error" , error);
    }
}

function carouselHTML(data){
    let arr = new Array();
    data.forEach((item ,index)=> arr.push(carouselBtn(index === 0 , index)));
    $("#carouselBtn").html(arr.join(""));
    arr = [];
    data.forEach((item ,index)=> arr.push(carouselItem(index === 0 , item.carId , item.title , item.content)));
    carousel.innerHTML = arr.join("");
    arr = [];
}

function carouselBtn(isFirst , index){
    const activeClass = isFirst ? `class="active" aria-current="true"` : ''
    return `<button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="${index}"
              aria-label="Slide ${index + 1}"
              ${activeClass}
            >              
            </button>`;
}

function carouselItem(isFirst , id , title ,content){
    const activeClass = isFirst ? 'active' : '';
    return `<div class="carousel-item ${activeClass}">
                <img src="${proName}/CarouselImage/${id}" class="d-block w-100" 
                   onerror="this.onerror=null; this.src='${proName}/images/Carousel01.jpg"/>
                <div class="carousel-caption d-none d-md-block">
                    <h5>${title}</h5>
                    <p>${content}</p>
                </div>
            </div>`;
}