<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/meta.file"%>

<meta charset="UTF-8">
<title>FAQ</title>

    <style>
        
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