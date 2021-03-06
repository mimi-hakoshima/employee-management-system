<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${employee != null}">
                <h2> ${employee.name_kanzi} の従業員情報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>社員番号</th>
                            <td><c:out value="${employee.code}" /></td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${employee.name_kanzi}" /></td>
                        </tr>
                        <tr>
                            <th>ふりがな</th>
                            <td><c:out value="${employee.name_kana}" /></td>
                        </tr>
                        <tr>
                            <th>所属</th>
                            <td><c:out value="${employee.belongs.belongs_name}" /></td>
                        </tr>
                        <tr>
                            <th>生年月日</th>
                            <td>
                                <fmt:formatDate value="${employee.birthday_at}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>入社日</th>
                            <td>
                                <fmt:formatDate value="${employee.join_at}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>退社日</th>
                            <td>
                                <fmt:formatDate value="${employee.leave_at}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>権限</th>
                            <td>
                                <c:choose>
                                    <c:when test="${employee.admin_flg == 1}">管理者</c:when>
                                    <c:otherwise>一般</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${report.create_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                （${report.create_name}）
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                （${report.report_name}）
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/employees/edit?id=${employee.id}' />">この従業員情報を編集する</a></p>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>


        <p><a href="<c:url value='/employees/index' />">一覧に戻る</a></p>
    </c:param>

</c:import>

