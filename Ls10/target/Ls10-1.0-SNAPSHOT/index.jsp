<%@ page import="org.alibek.ls10.entity.Car" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>JSP - Hello World</title>

    <%@include file="template/bootstrap.jsp" %>
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp" %> <%--хорошо подходит для оптимизации кода--%>
</div>
<div class="container mt-5">
    <div class="row">
        <div class="col-sm-6 offset-3">
            <form method="post" action="/index">
                <div class="form-group">
                    <label  class="form-label">NAME </label>
                    <input type="text" class="form-control"  placeholder="Input car name" name="name">
                </div>
                <div class="form-group mt-2">
                    <label  class="form-label">ENGINE VOLUME </label>
                    <input type="text" class="form-control"  name="engine_volume">
                </div>
                <div class="form-group mt-2">
                    <label  class="form-label">PRICE </label>
                    <input type="number" class="form-control"  name="price" min="0" value="2000">
                </div>
                <div class="form-group mt-2">
                   <button class="btn btn-success">
                       ADD CAR
                   </button>
                </div>
            </form>
        </div>
    </div>
    <div class="row mt-5">
        <div class="col-sm-12">
            <table class="table table-stripped">
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        NAME
                    </th>
                    <th>
                        ENGINE VOLUME
                    </th>
                    <th>
                        PRICE
                    </th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Car> cars = (List<Car>) request.getAttribute("cars");
                    for (Car car : cars) {
                %>
                <tr>
                    <td>
                        <%=car.getId()%>
                    </td>
                    <td>
                        <%=car.getName()%>
                    </td>
                    <td>
                        <%=car.getEngineVolume()%>
                    </td>
                    <td>
                        <%=car.getPrice()%>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

<%--в html уже возвращает отрендереную страницу--%>