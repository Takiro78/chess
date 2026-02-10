<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/board.css">
    <link rel="stylesheet" href="/css/piecePos.css">
</head>
<body>
    <div id="board">

        <c:forEach var="row" begin="0" end="7">
            <c:forEach var="col" begin="0" end="7">
                <div class="square ${((row + col) % 2 == 0) ? 'white' : 'black'} pos-${row}${col}" data-row="${row}" data-col="${col}">

                </div>
            </c:forEach>
        </c:forEach>

        <c:forEach var="piece" items="${pieces}">

                <img src="${piece.imgPath}" alt="${piece.type}" class="piece ${piece.type} square pos-${piece.row}${piece.col}">

        </c:forEach>


    </div>

<script src="/scripts/board.js"></script>
</body>
</html>