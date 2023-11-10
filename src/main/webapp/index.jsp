<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <%@ include file="/meta.file" %>
    <title>寵毛導師 Woof | 首頁</title>
  </head>

  <body>
    <!-- 導覽列 -->
    <%@ include file="/Header.file" %>

    <!-- 輪播 -->
    <header id="header" class="header">
      <div >
        <div
          id="carouselExampleCaptions"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-indicators">
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="0"
              class="active"
              aria-current="true"
              aria-label="Slide 1"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="1"
              aria-label="Slide 2"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="2"
              aria-label="Slide 3"
            ></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="webutil/images/Carousel01.jpg" class="d-block w-100" />
              <div class="carousel-caption d-none d-md-block">
                <h5>專業訓練師與忠實夥伴的訓練時刻</h5>
                <p>
                  透過訓練師的專業指導和狗的專注合作，這一刻展現了寵物訓練不僅是一種技術，也是一種藝術和信任的建立。
                </p>
              </div>
            </div>
            <div class="carousel-item">
              <img src="webutil/images/Carousel02.jpg" class="d-block w-100" />
              <div class="carousel-caption d-none d-md-block">
                <h5>快樂時光：狗與主人的無價時刻</h5>
                <p>這一刻不僅是一種簡單的娛樂，更是一個深刻的情感交流。</p>
              </div>
            </div>
            <div class="carousel-item">
              <img src="webutil/images/Carousel03.jpg" class="d-block w-100" />
              <div class="carousel-caption d-none d-md-block">
                <h5>愛與紀律：餵食時間的教育時刻</h5>
                <p>這不僅是一個簡單的餵食動作，也是一個教育和訓練的好機會。</p>
              </div>
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </header>
    <!-- 內容 -->
    <article class="mt-5">
      <div class="container">
        <div class="row">
          <h1 class="text-center h4 mytextcolor">
            一站式寵物天堂：訓練、用品、愛心
          </h1>
          <h2 class="text-center h5 my-5">
            我們的寵物訓練師和寵物用品都經過嚴格篩選，確保您的寵物得到最好的照顧。
          </h2>
          <div class="row mt-5">
            <div class="offset-xl-2 col-xl-4 col-12 animated fadeInUp">
              <img src="webutil/images/Carousel06.jpg" class="w-100" />
            </div>
            <div class="col-xl-6 col-12">
              <div class="m-5">
                <h2 class="h3 text-center mytextcolor">
                  高效寵物訓練，快樂寵物生活
                </h2>
                <div class="m-5">
                  <h2 class="h4 text-center text-warning">成犬班</h2>
                  <p>
                    適合一歲以上的成年狗狗參加。我們的成犬班課程涵蓋了基本服從性訓練、進階指令、
                    社交技巧，以及行為矯正。透過正面強化的教學方法，我們確保每隻狗狗都能在愉快和安全的環境中學習。
                    每節課60分鐘，共計X周。
                  </p>
                </div>

                <div class="m-5">
                  <h2 class="h3 text-center text-warning">幼犬班</h2>
                  <p>
                    專為8週到12個月大的幼犬設計。我們的幼犬班課程提供了基礎服從性訓練、簡單的命令、
                    社交化，以及預防未來行為問題的指導。我們使用遊戲和獎勵為基礎的教學方法，讓學習變得更加有趣。
                    每節課45分鐘，共計X周。
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="row my-5">
            <div class="col-xl-6 col-12">
              <h1 class="h3 text-center mytextcolor mt-5">
                愛，從選擇開始<br />為您的寵物選擇最好的
              </h1>
              <div class="text-center my-5 offset-xl-2 col-xl-8 col-12">
                <p>
                  在我們的寵物商城，您會發現購物從未如此簡單。多功能的搜尋引擎讓您能輕易找到所需。
                </p>
                <p>我們重視可持續性和環保，只選擇符合這些標準的商品。</p>
                <p>從客製化選項到多種付款方式，我們都考慮到了您的需求。</p>
                <p>
                  來看看我們的五星評價和客戶評論，您會知道為什麼大家都選擇我們。
                </p>
              </div>
            </div>
            <div class="col-xl-4 col-12 animated fadeInUp">
              <img src="webutil/images/Carousel08.jpg" class="w-100" />
            </div>
          </div>
        </div>
      </div>

      <div class="container-fluid bg-dark w-100 h-100"></div>
    </article>
    <!-- 頁尾 -->
    <%@ include file="/Footer.file" %>
  </body>
</html>

