<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>MealList</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #6495ED;
            color: white;
        }
    </style>
</head>
<body>


<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2"></th>
    </tr>

    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
        <tr>
            <td><c:out value="${meal.dateTime}" /></td>
            <td><c:out value="${meal.description}" /></td>
            <td><font color="${meal.exceed} ? red : green">
                <c:out value="${meal.calories}" />
            </font>
            </td>
            <td><form action="meals" method="post">
                <input type="hidden" name=action value="edit" >
                <input type="hidden" name=id value="${meal.id}" >
                <input type="submit" value="edit">
            </form>
            </td>
            <td><form action="meals" method="get">
                <input type="hidden" name=action value="delete" >
                <input type="hidden" name=id value="${meal.id}" >
                <input type="submit" value="delete">
                </form>
            </td>
        </tr>
    </c:forEach>
    <form action="meals" method="post">
        <tr>
            <input type="hidden" name="action" value="add">
            <td><input type="datetime-local" value="<%= java.time.LocalDateTime.now()%>" name="datetime"></td>
            <td><input type="text" name="description"></td>
            <td><input type="text" name="calories"></td>
            <td><input type="submit"  value="add"> </td>
        </tr>
    </form>
</table>

</body>
</html>
