import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import com.ibatis.common.jdbc.ScriptRunner;

public class Hi{

public static void main(String[] args) throws IOException
{
Files.copy(new File("dummy.txt").toPath(), new File("2.txt").toPath());
File file = new File("dummy.txt");
FileWriter fr = new FileWriter(file, true);
Date date= new Date();
long time = date.getTime();
fr.write("db"+time);
fr.close();
 new File("2.txt").delete();
}
}
