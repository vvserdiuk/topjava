<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Edit</title>

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
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.UserMeal"/>
    <form action="meals" method="post">
        <tr>
            <input type="hidden" name="id" value="${meal.id}">
            <input type="hidden" name="action" value="add">
            <td><input type="datetime-local" value="${meal.dateTime}" name="datetime"></td>
            <td><input type="text" value="${meal.description}" name="description"></td>
            <td><input type="text" value="${meal.calories}" name="calories"></td>
            <td><input type="submit"  value="edit"> </td>
        </tr>
    </form>
</table>
</body>
</html>
