package com.example.quanlysinhvien

import java.util.Scanner

data class Student(
    var studentId: String,
    var fullName: String,
    var age: Int,
    var major: String,
    var gpa: Double
) {
    override fun toString(): String {
        return String.format(
            "ID: %-6s | Name: %-20s | Age: %-3d | Major: %-25s | GPA: %.2f",
            studentId, fullName, age, major, gpa
        )
    }
}

val students = mutableListOf<Student>()
val sc = Scanner(System.`in`)

fun main() {
    initSampleData()

    var choice: Int
    do {
        printMenu()
        choice = readIntInput("Choose: ")

        when (choice) {
            1 -> addStudent()
            2 -> displayAllStudents()
            3 -> searchStudent()
            4 -> calculateAverageGPA()
            5 -> findStudentWithHighestGPA()
            6 -> removeStudent()
            0 -> println("Exiting program. Goodbye!")
            else -> println("Invalid choice, please try again.")
        }
        println()
    } while (choice != 0)
}

// 5 sinh viên mẫu - khác nhau hoàn toàn
fun initSampleData() {
    students.add(Student("SV001", "Nguyen Van A", 20, "Software Engineering", 8.5))
    students.add(Student("SV002", "Tran Thi B", 21, "Information Technology", 7.2))
    students.add(Student("SV003", "Le Van C", 22, "Computer Science", 9.1))
    students.add(Student("SV004", "Pham Thi D", 19, "Software Engineering", 4.8))
    students.add(Student("SV005", "Hoang Van E", 23, "Information Technology", 6.5))
}

fun printMenu() {
    println("========= STUDENT MANAGEMENT =========")
    println("1. Add student")
    println("2. Display all students")
    println("3. Search student (by ID)")
    println("4. Calculate average GPA (all)")
    println("5. Find student with highest GPA")
    println("6. Remove student")
    println("0. Exit")
    println("=======================================")
}

// 1. Add student
fun addStudent() {
    print("Enter Student ID: ")
    val id = sc.nextLine()
    print("Enter Full Name: ")
    val name = sc.nextLine()
    val age = readIntInput("Enter Age: ")
    print("Enter Major: ")
    val major = sc.nextLine()
    val gpa = readDoubleInput("Enter GPA: ")

    students.add(Student(id, name, age, major, gpa))
    println("Student added successfully!")
}

// 2. Display all students
fun displayAllStudents() {
    if (students.isEmpty()) {
        println("No students found.")
        return
    }
    println("--- List of all students ---")
    for (s in students) {
        println(s)
    }
}

// 3. Search student by ID
fun searchStudent() {
    print("Enter Student ID to search: ")
    val id = sc.nextLine().trim()
    val found = students.find { it.studentId.equals(id, ignoreCase = true) }
    if (found != null) {
        println("Found: $found")
    } else {
        println("Student ID not found: $id")
    }
}

// 4. Calculate average GPA (overall)
fun calculateAverageGPA() {
    if (students.isEmpty()) {
        println("No students to calculate.")
        return
    }
    val avg = students.sumByDouble { it.gpa } / students.size
    println("Average GPA of all students: %.2f".format(avg))
}

// 5. Find student with highest GPA
fun findStudentWithHighestGPA() {
    if (students.isEmpty()) {
        println("No students found.")
        return
    }
    val best = students.maxBy { it.gpa }
    println("Student with highest GPA:")
    println(best)
}

// 6. Remove student
fun removeStudent() {
    print("Enter Student ID to remove: ")
    val id = sc.nextLine().trim()
    val removed = students.removeIf { it.studentId.equals(id, ignoreCase = true) }
    println(if (removed) "Student removed successfully!" else "Student ID not found.")
}

fun readIntInput(prompt: String): Int {
    print(prompt)
    while (!sc.hasNextInt()) {
        print("Invalid input. $prompt")
        sc.next()
    }
    val value = sc.nextInt()
    sc.nextLine()
    return value
}

fun readDoubleInput(prompt: String): Double {
    print(prompt)
    while (!sc.hasNextDouble()) {
        print("Invalid input. $prompt")
        sc.next()
    }
    val value = sc.nextDouble()
    sc.nextLine()
    return value
}