<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

			<!--  add out html table here -->
			<table>
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<!-- loop over and print each customer -->
				<c:forEach var="tempCustomer" items="${customers}">

					<!--  construct an 'update'-link with customer id -->
					<c:url var="updateLink" value="/customer/showFormUpdateCustomer">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td><a href="${updateLink}">Update</a></td>
					</tr>


				</c:forEach>

			</table>
		</div>
	</div>

</body>

</html>