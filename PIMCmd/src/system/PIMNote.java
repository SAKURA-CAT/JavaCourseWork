/**
 * @ClassName: PIMNote
 * @Author: cuny
 * @Date: 2022/6/8 11:10
 * @Description: Note items must be PIMEntity defined in a class named PIMNote.
 * Each note item must have a priority (a string), and a string that contains the actual text of the note.
 **/
package system;

import java.text.SimpleDateFormat;

public class PIMNote extends PIMEntity {
    public static String kind = "NOTE";
    public String Priority; // each note has a priority
    public String content; // each note has a content

    public PIMNote() {
        Priority = "normal";
    }

    PIMNote(String priority){
        this.Priority = priority;
    }

    @Override
    public String getPriority() {
        return Priority;
    }

    @Override
    public void setPriority(String p) {
        this.Priority = p;
    }

    @Override
    public void fromString(String[] bufferData) {
        this.Priority = bufferData[1];
        this.content = bufferData[2];
    }

    @Override
    public String toString() {
        return "[" + kind + " " + Priority
                + " " + content + "]";
    }

    @Override
    public String toBuffer(){
        return kind + "&&&&" + Priority + "&&&&" + content;
    }

}

