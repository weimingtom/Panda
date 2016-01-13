// Group
group examples;

// import( Group )
import examples;
// import( Specific )
import another_examples > Test;
// import( As )
import another_examples.util > Test as Another;
// import( File )
import './util/Utils.sp';

// Main method called when the script starts
method main() {
    // Creates instances
    Test test = new Test();
    Another another = new Another();
    // Prints variables
    System.print(test);
    System.print(another);
}