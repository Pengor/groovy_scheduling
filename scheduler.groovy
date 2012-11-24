

//-- Schedule Data Structure --//

// TODO: This might be able to be moved into a separate file for readability
class Schedule {
    def courses
    Schedule() {
        courses = String[]
    }
    def add(course) {
        // TODO: Need to know how Catalog class will work
        //     before I can implement this method
    }
    def remove(course) {
        // TODO: Need to know how Catalog class will work
        //     before I can implement this method
    }
    // TODO: Add an additional method for operator overloading.
    //    This might replace the add(course) function and
    //    may have to be written outside the class.
}

userSched = new Schedule()

//-- User Interface --//

keyboard = new Scanner(System.in)
while (true /*userInput != "quit"*/) {
    // Prompt user
    println "Enter a course number to add to your schedule (ex: CSCE 343-01)," +
            " type review to list your current courses," +
            " or type quit to exit."
    // Save user input in variable
    userInput = keyboard.nextLine()

    if (userInput == "quit") {
        // Stop while loop
        break
    }
    else if (userInput == "review") {
        // TODO: Iterate through Schedule and print each class
        // TODO: Use if/else to allow for an empty schedule condition
    }
    // Allow for the user to remove a course from their schedule
    //else if (userInput.includes("remove")) {
    // 
    //}
    else {
        // TODO: Use the overloaded + operator to add a course
    }
}
// Print when the program is ending
println "Goodbye!"
