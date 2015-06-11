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

	<H2>Form</H2>
	<f:form method="post" action="somepage.do" commandName="welcomeform">
		<table>
			<tr>
				<td>name</td>
				<td><input name="name" type="text" /></td>
			</tr>
			<tr>
				<td>File</td>
				<td><input name="file" type="file" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="send" /></td>
			</tr>
		</table>
	</f:form>


	<H2>Anchor</H2>


	<div>
		<a href="${pageContext.request.contextPath}/greeting/?name=hello"> Restfull
			simple object</a>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/greeting/list">
			Restfull list</a>
	</div>


	<div>
		<a href="${pageContext.request.contextPath}/catchexception"> Test
			exception handler</a>
	</div>



	<div>
		<a href="${pageContext.request.contextPath}/companyListJSON">
			Redirect to companyListJSON</a>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/companyList"> Display
			companyList in table</a>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/companyListModelView">
			Display companyList (model view object)</a>
	</div>
	<div>
		<a href="welcome.html">Welcome Message... </a>
	</div>

	<H2>Data</H2>


	<br>
	<br>
	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align: center;"></div>
	<p>--${message}--</p>
	<p>--${avion} : ${avion.testDAO}--</p>
	<p>--${name}--</p>

	<br />
	<br />

	<h3>Compagnies</h3>
	<table>
		<c:forEach var="company" items="${companyList}">
			<tr>
				<td>${company.name}</td>
				<td>${company.headoffice}</td>
				<td>${company.employees}</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>