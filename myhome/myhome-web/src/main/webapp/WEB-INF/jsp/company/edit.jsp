<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title><spring:message code="tile.welcome" /></title>
</head>
<body>

	<h3>Edit Company</h3>
	<f:form method="post" modelAttribute="companyForm" action="saveCompany">
		<f:hidden path="id"  />
		<table>
			<tr>
				<td><spring:message code="company.name" /></td>
				<td><f:input path="name" /></td>
				<td><b><i><f:errors path="name" cssclass="error" /></i></b></td>
			</tr>
			<tr>
				<td><spring:message code="company.headoffice" /></td>
				<td><f:input path="headoffice" /></td>
				<td><b><i><f:errors path="headoffice" cssclass="error" /></i></b></td>
			</tr>
			<tr>
				<td><spring:message code="company.employees" /></td>
				<td><f:input path="employees" /></td>
				<td><b><i><f:errors path="employees" cssclass="error" /></i></b></td>
			</tr>

			<tr>
				<td colspan="2"><f:button name="cancel">Cancel</f:button> <f:button
						name="save">OK</f:button></td>
			</tr>
		</table>
	</f:form>

</body>
</html>

