<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="ListComputer"> Application -
				Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">
						id:
						<c:out value="${id}"></c:out>
					</div>
					<h1>Edit Computer</h1>

					<form:form action="EditComputer" method="POST"
						id="editComputerForm" modelAttribute="ComputerDTOEdit">
						<form:input type="hidden" value="${id}" id="id" path="id" />
						<fieldset>
							<div class="form-group">
								<form:label path="computerName">Computer name</form:label>
								<form:input type="text" class="form-control" id="computerName"
									path="computerName" value="${name}" placeholder="${name}" />
								<c:if test="${! empty erreurName }">
									<div class="alert alert-danger" role="alert" id="nameError">The
										computer name must not be empty.</div>
								</c:if>
							</div>

							<div class="form-group">
								<form:label path="introduced">Introduced date</form:label>
								<form:input type="date" class="form-control" id="introduced"
									path="introduced" placeholder="Introduced date" />
							</div>
							<div class="form-group">
								<form:label path="discontinued">Discontinued date</form:label>
								<form:input type="date" class="form-control" path="discontinued"
									id="discontinued" placeholder="Discontinued date" />

								<c:if test="${ !empty erreurDiscoBeforeIntro }">
									<div class="alert alert-danger" role="alert"
										id="discontinuedError">Discontinued is before
										introduced.</div>
								</c:if>
								<c:if test="${ !empty erreurNoIntro }">
									<div class="alert alert-danger" role="alert" id="noIntroError">Introduced
										is needed to input discontinued.</div>
								</c:if>
							</div>
							<div class="form-group">
								<form:label path="companyId">Company</form:label>
								<form:select class="form-control" path="companyId"
									id="companyId">
									<form:option value="0"> ... </form:option>
									<form:options items="${listCompanies}" itemLabel="name"
										itemValue="id" />

								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="ListComputer" class="btn btn-default">Cancel</a>
						</div>
					</form:form>
					<c:if test="${ !empty computerEdited }">
						<span id="computerEdited"><c:out value="${computerEdited}"></c:out>
						</span>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	<script src="${pageContext.request.contextPath}/js/addCompValidator.js"></script>
</body>
</html>