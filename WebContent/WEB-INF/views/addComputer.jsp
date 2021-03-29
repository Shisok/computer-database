<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="ListComputer" id="applicationTitle"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1 id="addTitle">Add Computer</h1>
                    
                    <form action="AddComputer" id="addComputerFrom" method="POST" >
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" name="computerName" id="computerName" placeholder="Computer name" required="required">
                                <c:if test="${ empty erreurName }"><div class="alert alert-danger" role="alert"
                                    style="display: none;" id="nameError">The computer name
                                    must not be empty.</div></c:if>
                                 
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" name="introduced" id="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" name="discontinued" id="discontinued" placeholder="Discontinued date" >
                                <c:if test="${ empty erreurDiscoBeforeIntro }"><div class="alert alert-danger" role="alert"
                                    style="display: none;" id="discontinuedError">Discontinued is before introduced.</div></c:if>
                               <c:if test="${ empty erreurNoIntro }"> <div class="alert alert-danger" role="alert"
                                    style="display: none;" id="noIntroError">Introduced is needed to input discontinued.</div></c:if>
                                
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
                                <option value="0"> ... </option>
                                	<c:forEach items="${listCompanies}" var="c">
										<option value="${c.getId()}"><c:out
										value="${c.getName()}"></c:out></option>
									</c:forEach>
                                    
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="${pageContext.request.contextPath}/ListComputer" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                    <c:if test="${ !empty computerAdded }"><span id="computerAdded"><c:out
										value="${computerAdded}"></c:out> </span></c:if>
                </div>
            </div>
        </div>
    </section>
     <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/addCompValidator.js"></script>  
 

</body>
</html>