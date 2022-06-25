<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>

<title>List customers</title>

<!-- reference css -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<!--  add new button: Add customer -->
			<input type="button" value="Add customer"
				onclick="window.location.href='showFormAddCustomer'; return false;"
				class="add-button" />
				
				<!-- add a search box -->
				<form:form action="search" method="GET">
				Search customer: <input type="text" name="searchName" />
				<input type="submit" value="Search" class="add-button" />
				</form:form>

			<!--  add out html table here -->
			<table>
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Update</th>
					<!-- <th>Delete</th> -->
				</tr>

				<!-- loop over and print each customer -->
				<c:forEach var="tempCustomer" items="${customers}">

					<!--  construct an 'update'-link with customer id -->
					<c:url var="updateLink" value="/customer/showFormUpdateCustomer">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<!--  construct a 'delete'-link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							<!--  display update and delete link --> 
							<a href="${updateLink}">Update</a>
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>

					</tr>


				</c:forEach>

			</table>
		</div>
	</div>

</body>

</html>