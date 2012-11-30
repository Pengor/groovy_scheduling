// Groovy Scheduler
// by Drew Johnson & Jessica Frierson
// for PLU's CSCE 343 - Fall 2012
// Language Project - Part 2


//-- File Reading and Saving --//

import java.awt.geom.Line2D;
//Variables
String sub, crs, sec, crn
int numcount
boolean needed= false
Map catalog = new HashMap()
List classes = new ArrayList()
regEx9= />[a-zA-Z]{4}<///Yay Winner!!! Pulls out DEPT
regEx8 = />[0-9]{3}<///Yay another Winner!!! pulls out course#
regEx10 = />[0-9]{2}<///Yay prints out section!!!
regEx11 = />[0-9]{5}<///Yay prints out CRN #!!!
//Opens and read the Banner Course HTML file
new File("BannerCourses.html").eachLine {line ->
    if (line =~/^<TR>/){
        needed = true
        numcount = 0
    }
    if(needed){
        if (line=~/^<TD .+/){
            numcount++;
            if(numcount == 3){
                matcher = line =~ regEx9
                if (matcher.find()){
                    sub= matcher.group().toString().substring(1, 5)
                }
            }
            else if (numcount == 4){
                matcher2  = line =~ regEx8
                if(matcher2.find()){

                    crs= matcher2.group().toString().substring(1, 4)
                }
            }
            if(numcount == 5){
                matcher3  = line =~ regEx10
                if(matcher3.find()){
                    sec= matcher3.group().toString().substring(1, 3)
                }
            }
            else if(numcount == 6){
                matcher4  = line =~ regEx11
                if(matcher4.find()){
                    crn= matcher4.group().toString().substring(1, 5)
                }
                needed = false
            }
        }
    } 
    if (sub != null && crs != null && sec != null && crn != null){
        classes = [sub, crs, sec]
        catalog.put(crn, classes)
        classes =[]
        }
//catalog.each{ k, v -> println "${k}:${v}" } Prints out the map key and items
}


//-- Print Course Listing --//

println "----------------------"
println "    Course Listing    "
println "----------------------"
println "Course       |  CRN      "
println "----------------------"
// Print the formatted courses from the catalog
catalog.each{ k, v -> println "${v[0]} ${v[1]}-${v[2]}  |  $k" }
println "----------------------"


//-- Schedule Data Structure --//

class Schedule {

    def courses // list to hold classes
    def isEmpty // boolean flag
    
    // Constructor (initializes courses as a list)
    Schedule() {
        courses = []
        isEmpty = true
    }
    // Overloaded addition operator (+)
    def plus(course) {
        courses.add(course)
        isEmpty = false
    }
    // Print schedule
    def printSched() {
        for (i in courses) { println i[0] + " " + i[1] + "-" + i[2] }
    }
}

userSched = new Schedule()


//-- User Interface --//

// List commands for user
println "Enter a CRN to add a course to your schedule (ex: 43210),"
println "type review to list your current courses,"
println "or type quit to stop the program."

keyboard = new Scanner(System.in) // Create scanner
while (true /*userInput != "quit"*/) {
    
    // Prompt user
    print "--> "
    
    // Save user input in variable
    userInput = keyboard.nextLine()
    
    // If user enters "quit"
    if (userInput.equalsIgnoreCase("quit"))
        break // Stop while loop
    // Or if the user enters "review"
    else if (userInput.equalsIgnoreCase("review")) {
        // Print header
        println "-------------"
        println "Your Schedule"
        println "-------------"
        // If the user's schedule is empty, tell them so!
        if (userSched.isEmpty)
            println "...is empty!"
        // Else, iterate through Schedule and print each class
        else
            userSched.printSched()
        println "-------------"
    }
    // Else assume the user is entering a CRN
    else {
        // If the catalog contains the given CRN
        if (catalog[userInput] != null) {
            // Add the course to the user's schedule
            userSched + catalog[userInput]
            // And then tell the user what happened
            println catalog[userInput][0] + " " + catalog[userInput][1] +
                     "-" + catalog[userInput][2] + " added!"
        }
        // Else the CRN either didn't exist or wasn't correctly entered
        else
            println "CRN not found, please try again." // Alert user
    }
}
// Print farewell message when the program is ending
println "Goodbye!"
