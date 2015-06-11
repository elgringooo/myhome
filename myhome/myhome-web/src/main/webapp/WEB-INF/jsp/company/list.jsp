<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title><spring:message code="tile.welcome" /></title>
</head>
<body>


	<H1>
		<spring:message code="tile.welcome" />
	</H1>

	<h3>Compagnies</h3>

	<table border="1">
		<thead>
			<tr>
				<th><spring:message code="company.identifiant" /></th>
				<th><spring:message code="company.name" /></th>
				<th><spring:message code="company.headoffice" /></th>
				<th><spring:message code="company.employees" /></th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${companyList}" var="company">
				<tr>
					<td><c:out value="${company.id}" /></td>
					<td>${company.name}</td>
					<td>${company.headoffice}</td>
					<td>${company.employees}</td>
					<td><c:url value="/redirectToUpdateCompany" var="updateUrl">
							<c:param name="idCompany" value="${company.id}" />
						</c:url> <a href="${updateUrl}"> Update </a> 
						
						<c:url value="/deleteCompany"
							var="deleteUrl">
							<c:param name="idCompany" value="${company.id}" />
						</c:url> <a href="${deleteUrl}"> Delete </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<a href="redirectToCreateCompany">Create</a>

</body>
</html>