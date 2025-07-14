<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dashboard</title>
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
	<section class="dashboard">
		<h1>Welcome, ${studentName}!</h1>
		<p>This is your dashboard.</p>
		<p>You can view your appointments, counselor details, and student feedback here.</p>
		<div class="button-group">
			<a href="appointments.jsp" class="btn">View Appointments</a>
			<a href="counselor-details.jsp" class="btn">View Counselor Details</a>
			<a href="student-feedback.jsp" class="btn">View Student Feedback</a>
		</div>
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
