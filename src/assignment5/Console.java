package assignment5;

/**
 * Created by jake on 11/8/16.
 * Solution for printing found on StackExchange
 */
import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

public class Console extends OutputStream
{
    private TextArea    output;

    public Console(TextArea ta)
    {
        this.output = ta;
    }

    @Override
    public void write(int i) throws IOException
    {
        output.appendText(String.valueOf((char) i));
    }

}


