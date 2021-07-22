<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>To-Do List</title>

</head>
<body>
 <div class="row">
  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

  <div class="container">
   <h3 class="text-center">List of To-Dos</h3>
   <hr>
   <div class="container text-left">

    <a href="<%=request.getContextPath()%>/new"
     class="btn btn-success">Add Task</a>
   </div>
   <br>
   <table class="table table-bordered">
    <thead>
     <tr>
      <th>Title</th>
      <th>Is Done?</th>
      <th>Actions</th>
     </tr>
    </thead>
    <tbody>
     <!--   for (ToDo todo: todos) {  -->
     <c:forEach var="todo" items="${listToDo}"> <!-- Before fill out table, check that list isn't empty; if empty don't display -->

      <tr>
       <td><c:out value="${todo.title}" /></td>
       <td><c:out value="${todo.status}" /></td>

       <td><a href="edit?id=<c:out value='${todo.id}' />">Edit</a>
        &nbsp;&nbsp;&nbsp;&nbsp; <a
        href="delete?id=<c:out value='${todo.id}' />">Delete</a></td>

       <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
                 <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
      </tr>
     </c:forEach>
     <!-- } -->
    </tbody>

   </table>
  </div>
 </div>

</body>
</html>