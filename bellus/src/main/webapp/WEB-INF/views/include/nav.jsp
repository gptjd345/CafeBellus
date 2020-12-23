<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--  --------header  ------------------------>
    <header>
        <nav class="navbar">
            <div class="navbar__logo">
                <i class="fas fa-coffee"></i>
                <a href="/">Cafe Bellus</a>
            </div>
            <ul class="navbar__menu">
                <li><a href="${path}/menu.do">Menu</a></li>
                <li><a href="${path}/view/list.do">View</a></li>
                <li><a href="${path}/board/list.do">Bulletin board</a></li>
                <li><a href="${path}/wayToCome.do">Way to come</a></li>
            </ul>
            <ul class="navbar__icons">
                <li>
                	<!-- <%= request.getSession().getAttribute("id") %> -->
                	<c:set var="user" value="${sessionScope.userid}"/>
                	<c:if test="${user == null}">
                		<a href="${path}/member/login.do"><i class="far fa-user" title="login-icon"></i></a>
                	</c:if>	
                	<c:if test="${user != null}">
                		<span>${sessionScope.userid}</span>
                		<a href="${path}/member/logout.do"><span id="logout">-Sign out-</span></a>
                	</c:if>
                </li>
                <li>
                    <a href="http://www.instagram.com/cafebellus">
                        <i class="fab fa-instagram"></i>
                    </a>
                </li>
            </ul>

            <a href="#" class="navbar__toggleBtn">
                <i class="fas fa-bars"></i>
            </a>
        </nav>
    </header>  
