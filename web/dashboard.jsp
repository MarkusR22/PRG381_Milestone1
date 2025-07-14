<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dashboard</title>
	<link rel="stylesheet" href="styles.css">
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
		<section class="dashboard">
			<h1>Welcome, ${studentName}!</h1>
			<p>This is your dashboard.</p>
			<p>You can view your appointments, counselor details, and student feedback here.</p>
			<button class="btn-appointments" onclick="window.location.href='appointments.jsp'">View Appointments</button>
			<button class="btn-counselor" onclick="window.location.href='counselor-details.jsp'">View Counselor Details</button>
			<button class="btn-feedback" onclick="window.location.href='student-feedback.jsp'">View Student Feedback</button>
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