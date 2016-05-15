<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="masterpage">
		<!-- TODO: Auto-generated template -->
		<html>
			<head>
				<meta charset="utf-8" />
				<meta name="viewport" content="width=device-width, initial-scale=1" />
				<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
				<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
			<a href="{@link}" class="btn btn-{@type} btn-block"><xsl:value-of select="text()"></xsl:value-of></a>
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
				<h3><xsl:value-of select="@title"></xsl:value-of></h3>
			</div>
			<div class="card-content">
				<xsl:choose>
					<xsl:when test="@type='list'">
						<ul class="card-list-ul">
							<xsl:apply-templates></xsl:apply-templates>
						</ul>
					</xsl:when>
					<xsl:when test="@type='details'">
						<table>
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
		<span><xsl:value-of select="@label"></xsl:value-of><b><xsl:value-of select="text()"></xsl:value-of></b></span>
	</xsl:template>
	
	<xsl:template match="getoutsection">
		<div class="getout-content">
			<p class="bg-info"><xsl:value-of select="@message"></xsl:value-of></p>
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
	                	<p class="danger"><xsl:value-of select="@loginfailedmsg"></xsl:value-of></p>
	                </xsl:if>
	            </div>
				<form action="{@formaction}" method="POST">
				  <div class="form-group signin-input-container">
				    <input type="text" class="form-control" name="{@usernameinput}" id="{@usernameinput}" placeholder="Username" required="true" maxlength="20"/>
				  </div>
				  <div class="form-group signin-input-container">
				    <input type="password" class="form-control" name="{@passwordinput}" id="{@passwordinput}" placeholder="Password" required="true" maxlength="20"/>
				  </div>
				  <div class="form-group signin-input-container">
				  	<button type="submit" class="btn btn-success btn-block">Login</button>
				  </div>
				</form>
			</div>
		</div>
	</xsl:template>
	
	<xsl:template match="newpollinput">
		<div class="row">
			<div class="card login-card container">
				<div class="page-header text-center sign-title">
	                <h1>New Poll</h1>
	            </div>
					<form action="{@formaction}" method="POST">
					<table>
					<tr><td>Poll Title</td><td><input name="{@title}" id="{@title}"/></td></tr>
					<tr><td>Meeting Location</td><td><input name="{@location}" id="{@location}"/></td></tr>
					<tr><td>Description</td><td><input name="{@description}" id="{@description}"/></td></tr>
					<tr><td>Possible Time</td><td><input name="{@time}" id="{@time}"/></td></tr>
					<tr><td></td><td><input type="submit" value="CREATE"/></td></tr>
					</table>
					</form>
			</div>
		</div>
	</xsl:template>

</xsl:stylesheet>




