<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Report</title>
</head>
<body class="bg-light">
<div class="container">
<h3>Report Application</h3>
	<form:form action="search" method="POST" modelAttribute="search">
        <table>
            <tr>
                <td><form:label path="planName">Plan Name: </form:label></td>
                <td>
                	<form:select path="planName">
                        <form:option value="">-Select-</form:option>
                      
                         <form:options items="${names}" />
                	</form:select>
                </td>
            
                <td><form:label path="planStatus">Plan Status: </form:label></td>
                <td>
                	<form:select path="planStatus">
                        <form:option value="">-Select-</form:option>
                        <form:options items="${status}" />
                	</form:select>
                </td>
                <td><form:label path="gender">Gender: </form:label></td>
                <td>
                	<form:select path="gender">
                        <form:option value="">-Select-</form:option>
                        <form:option value="Male">Male</form:option>
                        <form:option value="Fe-Male">Fe-Male</form:option>
                	</form:select>
                </td>
                
            </tr>
            <tr>
             <td>Start Date:</td>
            <td><form:input path="startDate" type="date" /></td>
            <td>End Date:</td>
            <td><form:input path="endDate" type="date" /></td>
            </tr>
            
            
  			<tr>
  			<td > <a href="/" class="btn btn-secondary">Reset</a></td>
                <td><input type="submit" value="Search" class="btn btn-info"></td>
            </tr>
        </table>
        </hr>
         <table class="table table-striped table-hover">
		    <thead>
		      <tr>
		        <th>citizenId</th>
		        <th>citizenName</th>
		        <th>gender</th>
		        <th>planName</th>
		        <th>planStatus</th>
		        <th>planStartDate</th>
		        <th>planEndDate</th>
		      
		        <th>denialReason</th>
		        <th>terminatedDate</th>
		        <th>terminationReason</th>
		      </tr>
		    </thead>
    		<tbody>
    		<c:forEach items="${plans}" var="plan" varStatus="index">
    		<tr>
    			<td>${index.count}</td>
    			<td>${plan.citizenName}</td>
    			<td>${plan.gender}</td>
    			<td>${plan.planName}</td>
    			<td>${plan.planStatus}</td>
    			<td>${plan.planStartDate}</td>
    			<td>${plan.planEndDate}</td>
    			
    			<td>${plan.denialReason}</td>
    			<td>${plan.terminatedDate}</td>
    			<td>${plan.terminationReason}</td>
    		</tr>
    		</c:forEach> 
    		<tr>
    		<c:if test="${empty plans }">
    		<td colspan="8" style="text-align:center">No records found</td>
    		</c:if>
    		</tr>
    	</tbody>
    
  
  </table>   
    </form:form>
    </div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>