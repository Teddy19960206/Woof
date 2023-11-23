<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/meta.file"%>

<meta charset="UTF-8">
<title>FAQ</title>

    <style>
        .container {
            max-width: 700px; /* 根據您的圖片設置最大寬度 */
        }
        .accordion-button {
            background-color: #fff; /* 設置為白色背景 */
            color: #333; /* 文字顏色 */
            font-weight: bold; /* 加粗字體 */
            box-shadow: none; /* 移除陰影 */
        }
        .accordion-button::after {
            background-image: url('plus-icon-path'); /* 使用加號圖示 */
            /* 添加必要的樣式以調整圖示 */
        }
/*         .accordion-button:not(.collapsed) { */
/*             color: #fff; /* 展開時的文字顏色 */ */
/*             background-color: #ffc107; /* 展開時的背景顏色 */ */
/*         } */
        .accordion-button:not(.collapsed)::after {
            background-image: url('minus-icon-path'); /* 使用減號圖示 */
            /* 添加必要的樣式以調整圖示 */
        }
/*         .accordion-body { */
/*             background-color: #ffc107; /* 答案部分的背景顏色 */ */
/*             color: #fff; /* 答案部分的文字顏色 */ */
/*         } */
    </style>
    
</head>
<body>
	<%@ include file="/Header.file"%>
	
	<div class="container mt-5 mb-5">
		<h1>FAQ</h1>

		<div class="accordion" id="faqAccordion">
			<c:forEach var="entry" items="${all}">
				<div class="accordion-item">
					<h2 class="accordion-header" id="heading${entry.faqNo}">
						<button class="accordion-button collapsed bg-light" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#collapse${entry.faqNo}" aria-expanded="true"
							aria-controls="collapse${entry.faqNo}">
							${entry.faqTitle}</button>
					</h2>
					<div id="collapse${entry.faqNo}"
						class="accordion-collapse collapse"
						aria-labelledby="heading${entry.faqNo}"
						data-bs-parent="#faqAccordion">
						<div class="accordion-body">${entry.faqContent}</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@ include file="/Footer.file"%>

</body>
</html>