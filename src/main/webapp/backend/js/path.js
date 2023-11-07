let pathName = window.document.location.pathname;
let projectName = pathName.substring(0, pathName.substring(1).indexOf("/") + 1);

let a = document.getElementById("productPage");
a.setAttribute("href", `${projectName}/productPage`);

