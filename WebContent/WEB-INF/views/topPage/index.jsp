<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>従業員管理システムへようこそ</h2>
        <c:if test="${sessionScope.login_employee != null}">
            <c:if test="${sessionScope.login_employee.admin_flg == 1}">
                <a href="<c:url value='/employees/index' />">従業員一覧</a><br /><br />
            </c:if>
                <a href="<c:url value='/employees/new' />">従業員登録</a>

        </c:if>
    </c:param>
</c:import>
