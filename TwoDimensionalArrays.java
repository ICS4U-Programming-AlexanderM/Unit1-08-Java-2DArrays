import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
* This program generates marks into a two dimensional array.
*
* @author  Alexander Matheson
* @version 1.0
* @since   2023-03-24
*/

public final class TwoDimensionalArrays {
    /**
    * For style checker.
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */
    private TwoDimensionalArrays() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Print messages.
    *
    * @param args Unused
    */
    public static void main(String[] args) {
        // Declare lists.
        final ArrayList<String> studentList = new ArrayList<String>();
        final ArrayList<String> assignmentList = new ArrayList<String>();

        try {
            // Choose a file to get input from.
            final File students = new File("Unit1-08-students.txt");
            final Scanner scanStudents = new Scanner(students);

            final File assignments = new File("Unit1-08-assignments.txt");
            final Scanner scanAssignments = new Scanner(assignments);

            // Choose (or create) a file to print output to.
            final FileWriter output = new FileWriter("marks.csv");

            // Loop to read each line of student file.
            while (scanStudents.hasNextLine()) {
                // Add the next line.
                studentList.add(scanStudents.nextLine());
            }

            // Loop to read each line of assignment file.
            while (scanAssignments.hasNextLine()) {
                // Add the next line.
                assignmentList.add(scanAssignments.nextLine());
            }

            // Create an array with all elements in the student list.
            final String[] studentArr = new String[studentList.size()];
            for (int location = 0; location < studentArr.length; location++) {
                studentArr[location] = studentList.get(location);
            }

            // Create an array with all elements in the assignment list.
            final String[] assignmentArr = new String[assignmentList.size()];
            for (int assignment = 0; assignment < assignmentArr.length;
                 assignment++) {
                assignmentArr[assignment] = assignmentList.get(assignment);
            }

            // Call functions.
            final String[][] studentMarks = generateMarks(studentArr,
                                                          assignmentArr);

            // Loop through 2D array and separate elements in row with commas.
            for (String[] row : studentMarks) {
                final StringBuilder builder = new StringBuilder();
                for (int column = 0; column < row.length; column++) {
                    builder.append(row[column]);
                    if (column != row.length - 1) {
                        builder.append(", ");
                    } else {
                        builder.append("\n");
                    }
                }

                // Output to file.
                output.write(builder.toString());

                // Print to console.
                System.out.print(builder.toString());
            }

            // Close writer.
            output.close();

        } catch (IOException err) {
            // For when no input file is found.
            System.err.println("Error: " + err.getMessage());
        }
    }

    /**
    * This function generates marks for each assignment.
    *
    * @param arrStudents array of students
    * @param arrAssignments array of assignments
    * @return studentMarks
    */
    public static String[][] generateMarks(String[] arrStudents,
                                          String[] arrAssignments) {
        // Declare array and variables.
        final String[][] studentMarks =
            new String[arrStudents.length + 1][arrAssignments.length + 1];
        int counter1 = 0;
        int num = 0;
        final Random random = new Random();

        // Fill out header row.
        studentMarks[0][0] = "Name";
        for (int position = 0; position < arrAssignments.length; position++) {
            studentMarks[0][position + 1] = arrAssignments[position];
        }

        // Populate array with random numbers.
        for (String student : arrStudents) {
            studentMarks[counter1 + 1][0] = student;
            for (int counter2 = 1;
                counter2 <= arrAssignments.length; counter2++) {
                // Only generate numbers between 0 and 100
                do {
                    num = (int)(random.nextGaussian() * 10 + 75);
                } while (num < 0 || num > 100);
                studentMarks[counter1 + 1][counter2] = Integer.toString(num);
            }
            counter1++;
        }
        return studentMarks;
    }
}
