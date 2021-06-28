package Info;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LectureInfo implements Serializable {
    private String subjectName;
    private String day;
    private String startTime;
    private String finishTime;

    public LectureInfo(String subjectName, String day, String startTime, String finishTime) {
        this.subjectName = subjectName;
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public Map<String, Object> getLectureInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("subjectName",subjectName);
        docData.put("day",day);
        docData.put("startTime",startTime);
        docData.put("finishTime",finishTime);
        return  docData;
    }
}
