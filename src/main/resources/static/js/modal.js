document.addEventListener('DOMContentLoaded', function () {
    // 綁定新增商品模態框的事件
    function bindAddProductModalEvents() {
        // 當模態框打開時，加載商品類別
        fetch(`productCategories`)
            .then(response => response.json())
            .then(categories => {
                const categorySelect = document.getElementById('productCategory');
                // 清空現有的選項
                categorySelect.innerHTML = '';
                // 動態添加新的選項到下拉選單
                categories.forEach(category => {
                    let option = new Option(category, category);
                    categorySelect.add(option);
                });
            })
            .catch(error => {
                console.error('無法加載商品類別', error);
            });

        // 處理新增產品表單的提交
        document.getElementById('saveProduct').addEventListener('click', function () {
            var form = document.getElementById('addProductForm');
            if (form.checkValidity()) {
                // 創建 FormData
                var formData = new FormData(form);

                // 添加參數到 FormData
                formData.set('prodCatName', document.getElementById('productCategory').value);
                formData.set('prodContent', document.getElementById('productDescription').value);
                formData.set('prodPrice', document.getElementById('productPrice').value);
                formData.set('prodName', document.getElementById('productName').value);

                var productStatusValue = document.getElementById('productStatus').value;
                formData.set('prodStatus', productStatusValue === '銷售中' ? '銷售中' : '下架中');

                // 添加商品照片
                var fileInput = document.getElementById('productImage');
                if (fileInput.files.length > 0) {
                    formData.set('prodPhoto', fileInput.files[0]);
                }

                fetch(`addProduct`, {
                    method: 'POST',
                    body: formData
                })
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            return response.json().then(error => Promise.reject(error));
                        }
                    })
                    .then(data => {
                        console.log('Product added:', data);
                        Swal.fire({
                            icon: "success",
                            title: "成功新增商品",
                        });
                        // 重新加載商品並更新分頁
                        fetchProductsAndUpdateTable(() => {
                            // 判斷新商品應該出現在哪一頁
                            let newPage = calculateNewPage(data);
                            // 更新當前頁碼
                            currentPage = newPage;
                            // 更新表格
                            updateTable();
                            setTimeout(() => {
                                highlightNewRow(data.prodNo);
                            }, 100); // 延時100毫秒
                        });
                    })
                    .catch(error => {
                        console.error('There has been a problem with your fetch operation:', error);
                    });

                // 隱藏模態框
                $('#addProductModal').modal('hide');
            } else {
                form.classList.add('was-validated');
            }
        });
    }

    function calculateNewPage(newProduct) {
        // 獲取總產品數量
        let totalProducts = document.querySelectorAll('.product-row').length + 1; // 加上新產品
        // 計算總頁數
        let totalPages = Math.ceil(totalProducts / rowsPerPage);
        return totalPages;
    }

    function highlightNewRow(prodNo) {
        let newRow = document.querySelector(`tr[data-prodno='${prodNo}']`);
        if (newRow) {
            newRow.scrollIntoView({ behavior: 'smooth', block: 'center' });
            newRow.classList.add('highlighted-row');
        }
    }
    // 當新增產品按鈕被點擊時，顯示模態框並綁定事件
    document.getElementById('addProduct').addEventListener('click', function () {
        $('#addProductModal').modal('show');
        bindAddProductModalEvents(); // 綁定模態框事件的函數
    });

    // 加載新增商品模態框
    fetch(`addModal.html`)
        .then(response => response.text())
        .then(html => {
            document.getElementById('addProductModalContainer').innerHTML = html;
        });
});
