<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="masterpage">
		<!-- TODO: Auto-generated template -->
		<html>
			<head>
				<meta charset="utf-8" />
				<meta name="viewport" content="width=device-width, initial-scale=1" />
				<link rel="stylesheet"
					href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
				<script
					src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
				<script
					src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
				<link rel="stylesheet" href="pollsApp.css" />
				<title>
					<xsl:attribute name="title"></xsl:attribute>
				</title>
			</head>
			<body>
				<xsl:apply-templates></xsl:apply-templates>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="menu">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.jsp">Team23</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<xsl:apply-templates select="menuitemleft"></xsl:apply-templates>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<xsl:apply-templates select="menuitemright"></xsl:apply-templates>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
	</xsl:template>

	<xsl:template match="menuitemleft">
		<li>
			<a href="{@link}">
				<xsl:if test="@active='true'">
					<xsl:attribute name="class">active</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="@title"></xsl:value-of>
			</a>
		</li>
	</xsl:template>

	<xsl:template match="menuitemright">
		<li>
			<a href="{@link}">
				<xsl:value-of select="@title"></xsl:value-of>
			</a>
		</li>
	</xsl:template>

	<xsl:template match="content">
		<div class="main-content container">
			<div class="row">
				<xsl:apply-templates></xsl:apply-templates>
			</div>
		</div>
	</xsl:template>

	<xsl:template match="buttonssection">
		<div class="col-sm-4 button-section">
			<xsl:apply-templates></xsl:apply-templates>
		</div>
	</xsl:template>

	<xsl:template match="buttonlink">
		<div class="row button-row">
			<a href="{@link}" class="btn btn-{@type} btn-block">
				<xsl:value-of select="text()"></xsl:value-of>
			</a>
		</div>
	</xsl:template>

	<xsl:template match="cardssection">
		<div class="col-sm-8 card-section">
			<xsl:apply-templates></xsl:apply-templates>
		</div>
	</xsl:template>

	<xsl:template match="card">
		<div class="card {@class}">
			<div class="card-title">
				<h3>
					<xsl:value-of select="@title"></xsl:value-of>
				</h3>
			</div>
			<div class="card-content">
				<xsl:choose>
					<xsl:when test="@type='list'">
						<ul class="card-list-ul">
							<xsl:apply-templates></xsl:apply-templates>
						</ul>
					</xsl:when>
					<xsl:when test="@type='details'">
						<table class="poll-details-table">
							<xsl:apply-templates></xsl:apply-templates>
						</table>
					</xsl:when>
					<xsl:otherwise>
						<xsl:apply-templates></xsl:apply-templates>
					</xsl:otherwise>
				</xsl:choose>
			</div>
		</div>
	</xsl:template>

	<xsl:template match="carditem">
		<a href="{@link}">
			<li class="card-list-li">
				<xsl:apply-templates></xsl:apply-templates>
			</li>
		</a>
	</xsl:template>

	<xsl:template match="cardtoken">
		<span>
			<xsl:value-of select="@label"></xsl:value-of>
			<b>
				<xsl:value-of select="text()"></xsl:value-of>
			</b>
		</span>
	</xsl:template>

	<xsl:template match="cardrow">
		<tr>
			<td>
				<b>
					<xsl:value-of select="@label"></xsl:value-of>
				</b>
			</td>
			<td>
				<xsl:value-of select="text()"></xsl:value-of>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="cardrowselectstatus">
		<tr>
			<td>
				<b>
					<xsl:value-of select="@label"></xsl:value-of>
				</b>
			</td>
			<td>
				<form action="updatePollStatus.jsp" method="POST">
					<input type="hidden" name="input_poll_id" value="{@poll_id}"></input>
					<select name="select_status" id="select_status" required="true"
						onchange="this.form.submit();">
						<xsl:apply-templates select="selectstatusitem"></xsl:apply-templates>
					</select>
				</form>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="selectstatusitem">
		<option value="{@value}">
			<xsl:if test="@selected='true'">
				<xsl:attribute name="selected">true</xsl:attribute>
			</xsl:if>
			<xsl:value-of select="text()"></xsl:value-of>
		</option>
	</xsl:template>

	<xsl:template match="pollresponses">
		<tr>
			<td colspan="2">
				<script src="pollsApp.js"></script>
				<form id="addResponseForm" method="POST" action="addResponse.jsp"
					onsubmit="return validateAddResponse();">
					<input type="hidden" name="input_poll_id" id="input_poll_id"
						value="{@pollid}"></input>
					<table class="poll-responses-table table table-striped table-bordered">
						<thead>
							<tr>
								<th class="response-possibledate-th">Responses</th>
								<xsl:apply-templates select="possibledate"></xsl:apply-templates>
							</tr>
						</thead>
						<tbody>
							<xsl:apply-templates select="response"></xsl:apply-templates>
						</tbody>
						<tfoot>
							<xsl:apply-templates select="addresponse"></xsl:apply-templates>
						</tfoot>
					</table>
				</form>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="possibledate">
		<th class="response-possibledate-th">
			<xsl:value-of select="text()"></xsl:value-of>
		</th>
	</xsl:template>

	<xsl:template match="response">
		<tr>
			<td class="response-name-td">
				<xsl:value-of select="@personName"></xsl:value-of>
				<span class="glyphicon glyphicon-chevron-right pull-right"
					aria-hidden="true"></span>
			</td>
			<xsl:apply-templates select="pollRdate"></xsl:apply-templates>
		</tr>
	</xsl:template>

	<xsl:template match="pollRdate">
		<xsl:if test="@going='yes'">
			<td class="response-going-yes success">
				<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
			</td>
		</xsl:if>
		<xsl:if test="@going='no'">
			<td class="response-going-no danger">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</td>
		</xsl:if>
	</xsl:template>

	<xsl:template match="addresponse">
		<tr>
			<td>
				<div class="input-group">
					<input type="text" id="input_name" name="input_name" class="form-control"
						required="true" maxlength="15" placeholder="Name" value="{@inputvalue}"></input>
					<div class="input-group-addon">
						<span class="glyphicon glyphicon-chevron-right pull-right"
							aria-hidden="true"></span>
					</div>
				</div>
			</td>
			<xsl:apply-templates select="addresponsedate"></xsl:apply-templates>
		</tr>
		<tr>
			<td id="td-button-container">
				<button type="submit" class="btn btn-success btn-block">Add
					Response</button>
			</td>
			<td colspan="10" class="td-message-required">
				<span id="required-name-message" class="bg-danger text-danger">You did not provide any
					name.</span>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="addresponsedate">
		<td class="add-response-date-td">
			<input type="checkbox" name="checkbox_response" value="{@date}"></input>
		</td>
	</xsl:template>

	<xsl:template match="addresponsebutton">

	</xsl:template>

	<xsl:template match="getoutsection">
		<div class="getout-content">
			<p class="bg-info">
				<xsl:value-of select="@message"></xsl:value-of>
			</p>
		</div>
	</xsl:template>

	<xsl:template match="loginfailed">
	</xsl:template>

	<xsl:template match="loginsection">
		<div class="row">
			<div class="card login-card container">
				<div class="page-header text-center sign-title">
					<h1>Sign In</h1>
					<xsl:if test="@loginfailedmsg">
						<p class="danger">
							<xsl:value-of select="@loginfailedmsg"></xsl:value-of>
						</p>
					</xsl:if>
				</div>
				<form action="{@formaction}" method="POST">
					<div class="form-group signin-input-container">
						<input type="text" class="form-control" name="{@usernameinput}"
							id="{@usernameinput}" placeholder="Username" required="true"
							maxlength="20" />
					</div>
					<div class="form-group signin-input-container">
						<input type="password" class="form-control" name="{@passwordinput}"
							id="{@passwordinput}" placeholder="Password" required="true"
							maxlength="20" />
					</div>
					<div class="form-group signin-input-container">
						<button type="submit" class="btn btn-success btn-block">Login</button>
					</div>
				</form>
			</div>
		</div>
	</xsl:template>

	<xsl:template match="registersection">
		<div class="row">
			<div class="card login-card container">
				<div class="page-header text-center sign-title">
					<h1>Register</h1>
					<xsl:if test="@loginfailedmsg">
						<p class="danger">
							<xsl:value-of select="@loginfailedmsg"></xsl:value-of>
						</p>
					</xsl:if>
				</div>
				<form action="{@formaction}" method="POST">
					<div class="form-group signin-input-container">
						<input type="text" class="form-control" name="{@usernameinput}"
							id="{@usernameinput}" placeholder="Username" required="true"
							maxlength="20" />
					</div>
					<div class="form-group signin-input-container">
						<input type="password" class="form-control" name="{@password1input}"
							id="{@password1input}" placeholder="Password" required="true"
							maxlength="20" />
					</div>
					<div class="form-group signin-input-container">
						<input type="password" class="form-control" name="{@password2input}"
							id="{@password2input}" placeholder="Confirm Password" required="true"
							maxlength="20" />
					</div>
					<div class="form-group signin-input-container">
						<button type="submit" class="btn btn-success btn-block">Register</button>
					</div>
				</form>
			</div>
		</div>
	</xsl:template>


	<xsl:template match="newpollinput">
		<div class="row">
			<div class="card login-card container">
					<form action="{@formaction}" method="POST">
						<div>
							<input type="text" class="form-control" name="{@titleinput}" id="{@titleinput}" placeholder="Poll title" required="true" maxlength="30"/>
						</div>
						<div>
							<input type="text" class="form-control" name="{@locationinput}" id="{@locationinput}" placeholder="Meeting location" required="true" maxlength="40"/>
						</div>
						<div>
							<input type="text" class="form-control" name="{@descriptioninput}" id="{@descriptioninput}" placeholder="Description" required="true" maxlength="50"/>
						</div>
						
						<input type="submit" name="action" class="btn btn-success btn-block" value="CREATE"/>
					</form>
					<form action="{@formaction}" method="POST">
						<div>
							<input type="datetime-local" class="form-control" name="{@dateinput}" id="{@dateinput}" required="true"/>
						</div>
						<input type="submit" name="action" class="btn btn-primary btn-block" value="ADD MEETING TIME"/>
					</form>
			</div>
		</div>
	</xsl:template>
</xsl:stylesheet>









