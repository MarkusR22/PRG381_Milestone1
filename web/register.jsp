<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
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
		<h1>Register</h1>
		<form action="${pageContext.request.contextPath}/register" method="post">
			<label for="studentNumber">Student Number:</label>
			<input type="text" id="studentNumber" name="studentNumber">

			<label for="name">Name:</label>
			<input type="text" id="name" name="name">

			<label for="surname">Surname:</label>
			<input type="text" id="surname" name="surname">

			<label for="email">Email:</label>
			<input type="email" id="email" name="email">

			<label for="phone">Phone:</label>
			<input type="text" id="phone" name="phone">

			<label for="password">Password:</label>
			<input type="password" id="password" name="password">

			<button type="submit">Register</button>
		</form>
		<p>Already have an account? <a href="login.jsp">Login</a></p>
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
