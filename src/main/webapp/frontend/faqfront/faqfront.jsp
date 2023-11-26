<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/meta.file"%>
<meta charset="UTF-8">
<title>寵毛導師 Woof | FAQ</title>

</head>
<body>

	<%@ include file="/Header.file"%>

	<div class="container mb-4 p-2">

		<div class="bg-light text-secondary mt-3 rounded">
			<p class="display-10 font-weight-light p-3 ">FAQ/常見問答</p>
		</div>

		<div class="row mt-4 ">
			<div class="col-md-2 d-flex flex-column align-items-center">
				<button id="petTraining" class="btn btn-secondary btn-block mb-3">課程問題</button>
				<button id="accountIssues" class="btn btn-secondary btn-block mb-3">帳號問題</button>
				<button id="orderQueries" class="btn btn-secondary btn-block mb-3">訂單問題</button>
				<button id="productInquiries"
					class="btn btn-secondary btn-block mb-3">商品問題</button>
			</div>

			<!-- 			<div class="row mt-3"> -->
			<div class="col-md-10 d-flex justify-content-center">
				<div style="width: 650px">
					<!-- 寵物訓練問題 -->
					<div id="petTrainingFaqs" class="faq-category">
						<div class="accordion" id="petTrainingFaqs">
							<c:forEach items="${all}" var="entry">
								<c:if test="${entry.faqClass eq '課程問題'}">
									<div class="accordion-item">
										<h2 class="accordion-header" id="heading${entry.faqNo}">
											<button class="accordion-button collapsed bg-light"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#collapse${entry.faqNo}"
												aria-expanded="true" aria-controls="collapse${entry.faqNo}">
												${entry.faqTitle}</button>
										</h2>
										<div id="collapse${entry.faqNo}"
											class="accordion-collapse collapse"
											aria-labelledby="heading${entry.faqNo}"
											data-bs-parent="#faqAccordionPetTraining">
											<div class="accordion-body">${entry.faqContent}</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>


					<!-- 帳號問題 -->
					<div id="accountIssuesFaqs" class="faq-category"
						style="display: none;">
						<div class="accordion" id="accountIssuesFaqs">
							<c:forEach items="${all}" var="entry">
								<c:if test="${entry.faqClass eq '帳號問題'}">
									<div class="accordion-item">
										<h2 class="accordion-header" id="heading${entry.faqNo}">
											<button class="accordion-button collapsed bg-light"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#collapse${entry.faqNo}"
												aria-expanded="true" aria-controls="collapse${entry.faqNo}">
												${entry.faqTitle}</button>
										</h2>
										<div id="collapse${entry.faqNo}"
											class="accordion-collapse collapse"
											aria-labelledby="heading${entry.faqNo}"
											data-bs-parent="#faqAccordionPetTraining">
											<div class="accordion-body">${entry.faqContent}</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>



					<!-- 訂單問題 -->
					<div id="orderQueriesFaqs" class="faq-category"
						style="display: none;">
						<div class="accordion" id="orderQueriesFaqs">
							<c:forEach items="${all}" var="entry">
								<c:if test="${entry.faqClass eq '訂單問題'}">
									<div class="accordion-item">
										<h2 class="accordion-header" id="heading${entry.faqNo}">
											<button class="accordion-button collapsed bg-light"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#collapse${entry.faqNo}"
												aria-expanded="true" aria-controls="collapse${entry.faqNo}">
												${entry.faqTitle}</button>
										</h2>
										<div id="collapse${entry.faqNo}"
											class="accordion-collapse collapse"
											aria-labelledby="heading${entry.faqNo}"
											data-bs-parent="#faqAccordionPetTraining">
											<div class="accordion-body">${entry.faqContent}</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>



					<!-- 商品問題 -->
					<div id="productInquiriesFaqs" class="faq-category"
						style="display: none;">
						<div class="accordion" id="productInquiriesFaqs">
							<c:forEach items="${all}" var="entry">
								<c:if test="${entry.faqClass eq '商品問題'}">
									<div class="accordion-item">
										<h2 class="accordion-header" id="heading${entry.faqNo}">
											<button class="accordion-button collapsed bg-light"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#collapse${entry.faqNo}"
												aria-expanded="true" aria-controls="collapse${entry.faqNo}">
												${entry.faqTitle}</button>
										</h2>
										<div id="collapse${entry.faqNo}"
											class="accordion-collapse collapse"
											aria-labelledby="heading${entry.faqNo}"
											data-bs-parent="#faqAccordionPetTraining">
											<div class="accordion-body">${entry.faqContent}</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/Footer.file"%>

	<script
		src="${pageContext.request.contextPath}/frontend/faqfront/js/faqfront.js"></script>

</body>
</html>