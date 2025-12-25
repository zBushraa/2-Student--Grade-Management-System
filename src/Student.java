public class Student {
    private String name;
    private int id;
    private double[] grades;
    private int gradeCount;
    private static final int MAX_GRADES = 10;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new double[MAX_GRADES];
        this.gradeCount = 0;
    }

    public void addGrade(double grade) {
        if (gradeCount < MAX_GRADES) {
            if (grade >= 0 && grade <= 100) {
                grades[gradeCount] = grade;
                gradeCount++;
                System.out.println("Grade " + grade + " added successfully!");
            } else {
                System.out.println("Invalid grade! Please enter a grade between 0 and 100.");
            }
        } else {
            System.out.println("Maximum grades reached for this student!");
        }
    }

    public double calculateAverage() {
        if (gradeCount == 0) {
            return 0.0;
        }
        double sum = 0;
        for (int i = 0; i < gradeCount; i++) {
            sum += grades[i];
        }
        return sum / gradeCount;
    }

    public char assignGradeLevel() {
        double average = calculateAverage();
        if (average >= 90) {
            return 'A';
        } else if (average >= 80) {
            return 'B';
        } else if (average >= 70) {
            return 'C';
        } else if (average >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGradeCount() {
        return gradeCount;
    }

    public double[] getGrades() {
        return grades;
    }

    public void displayInfo() {
        System.out.println("\n--- Student Information ---");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Number of Grades: " + gradeCount);
        if (gradeCount > 0) {
            System.out.print("Grades: ");
            for (int i = 0; i < gradeCount; i++) {
                System.out.print(grades[i] + " ");
            }
            System.out.println();
            System.out.println("Average Grade: " + String.format("%.2f", calculateAverage()));
            System.out.println("Grade Level: " + assignGradeLevel());
        } else {
            System.out.println("No grades recorded yet.");
        }
    }
}
