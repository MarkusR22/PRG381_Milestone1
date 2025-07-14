<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>BC Student Wellness Management System</title>
	<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
    </nav>
</header>
	<main>
		<section class="hero">
			<h1>BC Student Wellness Management System</h1>
			<p>This is a Java desktop application for managing student wellness services at Belgium Campus. Built with Java Swing, JavaDB, and designed using OOP principles and MVC architecture, the system provides counselors, students, and administrators with tools to manage appointments, counselor details, and student feedback.</p>
		<button class="btn-register" onclick="window.location.href='register.jsp'">Register</button>
		<button class="btn-login" onclick="window.location.href='login.jsp'">Login</button>
		</section>
	</main>
	<footer>
		<p>&copy; 2025 BC Student Wellness Management System</p>
		<ul>
			<li><a href="https://github.com/MarkusR22/PRG381_Milestone1">Milestone 1 link </a></li>
			<li><a href="https://github.com/MarkusR22/PRG381_Milestone2">Milestone 2 link </a></li>
			<li><a href="#">placeholder</a></li>
		</ul>
	</footer>
</body>
</html>