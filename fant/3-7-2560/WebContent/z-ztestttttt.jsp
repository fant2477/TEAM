<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
<title>EL Expression Examples</title>
</head>
<body>
<h1>EL Expression Examples</h1>

<h2>Logical Operators</h2>

<c:set var="guess" value="12"/>
<b>Your guess is </b>
<c:out value="${guess}"/>

<br/>

<c:if test="${(guess >= 10)  && (guess <= 20)}">
   <b>You're in range!</b><br/>
</c:if>
<c:if test="${(guess < 10)  || (guess > 20)}">
   <b>Try again!</b><br/>
</c:if>

</body>
</html>