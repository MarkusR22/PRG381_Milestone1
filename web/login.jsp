<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
	<div class="nav-container">
		<div class="logo">BC Wellness</div>
		<nav>
			<ul>
				<li><a href="login.jsp">Login</a></li>
				<li><a href="register.jsp">Register</a></li>
			</ul>
		</nav>
	</div>
</header>

<main>
	<section class="form-container">
		<h1>Login</h1>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<label for="email">Email:</label>
			<input type="email" id="email" name="email">

			<label for="password">Password:</label>
			<input type="password" id="password" name="password">

			<button type="submit">Login</button>
		</form>
		<p>Don't have an account? <a href="register.jsp">Register</a></p>
		<p style="color: red">${error}</p>
	</section>
</main>

<footer>
	<p>&copy; 2025 BC Student Wellness Management System</p>
	<ul>
		<li><a href="https://github.com/MarkusR22/PRG381_Milestone1">Milestone 1 link</a></li>
		<li><a href="https://github.com/MarkusR22/PRG381_Milestone2">Milestone 2 link</a></li>
		<li><a href="#">placeholder</a></li>
	</ul>
</footer>
</body>
</html>
