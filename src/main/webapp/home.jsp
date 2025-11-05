<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thư viện Online</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/home.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="home-container">
    <!-- Header -->
    <header class="header">
        <div class="title">📚 Thư viện Online</div>
        <div class="search-box">
            <input type="text" id="searchInput" placeholder="Tìm kiếm theo tên sách..." value="${param.keyword != null ? param.keyword : ''}" />
            <button id="btnSearch"><i class="fa fa-search"></i></button>
        </div>
    </header>

    <!-- Content -->
    <div class="row content">
        <!-- Sidebar -->
        <div class="col-4">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action active" data-toggle="list" href="#list-home">Tất cả</a>
                <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-profile">Yêu thích</a>
                <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-messages">Đã đọc</a>
            </div>
        </div>

        <!-- Tab content -->
        <div class="col-8">
            <div class="tab-content">
                <!-- Tất cả sách -->
                <div class="tab-pane fade show active" id="list-home">
                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4" id="all-books">
                        <c:forEach var="book" items="${books}">
                            <div class="col" data-bookid="${book.id_book}">
                                <div class="card h-100">
                                    <img src="${book.anh}" class="card-img-top" alt="${book.nameB}">
                                    <div class="card-body">
                                        <h5 class="card-title">${book.nameB}</h5>
                                        <p class="card-text">Tác giả: ${book.tac_gia}</p>
                                        <p class="card-text">Thể loại: ${book.the_loai}</p>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between align-items-center">
                                        <button class="btn btn-primary btn-sm btn-read" data-bookid="${book.id_book}" data-link="${book.link}">Đọc</button>
                                        <button class="btn btn-favorite btn-sm ${favoriteBooks.contains(book) ? 'active' : ''}" data-bookid="${book.id_book}">
                                            <i class="fa fa-heart"></i> Yêu thích
                                        </button>
                                        <c:if test="${readBooks.contains(book)}">
                                            <span class="badge badge-success ml-2">Đã đọc</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- Yêu thích -->
                <div class="tab-pane fade" id="list-profile">
                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4" id="fav-books">
                        <c:forEach var="book" items="${favoriteBooks}">
                            <div class="col" data-bookid="${book.id_book}">
                                <div class="card h-100">
                                    <img src="${book.anh}" class="card-img-top" alt="${book.nameB}">
                                    <div class="card-body">
                                        <h5 class="card-title">${book.nameB}</h5>
                                        <p class="card-text">Tác giả: ${book.tac_gia}</p>
                                        <p class="card-text">Thể loại: ${book.the_loai}</p>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between align-items-center">
                                        <button class="btn btn-primary btn-sm btn-read" data-bookid="${book.id_book}" data-link="${book.link}">Đọc</button>
                                        <button class="btn btn-remove-fav btn-sm" data-bookid="${book.id_book}">Xóa Yêu thích</button>
                                        <c:if test="${readBooks.contains(book)}">
                                            <span class="badge badge-success ml-2">Đã đọc</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- Đã đọc -->
                <div class="tab-pane fade" id="list-messages">
                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4" id="read-books">
                        <c:forEach var="book" items="${readBooks}">
                            <div class="col" data-bookid="${book.id_book}">
                                <div class="card h-100">
                                    <img src="${book.anh}" class="card-img-top" alt="${book.nameB}">
                                    <div class="card-body">
                                        <h5 class="card-title">${book.nameB}</h5>
                                        <p class="card-text">Tác giả: ${book.tac_gia}</p>
                                        <p class="card-text">Thể loại: ${book.the_loai}</p>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between align-items-center">
                                        <button class="btn btn-primary btn-sm btn-read" data-bookid="${book.id_book}" data-link="${book.link}">Đọc</button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <footer class="bg-dark text-center text-muted py-3 mt-auto">
        © 2025 Thư viện sách — Mọi quyền được bảo lưu
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const userId = 1;

    // Search
    $('#searchInput').on('keydown', function(e){
        if(e.key === 'Enter'){
            const keyword = $(this).val().trim();
            window.location.href = keyword === '' ? 'home' : 'home?action=search&keyword=' + encodeURIComponent(keyword);
        }
    });
    $('#btnSearch').on('click', function(){
        const keyword = $('#searchInput').val().trim();
        window.location.href = keyword === '' ? 'home' : 'home?action=search&keyword=' + encodeURIComponent(keyword);
    });

    // Favorite
    $(document).on('click', '.btn-favorite', function(){
        const btn = $(this);
        const bookId = btn.data('bookid');
        const isFav = btn.hasClass('active');

        $.post('home?action=favorite', {id_user:userId, id_book:bookId, favorite: !isFav}, function(){
            btn.toggleClass('active');

            if(!isFav){
                if($('#fav-books .col[data-bookid="'+bookId+'"]').length === 0){
                    const clone = btn.closest('.col').clone();
                    clone.find('.btn-favorite').remove();
                    clone.find('.card-footer').append('<button class="btn btn-remove-fav btn-sm" data-bookid="'+bookId+'">Xóa Yêu thích</button>');
                    $('#fav-books').append(clone);
                }
            } else {
                $('#fav-books .col[data-bookid="'+bookId+'"]').remove();
            }
        });
    });

    // Remove Favorite
    $(document).on('click', '.btn-remove-fav', function(){
        const btn = $(this);
        const bookId = btn.data('bookid');

        $.post('home?action=favorite', {id_user:userId, id_book:bookId, favorite:false}, function(){
            btn.closest('.col').remove();
            $('#all-books .col[data-bookid="'+bookId+'"] .btn-favorite').removeClass('active');
        });
    });

    // Read
    $(document).on('click', '.btn-read', function(){
        const btn = $(this);
        const bookId = btn.data('bookid');
        const bookLink = btn.data('link');

        $.post('home?action=read', {id_user:userId, id_book:bookId}, function(){
            const allCard = $('#all-books .col[data-bookid="'+bookId+'"]');
            const favCard = $('#fav-books .col[data-bookid="'+bookId+'"]');

            if(allCard.find('.badge-success').length === 0){
                allCard.find('.card-footer').append('<span class="badge badge-success ml-2">Đã đọc</span>');
            }
            if(favCard.length > 0 && favCard.find('.badge-success').length === 0){
                favCard.find('.card-footer').append('<span class="badge badge-success ml-2">Đã đọc</span>');
            }

            if($('#read-books .col[data-bookid="'+bookId+'"]').length === 0){
                let cloneCard = allCard.clone();
                cloneCard.find('.btn-favorite, .btn-remove-fav').remove();
                $('#read-books').append(cloneCard);
            }

            window.location.href = bookLink;
        });
    });
</script>
</body>
</html>


