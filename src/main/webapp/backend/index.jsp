<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/ae360af17e.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/x-icon" href="../webutil/icons/happy_1.png" />
    <title>å¯µæ¯å°å¸« Woof | å¾å°</title>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 bg-color p-0 min-vh-100 position-sticky top-0">
            <aside id="sidebar" class="">
                <img src="images/logo.png" width="220" class="text-center">
                <ul class="sidebar-nav p-0">
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#mem"
                           aria-expanded="false" aria-controls="mem">
                            <i class="fa-solid fa-user pe-3"></i>
                            æå¡ç³»çµ±
                        </a>
                        <ul id="mem" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="member/list_all_member.jsp" class="sidebar-link2">æå¡ç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="member/selectmember.jsp" class="sidebar-link2">æå¡æ¥è©¢</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#class"
                           aria-expanded="false" aria-controls="class">
                            <i class="fa-solid fa-calendar-days pe-3"></i>
                            èª²ç¨ç³»çµ±
                        </a>
                        <ul id="class" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="course/classContent.jsp" class="sidebar-link2">åé«å§å®¹ç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="course/trainerSchedule.jsp" class="sidebar-link2">åé«èª²ç¨èª²è¡¨æ¥è©¢</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="course/schedule.jsp" class="sidebar-link2">åé«å ±åç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">èª²å æªæè¨å®ç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">ç§äººé ç´ç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="course/trainerSchedule.jsp" class="sidebar-link2">ç§äººè¨ç·´å¸«èª²è¡¨æ¥è©¢</a>
                            </li>     
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#shop"
                           aria-expanded="false" aria-controls="shop">
                            <i class="fa-solid fa-cart-shopping pe-3"></i>
                            ååç³»çµ±
                        </a>
                        <ul id="shop" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="promotionactivity/PA.jsp" class="sidebar-link2">ä¿é·ç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a id="productPage" class="sidebar-link2">ååç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2 ">ååè¨å®ç®¡ç</a>
                            </li>
                        </ul>
                    </li>
<!--                     <li class="sidebar-item"> -->
<!--                         <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#staff" -->
<!--                            aria-expanded="false" aria-controls="staff"> -->
<!--                             <i class="fa-solid fa-briefcase pe-3"></i> -->
<!--                             å¡å·¥ç³»çµ± -->
<!--                         </a> -->
<!--                         <ul id="staff" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar"> -->
<!--                             <li class="sidebar-item"> -->
<!--                                 <a href="employee/trainer.jsp" class="sidebar-link2">è¨ç·´å¸«å°å</a> -->
<!--                             </li> -->
<!--                             <li class="sidebar-item"> -->
<!--                                 <a href="#" class="sidebar-link2">å¡å·¥ç®¡ç</a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse"
                           data-bs-target="#frontdesk" aria-expanded="false" aria-controls="frontdesk">
                            <i class="fa-solid fa-dog pe-3"></i>
                            åå°ç®¡çç³»çµ±
                        </a>
                        <ul id="frontdesk" class="sidebar-dropdown list-unstyled collapse"
                            data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">ææ°æ¶æ¯ç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="faq/faqfirst.jsp" class="sidebar-link2">FAQç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2 ">å®¢æç®¡ç</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item"> 
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#staff"
                           aria-expanded="false" aria-controls="staff">
                            <i class="fa-solid fa-user-tie pe-3"></i>
                            å¡å·¥ç®¡ç
                        </a>
                        <ul id="staff" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="employee/trainer.jsp" class="sidebar-link2">æ°å¢å¡å·¥</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">æ¬éç®¡ç</a>
                            </li>
                        </ul>
                    </li>
                      <li class="sidebar-item"> 
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#trainer"
                           aria-expanded="false" aria-controls="trainer">
                            <i class="fa-solid fa-calendar pe-3"></i>
                            è¨ç·´å¸«å°å
                        </a>
                        <ul id="trainer" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="employee/trainer.jsp" class="sidebar-link2">è¨ç·´å¸«åºæ¬è³æç®¡ç</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">è¨ç·´å¸«ç­è¡¨ç®¡ç</a>
                            </li>
                             <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">è¨ç·´å¸«èª²ç¨æ¥è©¢</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </aside>
        </div>

        <div class="col-md p-0 ">
            <div class="col-md-12 bg-light mx-auto"
                 style="height: 100px; box-shadow: 0 6px 2px -2px rgba(126, 126, 126, 0.2); height: 80px; position: sticky; top: 0;z-index:100;">
                <!-- <h1>éæ¯é é¨</h1> -->
                <div style="position: absolute; bottom: 0; right: 0; text-align: right; padding: 5px; ">
                    <img src="images/happy.png" width="30">
                    <span style="color: rgb(80, 80, 80);">è¶çº§ç®¡çå¡</span>
                    <a href="#" class="btn"
                       style="background-color: #FABA91; font-family: 'Noto Sans TC', sans-serif; font-weight: 700; ">ç»åº</a>
                    <!-- <button type="button" class="btn btn-outline-secondary" style="font-family: 'Noto Sans TC', sans-serif; font-weight: 700;">ç»åº</button> -->
                </div>
            </div>


            <!-- ééæ¯è®å¤§å®¶æ¾ä¸èªå·±è¦ç§åºä¾çå§å®¹ -->


            <div class="col-md-6">

                <div class="position-relative"></div>
                <img src="images/pexels-anna-shvets-4587992.jpg" alt="å¯µæ¯å°å¸«å¾å°é¦é " width="250">
                <div class="position-absolute top-50 start-50 translate-middle text-center"></div>
                <h2>æ­¡è¿å¯µæ¯å°å¸«çå¾å°</h2>
                <p>å¤§å®¶çåè½è«æ¾å¨éé</p>

            </div>

            <!-- ééæ¯è®å¤§å®¶æ¾ä¸èªå·±è¦ç§åºä¾çå§å®¹ -->


        </div>
    </div>
</div>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/path.js"></script>
</body>

</html>