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
    <title>寵毛導師 Woof | 後台</title>
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
                            會員系統
                        </a>
                        <ul id="mem" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="member/list_all_member.jsp" class="sidebar-link2">會員管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="member/selectmember.jsp" class="sidebar-link2">會員查詢</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#class"
                           aria-expanded="false" aria-controls="class">
                            <i class="fa-solid fa-calendar-days pe-3"></i>
                            課程系統
                        </a>
                        <ul id="class" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="course/classContent.jsp" class="sidebar-link2">團體內容管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="course/trainerSchedule.jsp" class="sidebar-link2">團體課程課表查詢</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="course/schedule.jsp" class="sidebar-link2">團體報名管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">課堂檔期訂單管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">私人預約管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="course/trainerSchedule.jsp" class="sidebar-link2">私人訓練師課表查詢</a>
                            </li>     
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#shop"
                           aria-expanded="false" aria-controls="shop">
                            <i class="fa-solid fa-cart-shopping pe-3"></i>
                            商城系統
                        </a>
                        <ul id="shop" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="promotionactivity/PA.jsp" class="sidebar-link2">促銷管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a id="productPage" class="sidebar-link2">商品管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2 ">商品訂單管理</a>
                            </li>
                        </ul>
                    </li>
<!--                     <li class="sidebar-item"> -->
<!--                         <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#staff" -->
<!--                            aria-expanded="false" aria-controls="staff"> -->
<!--                             <i class="fa-solid fa-briefcase pe-3"></i> -->
<!--                             員工系統 -->
<!--                         </a> -->
<!--                         <ul id="staff" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar"> -->
<!--                             <li class="sidebar-item"> -->
<!--                                 <a href="employee/trainer.jsp" class="sidebar-link2">訓練師專區</a> -->
<!--                             </li> -->
<!--                             <li class="sidebar-item"> -->
<!--                                 <a href="#" class="sidebar-link2">員工管理</a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse"
                           data-bs-target="#frontdesk" aria-expanded="false" aria-controls="frontdesk">
                            <i class="fa-solid fa-dog pe-3"></i>
                            前台管理系統
                        </a>
                        <ul id="frontdesk" class="sidebar-dropdown list-unstyled collapse"
                            data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">最新消息管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="faq/faqfirst.jsp" class="sidebar-link2">FAQ管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2 ">客服管理</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item"> 
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#staff"
                           aria-expanded="false" aria-controls="staff">
                            <i class="fa-solid fa-user-tie pe-3"></i>
                            員工管理
                        </a>
                        <ul id="staff" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="employee/trainer.jsp" class="sidebar-link2">新增員工</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">權限管理</a>
                            </li>
                        </ul>
                    </li>
                      <li class="sidebar-item"> 
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#trainer"
                           aria-expanded="false" aria-controls="trainer">
                            <i class="fa-solid fa-calendar pe-3"></i>
                            訓練師專區
                        </a>
                        <ul id="trainer" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="employee/trainer.jsp" class="sidebar-link2">訓練師基本資料管理</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">訓練師班表管理</a>
                            </li>
                             <li class="sidebar-item">
                                <a href="#" class="sidebar-link2">訓練師課程查詢</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </aside>
        </div>

        <div class="col-md p-0 ">
            <div class="col-md-12 bg-light mx-auto"
                 style="height: 100px; box-shadow: 0 6px 2px -2px rgba(126, 126, 126, 0.2); height: 80px; position: sticky; top: 0;z-index:100;">
                <!-- <h1>這是頂部</h1> -->
                <div style="position: absolute; bottom: 0; right: 0; text-align: right; padding: 5px; ">
                    <img src="images/happy.png" width="30">
                    <span style="color: rgb(80, 80, 80);">超级管理員</span>
                    <a href="#" class="btn"
                       style="background-color: #FABA91; font-family: 'Noto Sans TC', sans-serif; font-weight: 700; ">登出</a>
                    <!-- <button type="button" class="btn btn-outline-secondary" style="font-family: 'Noto Sans TC', sans-serif; font-weight: 700;">登出</button> -->
                </div>
            </div>


            <!-- 這邊是讓大家放上自己要秀出來的內容 -->


            <div class="col-md-6">

                <div class="position-relative"></div>
                <img src="images/pexels-anna-shvets-4587992.jpg" alt="寵毛導師後台首頁" width="250">
                <div class="position-absolute top-50 start-50 translate-middle text-center"></div>
                <h2>歡迎寵毛導師的後台</h2>
                <p>大家的功能請放在這邊</p>

            </div>

            <!-- 這邊是讓大家放上自己要秀出來的內容 -->


        </div>
    </div>
</div>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/path.js"></script>
</body>

</html>