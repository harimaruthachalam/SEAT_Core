
public class runExports{
    public static void main(String [] args){
        ExportCourses.execute("courses.csv");
        ExportDepartment.execute("dept.csv");
        ExportRankingCriteria.execute("ranking.csv");
        ExportSlots.execute("slot_config.csv");
    }
}

/*
Issues to resolve
1. Dept.csv - Does not exists
2. Ranking.csv - Does not exists
3. Additional slots in course table


For execution,
javac ExportCourses.java
javac ExportDepartment.java
javac ExportRankingCriteria.java
javac ExportSlots.java
javac runExports.java

jar cfm SEAT.jar Manifest.mf *.class

java -jar SEAT.jar
*/