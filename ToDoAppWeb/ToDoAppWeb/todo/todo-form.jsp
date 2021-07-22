<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>To-Do Manager</title>

</head>

</head>
<body>
 <div class="container col-md-5">
  <div class="card">
   <div class="card-body">
    <c:if test="${todo != null}">
     <form action="update" method="post">
    </c:if>
    <c:if test="${todo == null}">
     <form action="insert" method="post">
    </c:if>

    <caption>
     <h2>
      <c:if test="${todo != null}">
               Edit To-Do
              </c:if>
      <c:if test="${todo == null}">
               Add New To-Do
              </c:if>
     </h2>
    </caption>

    <c:if test="${todo != null}">
     <input type="hidden" name="id" value="<c:out value='${todo.id}' />" />
    </c:if>

    <fieldset class="form-group">
     <label>Task Title</label> <input type="text"
      value="<c:out value= '${todo.title}' />" class="form-control"
      name="title" required="required">
    </fieldset>

    <fieldset class="form-group">
     <label>Task Decription</label> <input type="text"
      value="<c:out value='${todo.description}' />" class="form-control"
      name="description">
    </fieldset>

    <fieldset class="form-group">
     <label>Task Status</label> <select class="form-control"
      name="isDone">
      <option value="false">In Progress</option>
      <option value="true">Complete</option>
     </select>
    </fieldset>

    <button type="submit" class="btn btn-success">Save</button>
    </form>
   </div>
  </div>
 </div>

</body>
</html>