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
		<div class="card {@type}">
			<h3>
				<xsl:value-of select="@class"></xsl:value-of>
			</h3>
			<div class="card-content">
				<xsl:choose>
					<xsl:when test="@type='list'">
						<ul>
							<xsl:apply-templates></xsl:apply-templates>
						</ul>
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
			<li>
				<xsl:apply-templates></xsl:apply-templates>
			</li>
		</a>
	</xsl:template>

	<xsl:template match="cardtoken">
		<span><xsl:value-of select="@label"></xsl:value-of><b><xsl:value-of select="text()"></xsl:value-of></b></span>
	</xsl:template>
</xsl:stylesheet>




