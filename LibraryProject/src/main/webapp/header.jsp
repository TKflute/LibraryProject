<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Application</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

</head>
<body>

<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	
	  <a class="navbar-brand" href="<%= request.getContextPath() %>/">Library Application</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	    
	      <li class="nav-item">
	        <a class="nav-link" href="<%= request.getContextPath() %>/book-list">View Books</a>
	      </li>
	      
	      <li class="nav-item">
	        <a class="nav-link" href="<%= request.getContextPath() %>/add-book">Add/Edit Books</a>
	      </li>
	      
	    </ul>
	  </div>
	  
	</nav>	
</header>