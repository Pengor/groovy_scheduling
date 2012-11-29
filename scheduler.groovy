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
