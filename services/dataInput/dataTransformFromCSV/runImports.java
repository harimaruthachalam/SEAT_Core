
public class runImports{
    public static void main(String [] args){
        // ImportSlots.execute("slot_config.csv");
        // ImportRankingCriteria.execute("ranking.csv");
        // ImportDepartment.execute("dept.csv");
        // ImportCourses.execute("courses.csv");
    }
}

/*
Issues to resolve
1. Dept.csv - Does not exists
2. Ranking.csv - Does not exists
3. Additional slots in course table


For execution,
javac ImportSlots.java
javac ImportRankingCriteria.java
javac ImportDepartment.java
javac ImportCourses.java
javac runImports.java

jar cfm SEAT.jar Manifest.mf *.class

java -jar SEAT.jar
*/
