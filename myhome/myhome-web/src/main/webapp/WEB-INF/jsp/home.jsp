<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title><spring:message code="tile.welcome" /></title>
</head>
<body>
	<H1>Home</H1>
	<div>${message}</div>

</body>
</html>